package poll;

import static org.junit.jupiter.api.Assertions.*;

import auxiliary.Person;
import auxiliary.Voter;
import org.junit.jupiter.api.Test;
import pattern.Selection2;
import pattern.Statistics2;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

import java.util.*;

class ElectionTest {
	
	// test strategy
	// TODO
	
	@Test
	public void testResult() throws Exception {
		Poll<Person> poll= Poll.create();
		String name="代表选举";
		Calendar date=Calendar.getInstance();
		Map<String,Integer> voteType=new HashMap<>();
		voteType.put("支持",1);
		voteType.put("弃权",0);
		voteType.put("反对",-1);
		VoteType vote=new VoteType(voteType);
		int quantity=3;
		Map<Voter,Double> voters=new HashMap<>();
		Voter v1=new Voter("投票人1");
		Voter v2=new Voter("投票人2");
		Voter v3=new Voter("投票人3");
		Voter v4=new Voter("投票人4");
		Voter v5=new Voter("投票人5");
		Voter v6=new Voter("投票人6");
		voters.put(v1,1.0);
		voters.put(v2,1.0);
		voters.put(v3,1.0);
		voters.put(v4,1.0);
		voters.put(v5,1.0);
		voters.put(v6,1.0);
		List<Person> candidates=new ArrayList<>();
		Person p1=new Person("A",20);
		Person p2=new Person("B",21);
		Person p3=new Person("C",22);
		Person p4=new Person("D",23);
		Person p5=new Person("E",24);
		candidates.add(p1);
		candidates.add(p2);
		candidates.add(p3);
		candidates.add(p4);
		candidates.add(p5);
		poll.setInfo(name,date,vote,quantity);
		poll.addVoters(voters);
		poll.addCandidates(candidates);
		Set<VoteItem<Person>> voteItem1=new HashSet<>();
		voteItem1.add(new VoteItem<>(p1,"支持"));
		voteItem1.add(new VoteItem<>(p2,"反对"));
		voteItem1.add(new VoteItem<>(p3,"支持"));
		voteItem1.add(new VoteItem<>(p4,"反对"));
		voteItem1.add(new VoteItem<>(p5,"弃权"));
		Set<VoteItem<Person>> voteItem2=new HashSet<>();
		voteItem2.add(new VoteItem<>(p1,"反对"));
		voteItem2.add(new VoteItem<>(p2,"反对"));
		voteItem2.add(new VoteItem<>(p3,"支持"));
		voteItem2.add(new VoteItem<>(p4,"支持"));
		voteItem2.add(new VoteItem<>(p5,"支持"));
		Set<VoteItem<Person>> voteItem3=new HashSet<>();
		voteItem3.add(new VoteItem<>(p1,"弃权"));
		voteItem3.add(new VoteItem<>(p2,"支持"));
		voteItem3.add(new VoteItem<>(p3,"支持"));
		voteItem3.add(new VoteItem<>(p4,"支持"));
		voteItem3.add(new VoteItem<>(p5,"支持"));
		Set<VoteItem<Person>> voteItem4=new HashSet<>();
		voteItem4.add(new VoteItem<>(p1,"反对"));
		voteItem4.add(new VoteItem<>(p2,"反对"));
		voteItem4.add(new VoteItem<>(p3,"反对"));
		voteItem4.add(new VoteItem<>(p4,"反对"));
		voteItem4.add(new VoteItem<>(p5,"反对"));
		Set<VoteItem<Person>> voteItem5=new HashSet<>();
		voteItem5.add(new VoteItem<>(p1,"反对"));
		voteItem5.add(new VoteItem<>(p2,"反对"));
		voteItem5.add(new VoteItem<>(p3,"支持"));
		voteItem5.add(new VoteItem<>(p4,"支持"));
		voteItem5.add(new VoteItem<>(p5,"支持"));
		Set<VoteItem<Person>> voteItem6=new HashSet<>();
		voteItem6.add(new VoteItem<>(p1,"弃权"));
		voteItem6.add(new VoteItem<>(p2,"支持"));
		voteItem6.add(new VoteItem<>(p3,"支持"));
		voteItem6.add(new VoteItem<>(p4,"反对"));
		voteItem6.add(new VoteItem<>(p5,"反对"));
		poll.addVote(new Vote<>(voteItem1));
		poll.addVote(new Vote<>(voteItem2));
		poll.addVote(new Vote<>(voteItem3));
		poll.addVote(new Vote<>(voteItem4));
		poll.addVote(new Vote<>(voteItem5));
		poll.addVote(new Vote<>(voteItem6));
		poll.statistics(new Statistics2(voteType));
		poll.selection(new Selection2(quantity));
		assertEquals("C 5\nD 3\n E 3\n", poll.result());
	}

}
