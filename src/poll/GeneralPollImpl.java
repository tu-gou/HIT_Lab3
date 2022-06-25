package poll;

import java.util.*;

import auxiliary.Voter;
import pattern.SelectionStrategy;
import pattern.StatisticsStrategy;
import vote.RealNameVote;
import vote.VoteItem;
import vote.VoteType;
import vote.Vote;

public class GeneralPollImpl<C> implements Poll<C> {

    // 投票活动的名称
    private String name;
    // 投票活动发起的时间
    private Calendar date;
    // 候选对象集合
    private List<C> candidates;
    // 投票人集合，key为投票人，value为其在本次投票中所占权重
    private Map<Voter, Double> voters;
    // 拟选出的候选对象最大数量
    private int quantity;
    // 本次投票拟采用的投票类型（合法选项及各自对应的分数）
    private VoteType voteType;
    // 所有选票集合
    protected Set<Vote> votes;
    // 计票结果，key为候选对象，value为其得分
    protected Map<C, Double> statistics;
    // 遴选结果，key为候选对象，value为其排序位次
    private Map<C, Double> results;

    //增加rep，用于检测选票的合法性
    private Map<C, String> check = new HashMap<>();
    //增加rep，检测选票合法性
    private Set<C> checkName = new HashSet<>();
    //增加rep，检测选票合法性
    private Map<Vote<C>, Boolean> checkVote;

    // Rep Invariants
    // TODO
    //name不为空，voters不为空，quantity大于0，小于候选对象数，votes不为空，statistics和results的key和candidates内容一致
    // Abstract Function
    // TODO
    //在名为name的投票中，有候选人集合candidates，voters为投票人及其权重，最多能选出quantity个候选人，对包含所有的选票的集合votes进行排序，计算出各个候选人的得分statistics，最后得到结果results
    // Safety from Rep Exposure
    // TODO
    //采用防御使编程，防止泄露

    private void checkRep() {
        // TODO
        assert name != null;
        //assert candidates!=null;
        //assert voters!=null;
        //assert quantity >0;
        assert voteType != null;
        //assert votes!=null;
        if (!statistics.isEmpty()) {
            assert statistics.keySet().size() == candidates.size();
            for (C c : candidates) {
                assert statistics.containsKey(c);
            }
        }
//        if (!results.isEmpty()) {
//            assert results.keySet().size() == candidates.size();
//            for (C c : candidates) {
//                assert results.containsKey(c);
//            }
//        }
    }

    /**
     * 构造函数
     */
    public GeneralPollImpl() {
        // TODO
    }

    @Override
    public void setInfo(String name, Calendar date, VoteType type, int quantity) {
        // TODO
        this.name = name;
        this.date = date;
        this.voteType = type;
        this.quantity = quantity;
        checkRep();
    }

    @Override
    public void addVoters(Map<Voter, Double> voters) {
        // TODO
        this.voters.putAll(voters);
        checkRep();
    }

    @Override
    public void addCandidates(List<C> candidates) {
        // TODO
        this.candidates.addAll(candidates);
        checkRep();
    }

    @Override
    public void addVote(Vote<C> vote) {
        // 此处应首先检查该选票的合法性并标记，为此需扩展或修改rep
        // TODO
        for (VoteItem<C> v : vote.getVoteItems()) {
            check.put(v.getCandidate(), v.getVoteValue());
        }
        if (check.keySet().size() != candidates.size()) {
            //throw new Exception("选票不合法！");//人数不相等，必然不合法
            checkVote.put(vote, false);
            return;
        }
        for (C c : candidates) {
            if (!check.containsKey(c)) {
                //throw new Exception("选票不合法！");//选票中含有不属于候选人的，不合法
                checkVote.put(vote, false);
                return;
            }
        }
        for (VoteItem<C> v : vote.getVoteItems()) {
            if (!checkName.contains(v.getCandidate())) {
                checkName.add(v.getCandidate());
            } else {
                //throw new Exception("选票不合法！");//选票中对同一个候选人有多次投票，不合法
                checkVote.put(vote, false);
                return;
            }
        }
        votes.add(vote);
        checkVote.put(vote, true);
        checkRep();
    }

    @Override
    public void statistics(StatisticsStrategy ss) throws Exception {
        // 此处应首先检查当前所拥有的选票的合法性
        // TODO
        if (votes.size() != voters.keySet().size()) {
            throw new Exception("选票未集齐 ！");
        }
        statistics = ss.calculate(votes);
        checkRep();
    }

    @Override
    public void selection(SelectionStrategy ss) throws Exception {
        // TODO
        results = ss.selection(statistics);
    }


    @Override
    public String result() {
        // TODO
        StringBuilder re = new StringBuilder();
        for (C c : results.keySet()) {
            re.append(c).append(" ").append(results.get(c)).append("\n");
        }
        return re.toString();
    }

    public Map<Vote<C>,Boolean> getCheckVote(){
        return new HashMap<>(checkVote);
    }

    @Override
    public double accept(CalculateVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralPollImpl<?> that = (GeneralPollImpl<?>) o;
        return quantity == that.quantity && Objects.equals(name, that.name) && Objects.equals(date, that.date) && Objects.equals(candidates, that.candidates) && Objects.equals(voters, that.voters) && Objects.equals(voteType, that.voteType) && Objects.equals(votes, that.votes) && Objects.equals(statistics, that.statistics) && Objects.equals(results, that.results) && Objects.equals(check, that.check) && Objects.equals(checkName, that.checkName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, candidates, voters, quantity, voteType, votes, statistics, results, check, checkName);
    }
}
