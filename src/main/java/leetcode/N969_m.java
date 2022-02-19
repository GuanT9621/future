package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/pancake-sorting/
 * 煎饼排序
 * 给你一个整数数组 arr ，请使用 煎饼翻转 完成对数组的排序。
 * 一次煎饼翻转的执行过程如下：1、选择一个整数 k ，1 <= k <= arr.length 2、反转子数组 arr[0...k-1]（下标从 0 开始）
 * 例如，arr = [3,2,1,4] ，选择 k = 3 进行一次煎饼翻转，反转子数组 [3,2,1] ，得到 arr = [1,2,3,4] 。
 * 以数组形式返回能使 arr 有序的煎饼翻转操作所对应的 k 值序列。任何将数组排序且翻转次数在 10 * arr.length 范围内的有效答案都将被判断为正确。
 * 条件：1 <= arr.length <= 100   1 <= arr[i] <= arr.length   arr 中的所有整数互不相同（即，arr 是从 1 到 arr.length 整数的一个排列）
 *
 * 思路
 * 1、关键点：每次从下标0开始反转
 * 2、那么最后一次结果为 1、2、3、4、5、6；
 *    则上一次结果为 3、2、1、4、5、6，前半部分逆序，后半部分顺序。
 *    则上上一次结果为 6、5、4、1、2、3，前半部分逆序，后半部分顺序。
 * 3、每次排序将最大值放到最后：先将它放到首，再从最后一个反转，将它放到最后。
 */
public class N969_m {

    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> ans = new ArrayList<>();
        int length = arr.length;
        while (length > 0) {
            int max = 0;
            for (int i = 0; i < length; i++) {
                if (arr[i] > arr[max]) {
                    max = i;
                }
            }
            if (max == length-1) { // 本来就在尾部
                length--;
            } else {
                ans.add(max+1);
                rev(arr, max);
                ans.add(length);
                rev(arr, length-1);
                length--;
            }
        }
        return ans;
    }

    private void rev(int[] arr, int end) {
        int s = 0;
        int e = end;
        while (s < e) {
            int temp = arr[e];
            arr[e] = arr[s];
            arr[s] = temp;
            s++;
            e--;
        }
    }

    public static void main(String[] args) {
        int[] ints = {3, 2, 4, 1};
//        int[] ints = {1,2,3};
        List<Integer> list = new N969_m().pancakeSort(ints);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

}
