package data.structure.heap;

/**
 * 最大堆(本例) / 最小堆
 * 最大堆：根结点的键值是所有堆结点键值中最大者的堆。
 * 最小堆：根结点的键值是所有堆结点键值中最小者的堆。
 *
 *              100 (0)
 *            /        \
 *         19(1)      36(2)
 *        /    \      /    \
 *     17(3)  3(4)  25(5)  1(6)
 *     /   \
 *   2(7)  7(8)
 *
 * 按照数组的形式为
 *  [100] [19] [36] [17] [3] [25] [1] [2] [7]
 *   0    1    2     3   4    5    6   7   8
 *
 * 公式：array[i] >= array[2i + 1] && array[i] >= array[2i + 2]
 *
 * 堆排序的基本思路：
 * a.将无需序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆;
 * b.将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
 * c.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
 * 其实就是每次拿走一下堆顶。（可以不用递归了，因为堆排序只需要堆顶就够了）
 *
 */
public class TopHeap {

    public static void sort(int[] array) {
        for (int i=0; i < array.length; i++) {
            reHeap(array, array.length - 1 - i);
            swap(array, 0, array.length - 1 - i);
        }
    }

    /**
     * https://www.jianshu.com/p/62b651797ad8
     *
     * 1 定位到最后一个非叶子节点
     * 2 依次每个非叶子节点与它的子节点比较，并交换。
     * 至此，已经完成了 "不完全最大堆"，要形成 "完全最大堆"需要在每次交换后向下判断。
     *
     * 3 提取出 "父节点比较左子/右子节点并交换" 作为独立方法，递归调用，即可实现"完全最大堆"
     */
    public static void reHeap(int[] array, int endIndex) {
        // 最后一个非叶子节点的位置
        int lastNonLeafIndex;
        // 左奇数，右偶数 // 下面过程可以简化为 lastNonLeafIndex = (endIndex - 2 + (endIndex % 2)) / 2;
        if (endIndex % 2 == 0) {
            lastNonLeafIndex = (endIndex - 2) / 2;
        } else {
            lastNonLeafIndex = (endIndex - 1) / 2;
        }
        for (int i = lastNonLeafIndex; i >= 0; i--) {
            reSub(array, i, endIndex);
        }
    }
    private static void reSub(int[] array, int i, int endIndex) {
        if ((2*i + 1) > endIndex)  {
            return;
        }
        if (array[2*i + 1] > array[i]) {
            swap(array, i, 2 * i + 1);
            reSub(array,2 * i + 1, endIndex);
        }
        if ((2*i + 2) > endIndex)  {
            return;
        }
        if (array[2*i + 2] > array[i]) {
            swap(array, i, 2 * i + 2);
            reSub(array, 2 * i + 2, endIndex);
        }
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static boolean compareAndAdd(int[] array, int in) {
        if (in > array[0]) { return false; }
        array[0] = in;
        reHeap(array, array.length - 1);
        return true;
    }

    public static void main(String[] args) {
        int[] array = new int[]{20, 25, 35, 30, 10, 15, 40, 45, 50};
        TopHeap.reHeap(array, array.length - 1);
        for (int i: array) { System.out.print(i + ", "); }

        System.out.println();
        TopHeap.compareAndAdd(array, 24);
        for (int i: array) { System.out.print(i + ", "); }

        System.out.println();
        int[] unsorted = new int[]{20, 25, 35, 30, 10, 15, 40, 45, 50, 100};
        TopHeap.sort(unsorted);
        for (int i: unsorted) { System.out.print(i + ", "); }
    }
}
