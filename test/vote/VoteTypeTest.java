package vote;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class VoteTypeTest {

	// test strategy
	// TODO
	//先构造VoteType，输入map中有的和没有的票选项，检验
	//先构造VoteType，输入一种票选项，检验返回值是否正确
	
	@Test
	public void testCheckLegality(){
		Map<String ,Integer> option=new HashMap<>();
		option.put("支持",1);
		option.put("反对",-1);
		option.put("弃权",0);
		VoteType v=new VoteType(option);
		assertEquals(true,v.checkLegality("支持"));
		assertEquals(true,v.checkLegality("反对"));
		assertEquals(true,v.checkLegality("弃权"));
		assertEquals(false,v.checkLegality("支"));
	}

	@Test
	public void testGetScoreByOption(){
		Map<String ,Integer> option=new HashMap<>();
		option.put("支持",1);
		option.put("反对",-1);
		option.put("弃权",0);
		VoteType v=new VoteType(option);
		assertEquals(1,v.getScoreByOption("支持"));
		assertEquals(0,v.getScoreByOption("反对"));
	}

	@Test
	public void testRegex() throws Exception {
		String regex="\"喜欢\"(2)|\"不喜欢\"(-1)|\"无所谓\"(1)";
		VoteType voteType=new VoteType(regex);
		assertEquals(true,voteType.checkLegality("喜欢"));
		assertEquals(true,voteType.checkLegality("不喜欢"));
		assertEquals(true,voteType.checkLegality("无所谓"));
		assertEquals(false,voteType.checkLegality("欢喜"));
		assertEquals(2,voteType.getScoreByOption("喜欢"));
		assertEquals(1,voteType.getScoreByOption("无所谓"));
		assertEquals(-1,voteType.getScoreByOption("不喜欢"));
	}

}
