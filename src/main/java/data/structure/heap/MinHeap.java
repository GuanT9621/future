package data.structure.heap;

import java.util.Arrays;

/**
 * 最小堆，顶部最小值
 * 最小堆的任何一个父节点的值，都小于或等于她的左右孩子的值
 *
 * 对于二叉堆有三种操作：
 * 1 插入节点
 * 2 删除节点
 * 3 构建二叉堆
 *
 */
public class MinHeap {
    private int[] array;
    private int size;

    public MinHeap(int k) {
        array = new int[k];
        size = 0;
    }

    /** 下沉 */
    private int fixDown() {
        int f = 0 ; //父节点的index
        int k ;  //较小者子节点的index
        while((k = (f << 1) + 1) < size) { //至少存在左子节点
            if(k < size - 1) {   //存在右子节点
                if (array[k] > array[k + 1]) k++; //左右子节点进行比较。
            }
            if(array[f] <= array[k]) break; //父节点小于较小者子节点，则找到合适的位置，退出循环
            int temp = array[f] ; array[f] = array[k]; array[k] = temp ;
            f = k ;
        }
        return f;
    }

    /** 上浮 */
    private void fixUp() {
        int ci = size - 1;
        int pi = (ci - 1) / 2;
        int temp = array[ci];
        while (ci > 0 && temp < array[pi]) {
            array[ci] = array[pi];
            ci = pi;
            pi = (pi - 1) / 2 ;
        }
        array[ci] = temp;
    }

    /** 移除并获取一个堆顶元素 */
    public int pop() {
        if(size <= 0)
            throw new IllegalStateException("不存在元素");
        int value = array[0];
        array[0] = array[--size] ; //将最后一个元素提到堆顶
        array[size] = 0 ; //清空最后一个的数据
        fixDown(); //下沉操作
        return value;
    }

    /** 添加一个元素在最小堆中上 */
    public void push(int item) {
        if (size >= array.length)
            array = Arrays.copyOf(array, size << 1) ;
        array[size++] = item;
        fixUp();
    }

    public static void main(String[] args) {
        MinHeap heap = new MinHeap(7);
        heap.push(6);
        heap.push(2);
        heap.push(7);
        heap.push(9);
        heap.push(1);

        heap.push(5);
        heap.push(10);
        heap.push(3);
        heap.push(8);
        System.out.println(Arrays.toString(heap.array));
    }
}
