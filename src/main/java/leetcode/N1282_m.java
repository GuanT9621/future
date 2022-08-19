package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.cn/problems/group-the-people-given-the-group-size-they-belong-to/
 */
public class N1282_m {

    class Solution {

        public List<List<Integer>> groupThePeople(int[] groupSizes) {
            Map<Integer, List<List<Integer>>> map = new HashMap<>();
            for (int i = 0; i < groupSizes.length; i++) {
                int groupSize = groupSizes[i];
                List<List<Integer>> lists = map.getOrDefault(groupSize, new ArrayList<>());
                if (lists.isEmpty() || lists.get(lists.size() - 1).size() == groupSize) {
                    lists.add(new ArrayList<>());
                }
                List<Integer> list = lists.get(lists.size() - 1);
                list.add(i);
                map.put(groupSize, lists);
            }
            List<List<Integer>> ans = new ArrayList<>();
            for (List<List<Integer>> value : map.values()) {
                ans.addAll(value);
            }
            return ans;
        }

    }

}
