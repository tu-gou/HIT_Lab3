package poll;

import auxiliary.Person;
import auxiliary.Voter;
import pattern.SelectionStrategy;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

import java.util.*;

public class Election extends GeneralPollImpl<Person> implements Poll<Person> {

    //代表选举

	//TODO

    // 投票活动的名称
    private String name;
    // 投票活动发起的时间
    private Calendar date;
    // 候选对象集合
    private List<Person> candidates;
    // 投票人集合，key为投票人，value为其在本次投票中所占权重
    private Map<Voter, Double> voters;
    // 拟选出的候选对象最大数量
    private int quantity;
    // 本次投票拟采用的投票类型（合法选项及各自对应的分数）
    private VoteType voteType;
    // 遴选结果，key为候选对象，value为其排序位次
    private Map<Person, Double> results;

    //增加rep，用于检测选票的合法性
    private Map<Person, String> check = new HashMap<>();
    //增加rep，检测选票合法性
    private Set<Person> checkName = new HashSet<>();
    //增加rep
    private Map<Vote<Person>,Boolean> checkVote;

    @Override
    public void addVote(Vote<Person> vote)  {
        // 此处应首先检查该选票的合法性并标记，为此需扩展或修改rep
        // TODO
        for (VoteItem<Person> v : vote.getVoteItems()) {
            check.put(v.getCandidate(), v.getVoteValue());
        }
        if (check.keySet().size() != candidates.size()) {
            //throw new Exception("选票不合法！");//人数不相等，必然不合法
            checkVote.put(vote,false);
            return;
        }
        for (Person c : candidates) {
            if (!check.containsKey(c)) {
                //throw new Exception("选票不合法！");//选票中含有不属于候选人的，不合法
                checkVote.put(vote,false);
                return;
            }
        }
        for (VoteItem<Person> v : vote.getVoteItems()) {
            if (!checkName.contains(v.getCandidate())) {
                checkName.add(v.getCandidate());
            } else {
                //throw new Exception("选票不合法！");//选票中对同一个候选人有多次投票，不合法
                checkVote.put(vote,false);
                return;
            }
        }
        int i=0;
        for(Person p:check.keySet()){
            if(voteType.getScoreByOption(check.get(p))==1){
                i++;
            }
        }
        if(i>quantity){
            //throw new Exception("支持票数量超过quantity!");
            checkVote.put(vote,false);
            return;
        }


//        Set<Person> p=new HashSet<>();
//        for(VoteItem<Person> v : vote.getVoteItems()){
//            if(p.contains(v.getCandidate())){
//                throw new Exception("支持票数大于quantity！");
//            }else{
//                p.add(v.getCandidate());
//            }
//        }
        votes.add(vote);
        checkVote.put(vote,true);
    }

    @Override
    public void selection(SelectionStrategy ss) throws Exception {
        if(quantity<=candidates.size()){
            results = ss.selection(statistics);
        }
        else{
            throw new Exception("quantity不符合范围！");
        }
    }

    @Override
    public Map<Vote<Person>,Boolean> getCheckVote(){
        return new HashMap<>(checkVote);
    }
}
