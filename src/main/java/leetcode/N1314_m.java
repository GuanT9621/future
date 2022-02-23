package leetcode;

/**
 * https://leetcode-cn.com/problems/matrix-block-sum/
 *
 * 关键词 二维前缀和 Prefix Sum
 *        二维前缀和数组可以帮助我们在 O(1) 的时间内求出任意一个矩形区域的元素之和。
 *        具体地，设我们需要求和的矩形区域的左上角为 (x1, y1)，右下角为 (x2, y2)，则该矩形区域的元素之和可以表示为：
 *        sum = A[x1..x2][y1..y2] = P[x2][y2] - P[x1 - 1][y2] - P[x2][y1 - 1] + P[x1 - 1][y1 - 1]
 *
 * 相关联题目 N1292
 *
 * 矩阵区域和 :
 * 给你一个 m x n （行 * 列）的矩阵 mat 和一个整数 k ，请你返回一个矩阵 answer ，其中每个 answer[i][j] 是所有满足下述条件的元素 mat[r][c] 的和：
 *      i - k <= r <= i + k, j - k <= c <= j + k 且 (r, c) 在矩阵内。
 * 条件 m == mat.length   n == mat[i].length   1 <= m, n, k <= 100     1 <= mat[i][j] <= 100
 *
 * 思路一 遍历
 * 1 经过分析可以得知返回的 answer 和 mat 宽高一样。
 * 2 区域和 i，j 为中心的方块的内的和
 *
 * 思路二 二维前缀和数组 + 遍历
 */
public class N1314_m {

    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j=0; j < n; j++) {
                int cs = j - k, ce = j + k;
                int rs = i - k, re = i + k;
                cs = cs < 0 ? 0 : cs >= n ? n - 1 : cs;
                ce = ce < 0 ? 0 : ce >= n ? n - 1 : ce;
                rs = rs < 0 ? 0 : rs >= m ? m - 1 : rs;
                re = re < 0 ? 0 : re >= m ? m - 1 : re;
                for (int c = cs; c <= ce; c++) {
                    for (int r = rs; r <= re; r++) {
                        ans[i][j] += mat[r][c];
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
       int[][] mat = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
       int k = 1;
        int[][] ints = new N1314_m().matrixBlockSum(mat, k);
        for (int[] is : ints) {
            for (int i : is) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

}
