package Utils;

import java.util.Comparator;
import java.util.Map;

public class ComparatorMapEntryUtilizadoresEncomendas implements Comparator<Map.Entry<String, Integer>> {
    @Override
    public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2) {
        int compara = o2.getValue().compareTo(o1.getValue());
        if (compara == 0) {
            return o1.getKey().compareTo(o2.getKey());
        }
        return compara;
    }
}
