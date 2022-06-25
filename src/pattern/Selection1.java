package pattern;

import java.util.*;

public class Selection1<C> implements SelectionStrategy<C> {

    //商业提案

    //存放经过遴选后的结果
    private C candidate;
    private Double score = 0.0;
    //需要选出的数量
    private int quantity;

    public Selection1(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public Map<C, Double> selection(Map<C, Double> votes) {
        for (C c : votes.keySet()) {
            if (votes.get(c) > score) {
                score = votes.get(c);
                candidate = c;
            }
        }
        if (score > (double) 2 / 3) {
            Map<C, Double> result = new HashMap<>();
            result.put(candidate, score);
            return result;
        } else {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Selection1<?> that = (Selection1<?>) o;
        return quantity == that.quantity && Objects.equals(candidate, that.candidate) && Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidate, score, quantity);
    }
}
