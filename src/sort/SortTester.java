package sort;

public class SortTester {

    private static final String COMMA = ",";

    private static void print(int[] array) {
        for (int i: array) {
            System.out.print(i);
            System.out.print(COMMA);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[] {5, 2, 8, 3, 7, 6, 9, 1, 0, 4};
//        int[] array = new int[] {18, 41, 39, 66, 74, 45, 54, 19, 67, 29, 64, 3, 47, 71, 50, 59, 31, 81, 76, 5, 14, 79, 69, 34, 9, 48, 72, 95, 82, 65, 13, 92, 85, 100, 33, 15, 25, 86, 73, 75, 63, 98, 38, 26, 35, 56, 20, 70, 10, 7, 55, 62, 24, 89, 83, 94, 77, 1, 36, 28, 30, 16, 90, 21, 93, 43, 78, 80, 88, 27, 2, 46, 23, 60, 37, 57, 68, 58, 42, 8, 17, 12, 87, 53, 99, 49, 96, 4, 84, 40, 61, 6, 32, 11, 0, 97, 44, 51, 52, 22};

        System.out.println("Array长度：" + array.length);

        System.out.print("原数组：");
        print(array);
        System.out.println();

        long s1 = System.nanoTime();
        System.out.print("冒泡排序：");
        print(BubbleSort.sort(array));
        System.out.println(" 耗时：" + (System.nanoTime() - s1));

        long s2 = System.nanoTime();
        System.out.print("快速排序：");
        print(QuickSort.sort(array, 0 , array.length -1));
        System.out.println(" 耗时：" + (System.nanoTime() - s2));
    }

}
