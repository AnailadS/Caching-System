package observerPattern.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class MapTool<K, V> {


    /**
     * Method that sorts the entries of a map by it's values. The values must be comparable objects!
     * @param map
     * @return
     */
    public List<K> mapSort(final Map<K, V> map) {

        List<Map.Entry<K, V>> sortedList = new ArrayList<Map.Entry<K, V>>(map.entrySet());

        Collections.sort(sortedList, new Comparator<Map.Entry<K, V>>() {

            @SuppressWarnings("unchecked")
            @Override
            public int compare(final Map.Entry<K, V> entry1, final Map.Entry<K, V> entry2) {
                return ((Comparable<V>) entry2.getValue()).compareTo(entry1.getValue());
            }

        });
        List<K> values = new ArrayList<K>();

        for (Map.Entry<K, V> entry : sortedList) {
            values.add(entry.getKey());
        }
        return values;
    }

}
