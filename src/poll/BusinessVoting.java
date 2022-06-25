package poll;

import auxiliary.Dish;
import auxiliary.Proposal;
import auxiliary.Voter;
import pattern.SelectionStrategy;
import pattern.StatisticsStrategy;
import vote.RealNameVote;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

import java.util.*;

public class BusinessVoting extends GeneralPollImpl<Proposal> implements Poll<Proposal> {

    //��ҵ�᰸

    //TODO

    private String name;
    // ͶƱ������ʱ��
    private Calendar date;
    // ��ѡ���󼯺�
    private List<Proposal> candidates;
    // ͶƱ�˼��ϣ�keyΪͶƱ�ˣ�valueΪ���ڱ���ͶƱ����ռȨ��
    private Map<Voter, Double> voters;
    // ��ѡ���ĺ�ѡ�����������
    private int quantity;
    // ����ͶƱ����õ�ͶƱ���ͣ��Ϸ�ѡ����Զ�Ӧ�ķ�����
    private VoteType voteType;
    // ��ѡ�����keyΪ��ѡ����valueΪ������λ��
    private Map<Proposal, Double> results;
    //ʵ��ͶƱ
    private Set<RealNameVote<Proposal>> rvotes;
    //����rep�����ڼ��ѡƱ�ĺϷ���
    private Map<Proposal, String> check = new HashMap<>();
    //����rep�����ѡƱ�Ϸ���
    private Set<Proposal> checkName = new HashSet<>();
    //����rep�����ѡƱ�Ϸ���
    private Map<RealNameVote<Proposal>,Boolean> checkVote;

    /**
     * ����addvote
     *
     * @param vote
     * @throws Exception
     */
    public void addVote(RealNameVote<Proposal> vote)  {
        for (VoteItem<Proposal> v : vote.getVoteItems()) {
            check.put(v.getCandidate(), v.getVoteValue());
        }
        if (check.keySet().size() != candidates.size()) {
            //throw new Exception("ѡƱ���Ϸ���");//��������ȣ���Ȼ���Ϸ�
            checkVote.put(vote,false);
            return;
        }
        for (Proposal c : candidates) {
            if (!check.containsKey(c)) {
                //throw new Exception("ѡƱ���Ϸ���");//ѡƱ�к��в����ں�ѡ�˵ģ����Ϸ�
                checkVote.put(vote,false);
                return;
            }
        }
        for (VoteItem<Proposal> v : vote.getVoteItems()) {
            if (!checkName.contains(v.getCandidate())) {
                checkName.add(v.getCandidate());
            } else {
                //throw new Exception("ѡƱ���Ϸ���");//ѡƱ�ж�ͬһ����ѡ���ж��ͶƱ�����Ϸ�
                checkVote.put(vote,false);
                return;
            }
        }
        rvotes.add(vote);
        checkVote.put(vote,true);
    }

    @Override
    public void statistics(StatisticsStrategy ss) throws Exception {
        //���Ϸ���
        if (rvotes.size() != voters.keySet().size()) {
            throw new Exception("ѡƱδ���� ��");
        }
        Set<String> checkP = new HashSet<>();
        for (RealNameVote<Proposal> p : rvotes) {
            if (checkP.contains(p.getVoter().getID())) {
                throw new Exception("���˶���ύѡƱ��");
            } else {
                checkP.add(p.getVoter().getID());
            }
        }
        statistics = ss.calculate(rvotes);
    }

    @Override
    public void selection(SelectionStrategy ss) throws Exception {
        if (quantity == 0 || quantity == 1) {
            results = ss.selection(statistics);
        } else {
            throw new Exception("quantity�����Ϸ�Χ��");
        }
    }

    @Override
    public Map<Vote<Proposal>,Boolean> getCheckVote(){
        return new HashMap<>(checkVote);
    }
}