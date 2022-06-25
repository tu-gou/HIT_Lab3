package vote;

import java.util.Set;

public interface VoteInterface<C> {

    /**
     * ��ѯ��ѡƱ�а���������ͶƱ��
     *
     * @return ����ͶƱ��
     */
    public Set<VoteItem<C>> getVoteItems();

    /**
     * һ���ض���ѡ���Ƿ������ѡƱ��
     *
     * @param candidate ����ѯ�ĺ�ѡ��
     * @return �������ú�ѡ�˵�ͶƱ��򷵻�true������false
     */
    public boolean candidateIncluded(C candidate);
}
