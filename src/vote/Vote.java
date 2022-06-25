package vote;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//immutable
public class Vote<C> implements VoteInterface<C> {

	// 缺省为“匿名”投票，即不需要管理投票人的信息

	// 一个投票人对所有候选对象的投票项集合
	private Set<VoteItem<C>> voteItems = new HashSet<>();
	// 投票时间
	private Calendar date = Calendar.getInstance();

	// Rep Invariants
	// TODO
	//投票项不为空
	// Abstract Function
	// TODO
	//voteItems为此人对所有人的投票情况，date为投票的时间
	// Safety from Rep Exposure
	// TODO
	//采用防御式编程

	private boolean checkRep(){
		// TODO
		assert voteItems!=null;
		return false;
	}

	/**
	 * 创建一个选票对象
	 * 
	 * 可以自行设计该方法所采用的参数
	 * 
	 */
	public Vote(Set<VoteItem<C>> voteItem) {
		// TODO
		voteItems.addAll(voteItem);
	}

	/**
	 * 查询该选票中包含的所有投票项
	 * 
	 * @return 所有投票项
	 */
	public Set<VoteItem<C>> getVoteItems() {
		// TODO
		Set<VoteItem<C>> value = new HashSet<>(voteItems);
		checkRep();
		return value;
	}

	/**
	 * 一个特定候选人是否包含本选票中
	 * 
	 * @param candidate 待查询的候选人
	 * @return 若包含该候选人的投票项，则返回true，否则false
	 */
	public boolean candidateIncluded(C candidate) {
		// TODO
		for(VoteItem<C> v:voteItems){
			if(v.getCandidate().equals(candidate)){
				checkRep();
				return true;
			}
		}
		checkRep();
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vote<C> vote = (Vote<C>) o;
		checkRep();
		return Objects.equals(voteItems, vote.voteItems) && Objects.equals(date, vote.date);
	}

	@Override
	public int hashCode() {
		return Objects.hash(voteItems, date);
	}
}
