package pattern;

import vote.RealNameVote;
import vote.Vote;

import java.util.*;

public interface StatisticsStrategy<C> {
	// TODO

    /**
     * �������Ʊ��ѡ���Ȩ�أ�����Ʊ����
     *
     * @return һ���Ѿ�����ø���Ʊ����Ȩ�ص�map����
     */
    public Map<C,Double> calculate(Set<RealNameVote<C>> votes);
}
