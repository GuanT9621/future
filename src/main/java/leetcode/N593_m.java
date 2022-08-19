package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 注意 1 会将正方形侧放 2 提供了四个点（而非四条线，所以这4个点组成的线必定相连）
 * 所以 证明 四边相等 && 一个角度==90 即可
 * 如何选出4个边？凸包算法没必要，只需找到每个角落即可（x y 最大，x y 最小，x最小y最大，x最大y最小）
 *
 */
public class N593_m {

    class Solution {
        /**
         * if set
         * a --- b
         * |     |
         * d --- c
         */
        public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
            List<int[]> points = new ArrayList<>();
            points.add(p1);
            points.add(p2);
            points.add(p3);
            points.add(p4);
            points.sort((a, b) -> a[0] - b[0] == 0 ? a[1] - b[1] : a[0] - b[0]);
            int[] a = points.get(0);
            int[] b = points.get(1);
            int[] c = points.get(3);
            int[] d = points.get(2);
            return sameLength(a, b, c, d) && rightAngle(a, b, c);
        }

        private boolean sameLength(int[] p1, int[] p2, int[] p3, int[] p4) {
            int l12 = lengthPow2(p1, p2);
            int l23 = lengthPow2(p2, p3);
            int l34 = lengthPow2(p3, p4);
            int l41 = lengthPow2(p4, p1);
            return l12 == l23 && l23 == l34 && l34 == l41 && l41 > 0;
        }

        private boolean rightAngle(int[] p1, int[] p2, int[] p3) {
            int l12 = lengthPow2(p1, p2);
            int l23 = lengthPow2(p2, p3);
            int l13 = lengthPow2(p1, p3);
            int max = Math.max(l12, Math.max(l23, l13));
            return max * 2 == l12 + l23 + l13;
        }

        private int lengthPow2(int[] a, int[] b) {
            return (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
        }
    }

    public static void main(String[] args) {
        boolean b = new N593_m().new Solution().validSquare(
                new int[]{1, 0},
                new int[]{0, 1},
                new int[]{-1, 0},
                new int[]{0, -1}
        );
        System.out.println(b);
    }

}
