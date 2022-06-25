package app;

import auxiliary.Dish;
import auxiliary.Voter;
import pattern.Selection3;
import pattern.Statistics3;
import poll.Poll;
import vote.RealNameVote;
import vote.VoteItem;
import vote.VoteType;

import java.io.FilterOutputStream;
import java.util.*;

public class DinnerOrderApp {
	
	public static void main(String[] args) throws Exception {
		// TODO

		//创建活动
		Poll<Dish> poll = Poll.create();

		//基本信息
		String name = "家庭聚餐";
		Calendar date = Calendar.getInstance();

		//投票类型
		Map<String, Integer> voteType = new HashMap<>();
		voteType.put("喜欢", 2);
		voteType.put("无所谓", 1);
		voteType.put("不喜欢", 0);
		VoteType vote = new VoteType(voteType);

		//侯选数
		int quantity = 4;
		Map<Voter, Double> voters = new HashMap<>();

		//投票人及其权重
		Voter v1 = new Voter("爷爷");
		Voter v2 = new Voter("爸爸");
		Voter v3 = new Voter("妈妈");
		Voter v4 = new Voter("儿子");
		voters.put(v1,4.0);
		voters.put(v2,1.0);
		voters.put(v3,2.0);
		voters.put(v4,2.0);

		//候选菜品
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

		//设定基本信息
		poll.setInfo(name ,date,vote,quantity);

		//添加投票人
		poll.addVoters(voters);

		//添加候选人
		poll.addCandidates(candidates);

		//创建投票
		Set<VoteItem<Dish>> voteItem1=new HashSet<>();
		voteItem1.add(new VoteItem<>(d1,"喜欢"));
		voteItem1.add(new VoteItem<>(d2,"喜欢"));
		voteItem1.add(new VoteItem<>(d3,"无所谓"));
		voteItem1.add(new VoteItem<>(d4,"无所谓"));
		voteItem1.add(new VoteItem<>(d5,"不喜欢"));
		voteItem1.add(new VoteItem<>(d6,"不喜欢"));
		Set<VoteItem<Dish>> voteItem2=new HashSet<>();
		voteItem2.add(new VoteItem<>(d1,"无所谓"));
		voteItem2.add(new VoteItem<>(d2,"喜欢"));
		voteItem2.add(new VoteItem<>(d3,"喜欢"));
		voteItem2.add(new VoteItem<>(d4,"喜欢"));
		voteItem2.add(new VoteItem<>(d5,"不喜欢"));
		voteItem2.add(new VoteItem<>(d6,"喜欢"));
		Set<VoteItem<Dish>> voteItem3=new HashSet<>();
		voteItem3.add(new VoteItem<>(d1,"喜欢"));
		voteItem3.add(new VoteItem<>(d2,"不喜欢"));
		voteItem3.add(new VoteItem<>(d3,"不喜欢"));
		voteItem3.add(new VoteItem<>(d4,"不喜欢"));
		voteItem3.add(new VoteItem<>(d5,"喜欢"));
		voteItem3.add(new VoteItem<>(d6,"不喜欢"));
		Set<VoteItem<Dish>> voteItem4=new HashSet<>();
		voteItem4.add(new VoteItem<>(d1,"喜欢"));
		voteItem4.add(new VoteItem<>(d2,"无所谓"));
		voteItem4.add(new VoteItem<>(d3,"喜欢"));
		voteItem4.add(new VoteItem<>(d4,"喜欢"));
		voteItem4.add(new VoteItem<>(d5,"喜欢"));
		voteItem4.add(new VoteItem<>(d6,"不喜欢"));

		//创建投票人的投票
		poll.addVote(new RealNameVote<>(voteItem1,v1));
		poll.addVote(new RealNameVote<>(voteItem2,v2));
		poll.addVote(new RealNameVote<>(voteItem3,v3));
		poll.addVote(new RealNameVote<>(voteItem4,v4));

		//按照计票规则计票
		poll.statistics(new Statistics3(voters,voteType));

		//按照遴选规则遴选
		poll.selection(new Selection3(quantity));
		System.out.println(poll.result());
	}
}
