package poll;

import auxiliary.Person;
import auxiliary.Voter;
import pattern.SelectionStrategy;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

import java.util.*;

public class Election extends GeneralPollImpl<Person> implements Poll<Person> {

    //����ѡ��

	//TODO

    // ͶƱ�������
    private String name;
    // ͶƱ������ʱ��
    private Calendar date;
    // ��ѡ���󼯺�
    private List<Person> candidates;
    // ͶƱ�˼��ϣ�keyΪͶƱ�ˣ�valueΪ���ڱ���ͶƱ����ռȨ��
    private Map<Voter, Double> voters;
    // ��ѡ���ĺ�ѡ�����������
    private int quantity;
    // ����ͶƱ����õ�ͶƱ���ͣ��Ϸ�ѡ����Զ�Ӧ�ķ�����
    private VoteType voteType;
    // ��ѡ�����keyΪ��ѡ����valueΪ������λ��
    private Map<Person, Double> results;

    //����rep�����ڼ��ѡƱ�ĺϷ���
    private Map<Person, String> check = new HashMap<>();
    //����rep�����ѡƱ�Ϸ���
    private Set<Person> checkName = new HashSet<>();
    //����rep
    private Map<Vote<Person>,Boolean> checkVote;

    @Override
    public void addVote(Vote<Person> vote)  {
        // �˴�Ӧ���ȼ���ѡƱ�ĺϷ��Բ���ǣ�Ϊ������չ���޸�rep
        // TODO
        for (VoteItem<Person> v : vote.getVoteItems()) {
            check.put(v.getCandidate(), v.getVoteValue());
        }
        if (check.keySet().size() != candidates.size()) {
            //throw new Exception("ѡƱ���Ϸ���");//��������ȣ���Ȼ���Ϸ�
            checkVote.put(vote,false);
            return;
        }
        for (Person c : candidates) {
            if (!check.containsKey(c)) {
                //throw new Exception("ѡƱ���Ϸ���");//ѡƱ�к��в����ں�ѡ�˵ģ����Ϸ�
                checkVote.put(vote,false);
                return;
            }
        }
        for (VoteItem<Person> v : vote.getVoteItems()) {
            if (!checkName.contains(v.getCandidate())) {
                checkName.add(v.getCandidate());
            } else {
                //throw new Exception("ѡƱ���Ϸ���");//ѡƱ�ж�ͬһ����ѡ���ж��ͶƱ�����Ϸ�
                checkVote.put(vote,false);
                return;
            }
        }
        int i=0;
        for(Person p:check.keySet()){
            if(voteType.getScoreByOption(check.get(p))==1){
                i++;
            }
        }
        if(i>quantity){
            //throw new Exception("֧��Ʊ��������quantity!");
            checkVote.put(vote,false);
            return;
        }


//        Set<Person> p=new HashSet<>();
//        for(VoteItem<Person> v : vote.getVoteItems()){
//            if(p.contains(v.getCandidate())){
//                throw new Exception("֧��Ʊ������quantity��");
//            }else{
//                p.add(v.getCandidate());
//            }
//        }
        votes.add(vote);
        checkVote.put(vote,true);
    }

    @Override
    public void selection(SelectionStrategy ss) throws Exception {
        if(quantity<=candidates.size()){
            results = ss.selection(statistics);
        }
        else{
            throw new Exception("quantity�����Ϸ�Χ��");
        }
    }

    @Override
    public Map<Vote<Person>,Boolean> getCheckVote(){
        return new HashMap<>(checkVote);
    }
}
