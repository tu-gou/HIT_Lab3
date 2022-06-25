package vote;

import auxiliary.Voter;

public class RealVote<C> extends VoteDecorator<C> implements VoteInterface<C>{
    private Voter voter;

    public RealVote(VoteInterface<C> voteInterface,Voter voter){
        super(voteInterface);
        this.voter=voter;
    }

    public Voter getVoter(){
        return this.voter;
    }
}
