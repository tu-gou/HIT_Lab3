package poll;

import auxiliary.Dish;
import auxiliary.Proposal;
import auxiliary.Voter;
import pattern.SelectionStrategy;
import pattern.StatisticsStrategy;
import vote.RealNameVote;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

import java.util.*;

public class DinnerOrder extends GeneralPollImpl<Dish> implements Poll<Dish> {

    //家庭聚餐

	//TODO

    private String name;
    // 投票活动发起的时间
    private Calendar date;
    // 候选对象集合
    private List<Dish> candidates;
    // 投票人集合，key为投票人，value为其在本次投票中所占权重
    private Map<Voter, Double> voters;
    // 拟选出的候选对象最大数量
    private int quantity;
    // 本次投票拟采用的投票类型（合法选项及各自对应的分数）
    private VoteType voteType;
    // 遴选结果，key为候选对象，value为其排序位次
    private Map<Dish, Double> results;
    //实名投票
    private Set<RealNameVote<Dish>> rvotes;
    //增加rep，用于检测选票的合法性
    private Map<Dish, String> check = new HashMap<>();
    //增加rep，检测选票合法性
    private Set<Dish> checkName = new HashSet<>();
    //增加rep
    private Map<RealNameVote<Dish>,Boolean> checkVote;

    /**
     * 重载addvote
     *
     * @param vote
     * @throws Exception
     */
    public void addVote(RealNameVote<Dish> vote)  {
        for (VoteItem<Dish> v : vote.getVoteItems()) {
            check.put(v.getCandidate(), v.getVoteValue());
        }
        if (check.keySet().size() != candidates.size()) {
            //throw new Exception("选票不合法！");//人数不相等，必然不合法
            checkVote.put(vote,false);
            return;
        }
        for (Dish c : candidates) {
            if (!check.containsKey(c)) {
                //throw new Exception("选票不合法！");//选票中含有不属于候选人的，不合法
                checkVote.put(vote,false);
                return;
            }
        }
        for (VoteItem<Dish> v : vote.getVoteItems()) {
            if (!checkName.contains(v.getCandidate())) {
                checkName.add(v.getCandidate());
            } else {
                //throw new Exception("选票不合法！");//选票中对同一个候选人有多次投票，不合法
                checkVote.put(vote,false);
                return;
            }
        }
        rvotes.add(vote);
        checkVote.put(vote,true);
    }

    @Override
    public void statistics(StatisticsStrategy ss) throws Exception {
        //检查合法性
        if (rvotes.size() != voters.keySet().size()) {
            throw new Exception("选票未集齐 ！");
        }
        Set<String> checkP=new HashSet<>();
        for(RealNameVote<Dish> p:rvotes){
            if(checkP.contains(p.getVoter().getID())){
                throw new Exception("有人多次提交选票！");
            }else{
                checkP.add(p.getVoter().getID());
            }
        }
        statistics = ss.calculate(rvotes);
    }

    @Override
    public void selection(SelectionStrategy ss) throws Exception {
        if(quantity>=voters.size()&&quantity<=Math.min(voters.size(),candidates.size())){
            results = ss.selection(statistics);
        }else{
            throw new Exception("quantity不符合范围！");
        }
    }

    @Override
    public Map<Vote<Dish>,Boolean> getCheckVote(){
        return new HashMap<>(checkVote);
    }
}
