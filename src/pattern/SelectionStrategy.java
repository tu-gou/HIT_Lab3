package pattern;

import java.util.*;

public interface SelectionStrategy<C> {

	// TODO

    /**
     * �Ӽ�Ʊ��ɵļ������ҵ����Ϲ���ĺ�ѡ��
     *
     * @param votes ������Ҫ��ѡ��ͶƱ
     * @return һ������ͶƱѡ���ĺ�ѡ�˵�map
     */
    public Map<C,Double> selection(Map<C,Double> votes);

}
