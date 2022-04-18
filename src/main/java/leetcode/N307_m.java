package leetcode;

/**
 * https://leetcode-cn.com/problems/range-sum-query-mutable/
 * 区域和检索 - 数组可修改
 * 1 给你一个数组 nums ，请你完成两类查询。
 * 2 其中一类查询要求 更新 数组 nums 下标对应的值
 * 3 另一类查询要求返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 ，其中 left <= right
 * 实现 NumArray 类：
 * NumArray(int[] nums) 用整数数组 nums 初始化对象
 * void update(int index, int val) 将 nums[index] 的值 更新 为 val
 * int sumRange(int left, int right) 返回数组 nums 中索引 left(包含) 和索引 right (包含)之间的nums元素的和
 *
 * 思路 前缀和 - 超时了
 * 1 记录前缀和到数组中
 * 2 更新重新计算index以后的值
 * 3 返回前缀和的差值即可
 *
 * 思路 线段树
 *
 */
public class N307_m {

    class NumArray {

        int n;
        int[] nums;
        int[] sum;

        public NumArray(int[] nums) {
            this.n = nums.length;
            this.nums = nums;
            this.sum = new int[n];
            sum[0] = nums[0];
            for (int i=1; i < n; i++) {
                sum[i] = sum[i - 1] + nums[i];
            }
        }

        public void update(int index, int val) {
            int old = nums[index];
            nums[index] = val;
            int p = val - old;
            for (int i = index; i < n; i++) {
                sum[i] = sum[i] + p;
            }
        }

        public int sumRange(int left, int right) {
            if (left == 0) {
                return sum[right];
            }
            return sum[right] - sum[left - 1];
        }
    }

    class NumArray2 {

        int n;
        int[] nums;
        int[] sum;

        public NumArray2(int[] nums) {
            this.n = nums.length;
            this.nums = nums;
            this.sum = new int[n];
            sum[0] = nums[0];
            for (int i=1; i < n; i++) {
                sum[i] = sum[i - 1] + nums[i];
            }
        }

        public void update(int index, int val) {
            int old = nums[index];
            nums[index] = val;
            int p = val - old;
            for (int i = index; i < n; i++) {
                sum[i] = sum[i] + p;
            }
        }

        public int sumRange(int left, int right) {
            if (left == 0) {
                return sum[right];
            }
            return sum[right] - sum[left - 1];
        }
    }

    public static void main(String[] args) {
        // [null,null,null,null,6,null,32,null,26,27,null]
        NumArray array = new N307_m().new NumArray(new int[]{ 7, 2, 7, 2, 0});
        array.update(4, 6);
        array.update(0, 2);
        array.update(0, 9);
        System.out.println(array.sumRange(4, 4));
        array.update(3, 8);
        System.out.println(array.sumRange(0, 4));
        array.update(4, 1);
        System.out.println(array.sumRange(0, 3));
        System.out.println(array.sumRange(0, 4));
        array.update(0, 4);
    }
}
