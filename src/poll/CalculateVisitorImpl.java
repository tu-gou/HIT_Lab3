package poll;

import vote.Vote;

import java.util.*;

public class CalculateVisitorImpl<C> implements CalculateVisitor<C>{

    @Override
    public double visit(GeneralPollImpl generalPoll){
        Map<Vote<C>,Boolean> checkVote=generalPoll.getCheckVote();
        int size=checkVote.size();
        int trueVote=0;
        for(Vote<C> v:checkVote.keySet()){
            if(checkVote.get(v)){
                trueVote++;
            }
        }
        return (double)(trueVote/size);
    }
}
