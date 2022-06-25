package vote;

import javax.swing.text.MutableAttributeSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//immutable
public class VoteType {

	// keyΪѡ������valueΪѡ������Ӧ�ķ���
	private Map<String, Integer> options = new HashMap<>();

	// Rep Invariants
	// TODO
	//map��Ϊ��
	// Abstract Function
	// TODO
	//map��key��ӦƱ��Ȩ�أ�value��ӦƱ������
	// Safety from Rep Exposure
	// TODO
	//���÷���ʽ���

	private boolean checkRep() {
		// TODO
		assert options!=null;
		return false;
	}

	/**
	 * ����һ��ͶƱ���Ͷ���
	 * 
	 * ����������Ƹ÷��������õĲ���
	 */
	public VoteType(Map<String,Integer> option) {
		// TODO
		this.options.putAll(option);
	}

	/**
	 * ���������ض��﷨������ַ���������һ��ͶƱ���Ͷ���
	 * 
	 * @param regex ��ѭ�ض��﷨�ġ�����ͶƱ������Ϣ���ַ�����������12�ٿ��ǣ�
	 */
	public VoteType(String regex) throws Exception {
		// TODO
		String[] s=regex.split("\\|");
		String regex1="[\\u4e00-\\u9fa5]{1,5}";
		String regex2="-?\\d";
		Matcher match1;
		Matcher match2;
		for(int i=0;i<s.length;i++){
			match1=Pattern.compile(regex1).matcher(s[i]);
			match2=Pattern.compile(regex2).matcher(s[i]);
			if(match1.find()){
				if(match2.find()){
					options.put(match1.group(),Integer.parseInt(match2.group()));
				}else{
					options.put(match1.group(),1);
				}
			}else{
				throw new Exception("�����ַ������Ϸ�!");
			}
		}
	}

	/**
	 * �ж�һ��ͶƱѡ���Ƿ�Ϸ�������Poll�ж�ѡƱ�ĺϷ��Լ�飩
	 * 
	 * ���磬ͶƱ�˸�����ͶƱѡ���ǡ�Strongly reject������options�в��������ѡ�������ǷǷ���
	 * 
	 * ���ܸĶ��÷����Ĳ���
	 * 
	 * @param option һ��ͶƱѡ��
	 * @return �Ϸ���true������false
	 */
	public boolean checkLegality(String option) {
		// TODO
		if(options.containsKey(option)) {
			checkRep();
			return true;
		}
		else {
			checkRep();
			return false;
		}
	}

	/**
	 * ����һ��ͶƱѡ��õ����Ӧ�ķ���
	 * 
	 * ���磬ͶƱ�˸�����ͶƱѡ���ǡ�֧�֡�����ѯ�õ����Ӧ�ķ�����1
	 * 
	 * ���ܸĶ��÷����Ĳ���
	 * 
	 * @param option һ��ͶƱ��ȡֵ
	 * @return ��ѡ������Ӧ�ķ���
	 */
	public int getScoreByOption(String option)  {
		// TODO
//		if(!options.containsKey(option)){
//			throw new Exception("����ѡ����ڣ�");
//		}
		int score=options.get(option);
		checkRep();
		return score;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		VoteType voteType = (VoteType) o;
		checkRep();
		return Objects.equals(options, voteType.options);
	}

	@Override
	public int hashCode() {
		return Objects.hash(options);
	}
}
