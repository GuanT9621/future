package sort;

/**
 * 快速排序，又称划分交换排序（partition-exchange sort）
 * https://zh.wikipedia.org/wiki/%E5%BF%AB%E9%80%9F%E6%8E%92%E5%BA%8F
 *
 * 算法介绍
 *  快速排序的基本思想：通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，
 *  则可分别对这两部分记录继续进行排序，以达到整个序列有序。
 *
 * 算法描述
 * 快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。具体算法描述如下：
 *  1 从数列中挑出一个元素，称为 “基准”（pivot）；
 *  2 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
 *    在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 *  3 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 *
 * 复杂度
 *  时间复杂度（平均）O(n log n)
 *  时间复杂度（最好）O(n log n)
 *  时间复杂度（最坏）O(n^2)
 *  空间复杂度 O(n log n)
 *  稳定性 不稳定
 *
 * 思路：二分法思路。（基准排序）（递归调用）
 * 冒泡排序的优化：每一趟冒泡将数据分割成两部分。
 *
 * 实现方式：
 * 挖坑法
 * 指针交换法
 */
class QuickSort {

    /**
     * wiki 简化版的指针交换法
     */
    static void qSort(int[] array, int startIndex, int endIndex) {
        if (startIndex >= endIndex || array == null || array.length <= 1) {
            return;
        }
        int left = startIndex, right = endIndex, pivot = array[startIndex];
        while (left <= right) {
            while (array[left] < pivot) {
                ++left;
            }
            while (array[right] > pivot) {
                --right;
            }
            if (left < right) {
                int t = array[left];
                array[left] = array[right];
                array[right] = t;
                ++left;
                --right;
            } else if (left == right) {
                ++left;
            }
        }
        qSort(array, startIndex, right);
        qSort(array, left, endIndex);
    }

    /**
     * 挖坑法 & 指针交换法
     */
    static void sort(int[] array, int startIndex, int endIndex) {
        if (startIndex >= endIndex) { // 递归结束条件：startIndex大等于endIndex的时候
            return;
        }
        int pivotIndex = partition2(array, startIndex, endIndex); // 得到基准元素位置
        sort(array, startIndex, pivotIndex - 1); // 用分治法递归数列的两部分
        sort(array, pivotIndex + 1, endIndex);
    }

    /**
     * 挖坑法
     * 左右换坑: partition方法则实现元素的移动，让数列中的元素依据自身大小，分别移动到基准元素的左右两边。在这里，我们使用移动方式是挖坑法。
     */
    private static int partition1(int[] array, int startIndex, int endIndex) {
        int left = startIndex, right = endIndex, pivot = array[startIndex];
        // 坑的位置，初始等于pivot的位置
        int index = startIndex;
        while (right >= left) {
            while (right >= left) {
                if (array[right] < pivot) {
                    array[left] = array[right];
                    index = right;
                    left++;
                    break;
                }
                right--;
            }
            while (right >= left) {
                if (array[left] > pivot) {
                    array[right] = array[left];
                    index = left;
                    right--;
                    break;
                }
                left++;
            }
        }
        array[index] = pivot;
        return index;
    }

    /**
     * 指针交换法
     * 和挖坑法相比，指针交换法在partition方法中进行的元素交换次数更少。
     */
    private static int partition2(int[] array, int startIndex, int endIndex) {
        int left = startIndex, right = endIndex, pivot = array[startIndex];
        while ( left != right) {
            while (left < right && array[right] > pivot) {
                right--;
            }
            while (left < right && array[left] <= pivot) {
                left++;
            }
            if (left < right) {
                int t = array[left];
                array[left] = array[right];
                array[right] = t;
            }
        }
        //pivot和指针重合点交换
        int p = array[left];
        array[left] = array[startIndex];
        array[startIndex] = p;
        return left;
    }

}
