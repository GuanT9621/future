package sort;

/**
 * 归并排序（Merge Sort）
 *
 * 算法介绍
 * 归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 * 将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。
 *
 * 算法描述
 *  1 把长度为n的输入序列分成两个长度为n/2的子序列；
 *  2 对这两个子序列分别采用归并排序；
 *  3 将两个排序好的子序列合并成一个最终的排序序列。
 *
 * 复杂度
 *  时间复杂度（平均）O(n log n)
 *  时间复杂度（最好）O(n log n)
 *  时间复杂度（最坏）O(n log n)
 *  空间复杂度 O(n)
 *  稳定性 稳定
 *
 * 思路：分而治之。
 * 实现：
 *  递归法（Top-down）
 *   1 申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
 *   2 设定两个指针，最初位置分别为两个已经排序序列的起始位置
 *   3 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
 *   4 重复步骤3直到某一指针到达序列尾
 *   5 将另一序列剩下的所有元素直接复制到合并序列尾
 *
 *  迭代法（Bottom-up）
 *  原理如下（假设序列共有n个元素）：
 *   1 将序列每相邻两个数字进行归并操作，形成 {ceil(n/2)} {ceil(n/2)}个序列，排序后每个序列包含两/一个元素
 *   2 若此时序列数不是1个则将上述序列再次归并，形成 {ceil(n/4)} {ceil(n/4)}个序列，每个序列包含四/三个元素
 *   3 重复步骤2，直到所有元素排序完毕，即序列数为1
 */
class MergeSort {

    /**
     * 递归法（Top-down）
     */
    static void sort(int[] arr, int[] result, int start, int end) {
        if (start >= end)
            return;
        int len = end - start, mid = (len >> 1) + start;
        int start1 = start, end1 = mid;
        int start2 = mid + 1, end2 = end;

        sort(arr, result, start1, end1);
        sort(arr, result, start2, end2);

        int k = start;
        while (start1 <= end1 && start2 <= end2)
            result[k++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];

        while (start1 <= end1)
            result[k++] = arr[start1++];

        while (start2 <= end2)
            result[k++] = arr[start2++];

        for (k = start; k <= end; k++)
            arr[k] = result[k];
    }

    /**
     * 迭代法（Bottom-up）
     */
    static void sort(int[] array) {
        int[] orderedArr = new int[array.length];

        for (int i = 2; i < array.length * 2; i *= 2) {
            for (int j = 0; j < (array.length + i - 1) / i; j++) {
                int left = i * j;
                int mid = left + i / 2 >= array.length ? (array.length - 1) : (left + i / 2);
                int right = i * (j + 1) - 1 >= array.length ? (array.length - 1) : (i * (j + 1) - 1);
                int start = left, l = left, m = mid;
                while (l < mid && m <= right) {
                    if (array[l] < array[m]) {
                        orderedArr[start++] = array[l++];
                    } else {
                        orderedArr[start++] = array[m++];
                    }
                }
                while (l < mid)
                    orderedArr[start++] = array[l++];
                while (m <= right)
                    orderedArr[start++] = array[m++];

                System.arraycopy(orderedArr, left, array, left, right - left + 1);
            }
        }
    }
}
