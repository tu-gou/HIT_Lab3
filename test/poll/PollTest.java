package poll;

import static org.junit.jupiter.api.Assertions.*;

import auxiliary.Voter;
import org.junit.jupiter.api.Test;
import pattern.Selection2;
import pattern.Statistics2;
import pattern.StatisticsStrategy;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

import java.util.*;

class PollTest {
	
	// test strategy
	// TODO
	//三位投票人选取三位候选人（A，B，C），支持/弃权/反对(1/0/-1)，A有两票支持，B无支持，C有一票支持，最后得到A

	@Test
	public void testResult() throws Exception {
		Poll<String> poll=Poll.create();
		Calendar date = Calendar.getInstance();
		Map<String,Integer> voteType=new HashMap<>();
		voteType.put("支持",1);
		voteType.put("弃权",0);
		voteType.put("反对",-1);
		VoteType vote=new VoteType(voteType);
		poll.setInfo("代表选举",date,vote,1);
		List<String> candidate=new ArrayList<>();
		candidate.add("A");
		candidate.add("B");
		candidate.add("C");
		poll.addCandidates(candidate);
		Map<Voter,Double> voters=new HashMap<>();
		voters.put(new Voter("投票人1"),1.0);
		voters.put(new Voter("投票人2"),1.0);
		voters.put(new Voter("投票人3"),1.0);
		poll.addVoters(voters);
		Set<VoteItem<String>> voteItem1=new HashSet<>();
		voteItem1.add(new VoteItem<>("A","支持"));
		voteItem1.add(new VoteItem<>("B","反对"));
		voteItem1.add(new VoteItem<>("C","弃权"));
		Set<VoteItem<String>> voteItem2=new HashSet<>();
		voteItem2.add(new VoteItem<>("A","支持"));
		voteItem2.add(new VoteItem<>("B","反对"));
		voteItem2.add(new VoteItem<>("C","反对"));
		Set<VoteItem<String>> voteItem3=new HashSet<>();
		voteItem3.add(new VoteItem<>("A","反对"));
		voteItem3.add(new VoteItem<>("B","反对"));
		voteItem3.add(new VoteItem<>("C","支持"));
		poll.addVote(new Vote<>(voteItem1));
		poll.addVote(new Vote<>(voteItem2));
		poll.addVote(new Vote<>(voteItem3));
		poll.statistics(new Statistics2(voteType));
		poll.selection(new Selection2(1));
		assertEquals("A 1\n",poll.result());

	}

}
