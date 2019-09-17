package algorithm;

import java.util.*;

public class TokenCounter {

    public static Iterable<String> top(Iterable<String> input, int n) {
        Map<String, Integer> countMap = new HashMap<>();
        for (String string : input) {
            Integer count = 0;
            if (countMap.containsKey(string)) {
                count = countMap.get(string);
                count++;
            }
            countMap.put(string, count);
        }
        countMap = sortMapByValue(countMap);
        List<String> result = toSubList(countMap);
        return result.subList(0, n);
    }

    static class MapValueComparator implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> me1, Map.Entry<String, Integer> me2) {
            return me2.getValue().compareTo(me1.getValue());
        }
    }

    public static Map<String, Integer> sortMapByValue(Map<String, Integer> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(oriMap.entrySet());
        Collections.sort(entryList, new MapValueComparator());
        Iterator<Map.Entry<String, Integer>> iter = entryList.iterator();
        Map.Entry<String, Integer> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }

    public static List<String> toSubList(Map<String, Integer> oriMap) {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : oriMap.entrySet()) {
            list.add(entry.getKey());
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("b");
        list.add("b");
        list.add("c");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("f");
        Iterable<String> top = top(list, 3);
        for (String string : top) {
            System.out.println(string);
        }
    }

}
