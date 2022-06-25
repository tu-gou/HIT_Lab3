package pattern;

import auxiliary.Voter;
import vote.RealNameVote;
import vote.VoteItem;

import java.util.*;

public class Statistics1<C> implements StatisticsStrategy<C>{

    //��ҵ���

    //�������ѡƱ
    //private Set<RealNameVote<C>> votes;
    //���澭���ü�Ʊ�������Ľ����keyΪ��ѡ�ˣ�valueΪ��ռ����
    private Map<C,Double> result;
    //ͶƱ�˼���Ȩ��
    private Map<Voter,Double> voters;
    //ͶƱ����
    private Map<String,Integer> voteType;

    //���캯��
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
