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
	//��λͶƱ��ѡȡ��λ��ѡ�ˣ�A��B��C����֧��/��Ȩ/����(1/0/-1)��A����Ʊ֧�֣�B��֧�֣�C��һƱ֧�֣����õ�A

	@Test
	public void testResult() throws Exception {
		Poll<String> poll=Poll.create();
		Calendar date = Calendar.getInstance();
		Map<String,Integer> voteType=new HashMap<>();
		voteType.put("֧��",1);
		voteType.put("��Ȩ",0);
		voteType.put("����",-1);
		VoteType vote=new VoteType(voteType);
		poll.setInfo("����ѡ��",date,vote,1);
		List<String> candidate=new ArrayList<>();
		candidate.add("A");
		candidate.add("B");
		candidate.add("C");
		poll.addCandidates(candidate);
		Map<Voter,Double> voters=new HashMap<>();
		voters.put(new Voter("ͶƱ��1"),1.0);
		voters.put(new Voter("ͶƱ��2"),1.0);
		voters.put(new Voter("ͶƱ��3"),1.0);
		poll.addVoters(voters);
		Set<VoteItem<String>> voteItem1=new HashSet<>();
		voteItem1.add(new VoteItem<>("A","֧��"));
		voteItem1.add(new VoteItem<>("B","����"));
		voteItem1.add(new VoteItem<>("C","��Ȩ"));
		Set<VoteItem<String>> voteItem2=new HashSet<>();
		voteItem2.add(new VoteItem<>("A","֧��"));
		voteItem2.add(new VoteItem<>("B","����"));
		voteItem2.add(new VoteItem<>("C","����"));
		Set<VoteItem<String>> voteItem3=new HashSet<>();
		voteItem3.add(new VoteItem<>("A","����"));
		voteItem3.add(new VoteItem<>("B","����"));
		voteItem3.add(new VoteItem<>("C","֧��"));
		poll.addVote(new Vote<>(voteItem1));
		poll.addVote(new Vote<>(voteItem2));
		poll.addVote(new Vote<>(voteItem3));
		poll.statistics(new Statistics2(voteType));
		poll.selection(new Selection2(1));
		assertEquals("A 1\n",poll.result());

	}

}
