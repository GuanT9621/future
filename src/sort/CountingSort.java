package sort;

/**
 * 计数排序（Counting Sort）
 *  计数排序不是基于比较的排序算法，其核心在于将输入的数据值转化为键存储在额外开辟的数组空间中。
 *  作为一种线性时间复杂度的排序，计数排序要求输入的数据必须是有确定范围的整数。
 *
 * 算法描述
 *  1 找出待排序的数组中最大和最小的元素；
 *  2 统计数组中每个值为i的元素出现的次数，存入数组C的第i项；
 *  3 对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）；
 *  4 反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1。
 *
 * 复杂度
 *  时间复杂度（平均）O(n + k)
 *  时间复杂度（最好）O(n + k)
 *  时间复杂度（最坏）O(n + k)
 *  空间复杂度 O(n + k)
 *  稳定性 稳定
 *
 * 算法分析
 *  计数排序是一个稳定的排序算法。当输入的元素是 n 个 0到 k 之间的整数时，时间复杂度是O(n+k)，空间复杂度也是O(n+k)，其排序速度快于任何比较排序算法。
 *  当k不是很大并且序列比较集中时，计数排序是一个很有效的排序算法。
 *
 *  https://zh.wikipedia.org/wiki/%E8%AE%A1%E6%95%B0%E6%8E%92%E5%BA%8F
 */
class CountingSort {

    static int[] sort(int[] array) {
        return countingSort(array);
    }

    static int[] countingSort(int[] A) {
        int[] B = new int[A.length];
        // 假设A中的数据a'有，0<=a' && a' < k并且k=101
        int k = 101;

        int[] C = new int[k];
        // 计数
        for (int j = 0; j < A.length; j++) {
            int a = A[j];
            C[a] += 1;
        }
        // 求计数和
        for (int i = 1; i < k; i++) {
            C[i] = C[i] + C[i - 1];
        }
        // 整理
        for (int j = A.length - 1; j >= 0; j--) {
            int a = A[j];
            B[C[a] - 1] = a;
            C[a] -= 1;
        }
        return B;
    }

    //针对c数组的大小，优化过的计数排序
    public static int[] countSort(int []a){
        int b[] = new int[a.length];
        int max = a[0], min = a[0];
        for(int i : a){
            if(i > max){
                max = i;
            }
            if(i < min){
                min = i;
            }
        }
        //这里k的大小是要排序的数组中，元素大小的极值差+1
        int k = max - min + 1;
        int c[] = new int[k];
        for(int i = 0; i < a.length; ++i){
            c[a[i]-min] += 1;//优化过的地方，减小了数组c的大小
        }
        for(int i = 1; i < c.length; ++i){
            c[i] = c[i] + c[i-1];
        }
        for(int i = a.length-1; i >= 0; --i){
            b[--c[a[i]-min]] = a[i];//按存取的方式取出c的元素
        }
        return b;
    }

}
