package leetcode;

/**
 * 双指针
 * 双指针卡尺
 * 滑动窗口
 *      例如 N1984 N438
 * 栈的活用
 *      例如 S_Calc
 * 单调栈
 *      例如 S_firstMax
 * 双栈法
 *
 * 递归（几乎所有的树，都需要递归）
 *
 * 回溯 = 深度优先搜索 -> 递归(例如全排列)
 *      例如 8皇后问题，
 *
 * DFS Deep First Search 深度优先搜索
 *      例如 N655_m
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
 *      例如 N208 N677
 * 异或计算
 *      例如 N1720 N1734
 * 全排列
 *      例如 N491难点
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
/**
 * 未处理的题目 N1719-树的构建 N906 N1994
 * TODO 1927 688
 * UNDO 1314 1292
 * DOING 1156 721 1405 1601
 * 有意思 N343
 */
public class N0 {

    /**
     * 检测大写字母
     * 我们定义，在以下情况时，单词的大写用法是正确的：
     * 全部字母都是大写，比如 "USA" 。
     * 单词中所有字母都不是大写，比如 "leetcode" 。
     * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google" 。
     * 给你一个字符串 word 。如果大写用法正确，返回 true ；否则，返回 false 。
     */
    public boolean detectCapitalUse(String word) {
        char[] chars = word.toCharArray();
        // A-Z 65-90  a-z 97-122
        if (chars[0] <= 90) {
            if (chars.length > 1) {
                if (chars[1] <= 90) {
                    for (int i = 2; i < chars.length; i++) {
                        if (chars[i] >= 97) {
                            return false;
                        }
                    }
                } else {
                    for (int i = 2; i < chars.length; i++) {
                        if (chars[i] <= 90) {
                            return false;
                        }
                    }
                }
            }
        } else {
            for (char c : chars) {
                if (c <= 90) {
                    return false;
                }
            }
        }
        return true;
    }


}
