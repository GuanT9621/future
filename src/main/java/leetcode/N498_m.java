package leetcode;

/**
 * https://leetcode.cn/problems/diagonal-traverse/
 * 对角线遍历
 * 给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
 * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,4,7,5,3,6,8,9]
 *
 * 思路 遍历
 * 亲手画一画会发现 m 和 n 方向总是 m递增n递减 或者 m递减n递增。
 * 边界为 0、2 时，坐标依次为
 * (0,0)
 * (0,1) (1,0)
 * (2,0) (1,1) (0,2)
 * (1,2) (2,1)
 * (2,2)
 * 这里有两个细节
 * 1 决定前进方向的一个变量
 * 2 触碰边界时下一步的处理
 *
 * 边界处理：
 * x小于0 y小于n 右移，y小于0 x小于m 下移
 * x小于0 y等于n 下移，y小于0 x等于m 右移
 */
public class N498_m {

    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] ans = new int[m * n];
        int flag = -1;
        for (int i = 0, x = 0, y = 0; i < m * n; i++) {
            ans[i] = mat[x][y];
            x += flag;
            y -= flag;
            // 定方向
            if (x < 0 || y == n) {
                flag = 1;
            }
            if (y < 0 || x == m) {
                flag = -1;
            }
            // 掉头
            if (x < 0 && y < n) {
                x = 0;
            }
            if (y < 0 && x < m) {
                y = 0;
            }
            if (y == n) {
                x += 2;
                y = n - 1;
            }
            if (x == m) {
                y += 2;
                x = m - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // [1,2,4,7,5,3,6,8,9]
        int[] diagonalOrder = new N498_m().findDiagonalOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        for (int i : diagonalOrder) {
            System.out.print(i + " ");
        }
    }

}
