package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/lucky-numbers-in-a-matrix/
 * 矩阵中的幸运数
 * 给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
 * 幸运数是指矩阵中满足同时下列两个条件的元素：
 * 在同一行的所有元素中最小
 * 在同一列的所有元素中最大
 *
 * 思路一 先求出行最小值，列最大值，然后匹配是否为同一个值。
 * 思路二 数学推理得知，返回所有幸运数，其实答案只有0个或者1个两种情况，
 *                  如果存在的话一定是所有最小值中的最大值，或者换一种说法，所有最大值中的最小值。
 */
public class N1380_e {

    public static List<Integer> luckyNumbers (int[][] matrix) {
        int[] rowMin = new int[matrix.length];
        for (int i=0; i<matrix.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int num : matrix[i]) {
                min = Math.min(min, num);
            }
            rowMin[i] = min;
        }
        int[] columnMax = new int[matrix[0].length];
        for (int j=0; j<matrix[0].length; j++) {
            int max =Integer.MIN_VALUE;
            for (int i=0; i<matrix.length; i++) {
                max = Math.max(max, matrix[i][j]);
            }
            columnMax[j] = max;
        }
        List<Integer> ans = new ArrayList<>();
        for (int j : rowMin) {
            for (int max : columnMax) {
                if (j == max) {
                    ans.add(j);
                }
            }
        }
        return ans;
    }

    public static List<Integer> luckyNumbers2 (int[][] matrix) {
        int minMax = Integer.MIN_VALUE;
        for (int[] row : matrix) {
            int min = Integer.MAX_VALUE;
            for (int i : row) {
                min = Math.min(min, i);
            }
            minMax = Math.max(min, minMax);
        }
        int maxMin = Integer.MAX_VALUE;
        for (int i=0; i<matrix[0].length; i++)  {
            int max = Integer.MIN_VALUE;
            for (int j=0; j<matrix.length; j++) {
                max = Math.max(max, matrix[j][i]);
            }
            maxMin = Math.min(max, maxMin);
        }
        List<Integer> ans = new ArrayList<>();
        if (minMax == maxMin) {
            ans.add(maxMin);
        }
        return ans;
    }

    public static void main(String[] args) {
//        int[][] matrix = new int[][] {{3,7,8},{9,11,13},{15,16,17}};
//        int[][] matrix = new int[][] {{1,10,4,2},{9,3,8,7},{15,16,17,12}};
        int[][] matrix = new int[][] {{3,6},{7,1},{5,2},{4,8}};
        List<Integer> integers = luckyNumbers2(matrix);
        for (Integer integer : integers)
            System.out.println(integer);
    }
}
