package poll;

import static org.junit.jupiter.api.Assertions.*;

//import org.junit.Test;
import auxiliary.Dish;
import auxiliary.Person;
import auxiliary.Voter;
import org.junit.jupiter.api.Test;
import pattern.Selection3;
import pattern.Statistics2;
import pattern.Statistics3;
import vote.RealNameVote;
import vote.VoteItem;
import vote.VoteType;

import java.util.Calendar;
import java.util.*;

class DinnerOrderTest {

    // test strategy
    // TODO

    @Test
    public void testResult() throws Exception {
        Poll<Dish> poll = Poll.create();
        String name = "??ͥ?۲?";
        Calendar date = Calendar.getInstance();
        Map<String, Integer> voteType = new HashMap<>();
        voteType.put("ϲ??", 2);
        voteType.put("????ν", 1);
        voteType.put("??ϲ??", 0);
        VoteType vote = new VoteType(voteType);
        int quantity = 4;
        Map<Voter, Double> voters = new HashMap<>();
        Voter v1 = new Voter("үү");
        Voter v2 = new Voter("?ְ?");
        Voter v3 = new Voter("????");
        Voter v4 = new Voter("????");
		voters.put(v1,4.0);
		voters.put(v2,1.0);
		voters.put(v3,2.0);
		voters.put(v4,2.0);
		List<Dish> candidates=new ArrayList<>();
		Dish d1=new Dish("A",10.0);
		Dish d2=new Dish("B",11.0);
		Dish d3=new Dish("C",12.0);
		Dish d4=new Dish("D",13.0);
		Dish d5=new Dish("E",14.0);
		Dish d6=new Dish("F",15.0);
		candidates.add(d1);
		candidates.add(d2);
		candidates.add(d3);
		candidates.add(d4);
		candidates.add(d5);
		candidates.add(d6);
		poll.setInfo(name ,date,vote,quantity);
		poll.addVoters(voters);
		poll.addCandidates(candidates);
		Set<VoteItem<Dish>> voteItem1=new HashSet<>();
		voteItem1.add(new VoteItem<>(d1,"ϲ??"));
		voteItem1.add(new VoteItem<>(d2,"ϲ??"));
		voteItem1.add(new VoteItem<>(d3,"????ν"));
		voteItem1.add(new VoteItem<>(d4,"????ν"));
		voteItem1.add(new VoteItem<>(d5,"??ϲ??"));
		voteItem1.add(new VoteItem<>(d6,"??ϲ??"));
		Set<VoteItem<Dish>> voteItem2=new HashSet<>();
		voteItem2.add(new VoteItem<>(d1,"????ν"));
		voteItem2.add(new VoteItem<>(d2,"ϲ??"));
		voteItem2.add(new VoteItem<>(d3,"ϲ??"));
		voteItem2.add(new VoteItem<>(d4,"ϲ??"));
		voteItem2.add(new VoteItem<>(d5,"??ϲ??"));
		voteItem2.add(new VoteItem<>(d6,"ϲ??"));
		Set<VoteItem<Dish>> voteItem3=new HashSet<>();
		voteItem3.add(new VoteItem<>(d1,"ϲ??"));
		voteItem3.add(new VoteItem<>(d2,"??ϲ??"));
		voteItem3.add(new VoteItem<>(d3,"??ϲ??"));
		voteItem3.add(new VoteItem<>(d4,"??ϲ??"));
		voteItem3.add(new VoteItem<>(d5,"ϲ??"));
		voteItem3.add(new VoteItem<>(d6,"??ϲ??"));
		Set<VoteItem<Dish>> voteItem4=new HashSet<>();
		voteItem4.add(new VoteItem<>(d1,"ϲ??"));
		voteItem4.add(new VoteItem<>(d2,"????ν"));
		voteItem4.add(new VoteItem<>(d3,"ϲ??"));
		voteItem4.add(new VoteItem<>(d4,"ϲ??"));
		voteItem4.add(new VoteItem<>(d5,"ϲ??"));
		voteItem4.add(new VoteItem<>(d6,"??ϲ??"));
		poll.addVote(new RealNameVote<>(voteItem1,v1));
		poll.addVote(new RealNameVote<>(voteItem2,v2));
		poll.addVote(new RealNameVote<>(voteItem3,v3));
		poll.addVote(new RealNameVote<>(voteItem4,v4));
		poll.statistics(new Statistics3(voters,voteType));
		poll.selection(new Selection3(quantity));
		assertEquals("A 17\nB 12\nC 10\nD 10\n",poll.result());
    }

}
