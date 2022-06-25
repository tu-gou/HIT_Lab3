package app;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import auxiliary.Person;
import auxiliary.Voter;
import pattern.Selection2;
import pattern.Statistics2;
import poll.Poll;
import vote.RealNameVote;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

public class ElectionApp {

	public static void main(String[] args) throws Exception {

		// ����2��ͶƱ��
		Voter vr1 = new Voter("v1");
		Voter vr2 = new Voter("v2");

		// �趨2��ͶƱ�˵�Ȩ��
		Map<Voter, Double> weightedVoters = new HashMap<>();
		weightedVoters.put(vr1, 1.0);
		weightedVoters.put(vr2, 1.0);

		// �趨ͶƱ����
		Map<String, Integer> types = new HashMap<>();
		types.put("Support", 1);
		types.put("Oppose", -1);
		types.put("Waive", 0);
		VoteType vt = new VoteType(types);

		// ������ѡ���󣺺�ѡ��
		Person p1 = new Person("ABC", 19);
		Person p2 = new Person("DEF", 20);
		Person p3 = new Person("GHI", 21);

		// ����ͶƱ�ǰ������ͶƱ��vr1��������ѡ�����ͶƱ���������vr2��ͶƱ��
		VoteItem<Person> vi11 = new VoteItem<>(p1, "Support");
		VoteItem<Person> vi12 = new VoteItem<>(p2, "Oppose");
		VoteItem<Person> vi13 = new VoteItem<>(p3, "Support");
		VoteItem<Person> vi21 = new VoteItem<>(p1, "Oppose");
		VoteItem<Person> vi22 = new VoteItem<>(p2, "Waive");
		VoteItem<Person> vi23 = new VoteItem<>(p3, "Waive");

		// ����2��ͶƱ��vr1��vr2��ѡƱ
		Set<VoteItem<Person>> voteItem1=new HashSet<>();
		Set<VoteItem<Person>> voteItem2=new HashSet<>();
		voteItem1.add(vi11);
		voteItem1.add(vi12);
		voteItem1.add(vi13);
		voteItem2.add(vi21);
		voteItem2.add(vi22);
		voteItem2.add(vi23);
		Vote<Person> rv1 = new Vote<Person>(voteItem1);
		Vote<Person> rv2 = new Vote<Person>(voteItem2);

		// ����ͶƱ�
		Poll<Person> poll = Poll.create();
		// �趨ͶƱ������Ϣ�����ơ����ڡ�ͶƱ���͡�ѡ��������
		poll.setInfo("����ѡ��",Calendar.getInstance(),vt,1);
		// ����ͶƱ�˼���Ȩ��
		poll.addVoters(weightedVoters);
		// ��������ͶƱ�˵�ѡƱ
		poll.addVote(rv1);
		poll.addVote(rv2);

		// �������Ʊ
		poll.statistics(new Statistics2(types));
		// ��������ѡ
		poll.selection(new Selection2(1));
		// �����ѡ���
		System.out.println(poll.result());
	}

}
