package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class N658_m {

    class Solution {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            int n = arr.length;
            if (x <= arr[0]) {
                return sub(arr, 0, k - 1);
            }
            if (arr[n - 1] <= x) {
                return sub(arr, n - k, n - 1);
            }
            Queue<Integer> ans = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                if (ans.size() < k) {
                    ans.offer(arr[i]);
                    continue;
                }
                if (x - ans.peek() <= arr[i] - x) {
                    break;
                } else {
                    ans.poll();
                    ans.offer(arr[i]);
                }
            }
            return new ArrayList<>(ans);
        }
        private List<Integer> sub(int[] arr, int s, int e) {
            List<Integer> ans = new ArrayList<>();
            for (int i = s; i <= e; i++) {
                ans.add(arr[i]);
            }
            return ans;
        }
    }

}
