package sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 桶排序（Bucket Sort）
 * 桶排序是计数排序的升级版。它利用了函数的映射关系，高效与否的关键就在于这个映射函数的确定。
 *
 * 桶排序 (Bucket sort)的工作的原理：
 * 假设输入数据服从均匀分布，将数据分到有限数量的桶里，每个桶再分别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排）。
 *
 * 桶排序是鸽巢排序的一种归纳结果。桶排序并不是比较排序，他不受到 O(n log n) 下限的影响。
 *
 * 算法描述
 *  1 设置一个定量的数组当作空桶；
 *  2 遍历输入数据，并且把数据一个一个放到对应的桶里去；
 *  3 对每个不是空的桶进行排序；
 *  4 从不是空的桶里把排好序的数据拼接起来。
 *
 * 复杂度
 *  时间复杂度（平均）O(n + k)
 *  时间复杂度（最好）O(n)
 *  时间复杂度（最坏）O(n^2)
 *  空间复杂度 O(n + k)
 *  稳定性 稳定
 *
 * 算法分析
 * 桶排序最好情况下使用线性时间O(n)，桶排序的时间复杂度，取决与对各个桶之间数据进行排序的时间复杂度，因为其它部分的时间复杂度都为O(n)。
 * 很显然，桶划分的越小，各个桶之间的数据越少，排序所用的时间也会越少。但相应的空间消耗就会增大。
 */
class BucketSort {

    static void sort(int[] array) {
        int max = array[0], min = array[0];
        for (int a : array) {
            if (max < a)
                max = a;
            if (min > a)
                min = a;
        }
        // 該值也可根據實際情況選擇
        int bucketNum = max / 10 - min / 10 + 1;
        List buckList = new ArrayList<List<Integer>>();
        // create bucket
        for (int i = 1; i <= bucketNum; i++) {
            buckList.add(new ArrayList<Integer>());
        }
        // push into the bucket
        for (int i = 0; i < array.length; i++) {
            int index = indexFor(array[i], min, 10);
            ((ArrayList<Integer>) buckList.get(index)).add(array[i]);
        }
        ArrayList<Integer> bucket = null;
        int index = 0;
        for (int i = 0; i < bucketNum; i++) {
            bucket = (ArrayList<Integer>) buckList.get(i);
            insertSort(bucket);
            for (int k : bucket) {
                array[index++] = k;
            }
        }

    }

    static int indexFor(int a, int min, int step) {
        return (a - min) / step;
    }

    // 把桶內元素插入排序
    static void insertSort(List<Integer> bucket) {
        for (int i = 1; i < bucket.size(); i++) {
            int temp = bucket.get(i);
            int j = i - 1;
            for (; j >= 0 && bucket.get(j) > temp; j--) {
                bucket.set(j + 1, bucket.get(j));
            }
            bucket.set(j + 1, temp);
        }
    }
}
