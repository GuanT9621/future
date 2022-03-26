package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/online-majority-element-in-subarray/
 * 子数组中占绝大多数的元素
 * 设计一个数据结构，有效地找到给定子数组的多数元素 。
 * 子数组的 多数元素 是在子数组中出现 threshold 次数或次数以上的元素。
 * 实现 MajorityChecker 类:
 * MajorityChecker(int[] arr) 会用给定的数组 arr 对 MajorityChecker 初始化。
 * int query(int left, int right, int threshold) 返回子数组中的元素  arr[left...right] 至少出现 threshold 次数，如果不存在这样的元素则返回 -1。
 *
 * 思路 前缀和
 * 1 从位置0开始计算每个元素的数量
 * 2 query 时，在位置right 减去位置left 的每个元素的数量，返回符合 threshold 的元素。
 */
public class N1157_h {

    class MajorityChecker {
        List<Map<Integer, Integer>> list = new ArrayList<>();
        int[] arr;

        public MajorityChecker(int[] arr) {
            this.arr = arr;
            Map<Integer, Integer> objectObjectHashMap = new HashMap<>();
            objectObjectHashMap.put(arr[0], 1);
            list.add(objectObjectHashMap);
            for (int i=1; i < arr.length; i++) {
                Map<Integer, Integer> map = list.get(i - 1);
                Map<Integer, Integer> curMap = new HashMap<>(map);
                curMap.put(arr[i], curMap.getOrDefault(arr[i], 0) + 1);
                list.add(curMap);
            }
        }

        public int query(int left, int right, int threshold) {
            Map<Integer, Integer> leftMap = list.get(left);
            Map<Integer, Integer> rightMap = list.get(right);
            Set<Integer> keySet = new HashSet<>();
            keySet.addAll(leftMap.keySet());
            keySet.addAll(rightMap.keySet());
            for (Integer key : keySet) {
                int i = rightMap.getOrDefault(key, 0) - leftMap.getOrDefault(key, 0);
                i += this.arr[left] == key ? 1 : 0;
                if (i >= threshold) {
                    return key;
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        MajorityChecker majorityChecker = new N1157_h().new MajorityChecker(new int[]{1,1,2,2,1,1});
        System.out.println(majorityChecker.query(0, 5, 4)); // 返回 1
        System.out.println(majorityChecker.query(0, 3, 3)); // 返回 -1
        System.out.println(majorityChecker.query(2, 3, 2)); // 返回 2
    }
}
