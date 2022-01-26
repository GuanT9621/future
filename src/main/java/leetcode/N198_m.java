package leetcode;

/**
 * https://leetcode-cn.com/problems/house-robber/
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * 例如 输入：[2,7,9,3,1] 输出：2 + 9 + 1 = 12
 *
 * 正确答案：动态规划
 * 1 状态转移方程 dp[i] = max(dp[i−2] + nums[i], dp[i−1])
 * 2 边界条件为：dp[0] = nums[0] 只有一间房屋，则偷窃该房屋
 *             dp[1] = max(nums[0], nums[1]) 只有两间房屋，选择其中金额较高的房屋进行偷窃
 * 3 最终的答案即为 dp[n−1]，其中 n 是数组的长度。
 */
public class N198_m {

    public static int rob(int[] nums) {
        if (1 == nums.length)
            return nums[0];
        if (2 == nums.length)
            return Math.max(nums[0], nums[1]);

        int first = nums[0];
        int second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

    public static void main(String[] args) {
        int[] in = new int[]{10,1,6,100,90,2};
//        int[] in = new int[] {1,2,3,4,5,6};
        int rob = rob(in);
        System.out.println(rob);
    }

}
