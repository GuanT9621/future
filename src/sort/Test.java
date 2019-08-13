package sort;

public class Test {
    private static final String TITEL_FORMAT = "\n%-10s %-15s";
    private static final String SPACE_FORMAT = " %d";

    private static void formatPrint(String sort, String time, int[] array) {
        System.out.format(TITEL_FORMAT, sort, time);
        for (int i : array) {
            System.out.format(SPACE_FORMAT, i);
        }
    }

    public static void main(String[] args) {
//        int[] array = new int[] {5, 2, 8, 3, 7, 6, 9, 1, 0, 4};
        int[] array = new int[] {18, 41, 39, 66, 74, 45, 54, 19, 67, 29, 64, 3, 47, 71, 50, 59, 31, 81, 76, 5, 14, 79, 69, 34, 9, 48, 72, 95, 82, 65, 13, 92, 85, 100, 33, 15, 25, 86, 73, 75, 63, 98, 38, 26, 35, 56, 20, 70, 10, 7, 55, 62, 24, 89, 83, 94, 77, 1, 36, 28, 30, 16, 90, 21, 93, 43, 78, 80, 88, 27, 2, 46, 23, 60, 37, 57, 68, 58, 42, 8, 17, 12, 87, 53, 99, 49, 96, 4, 84, 40, 61, 6, 32, 11, 0, 97, 44, 51, 52, 22};


        formatPrint("原始数组", "Array长度:" + array.length, array);

        long s1 = System.nanoTime();
        int[] array1 = array.clone();
        BubbleSort.sort(array1);
        formatPrint("冒泡排序", "耗时:" + (System.nanoTime() - s1), array1);

        long s2 = System.nanoTime();
        int[] array2 = array.clone();
        QuickSort.sort(array2, 0 , array2.length -1);
        formatPrint("快速排序", "耗时:" + (System.nanoTime() - s2), array2);

        long s3 = System.nanoTime();
        int[] array3 = array.clone();
        InsertionSort.sort(array3);
        formatPrint("插入排序", "耗时:" + (System.nanoTime() - s3), array3);

        long s4 = System.nanoTime();
        int[] array4 = array.clone();
        MergeSort.sort(array4);
        formatPrint("归并排序", "耗时:" + (System.nanoTime() - s4), array4);

        long s5 = System.nanoTime();
        int[] array5 = array.clone();
        ShellSort.sort(array5);
        formatPrint("希尔排序", "耗时:" + (System.nanoTime() - s5), array5);

        long s6 = System.nanoTime();
        int[] array6 = array.clone();
        SelectionSort.sort(array6);
        formatPrint("选择排序", "耗时:" + (System.nanoTime() - s6), array6);

        long s7 = System.nanoTime();
        int[] array7 = array.clone();
        new HeapSort(array7).sort();
        formatPrint("堆排序法", "耗时:" + (System.nanoTime() - s7), array7);

        long s8 = System.nanoTime();
        int[] array8 = array.clone();
        int[] result = CountingSort.sort(array8);
        formatPrint("计数排序", "耗时:" + (System.nanoTime() - s8), result);

        long s9 = System.nanoTime();
        int[] array9 = array.clone();
        RadixSort.sort(array9, 3);
        formatPrint("基数排序", "耗时:" + (System.nanoTime() - s9), array9);

        long s10 = System.nanoTime();
        int[] array10 = array.clone();
        BucketSort.sort(array10);
        formatPrint("桶排序法", "耗时:" + (System.nanoTime() - s10), array10);

    }

}
