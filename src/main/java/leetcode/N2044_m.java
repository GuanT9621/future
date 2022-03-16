package leetcode;

/**
 * https://leetcode-cn.com/problems/count-number-of-maximum-bitwise-or-subsets/
 * 统计按位或能得到最大值的子集数目
 * 给你一个整数数组 nums ，请你找出 nums 子集 按位或 可能得到的 最大值 ，并返回按位或能得到最大值的 不同非空子集的数目 。
 * 如果数组 a 可以由数组 b 删除一些元素（或不删除）得到，则认为数组 a 是数组 b 的一个 子集 。如果选中的元素下标位置不一样，则认为两个子集 不同 。
 * 对数组 a 执行 按位或 ，结果等于 a[0] OR a[1] OR ... OR a[a.length - 1]（下标从 0 开始）。
 * 提示：  1 <= nums.length <= 16      1 <= nums[i] <= 10^5
 *
 * 思路 暴力-遍历
 *
 * 思路 回溯 - dfs
 * 方法一的缺点是，计算不同状态的按位或的值，都需要消耗 O(n) 的时间。
 * 这一步部分可以进行优化。每个长度为 n 比特的状态的按位或的值，都是可以在长度为 n - 1 比特的状态的按位或的值上计算出来的，
 * 而这个计算只需要消耗常数时间。以此类推，边界情况是长度为 0 比特的状态的按位或的值。
 * 我们定义一个搜索函数，参数 pos 表示当前下标，orVal 表示当前下标之前的某个子集按位或值，这样就可以保存子集按位或的值的信息，
 * 并根据当前元素选择与否更新 orVal。当搜索到最后位置时，更新最大值和子集个数。
 *
 */
public class N2044_m {

    public int countMaxOrSubsets(int[] nums) {
        int ans = 0;
        long max = 0;
        int n = nums.length;
        int total = 1 << n;
        for (int i=0; i < total; i++) {
            long cur = 0;
            int mark = i;
            for (int num : nums) {
                if ((mark & 1) == 1) {
                    cur |= num;
                }
                mark >>= 1;
            }
            if (max == cur) {
                ans++;
            } else if (max < cur) {
                max = cur;
                ans = 1;
            }
        }
        return ans;
    }


    int[] nums;
    int maxOr;
    int cnt;

    public int countMaxOrSubsets2(int[] nums) {
        this.nums = nums;
        this.maxOr = 0;
        this.cnt = 0;
        dfs(0, 0);
        return cnt;
    }

    public void dfs(int pos, int orVal) {
        if (pos == nums.length) {
            if (orVal > maxOr) {
                maxOr = orVal;
                cnt = 1;
            } else if (orVal == maxOr) {
                cnt++;
            }
            return;
        }
        dfs(pos + 1, orVal | nums[pos]);
        dfs(pos + 1, orVal);
    }

}
