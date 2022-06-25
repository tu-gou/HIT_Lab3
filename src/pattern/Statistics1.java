package pattern;

import auxiliary.Voter;
import vote.RealNameVote;
import vote.VoteItem;

import java.util.*;

public class Statistics1<C> implements StatisticsStrategy<C>{

    //商业表决

    //待计算的选票
    //private Set<RealNameVote<C>> votes;
    //保存经过该计票规则计算的结果，key为候选人，value为所占比例
    private Map<C,Double> result;
    //投票人及其权重
    private Map<Voter,Double> voters;
    //投票种类
    private Map<String,Integer> voteType;

    //构造函数
    public Statistics1(/*Set<RealNameVote<C>> vote,*/Map<Voter,Double> voters,Map<String,Integer> voteType){
        //this.votes=vote;
        this.voters=voters;
        this.voteType=voteType;
    }

    @Override
    public Map<C,Double> calculate(Set<RealNameVote<C>> votes){
        for(RealNameVote<C> r:votes){
            for(VoteItem<C> v:r.getVoteItems()){
                if(voteType.get(v.getVoteValue())==1) {
                    if (result.containsKey(v.getCandidate())) {
                        result.replace(v.getCandidate(), result.get(v.getCandidate()), result.get(v.getCandidate()) + voters.get(r.getVoter()) * 1.0);
                    } else {
                        result.put(v.getCandidate(), voters.get(r.getVoter()) * 1.0);
                    }
                }
            }
        }
        return new HashMap<>(result);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistics1<?> that = (Statistics1<?>) o;
        return Objects.equals(result, that.result) && Objects.equals(voters, that.voters) && Objects.equals(voteType, that.voteType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, voters, voteType);
    }
}
