package leetcode;

/**
 * https://leetcode.cn/problems/max-chunks-to-make-sorted-ii/
 */
public class N768_h {

    class Solution {

        public int maxChunksToSorted(int[] arr) {
            return maxChunksToSorted(arr, 0, arr.length - 1);
        }

        private int maxChunksToSorted(int[] array, int start, int end) {
            if (start == end) {
                return 1;
            }
            for (int i = start; i < end; i++) {
                int leftMax = max(array, start, i);
                int rightMin = min(array, i + 1, end);
                if (leftMax <= rightMin) {
                    return maxChunksToSorted(array, start, i) + maxChunksToSorted(array, i + 1, end);
                }
            }
            return 1;
        }

        private int max(int[] arr, int s, int e) {
            int max = Integer.MIN_VALUE;
            for (int i = s; i <= e; i++) {
                max = Math.max(max, arr[i]);
            }
            return max;
        }

        private int min(int[] arr, int s, int e) {
            int min = Integer.MAX_VALUE;
            for (int i = s; i <= e; i++) {
                min = Math.min(min, arr[i]);
            }
            return min;
        }

    }

    public static void main(String[] args) {
        int i = new N768_h().new Solution().maxChunksToSorted(new int[]{2, 1, 3, 4, 4});
        System.out.println(i);
    }

}
