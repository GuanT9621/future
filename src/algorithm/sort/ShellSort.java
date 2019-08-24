package algorithm.sort;

/**
 * 希尔排序（Shell Sort）递减增量排序算法
 *
 * 算法介绍
 * 1959年Shell发明，第一个突破O(n2)的排序算法，是简单插入排序的改进版。
 * 它与插入排序的不同之处在于，它会优先比较距离较远的元素。希尔排序又叫缩小增量排序。
 *
 * 希尔排序是基于插入排序的以下两点性质而提出改进方法的：
 * 插入排序在对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率
 * 但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位
 *
 * 算法描述
 * 先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，具体算法描述：
 *  1 选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
 *  2 按增量序列个数k，对序列进行k 趟排序；
 *  3 每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。
 *  仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
 *
 * 复杂度
 *  时间复杂度（平均）O(n^1.3)
 *  时间复杂度（最好）O(n)
 *  时间复杂度（最坏）O(n^2)
 *  空间复杂度 O(n)
 *  稳定性 不稳定
 *
 * 算法分析
 * 希尔排序的核心在于间隔序列的设定。
 * 既可以提前设定好间隔序列，也可以动态的定义间隔序列。
 * 动态定义间隔序列的算法是《算法（第4版）》的合著者Robert Sedgewick提出的。
 *
 * https://zh.wikipedia.org/wiki/%E5%B8%8C%E5%B0%94%E6%8E%92%E5%BA%8F
 */
class ShellSort {

    static void sort(int[] array) {
        int number = array.length / 2;
        int i, j, temp;
        while (number >= 1) {
            for (i = number; i < array.length; i++) {
                temp = array[i];
                j = i - number;
                while (j >= 0 && array[j] > temp) { //需要注意的是，這裡array[j] < temp將會使數组從大到小排序。
                    array[j + number] = array[j];
                    j = j - number;
                }
                array[j + number] = temp;
            }
            number = number / 2;
        }
    }

}
