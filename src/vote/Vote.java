package vote;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//immutable
public class Vote<C> implements VoteInterface<C> {

	// ȱʡΪ��������ͶƱ��������Ҫ����ͶƱ�˵���Ϣ

	// һ��ͶƱ�˶����к�ѡ�����ͶƱ���
	private Set<VoteItem<C>> voteItems = new HashSet<>();
	// ͶƱʱ��
	private Calendar date = Calendar.getInstance();

	// Rep Invariants
	// TODO
	//ͶƱ�Ϊ��
	// Abstract Function
	// TODO
	//voteItemsΪ���˶������˵�ͶƱ�����dateΪͶƱ��ʱ��
	// Safety from Rep Exposure
	// TODO
	//���÷���ʽ���

	private boolean checkRep(){
		// TODO
		assert voteItems!=null;
		return false;
	}

	/**
	 * ����һ��ѡƱ����
	 * 
	 * ����������Ƹ÷��������õĲ���
	 * 
	 */
	public Vote(Set<VoteItem<C>> voteItem) {
		// TODO
		voteItems.addAll(voteItem);
	}

	/**
	 * ��ѯ��ѡƱ�а���������ͶƱ��
	 * 
	 * @return ����ͶƱ��
	 */
	public Set<VoteItem<C>> getVoteItems() {
		// TODO
		Set<VoteItem<C>> value = new HashSet<>(voteItems);
		checkRep();
		return value;
	}

	/**
	 * һ���ض���ѡ���Ƿ������ѡƱ��
	 * 
	 * @param candidate ����ѯ�ĺ�ѡ��
	 * @return �������ú�ѡ�˵�ͶƱ��򷵻�true������false
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
