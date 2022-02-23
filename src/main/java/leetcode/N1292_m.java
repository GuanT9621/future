package leetcode;

/**
 * https://leetcode-cn.com/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold/
 *
 * 关键词 二维前缀和 Prefix Sum
 *          二维前缀和数组可以帮助我们在 O(1) 的时间内求出任意一个矩形区域的元素之和。
 *          具体地，设我们需要求和的矩形区域的左上角为 (x1, y1)，右下角为 (x2, y2)，则该矩形区域的元素之和可以表示为：
 *          sum = A[x1..x2][y1..y2] = P[x2][y2] - P[x1 - 1][y2] - P[x2][y1 - 1] + P[x1 - 1][y1 - 1]
 *
 * 相关联题目 N1314
 *
 * 元素和小于等于阈值的正方形的最大边长
 * 给你一个大小为 m x n 的矩阵 mat 和一个整数阈值 threshold。
 * 请你返回元素总和小于或等于阈值的正方形区域的最大边长；如果没有这样的正方形区域，则返回 0 。
 * 条件：1 <= m, n <= 300    m == mat.length    n == mat[i].length    0 <= mat[i][j] <= 10000    0 <= threshold <= 10^5
 *
 * 思路一 遍历尝试
 * 1 从(0, 0)元素开始遍历
 * 2 将当前元素作为左上角尝试拓展正方形
 *
 * 思路二 二维前缀和数组 + 二分查找
 * 1 制作二维前缀和的数组
 * 2 二分查找
 */
public class N1292_m {

    public int maxSideLength(int[][] mat, int threshold) {
        int max = 0;
        int m = mat.length;
        int n = mat[0].length;
        int limitMax = Math.min(m, n);
        int[][] p = prefixSum(mat);
        for (int i=0; i < m; i++) {
            for (int j=0; j < n; j++) {
                for (int k=1; k <= limitMax; k++) {
                    if (k+i <= m && k+j <= n) {
                        // A[i][j] = P[i][j] - P[i - 1][j] - P[i][j - 1] + P[i - 1][j - 1]
                        int sum = p[k + i][k + j] - p[i][k + j] - p[k + i][j] + p[i][j];
                        if (sum <= threshold) {
                            max = Math.max(max, k);
                        }
                    }
                }
            }
        }
        return max;
    }

    /** P[i][j] = P[i - 1][j] + P[i][j - 1] - P[i - 1][j - 1] + A[i][j] */
    public int[][] prefixSum(int[][] mat) {
        int row = mat.length;
        int column = mat[0].length;
        int[][] p = new int[row + 1][column + 1];
        for (int r=1; r <= row; r++) {
            for (int c=1; c <= column; c++) {
                p[r][c] = p[r - 1][c] + p[r][c - 1] - p[r - 1][c - 1] + mat[r - 1][c - 1];
            }
        }
        return p;
    }

    public static void main(String[] args) {
        int[][] mat = new int[][]{{18,70},{61,1},{25,85},{14,40},{11,96},{97,96},{63,45}};
        int threshold = 40184;
        int i = new N1292_m().maxSideLength(mat, threshold);
        System.out.println(i);
    }

}
