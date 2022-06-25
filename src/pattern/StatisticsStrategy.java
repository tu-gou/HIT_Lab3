package pattern;

import vote.RealNameVote;
import vote.Vote;

import java.util.*;

public interface StatisticsStrategy<C> {
	// TODO

    /**
     * 计算各个票的选项的权重，即计票规则
     *
     * @return 一个已经计算好各个票及其权重的map集合
     */
    public Map<C,Double> calculate(Set<RealNameVote<C>> votes);
}
