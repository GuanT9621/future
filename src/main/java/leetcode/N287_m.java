package leetcode;

/**
 * https://leetcode.cn/problems/find-the-duplicate-number/
 * 寻找重复数
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 * 提示：
 * 1 <= n <= 10^5
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
 *
 * 进阶：
 * 如何证明 nums 中至少存在一个重复的数字?
 * 你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？
 *
 *
 * 思路
 * 1 以前遇到过数字范围在 n 的数组，将数字调到对应的下标即可。但是本题不允许修改数组。
 * 2 用一个数的二进制位来保存是否存在。将 1 右移 num 次，使用 ｜ 来修改数字，如果修改后的数字没有变化，则说明重复了。但是仅限 n < 63
 * 3 排序后，遍历。但是本题不允许修改数组，O(1)空间
 * 4 不排序，直接暴力查询。
 * 5 快慢指针
 * 6 二进制
 */
public class N287_m {

    public int findDuplicate5(int[] nums) {
        int slow = 0;
        int fast = 0;
        // 快慢指针找到相遇点
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        // 将 slow 放置起点 0，两个指针每次同时移动一步，相遇的点就是答案。
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public int findDuplicate6(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int bitMax = 31;
        while (((n - 1) >> bitMax) == 0) {
            bitMax -= 1;
        }
        for (int bit = 0; bit <= bitMax; bit++) {
            int x = 0;
            int y = 0;
            for (int i = 0; i < n; i++) {
                if ((nums[i] & (1 << bit)) != 0) {
                    x += 1;
                }
                if (i >= 1 && ((i & (1 << bit)) != 0)) {
                    y += 1;
                }
            }
            if (x > y) {
                ans |= 1 << bit;
            }
        }
        return ans;
    }


    public int findDuplicate2(int[] nums) {
        long db = 0;
        for (int num : nums) {
            long temp = db;
            db |= 1L << num;
            if (temp == db) {
                return num;
            }
        }
        return -1;
    }

    public int findDuplicate4(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] == nums[j]) {
                    return nums[i];
                }
            }
        }
        return -1;
    }

}
