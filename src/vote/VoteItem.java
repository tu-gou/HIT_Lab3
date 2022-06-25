package vote;

import java.util.Objects;

//immutable
public class VoteItem<C> {

	// 本投票项所针对的候选对象
	private C candidate;
	// 对候选对象的具体投票选项，例如“支持”、“反对”等
	// 无需保存具体数值，需要时可以从投票活动的VoteType对象中查询得到
	private String value;

	// Rep Invariants
	// TODO
	//candidate不为空，value不为空
	// Abstract Function
	// TODO
	//candidate代表候选对象，value代表给该候选对象的投票的选项
	// Safety from Rep Exposure
	// TODO
	//采用防御式编程
	private boolean checkRep() {
		// TODO
		assert candidate!=null;
		assert value!=null;
		return false;
	}

	/**
	 * 创建一个投票项对象 例如：针对候选对象“张三”，投票选项是“支持”
	 * 
	 * @param candidate 所针对的候选对象
	 * @param value     所给出的投票选项
	 */
	public VoteItem(C candidate, String value) {
		this.candidate = candidate;
		this.value = value;
	}

	/**
	 * 得到该投票选项的具体投票项
	 * 
	 * @return
	 */
	public String getVoteValue() {
		return this.value;
	}

	/**
	 * 得到该投票选项所对应的候选人
	 * 
	 * @return
	 */
	public C getCandidate() {
		return this.candidate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		VoteItem<C> voteItem = (VoteItem<C>) o;
		checkRep();
		return Objects.equals(candidate, voteItem.candidate) && Objects.equals(value, voteItem.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(candidate, value);
	}
}
