package vote;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VoteItemTest {

	// test strategy
	// TODO
	//����һ��VoteItem��Ķ��󣬷ֱ���÷�����֤��ȷ��
	
	@Test
	public void testGetVoteValue(){
		VoteItem v=new VoteItem("a","֧��");
		assertEquals("֧��",v.getVoteValue());
	}

	@Test
	public void testGetCandidate(){
		VoteItem v=new VoteItem("a","֧��");
		assertEquals("a",v.getCandidate());
	}

}
