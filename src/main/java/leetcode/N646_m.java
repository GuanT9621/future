package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 思路 动态规划
 * TLE优化 sort + dp
 */
public class N646_m {

    /** TLE */
    class Solution {

        public int findLongestChain(int[][] pairs) {
            return dynamic(pairs, pairs.length - 1, new ArrayList<>());
        }

        private int dynamic(int[][] pairs, int k, List<int[]> pick) {
            if (!judge(pick)) {
                return -1;
            }
            if (k < 0) {
                return 0;
            }
            ArrayList<int[]> picked = new ArrayList<>(pick);
            picked.add(pairs[k]);
            int i1 = dynamic(pairs, k - 1, picked);
            int i2 = dynamic(pairs, k - 1, pick);
            return Math.max(1 + i1, i2);
        }

        private boolean judge(List<int[]> picked) {
            int size = picked.size();
            for (int i = 0; i < size; i++) {
                int[] tmp = picked.get(i);
                for (int j = 0; j < size; j++) {
                    int[] tmp1 = picked.get(j);
                    if (i == j) {
                        continue;
                    }
                    if (!(tmp[1] < tmp1[0] || tmp1[1] < tmp[0])) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    class Solution2 {

        public int findLongestChain(int[][] pairs) {
            // todo
            return 1;
        }

    }

    public static void main(String[] args) {
//        int[][] ints = {{1, 2}, {2, 3}, {3, 4}};
        int[][] ints = {{9,10},{-4,9},{-5,6},{-5,9},{8,9}};
//        int[][] ints = {{9,10}};

        int i = new N646_m().new Solution2().findLongestChain(ints);
        System.out.println(i);
    }

}
