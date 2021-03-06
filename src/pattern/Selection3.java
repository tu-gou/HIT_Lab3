package pattern;

import java.util.*;

public class Selection3<C> implements SelectionStrategy<C>{

//代表选举

    //遴选候选对象数
    private int quantity;
    //最终遴选结果
    private Map<C,Double> result;
    //临时存放
    private Map<C,Double> temp;

    public Selection3(int quantity){
        this.quantity=quantity;
    }
    @Override
    public Map<C,Double> selection(Map<C,Double> votes){
        List<Map.Entry<C,Double>> list = new ArrayList<Map.Entry<C,Double>>(votes.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<C,Double>>() {
            @Override
            public int compare(Map.Entry<C, Double> o1,
                               Map.Entry<C, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        for(Map.Entry<C,Double> mapping:list){
            temp.put(mapping.getKey(),mapping.getValue());
        }
        int i=0;
        for(C c:temp.keySet()){
            if(i<quantity){
                result.put(c,temp.get(c));
                i++;
            }else{
                break;
            }
        }
        return new HashMap<>(result);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Selection3<?> that = (Selection3<?>) o;
        return quantity == that.quantity && Objects.equals(result, that.result) && Objects.equals(temp, that.temp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, result, temp);
    }
}
