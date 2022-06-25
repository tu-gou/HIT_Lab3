package vote;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class VoteTest {

	// test strategy
	// TODO
	
	@Test
	public void testGetVoteItems(){
		Set<VoteItem<String>> vote=new HashSet<>();
		vote.add(new VoteItem<>("a","支持"));
		vote.add(new VoteItem<>("b","反对"));
		vote.add(new VoteItem<>("c","弃权"));
		Vote v=new Vote(vote);
		assertEquals(vote,v.getVoteItems());
	}

	@Test
	public void testCandidateIncluded(){
		Set<VoteItem<String>> vote=new HashSet<>();
		vote.add(new VoteItem<>("a","支持"));
		vote.add(new VoteItem<>("b","反对"));
		vote.add(new VoteItem<>("c","弃权"));
		Vote v=new Vote(vote);
		assertEquals(true,v.candidateIncluded("a"));
		assertEquals(true,v.candidateIncluded("b"));
		assertEquals(true,v.candidateIncluded("c"));
		assertEquals(false,v.candidateIncluded("d"));
	}

}
