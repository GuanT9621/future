package leetcode;

/**
 * https://leetcode-cn.com/problems/spiral-matrix-ii/
 * 螺旋矩阵 II
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * 条件 1 <= n <= 20
 *
 * 思路 层层模拟
 * 1 只考虑最外面一圈，将 x，y 按照 右，下，左，上的顺序填充数据
 * 2 将起始位置移动加一 ，填充内部
 * 3 当起始位置已经存在数据时说明已经被填充过了，结束。
 */
public class N59_m {

    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        if (n == 1) {
            ans[0][0] = 1;
            return ans;
        }
        int put = 0, s = 0, e = n - 1;
        while (ans[s][s] == 0) {
            for (int y=s; y <= e; y++) ans[s][y] = ++put;
            for (int x=s + 1; x <= e; x++) ans[x][e] = ++put;
            for (int y=e - 1; y >= s; y--) ans[e][y] = ++put;
            for (int x=e - 1; x > s; x--) ans[x][s] = ++put;
            s++;
            e--;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] ints = new N59_m().generateMatrix(3);
        for (int[] ii : ints) {
            for (int i : ii) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
