package sort;

/**
 * 快速排序，又称划分交换排序（partition-exchange sort）
 * <p>
 * 算法介绍
 * 快速排序的基本思想：通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，
 * 则可分别对这两部分记录继续进行排序，以达到整个序列有序。
 * <p>
 * 算法描述
 * 快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。具体算法描述如下：
 * 1 从数列中挑出一个元素，称为 “基准”（pivot）；
 * 2 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
 * 在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 * 3 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 * <p>
 * 复杂度
 * 时间复杂度（平均）O(nlog2n)
 * 时间复杂度（最好）O(nlog2n)
 * 时间复杂度（最坏）O(n^2)
 * 空间复杂度 O(nlog2n)
 * 稳定性 不稳定
 * <p>
 * 思路：二分法思路。（基准排序）（递归调用）
 * 冒泡排序的优化：每一趟冒泡将数据分割成两部分。
 * <p>
 * 实现方式：
 * 挖坑法
 * 指针交换法
 */
class QuickSort {

    static int[] sort(int[] array, int head, int tail) {
        return sort1(array, head, tail);
    }

    /**
     * wiki 最优算法
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
        sort1(array, head, j);
        sort1(array, i, tail);
        return array;
    }


    /**
     * 挖坑法
     * 左右换坑: partition方法则实现元素的移动，让数列中的元素依据自身大小，分别移动到基准元素的左右两边。在这里，我们使用移动方式是挖坑法。
     */
    public static void quickSort1(int[] arr, int startIndex, int endIndex) {
        // 递归结束条件：startIndex大等于endIndex的时候
        if (startIndex >= endIndex) {
            return;
        }
        // 得到基准元素位置
        int pivotIndex = partition1(arr, startIndex, endIndex);
        // 用分治法递归数列的两部分
        quickSort1(arr, startIndex, pivotIndex - 1);
        quickSort1(arr, pivotIndex + 1, endIndex);
    }

    private static int partition1(int[] arr, int startIndex, int endIndex) {
        // 取第一个位置的元素作为基准元素
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;
        // 坑的位置，初始等于pivot的位置
        int index = startIndex;
        //大循环在左右指针重合或者交错时结束
        while (right >= left) {
            //right指针从右向左进行比较
            while (right >= left) {

                if (arr[right] < pivot) {
                    arr[left] = arr[right];
                    index = right;
                    left++;
                    break;
                }
                right--;
            }
            //left指针从左向右进行比较
            while (right >= left) {
                if (arr[left] > pivot) {
                    arr[right] = arr[left];
                    index = left;
                    right--;
                    break;
                }
                left++;
            }
        }
        arr[index] = pivot;
        return index;
    }

    /**
     * 指针交换法
     * 和挖坑法相比，指针交换法在partition方法中进行的元素交换次数更少。
     */
    public static void quickSort2(int[] arr, int startIndex, int endIndex) {
        // 递归结束条件：startIndex大等于endIndex的时候
        if (startIndex >= endIndex) {
            return;
        }
        // 得到基准元素位置
        int pivotIndex = partition2(arr, startIndex, endIndex);
        // 根据基准元素，分成两部分递归排序
        quickSort2(arr, startIndex, pivotIndex - 1);
        quickSort2(arr, pivotIndex + 1, endIndex);
    }

    private static int partition2(int[] arr, int startIndex, int endIndex) {
        // 取第一个位置的元素作为基准元素
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;
        while ( left != right) {
            //控制right指针比较并左移
            while (left<right && arr[right] > pivot){
                right--;
            }
            //控制right指针比较并右移
            while (left<right && arr[left] <= pivot) {
                left++;
            }
            //交换left和right指向的元素
            if (left<right) {
                int p = arr[left];
                arr[left] = arr[right];
                arr[right] = p;
            }
        }
        //pivot和指针重合点交换
        int p = arr[left];
        arr[left] = arr[startIndex];
        arr[startIndex] = p;
        return left;
    }

}
