package poll;

import java.util.*;

import auxiliary.Voter;
import pattern.SelectionStrategy;
import pattern.StatisticsStrategy;
import vote.RealNameVote;
import vote.VoteItem;
import vote.VoteType;
import vote.Vote;

public class GeneralPollImpl<C> implements Poll<C> {

    // ͶƱ�������
    private String name;
    // ͶƱ������ʱ��
    private Calendar date;
    // ��ѡ���󼯺�
    private List<C> candidates;
    // ͶƱ�˼��ϣ�keyΪͶƱ�ˣ�valueΪ���ڱ���ͶƱ����ռȨ��
    private Map<Voter, Double> voters;
    // ��ѡ���ĺ�ѡ�����������
    private int quantity;
    // ����ͶƱ����õ�ͶƱ���ͣ��Ϸ�ѡ����Զ�Ӧ�ķ�����
    private VoteType voteType;
    // ����ѡƱ����
    protected Set<Vote> votes;
    // ��Ʊ�����keyΪ��ѡ����valueΪ��÷�
    protected Map<C, Double> statistics;
    // ��ѡ�����keyΪ��ѡ����valueΪ������λ��
    private Map<C, Double> results;

    //����rep�����ڼ��ѡƱ�ĺϷ���
    private Map<C, String> check = new HashMap<>();
    //����rep�����ѡƱ�Ϸ���
    private Set<C> checkName = new HashSet<>();
    //����rep�����ѡƱ�Ϸ���
    private Map<Vote<C>, Boolean> checkVote;

    // Rep Invariants
    // TODO
    //name��Ϊ�գ�voters��Ϊ�գ�quantity����0��С�ں�ѡ��������votes��Ϊ�գ�statistics��results��key��candidates����һ��
    // Abstract Function
    // TODO
    //����Ϊname��ͶƱ�У��к�ѡ�˼���candidates��votersΪͶƱ�˼���Ȩ�أ������ѡ��quantity����ѡ�ˣ��԰������е�ѡƱ�ļ���votes�������򣬼����������ѡ�˵ĵ÷�statistics�����õ����results
    // Safety from Rep Exposure
    // TODO
    //���÷���ʹ��̣���ֹй¶

    private void checkRep() {
        // TODO
        assert name != null;
        //assert candidates!=null;
        //assert voters!=null;
        //assert quantity >0;
        assert voteType != null;
        //assert votes!=null;
        if (!statistics.isEmpty()) {
            assert statistics.keySet().size() == candidates.size();
            for (C c : candidates) {
                assert statistics.containsKey(c);
            }
        }
//        if (!results.isEmpty()) {
//            assert results.keySet().size() == candidates.size();
//            for (C c : candidates) {
//                assert results.containsKey(c);
//            }
//        }
    }

    /**
     * ���캯��
     */
    public GeneralPollImpl() {
        // TODO
    }

    @Override
    public void setInfo(String name, Calendar date, VoteType type, int quantity) {
        // TODO
        this.name = name;
        this.date = date;
        this.voteType = type;
        this.quantity = quantity;
        checkRep();
    }

    @Override
    public void addVoters(Map<Voter, Double> voters) {
        // TODO
        this.voters.putAll(voters);
        checkRep();
    }

    @Override
    public void addCandidates(List<C> candidates) {
        // TODO
        this.candidates.addAll(candidates);
        checkRep();
    }

    @Override
    public void addVote(Vote<C> vote) {
        // �˴�Ӧ���ȼ���ѡƱ�ĺϷ��Բ���ǣ�Ϊ������չ���޸�rep
        // TODO
        for (VoteItem<C> v : vote.getVoteItems()) {
            check.put(v.getCandidate(), v.getVoteValue());
        }
        if (check.keySet().size() != candidates.size()) {
            //throw new Exception("ѡƱ���Ϸ���");//��������ȣ���Ȼ���Ϸ�
            checkVote.put(vote, false);
            return;
        }
        for (C c : candidates) {
            if (!check.containsKey(c)) {
                //throw new Exception("ѡƱ���Ϸ���");//ѡƱ�к��в����ں�ѡ�˵ģ����Ϸ�
                checkVote.put(vote, false);
                return;
            }
        }
        for (VoteItem<C> v : vote.getVoteItems()) {
            if (!checkName.contains(v.getCandidate())) {
                checkName.add(v.getCandidate());
            } else {
                //throw new Exception("ѡƱ���Ϸ���");//ѡƱ�ж�ͬһ����ѡ���ж��ͶƱ�����Ϸ�
                checkVote.put(vote, false);
                return;
            }
        }
        votes.add(vote);
        checkVote.put(vote, true);
        checkRep();
    }

    @Override
    public void statistics(StatisticsStrategy ss) throws Exception {
        // �˴�Ӧ���ȼ�鵱ǰ��ӵ�е�ѡƱ�ĺϷ���
        // TODO
        if (votes.size() != voters.keySet().size()) {
            throw new Exception("ѡƱδ���� ��");
        }
        statistics = ss.calculate(votes);
        checkRep();
    }

    @Override
    public void selection(SelectionStrategy ss) throws Exception {
        // TODO
        results = ss.selection(statistics);
    }


    @Override
    public String result() {
        // TODO
        StringBuilder re = new StringBuilder();
        for (C c : results.keySet()) {
            re.append(c).append(" ").append(results.get(c)).append("\n");
        }
        return re.toString();
    }

    public Map<Vote<C>,Boolean> getCheckVote(){
        return new HashMap<>(checkVote);
    }

    @Override
    public double accept(CalculateVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralPollImpl<?> that = (GeneralPollImpl<?>) o;
        return quantity == that.quantity && Objects.equals(name, that.name) && Objects.equals(date, that.date) && Objects.equals(candidates, that.candidates) && Objects.equals(voters, that.voters) && Objects.equals(voteType, that.voteType) && Objects.equals(votes, that.votes) && Objects.equals(statistics, that.statistics) && Objects.equals(results, that.results) && Objects.equals(check, that.check) && Objects.equals(checkName, that.checkName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, candidates, voters, quantity, voteType, votes, statistics, results, check, checkName);
    }
}
