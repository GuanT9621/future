package sort;

/**
 * 快速排序，又称划分交换排序（partition-exchange sort）
 *
 * 算法介绍
 * 快速排序的基本思想：通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，
 * 则可分别对这两部分记录继续进行排序，以达到整个序列有序。
 *
 * 算法描述
 * 快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。具体算法描述如下：
 *   1 从数列中挑出一个元素，称为 “基准”（pivot）；
 *   2 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
 *     在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 *   3 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 *
 * 复杂度
 * 时间复杂度（平均）O(nlog2n)
 * 时间复杂度（最好）O(nlog2n)
 * 时间复杂度（最坏）O(n^2)
 * 空间复杂度 O(nlog2n)
 * 稳定性 不稳定
 *
 * 思路：二分法思路。（基准排序）（递归调用）
 *      冒泡排序的优化：每一趟冒泡将数据分割成两部分。
 *
 * 实现方式：
 *   挖坑法
 *   指针交换法
 */
class QuickSort {

    static int[] sort(int[] array, int head, int tail) {
        return sort1(array, head, tail);
    }

    /**
     * 挖坑法
     */
    static int[] sort1(int[] array, int head, int tail) {
        if (head >= tail || array == null || array.length <= 1) {
            return array;
        }
        int i = head, j = tail, pivot = array[(head + tail) / 2];
        while (i <= j) {
            while (array[i] < pivot) {
                ++i;
            }
            while (array[j] > pivot) {
                --j;
            }
            if (i < j) {
                int t = array[i];
                array[i] = array[j];
                array[j] = t;
                ++i;
                --j;
            } else if (i == j) {
                ++i;
            }
        }
        sort(array, head, j);
        sort(array, i, tail);
        return array;
    }

    static int[] sort2(int[] array, int head, int tail) {
        return array;
    }

}
