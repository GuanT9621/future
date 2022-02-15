package leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode-cn.com/problems/increasing-subsequences/
 * 递增子序列
 * 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。
 * 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
 * 1 <= nums.length <= 15       -100 <= nums[i] <= 100
 *
 * 思路一 二进制枚举 + 哈希
 * 枚举出所有的子序列，然后判断当前的子序列是否是非严格递增的，同时去重
 * 难点 1 全枚举
 *       长度为 n 的序列选择子序列一共会有 2^n 种情况，这 2^n 就是区间 [0， 2^(n-1)]的所有整数的二进制表示。
 *       我们可以枚举区间中的每一个数，然后对它做二进制数位拆分，我们会得到一个 0/1 序列，接着可以构造出这个 0/1 序列对应的子序列
 *
 * 难点 2 哈希计算
 *
 * 思路二 递归枚举 + 减枝
 * 把方法一变成递归形式，然后进行剪枝。
 *
 */
public class N491_m {
    List<Integer> temp = new ArrayList<>();
    Set<Integer> set = new HashSet<>();
    int n;

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        n = nums.length;
        int total = 1 << n;
        for (int i=0; i<total; i++) {
            findSubsequences(i, nums);
            int hashCode = getHash(263, (int) 1E9 + 7);
//            int hashCode = temp.hashCode();
            if (!set.contains(hashCode) && check()) {
                ans.add(new ArrayList<>(temp));
                set.add(hashCode);
            }
        }
        return ans;
    }

    public void findSubsequences(int mask, int[] nums) { // mask的二进制代表一种排列情况
        temp.clear();
        for (int i = 0; i < n; ++i) {
            if ((mask & 1) != 0) { // 每次计算最低位是0/1 ，1则加入
                temp.add(nums[i]);
            }
            mask >>= 1; // 右移一位
        }
    }
    public int getHash(int base, int mod) { // 为什么重写hash计算
        int hashValue = 0;
        for (int x : temp) {
            hashValue = hashValue * base % mod + (x + 101);
            hashValue %= mod;
        }
        return hashValue;
    }
    public boolean check() {
        for (int i = 1; i < temp.size(); ++i) {
            if (temp.get(i) < temp.get(i - 1)) {
                return false;
            }
        }
        return temp.size() >= 2;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,6,7,5};
        N491_m n491_m = new N491_m();
        List<List<Integer>> subsequences = n491_m.findSubsequences(nums);
        for (List<Integer> subsequence : subsequences) {
            String collect = subsequence.stream().map(Objects::toString).collect(Collectors.joining(" "));
            System.out.println(collect);
        }
    }

}
