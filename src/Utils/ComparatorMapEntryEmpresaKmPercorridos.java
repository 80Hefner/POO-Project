package Utils;

import java.util.Comparator;
import java.util.Map;

public class ComparatorMapEntryEmpresaKmPercorridos implements Comparator<Map.Entry<String, Double>> {
    @Override
    public int compare(Map.Entry<String,Double> o1, Map.Entry<String,Double> o2) {
        double compara = o2.getValue().compareTo(o1.getValue());
        if (compara == 0) {
            return o1.getKey().compareTo(o2.getKey());
        }
        else if (compara > 0) return 1;
        else return -1;
    }
}
