package pattern;

import vote.RealNameVote;
import vote.Vote;
import vote.VoteItem;

import java.util.*;

public class Statistics2<C> implements StatisticsStrategy<C> {

    //����ѡ��

    //���澭���ü�Ʊ�������Ľ����keyΪ��ѡ�ˣ�valueΪ��ռ����
    private Map<C, Double> result;
    //ͶƱ����
    private Map<String, Integer> voteType;

    public Statistics2(Map<String, Integer> voteType) {
        this.voteType = voteType;
    }

    @Override
    public Map<C, Double> calculate(Set<RealNameVote<C>> votes) {
        for (RealNameVote<C> r : votes) {
            for (VoteItem<C> v : r.getVoteItems()) {
                if (voteType.get(v.getVoteValue()) == 1) {
                    if (result.containsKey(v.getCandidate())) {
                        result.replace(v.getCandidate(), result.get(v.getCandidate()), result.get(v.getCandidate()) + 1.0);
                    } else {
                        result.put(v.getCandidate(), 1.0);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistics2<?> that = (Statistics2<?>) o;
        return Objects.equals(result, that.result) && Objects.equals(voteType, that.voteType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, voteType);
    }
}
