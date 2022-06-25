package vote;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VoteItemTest {

	// test strategy
	// TODO
	//创建一个VoteItem类的对象，分别调用方法验证正确性
	
	@Test
	public void testGetVoteValue(){
		VoteItem v=new VoteItem("a","支持");
		assertEquals("支持",v.getVoteValue());
	}

	@Test
	public void testGetCandidate(){
		VoteItem v=new VoteItem("a","支持");
		assertEquals("a",v.getCandidate());
	}

}
