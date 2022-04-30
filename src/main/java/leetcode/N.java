package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * @author guanhao02
 * @date 2022/4/28
 */
public class N {

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

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        while (left <= right) {
            if (isSelfDividing(left)) {
                ans.add(left);
            }
            left++;
        }
        return ans;
    }

    private boolean isSelfDividing(int number) {
        int tmp = number;
        while (tmp != 0) {
            int i = tmp % 10;
            if (i != 0 && number % i == 0) {
                tmp /= 10;
            } else {
                return false;
            }
        }
        return true;
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

    public boolean hasAlternatingBits(int n) {
        // a 为 全 1 ， a + 1 为全 0
        int a = n ^ (n >> 1);
        return (a & (a + 1)) == 0;
    }

    public int countPrimeSetBits(int left, int right) {
        int ans = 0;
        for (int i=left; i <= right; i++) {
            int count = count(i);
            if (prime(count)) {
                ans++;
            }
        }
        return ans;
    }
    private boolean prime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i=2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
    private int count(int num) {
        int count = 0;
        int tmp = num;
        while (tmp != 0) {
            int i = tmp % 2;
            if (i == 1) {
                count++;
            }
            tmp = tmp / 2;
        }
        return count;
    }

    public int countPrimeSetBits2(int left, int right) {
        // 已知最大 10^6 即 2^20 ,那么 1 的个数为 0-20，即 2，3，5，7，11，13，17，19
        // mask = 10100010100010101100
        int ans = 0;
        int mask = Integer.parseInt("10100010100010101100", 2);
        for (int i=left; i <= right; i++) {
            if (((1 << Integer.bitCount(i)) & mask) != 0) {
                ++ans;
            }
        }
        return ans;
    }

    public static boolean rotateString(String s, String goal) {
        int n = s.length();
        if (n != goal.length()) {
            return false;
        }
        for (int i=0; i < n; i++) {
            s = s.substring(1) + s.charAt(0);
            if (s.equals(goal)) {
                return true;
            }
        }
        return false;
    }

    public int uniqueMorseRepresentations(String[] words) {
        String[] meta = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        Set<String> set = new HashSet<>();
        for (String string : words) {
            StringBuilder sb = new StringBuilder();
            for (int i=0; i < string.length(); i++) {
                sb.append(meta[string.charAt(i) - 'a']);
            }
            set.add(sb.toString());
        }
        return set.size();
    }

    public int[] numberOfLines(int[] widths, String s) {
        int[] ans = new int[2];
        int temp = 0;
        for (int i=0; i < s.length(); i++) {
            int c = widths[s.charAt(i) - 'a'];
            if (temp + c > 100) {
                temp = c;
                ans[0]++;
            } else {
                temp += c;
            }
        }
        ans[0] += temp == 0 ? 0 : 1;
        ans[1] = temp;
        return ans;
    }

    public static String word(String paragraph, String[] banned) {
        Set<String> set = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> map = new HashMap<>();
        paragraph += ".";
        String word = "";
        for (int i=0; i < paragraph.length(); i++) {
            char ch = paragraph.charAt(i);
            if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
                word += ch;
            } else {
                word = word.toLowerCase();
                if (!set.contains(word) && !word.isEmpty()) {
                    map.put(word, map.getOrDefault(word, 0) + 1);
                }
                word = "";
            }
        }

        String ans = "";
        int max = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (max < entry.getValue()) {
                ans = entry.getKey();
                max = entry.getValue();
            }
        }
        return ans;
    }

    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        List<Integer> cs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) {
                cs.add(i);
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (Integer cc : cs) {
                min = Math.min(min, Math.abs(i - cc));
            }
            ans[i] = min;
        }
        return ans;
    }

    public int[] shortestToChar1(String s, char c) {
        int n = s.length();
        int[] ans = new int[n];
        int ci = n + 1;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) {
                ci = i;
            } else {
                ans[i] = Math.abs(i - ci);
            }
        }
        ci = -1;
        for (int i = n - 1; i >=0; i--) {
            if (s.charAt(i) == c) {
                ci = i;
            } else {
                ans[i] = Math.min(ans[i], Math.abs(i - ci));
            }
        }
        return ans;
    }

    public int binaryGap(int n) {
        // 遍历 traversal
        int max = 0, right = -1;
        for (int index = 0; n != 0; index++) {
            if (n % 2 == 1) {
                if (right != -1) {
                    max = Math.max(max, index - right);
                }
                right = index;
            }
            n >>= 1;
        }
        return max;
    }
    public int binaryGap1(int n) {
        int last = -1, ans = 0;
        for (int i = 0; n != 0; ++i) {
            if ((n & 1) == 1) {
                if (last != -1) {
                    ans = Math.max(ans, i - last);
                }
                last = i;
            }
            n >>= 1;
        }
        return ans;
    }

    public static String toBin(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            // n % 2  或者 n & 1 都可以求的最低位的01值
            sb.append(n % 2);
            sb.append(n & 1);
            n >>= 1;
        }
        return sb.reverse().toString();
    }

    public int projectionArea(int[][] grid) {
        int n = grid.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int row = 0;
            int column = 0;
            for (int j = 0; j < n; j++) {
                row = Math.max(row, grid[i][j]);
                column = Math.max(column, grid[j][i]);
                if (grid[i][j] != 0) {
                    ans++;
                }
            }
            ans = ans + row + column;
        }
        return ans;
    }

    public int[] sortArrayByParity(int[] nums) {
        for (int i = 0, p = -1; i < nums.length; i++) {
            if (nums[i] % 2 == 0 && p > -1) {
                nums[i] = nums[i] + nums[p];
                nums[p] = nums[i] - nums[p];
                nums[i] = nums[i] - nums[p];
                for (int j = p; j <= i; j++) {
                    if (nums[j] % 2 == 1) {
                        p = j;
                        break;
                    }
                }
            }
            if (nums[i] % 2 == 1 && p == -1) {
                p = i;
            }
        }
        return nums;
    }
    public int[] sortArrayByParity2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] % 2 == 0) {
                left++;
                continue;
            }
            if (nums[right] % 2 == 1) {
                right--;
                continue;
            }
            nums[left] = nums[left] + nums[right];
            nums[right] = nums[left] - nums[right];
            nums[left] = nums[left] - nums[right];
        }
        return nums;
    }

    public int smallestRangeI(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(num, min);
            max = Math.max(num, max);
        }
        int scope = max - min;
        if (scope > 2 * k) {
            return scope - 2 * k;
        }
        return 0;
    }

    public static void main(String[] args) {
        String word = toBin(8);
        System.out.println(word);
    }
}
