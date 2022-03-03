package leetcode;

/**
 * https://leetcode-cn.com/problems/optimal-division/
 * 最优除法
 * 给定一组正整数，相邻的整数之间将会进行浮点除法操作。例如， [2,3,4] -> 2 / 3 / 4 。
 * 但是，你可以在任意位置添加任意数目的括号，来改变算数的优先级。你需要找出怎么添加括号，才能得到最大的结果，并且返回相应的字符串格式的表达式。
 * 你的表达式不应该含有冗余的括号。
 * 说明 输入数组的长度在 [1, 10] 之间。数组中每个元素的大小都在 [2, 1000] 之间。每个测试用例只有一个最优除法解。
 *
 * 思路 数学分析 递归
 * 1 要最大结果，那么要么分子变大，要么分母变小；在当前都是除法的情况下，分子只会越小，那么只能是分母变小。
 * 2 将分母迭代到条件一中，可以得知，分母中分子变小，然后继续迭代分母的分子的分子变小。
 * 3 这是就可以简化出一个模型，即 ans = x0 / (x1 / x2 / ... / xn)
 *
 */
public class N553_m {

    public String optimalDivision(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0] + "";
        }
        if (length == 2) {
            return nums[0] + "/" + nums[1];
        }
        StringBuilder sb = new StringBuilder();
        sb.append(nums[0]).append("/(");
        for (int i=1; i < length; i++) {
            sb.append(nums[i]);
            if (i != length-1) {
                sb.append("/");
            }
        }
        sb.append(")");
        return sb.toString();
    }

}
