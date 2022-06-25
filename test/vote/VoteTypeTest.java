package vote;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class VoteTypeTest {

	// test strategy
	// TODO
	//�ȹ���VoteType������map���еĺ�û�е�Ʊѡ�����
	//�ȹ���VoteType������һ��Ʊѡ����鷵��ֵ�Ƿ���ȷ
	
	@Test
	public void testCheckLegality(){
		Map<String ,Integer> option=new HashMap<>();
		option.put("֧��",1);
		option.put("����",-1);
		option.put("��Ȩ",0);
		VoteType v=new VoteType(option);
		assertEquals(true,v.checkLegality("֧��"));
		assertEquals(true,v.checkLegality("����"));
		assertEquals(true,v.checkLegality("��Ȩ"));
		assertEquals(false,v.checkLegality("֧"));
	}

	@Test
	public void testGetScoreByOption(){
		Map<String ,Integer> option=new HashMap<>();
		option.put("֧��",1);
		option.put("����",-1);
		option.put("��Ȩ",0);
		VoteType v=new VoteType(option);
		assertEquals(1,v.getScoreByOption("֧��"));
		assertEquals(0,v.getScoreByOption("����"));
	}

	@Test
	public void testRegex() throws Exception {
		String regex="\"ϲ��\"(2)|\"��ϲ��\"(-1)|\"����ν\"(1)";
		VoteType voteType=new VoteType(regex);
		assertEquals(true,voteType.checkLegality("ϲ��"));
		assertEquals(true,voteType.checkLegality("��ϲ��"));
		assertEquals(true,voteType.checkLegality("����ν"));
		assertEquals(false,voteType.checkLegality("��ϲ"));
		assertEquals(2,voteType.getScoreByOption("ϲ��"));
		assertEquals(1,voteType.getScoreByOption("����ν"));
		assertEquals(-1,voteType.getScoreByOption("��ϲ��"));
	}

}
