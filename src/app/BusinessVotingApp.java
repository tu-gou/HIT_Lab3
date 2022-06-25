package app;

import auxiliary.Proposal;
import auxiliary.Voter;
import pattern.Selection1;
import pattern.Statistics1;
import poll.BusinessVoting;
import poll.Poll;
import vote.RealNameVote;
import vote.VoteItem;
import vote.VoteType;

import java.util.*;

public class BusinessVotingApp {

	public static void main(String[] args) throws Exception {
		// TODO
		//创建投票活动
		Poll<Proposal> poll=new BusinessVoting();

		//设定投票基本属性
		String name ="商业提案";
		Calendar date=Calendar.getInstance();
		Map<String,Integer> voteType=new HashMap<>();
		voteType.put("支持",1);
		voteType.put("弃权",0);
		voteType.put("反对",-1);
		VoteType vote=new VoteType(voteType);
		int quantity=1;

		//添加投票人及其权重
		Map<Voter,Double> voters=new HashMap<>();
		Voter v1=new Voter("A");
		Voter v2=new Voter("B");
		Voter v3=new Voter("C");
		Voter v4=new Voter("D");
		Voter v5=new Voter("E");
		voters.put(v1,0.05);
		voters.put(v2,0.51);
		voters.put(v3,0.1);
		voters.put(v4,0.14);
		voters.put(v5,0.2);

		//添加候选对象
		List<Proposal> candidates=new ArrayList<>();
		Proposal p=new Proposal("xx提案",date);
		candidates.add(p);

		//添加信息
		poll.setInfo(name,date,vote,quantity);

		//添加投票人
		poll.addVoters(voters);

		//添加候选人
		poll.addCandidates(candidates);

		//创建投票人对候选对象的选票
		Set<VoteItem<Proposal>> voteItem1=new HashSet<>();
		voteItem1.add(new VoteItem<>(p,"反对"));
		Set<VoteItem<Proposal>> voteItem2=new HashSet<>();
		voteItem2.add(new VoteItem<>(p,"支持"));
		Set<VoteItem<Proposal>> voteItem3=new HashSet<>();
		voteItem3.add(new VoteItem<>(p,"支持"));
		Set<VoteItem<Proposal>> voteItem4=new HashSet<>();
		voteItem4.add(new VoteItem<>(p,"反对"));
		Set<VoteItem<Proposal>> voteItem5=new HashSet<>();
		voteItem5.add(new VoteItem<>(p,"弃权"));

		//选票加入投票中
		poll.addVote(new RealNameVote<>(voteItem1,v1));
		poll.addVote(new RealNameVote<>(voteItem2,v2));
		poll.addVote(new RealNameVote<>(voteItem3,v3));
		poll.addVote(new RealNameVote<>(voteItem4,v4));
		poll.addVote(new RealNameVote<>(voteItem5,v5));

		//计票规则计票
		poll.statistics(new Statistics1(voters,voteType));

		//遴选规则遴选
		poll.selection(new Selection1(quantity));

		//输出结果
		System.out.println(poll.result());
	}
}
