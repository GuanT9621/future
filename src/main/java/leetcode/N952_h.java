package leetcode;

import java.util.*;

/**
 * https://leetcode.cn/problems/largest-component-size-by-common-factor/
 * 不相交集类 并查集 union/find 算法
 *
 */
public class N952_h {

    class Solution {

        // todo  并查集

        public int largestComponentSize(int[] nums) {
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int n1 : nums) {
                for (int n2 : nums) {
                    if (n1 == n2) {
                        continue;
                    }
                    int max = Math.max(n1, n2);
                    int min = Math.min(n1, n2);
                    if (gcd(max, min) > 1) {
                        List<Integer> list = map.getOrDefault(n1, new ArrayList<>());
                        list.add(n2);
                        map.put(n1, list);
                    }
                }
            }
            int ans = 0;
//            for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
//                Set<Integer> set = new HashSet<>();
//                set.addAll(entry.getValue());
//                int tmp = entry.getValue().size();
//                for () {
//
//                }
//
//            }
            return ans;
        }

        private int gcd(int m, int n) {
            return n == 0 ? m : gcd(n, m % n);
        }

    }

    public static void main(String[] args) {
        int[] nums = new int[] {4,6,15,35};
        int i = new N952_h().new Solution().largestComponentSize(nums);
        System.out.println(i);
    }

}
