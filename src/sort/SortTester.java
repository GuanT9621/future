package sort;

public class SortTester {

    private static final String COMMA = ",";

    private static void print(int[] array) {
        for (int i: array) {
            System.out.print(i);
            System.out.print(COMMA);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] array = new int[] {5, 2, 8, 3, 7, 6, 9, 1, 0, 4};

        System.out.println("Array长度：" + array.length);

        System.out.print("原数组：");
        print(array);

        System.out.print("冒泡排序：");
        print(BubbleSort.sort(array));

        System.out.print("快速排序：");
        print(QuickSort.sort(array, 0 , array.length -1));
    }

}
