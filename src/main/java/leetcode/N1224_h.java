package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class N1224_h {

    /**
     * 双指针 + HashMap
     */
    class Solution {
        public int maxEqualFreq(int[] nums) {
            int ans = 0;
            for (int i = 0; i < nums.length; i++) {
                Map<Integer, Integer> cm = new HashMap<>();
                for (int j = i; j < nums.length; j++) {
                    cm.put(nums[j], cm.getOrDefault(nums[j], 0) + 1);
                    if (judge(cm)) {
                        ans = Math.max(ans, j - i + 1);
                    }
                }
            }
            return ans;
        }
        private boolean judge(Map<Integer, Integer> cm) {
            Set<Integer> set = new HashSet<>(cm.values());
            return set.size() == 2;
        }
    }

}
