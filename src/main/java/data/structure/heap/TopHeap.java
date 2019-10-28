package data.structure.heap;

import java.lang.reflect.Array;

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
 * 其实也可以用堆排序实现大顶堆
 */
public class TopHeap {
    private int[] array;

    public TopHeap(int[] array) {
        this.array = array;
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
    public synchronized void reHeap() {
        int allIndex = array.length - 1;
        int endIndex; //最后一个非叶子节点的位置
        // 左奇数，右偶数
        if (allIndex / 2 == 0) {
            endIndex = (allIndex - 2) / 2;
        } else {
            endIndex = (allIndex - 1) / 2;
        }
        for (int i = endIndex; i >= 0; i--) {
            reSub(i);
        }
    }
    private void reSub(int i) {
        if ((2*i + 1) > array.length - 1)  {
            return;
        }
        if (array[2*i + 1] > array[i]) {
            swap(array, i, 2 * i + 1);
            reSub(2 * i + 1);
        }
        if (array[2*i + 2] > array[i]) {
            swap(array, i, 2 * i + 2);
            reSub(2 * i + 2);
        }
    }
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public synchronized boolean compareAndAdd(int in) {
        if (in > array[0]) { return false; }
        array[0] = in;
        reHeap();
        return true;
    }

    public static void main(String[] args) {
        int[] array = new int[]{20, 25, 35, 30, 10, 15, 40, 45, 50};

        TopHeap topHeap = new TopHeap(array);
        topHeap.reHeap();
        for (int i: array) {
            System.out.print(i + ", ");
        }

        System.out.println();
        topHeap.compareAndAdd(24);
        for (int i: array) {
            System.out.print(i + ", ");
        }
    }
}
