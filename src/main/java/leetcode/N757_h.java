package leetcode;

import java.util.Arrays;

/**
 * 思路 贪心
 *
 */
public class N757_h {


    class Solution {

        public int intersectionSizeTwo(int[][] intervals) {
            Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
            int a = -1, b = -1, ans = 0;
            for (int[] i : intervals) {
                if (i[0] > a) {
                    if (i[0] > b) {
                        ans += 2;
                    } else {
                        b = b == -1 ? i[1] : Math.min(b, i[1]);
                    }
                    a = i[0];
                }
            }
            return ans;
        }
    }

    class Solution2 {
        public int intersectionSizeTwo(int[][] ins) {
            Arrays.sort(ins, (a, b) -> a[1] != b[1] ? a[1] - b[1] : b[0] - a[0]);
            int a = -1, b = -1, ans = 0;
            for (int[] i : ins) {
                if (i[0] > b) {
                    a = i[1] - 1; b = i[1];
                    ans += 2;
                } else if (i[0] > a) {
                    a = b; b = i[1];
                    ans++;
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        // [0,1] [1,2] [2,5] [4,9] [6,7] [7,9]
        // [0,1] [1,2] [4,5] [6,7] [7,9]

        // [0,1] [1,2] [2,5] [6,7] [7,9]
//        int[][] in = new int[][] {{1,3},{4,9},{0,10},{6,7},{1,2},{0,6},{7,9},{0,1},{2,5},{6,8}};
        int[][] in = new int[][] {{2,10},{3,7},{3,15},{4,11},{6,12},{6,16},{7,8},{7,11},{7,15},{11,12}};
        int i = new N757_h().new Solution().intersectionSizeTwo(in);
        int i2 = new N757_h().new Solution2().intersectionSizeTwo(in);
        System.out.println("all number1: " + i);
        System.out.println("all number2: " + i2);
    }

}
