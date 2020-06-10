package Utils;

import java.util.Comparator;
import java.util.Map;

/**
 * Classe com Comparator de Map.Entrys que possuem como key uma String e como Value um Integer
 */
public class ComparatorMapEntryUtilizadoresEncomendas implements Comparator<Map.Entry<String, Integer>> {

    /**
     * @brief              Função que compara dois Map.Entrys que possuem como key uma String e como Value um Integer
     * @param o1           Map.Entry<String,Integer>
     * @param o2           Map.Entry<String,Integer>
     * @return             Inteiro que vai servir de comparação, como nas funções gerais de compare
     */
    @Override
    public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2) {
        int compara = o2.getValue().compareTo(o1.getValue());
        if (compara == 0) {
            if (o1.getKey().length() < o2.getKey().length()) {
                return -1;
            } else if (o1.getKey().length() > o2.getKey().length())
                return 1;
            else {
                return o1.getKey().compareTo(o2.getKey());
            }
        }
        return compara;
    }
}
