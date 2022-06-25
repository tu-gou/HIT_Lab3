package pattern;

import java.util.*;

public interface SelectionStrategy<C> {

	// TODO

    /**
     * 从计票完成的集合里找到符合规则的候选人
     *
     * @param votes 输入需要遴选的投票
     * @return 一个包含投票选出的候选人的map
     */
    public Map<C,Double> selection(Map<C,Double> votes);

}
