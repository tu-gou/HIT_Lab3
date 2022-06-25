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
		//����ͶƱ�
		Poll<Proposal> poll=new BusinessVoting();

		//�趨ͶƱ��������
		String name ="��ҵ�᰸";
		Calendar date=Calendar.getInstance();
		Map<String,Integer> voteType=new HashMap<>();
		voteType.put("֧��",1);
		voteType.put("��Ȩ",0);
		voteType.put("����",-1);
		VoteType vote=new VoteType(voteType);
		int quantity=1;

		//���ͶƱ�˼���Ȩ��
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

		//��Ӻ�ѡ����
		List<Proposal> candidates=new ArrayList<>();
		Proposal p=new Proposal("xx�᰸",date);
		candidates.add(p);

		//�����Ϣ
		poll.setInfo(name,date,vote,quantity);

		//���ͶƱ��
		poll.addVoters(voters);

		//��Ӻ�ѡ��
		poll.addCandidates(candidates);

		//����ͶƱ�˶Ժ�ѡ�����ѡƱ
		Set<VoteItem<Proposal>> voteItem1=new HashSet<>();
		voteItem1.add(new VoteItem<>(p,"����"));
		Set<VoteItem<Proposal>> voteItem2=new HashSet<>();
		voteItem2.add(new VoteItem<>(p,"֧��"));
		Set<VoteItem<Proposal>> voteItem3=new HashSet<>();
		voteItem3.add(new VoteItem<>(p,"֧��"));
		Set<VoteItem<Proposal>> voteItem4=new HashSet<>();
		voteItem4.add(new VoteItem<>(p,"����"));
		Set<VoteItem<Proposal>> voteItem5=new HashSet<>();
		voteItem5.add(new VoteItem<>(p,"��Ȩ"));

		//ѡƱ����ͶƱ��
		poll.addVote(new RealNameVote<>(voteItem1,v1));
		poll.addVote(new RealNameVote<>(voteItem2,v2));
		poll.addVote(new RealNameVote<>(voteItem3,v3));
		poll.addVote(new RealNameVote<>(voteItem4,v4));
		poll.addVote(new RealNameVote<>(voteItem5,v5));

		//��Ʊ�����Ʊ
		poll.statistics(new Statistics1(voters,voteType));

		//��ѡ������ѡ
		poll.selection(new Selection1(quantity));

		//������
		System.out.println(poll.result());
	}
}
