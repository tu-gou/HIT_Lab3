package vote;

import javax.swing.text.MutableAttributeSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//immutable
public class VoteType {

	// key为选项名、value为选项名对应的分数
	private Map<String, Integer> options = new HashMap<>();

	// Rep Invariants
	// TODO
	//map不为空
	// Abstract Function
	// TODO
	//map的key对应票的权重，value对应票的种类
	// Safety from Rep Exposure
	// TODO
	//采用防御式编程

	private boolean checkRep() {
		// TODO
		assert options!=null;
		return false;
	}

	/**
	 * 创建一个投票类型对象
	 * 
	 * 可以自行设计该方法所采用的参数
	 */
	public VoteType(Map<String,Integer> option) {
		// TODO
		this.options.putAll(option);
	}

	/**
	 * 根据满足特定语法规则的字符串，创建一个投票类型对象
	 * 
	 * @param regex 遵循特定语法的、包含投票类型信息的字符串（待任务12再考虑）
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
				throw new Exception("输入字符串不合法!");
			}
		}
	}

	/**
	 * 判断一个投票选项是否合法（用于Poll中对选票的合法性检查）
	 * 
	 * 例如，投票人给出的投票选项是“Strongly reject”，但options中不包含这个选项，因此它是非法的
	 * 
	 * 不能改动该方法的参数
	 * 
	 * @param option 一个投票选项
	 * @return 合法则true，否则false
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
	 * 根据一个投票选项，得到其对应的分数
	 * 
	 * 例如，投票人给出的投票选项是“支持”，查询得到其对应的分数是1
	 * 
	 * 不能改动该方法的参数
	 * 
	 * @param option 一个投票项取值
	 * @return 该选项所对应的分数
	 */
	public int getScoreByOption(String option)  {
		// TODO
//		if(!options.containsKey(option)){
//			throw new Exception("输入选项不存在！");
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
