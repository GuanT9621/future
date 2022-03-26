package leetcode;

/**
 * 双指针
 * 双指针卡尺
 * 滑动窗口
 *      例如 N1984 N438
 * 栈的活用
 *      例如 S_Calc
 * 单调栈
 *      例如 S_firstMax N2104 N1475
 * 双栈法
 *
 * 递归（几乎所有的树，都需要递归）
 *
 * 回溯 = 深度优先搜索 -> 递归(例如全排列)
 *      例如 8皇后问题，
 *
 * DFS Deep First Search 深度优先搜索
 *      例如 N655_m N2044 N720
 * BFS Breath First Search 广度优先搜索
 *      例如 S_PrintTree
 * 记忆化搜索
 *      例如 N688
 * 动态规划
 *      例如 N64 N198 爬楼梯 N70，背包问题，零钱问题 N322, N518
 *      动态规划转移方程，边界条件，动态规划常常适用于有重叠子问题和最优子结构性质的问题，动态规划方法所耗时间往往远少于朴素解法
 *      动态规划的全部情况依次展开，其实就是一棵树，就可以使用 dfs 来暴力解决，然后就可以使用记忆化搜索进行效率优化，最后就是动态规划。
 * 自动机
 *      例如 N8
 * 前缀树
 *      例如 N208 N677 N720
 * 异或计算
 *      例如 N1720 N1734
 * 全排列
 *      例如 N491难点 N2044
 * 模拟
 *      例如 N564
 *
 *
 *
 *
 *
 *
 */
/** 时间复杂度：如果一个算法的执行次数是 T(n)，那么只保留最高次项，同时忽略最高项的系数后得到函数 f(n)，此时算法的时间复杂度就是 O(f(n)) */
/** 关于数的处理：最好列举一些数字，注意下特殊场景，比如位数的变化，0，负数，一位数，等。 */

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 未处理的题目 N1719-树的构建 N906 N1994
 * TODO 1927 688
 * UNDO 1314 1292
 * DOING 1156 721 1405 1601 2014 manacher 798
 * 有意思 N343 N2044
 */
public class N0 {

    StringBuilder ans = new StringBuilder();

    public String tree2str(TreeNode root) {
        dfs(root);
        return ans.substring(1, ans.length() - 1);
    }
    private void dfs(TreeNode treeNode) {
        ans.append("(");
        ans.append(treeNode.val);
        if (treeNode.left == null && treeNode.right != null) {
            ans.append("()");
        }
        if (treeNode.left != null) {
            tree2str(treeNode.left);
        }
        if (treeNode.right != null) {
            tree2str(treeNode.right);
        }
        ans.append(")");
    }


    public boolean canThreePartsEqualSum(int[] arr) {
        // 暴力，优化
        int n = arr.length;
        long all = 0;
        for (int a : arr) {
            all += a;
        }
        if (all % 3 != 0) {
            return false;
        }
        long part = all / 3;
        long p1 = 0;
        for (int i=0; i < n; i++) {
            p1 += arr[i];
            if (p1 == part) {
                long p2 = 0;
                for (int j=i + 1; j < n - 1; j++) {
                    p2 += arr[j];
                    if (p1 == p2) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        List<String> ans = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        for (int i=0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        for (int i=0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                Integer index = map.get(list2[i]);
                if (min == index + i) {
                    ans.add(list2[i]);
                } else if (min > index + i) {
                    ans.clear();
                    ans.add(list2[i]);
                    min = index + i;
                }
            }
        }
        return ans.toArray(new String[0]);
    }

    public int[] finalPrices(int[] prices) {
        // 寻找元素右侧第一个小于该元素的值，单调递增栈
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[prices.length];
        for (int i=0; i < prices.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }
            while (!stack.isEmpty() && prices[i] <= prices[stack.peek()]) {
                Integer pop = stack.pop();
                ans[pop] = prices[pop] - prices[i];
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            ans[pop] = prices[pop];
        }
        return ans;
    }


    public boolean findTarget(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            if (set.contains(k - pop.val)) {
                return true;
            }
            set.add(pop.val);
            if (pop.left != null) {
                stack.push(pop.left);
            }
            if (pop.right != null) {
                stack.push(pop.right);
            }
        }
        return false;
    }

    public int[][] imageSmoother(int[][] img) {
        final int[][] parts = new int[][]{{-1, -1}, {0, -1}, {1, -1}, {-1, 0}, {0, 0}, {1, 0}, {-1, 1}, {0, 1}, {1, 1}};
        int[][] ans = new int[img.length][img[0].length];
        for (int i=0; i < img.length; i++) {
            for (int j=0; j < img[0].length; j++) {
                int total = 0;
                int count = 0;
                for (int[] part : parts) {
                    if (i + part[0] >= 0 && j + part[1] >= 0 && i + part[0] < img.length && j + part[1] < img[0].length) {
                        count++;
                        total += img[i + part[0]][j + part[1]];
                    }
                }
                ans[i][j] = total / count;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] prices = new int[] {12,-4,16,-5,9,-3,3,8,0};
        boolean a = new N0().canThreePartsEqualSum(prices);
    }

}
