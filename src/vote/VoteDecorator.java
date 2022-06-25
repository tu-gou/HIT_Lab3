package vote;

import java.util.Set;

public abstract class VoteDecorator<C> implements VoteInterface<C> {

    protected final VoteInterface<C> voteInterface;

    public VoteDecorator(VoteInterface<C> voteInterface){
        this.voteInterface=voteInterface;
    }

    public Set<VoteItem<C>> getVoteItems() {
        return voteInterface.getVoteItems();
    }

    public boolean candidateIncluded(C candidate){
        return voteInterface.candidateIncluded(candidate);
    }

}
