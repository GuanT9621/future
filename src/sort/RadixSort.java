package sort;

/**
 * 基数排序（Radix Sort）
 *  基数排序是按照低位先排序，然后收集；再按照高位排序，然后再收集；依次类推，直到最高位。
 *  有时候有些属性是有优先级顺序的，先按低优先级排序，再按高优先级排序。最后的次序就是高优先级高的在前，高优先级相同的低优先级高的在前。
 *
 * 算法描述
 *  1 取得数组中的最大数，并取得位数；
 *  2 array为原始数组，从最低位开始取每个位组成radix数组；
 *  3 对radix进行计数排序（利用计数排序适用于小范围数的特点）；
 *
 * 复杂度
 *  时间复杂度（平均）O(n * k)
 *  时间复杂度（最好）O(n * k)
 *  时间复杂度（最坏）O(n * k)
 *  空间复杂度 O(n + k)
 *  稳定性 稳定
 *
 * 算法分析
 *  基数排序基于分别排序，分别收集，所以是稳定的。
 *  但基数排序的性能比桶排序要略差，每一次关键字的桶分配都需要O(n)的时间复杂度，而且分配之后得到新的关键字序列又需要O(n)的时间复杂度。
 *  假如待排数据可以分为d个关键字，则基数排序的时间复杂度将是O(d*2n) ，当然d要远远小于n，因此基本上还是线性级别的。
 *
 *  基数排序的空间复杂度为O(n+k)，其中k为桶的数量。一般来说n>>k，因此额外空间需要大概n个左右。
 *
 *  https://zh.wikipedia.org/wiki/%E5%9F%BA%E6%95%B0%E6%8E%92%E5%BA%8F
 */
public class RadixSort {

    static int[] sort(int[] array) {
        sort1(array, 3);
        return array;
    }

    static void sort1(int[] array, int d) {//d表示最大的数有多少位
        int k = 0;
        int n = 1;
        int m = 1; //控制键值排序依据在哪一位
        int[][]temp = new int[10][array.length]; //数组的第一维表示可能的余数0-9
        int[]order = new int[10]; //数组orderp[i]用来表示该位是i的数的个数
        while(m <= d)
        {
            for(int i = 0; i < array.length; i++)
            {
                int lsd = ((array[i] / n) % 10);
                temp[lsd][order[lsd]] = array[i];
                order[lsd]++;
            }
            for(int i = 0; i < 10; i++)
            {
                if(order[i] != 0)
                    for(int j = 0; j < order[i]; j++)
                    {
                        array[k] = temp[i][j];
                        k++;
                    }
                order[i] = 0;
            }
            n *= 10;
            k = 0;
            m++;
        }
    }
}
