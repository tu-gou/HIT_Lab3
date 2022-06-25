package poll;

public interface CalculateVisitor<C> {
    public double visit(GeneralPollImpl generalPoll);
}
