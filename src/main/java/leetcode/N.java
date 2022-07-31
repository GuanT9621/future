package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
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
                    if (i + part[0] >= 0 && j + part[1] >= 0
                            && i + part[0] < img.length && j + part[1] < img[0].length) {
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
        String[] meta = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..",
                "--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
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
        for (int i = n - 1; i >= 0; i--) {
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
        int max = 0;
        int right = -1;
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
        int last = -1;
        int ans = 0;
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

    class RecentCounter {
        Queue<Integer> queue;
        public RecentCounter() {
            queue = new ArrayDeque<>();
        }
        public int ping(int t) {
            queue.offer(t);
            int t0 = t - 3000;
            while (queue.peek() < t0) {
                queue.poll();
            }
            return queue.size();
        }
    }

    public int[] diStringMatch(String s) {
        // 942 先生成，再交换
        int n = s.length();
        int[] ans = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            ans[i] = i;
        }
        boolean done = false;
        while (!done) {
            done = true;
            for (int i = 0; i < n; i++) {
                if (('I' == s.charAt(i) && ans[i] > ans[i + 1]) || ('D' == s.charAt(i) && ans[i] < ans[i + 1])) {
                    int temp = ans[i];
                    ans[i] = ans[i + 1];
                    ans[i + 1] = temp;
                    done = false;
                }
            }
        }
        return ans;
    }

    public int[] diStringMatch1(String s) {
        // 942 贪心算法
        int n = s.length();
        int min = 0;
        int max = n;
        int[] ans = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if ('I' == s.charAt(i)) {
                ans[i] = min++;
            } else
            if ('D' == s.charAt(i)) {
                ans[i] = max--;
            }
        }
        ans[n] = min;
        return ans;
    }

    public int minDeletionSize(String[] strs) {
        // 944
        int row = strs.length;
        int column = strs[0].length();
        int ans = 0;
        for (int c = 0; c < column; c++) {
            for (int r = 0; r < row; r++) {
                if (r + 1 < row && strs[r].charAt(c) > strs[r + 1].charAt(c)) {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }

    public boolean oneEditAway(String first, String second) {
        int n = first.length();
        int m = second.length();
        if (n - m < -1 || n - m > 1) {
            return false;
        }
        if (n == m && n == 1) {
            return true;
        }
        int diff = 0;
        for (int i = 0, j = 0; i < n && j < m; i++, j++) {
            if (first.charAt(i) != second.charAt(j)) {
                diff++;
                if (diff > 1) {
                    return false;
                }
                if (n < m) {
                    i--;
                } else if (n > m) {
                    j--;
                }
            }
        }
        return true;
    }

    public double largestTriangleArea(int[][] points) {
        // 812 组成最大面积三角形：海伦公式 暴力
        double max = 0;
        for (int[] a : points) {
            for (int[] b : points) {
                double ab = line(a, b);
                if (ab == 0) {
                    continue;
                }
                for (int[] c : points) {
                    double bc = line(b, c);
                    double ac = line(a, c);
                    if (bc == 0 || ac == 0) {
                        continue;
                    }
                    double s = (ab + ac + bc) / 2;
                    double area = Math.sqrt(s * (s - ab) * (s - bc) * (s - ac));
                    if (Double.isNaN(area) || Double.isInfinite(area)) {
                        continue;
                    }
                    max = Math.max(max, area);
                }
            }
        }
        return max;
    }
    private double line(int[] a, int[] b) {
        return Math.sqrt(Math.pow(Math.abs(a[0] - b[0]), 2) + Math.pow(Math.abs(a[1] - b[1]), 2));
    }

    public boolean isAlienSorted(String[] words, String order) {
        // 953
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), i);
        }
        for (int i = 1; i < words.length; i++) {
            String word0 = words[i - 1];
            String word1 = words[i];
            if (word1.contains(word0)) {
                continue;
            }
            if (word0.contains(word1)) {
                return false;
            }
            int n = Math.min(word0.length(), word1.length());
            for (int j = 0; j < n; j++) {
                if (map.get(word0.charAt(j)) > map.get(word1.charAt(j))) {
                    return false;
                } else if (map.get(word0.charAt(j)) < map.get(word1.charAt(j))) {
                    break;
                }
            }
        }
        return true;
    }

    public int searchInsert(int[] nums, int target) {
        // 35 O(n)
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return n;
    }
    public int searchInsert2(int[] nums, int target) {
        // 35 O(log n)
        int n = nums.length;
        if (n == 1) {
            return nums[0] >= target ? 0 : 1;
        }
        if (target <= nums[0]) {
            return 0;
        }
        if (target > nums[n - 1]) {
            return n;
        }
        int left = 0;
        int right = n - 1;
        while (1 < right - left) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (nums[left] > target) {
            return left;
        }
        if (nums[right] < target) {
            return right + 1;
        }
        return right;
    }

    public boolean isValid(String s) {
        // 20 栈
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character pop = stack.pop();
                if (pop == '(' && c != ')') {
                    return false;
                }
                if (pop == '{' && c != '}') {
                    return false;
                }
                if (pop == '[' && c != ']') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public boolean isUnivalTree(TreeNode root) {
        // 965
        int value = root.val;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll == null) {
                continue;
            }
            if (poll.val != value) {
                return false;
            }
            queue.offer(poll.left);
            queue.offer(poll.right);
        }
        return true;
    }

    public int repeatedNTimes(int[] nums) {
        // 961
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return num;
            } else {
                set.add(num);
            }
        }
        return 0;
    }

    public String removeOuterParentheses(String s) {
        // 1021
        StringBuilder sb = new StringBuilder();
        int prev = 0;
        int split = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ('(' == c) {
                split++;
            } else {
                split--;
            }
            if (split == 0) {
                if (prev + 1 < i) {
                    sb.append(s, prev + 1, i);
                }
                prev = i + 1;
            }
        }
        return sb.toString();
    }

    public boolean isBoomerang(int[][] points) {
        // 1037
        int[] ab = new int[] {points[1][0] - points[0][0], points[1][1] - points[0][1]};
        int[] bc = new int[] {points[2][0] - points[1][0], points[2][1] - points[1][1]};
        return ab[0] * bc[1] - ab[1] * bc[0] != 0;
    }

    public int heightChecker(int[] heights) {
        // 1051 计数排序
        int[] counts = new int[101];
        for (int height : heights) {
            counts[height]++;
        }
        int ans = 0, index = 0;
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= counts[i]; j++) {
                if (heights[index] != i) {
                    ans++;
                }
                index++;
            }
        }
        return ans;
    }

    public void duplicateZeros(int[] arr) {
        // 1089
        int n = arr.length;
        // find it duplicate it and move other, cause 1 <= n <= 10000 timeout
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0 && i + 1 < n) {
                for (int j = n - 1; j > i; j--) {
                    arr[j] = arr[j - 1];
                }
                i++;
            }
        }
    }

    public void duplicateZeros2(int[] arr) {
        // 1089
        int n = arr.length;
        Queue<Integer> queue = new LinkedList<>();
        // eat eggs and lay eggs
        for (int out = 0, in = 0; out < n; out++, in++) {
            queue.offer(arr[in]);
            if (arr[in] == 0) {
                queue.offer(arr[in]);
            }
            arr[out] = queue.poll();
        }
    }

    public void duplicateZeros3(int[] arr) {
        int n = arr.length;
        int top = 0;
        int i = -1;
        while (top < n) {
            i++;
            if (arr[i] != 0) {
                top++;
            } else {
                top += 2;
            }
        }
        int j = n - 1;
        if (top == n + 1) {
            arr[j] = 0;
            j--;
            i--;
        }
        while (j >= 0) {
            arr[j] = arr[i];
            j--;
            if (arr[i] == 0) {
                arr[j] = arr[i];
                j--;
            }
            i--;
        }
    }

    // 1175 1 <= n <= 100
    int[] table = new int[] {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};
    int mod = 1000000007;
    public int numPrimeArrangements(int n) {
        int primeNum = 0;
        for (int prime : table) {
            if (prime <= n) {
                primeNum++;
            } else {
                break;
            }
        }
        int compositeNum = n - primeNum;
        long ans = 1;
        for (int i = 2; i <= primeNum; i++) {
            ans *= i;
            ans %= mod;
        }
        for (int i = 2; i <= compositeNum; i++) {
            ans *= i;
            ans %= mod;
        }
        // 模 mod (10^9 + 7)
        return (int) ans % mod;
    }

    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        // 1200
        Arrays.sort(arr);
        int sub = Integer.MAX_VALUE;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < arr.length - 1; i++) {
            int delta = arr[i + 1] - arr[i];
            if (delta < sub) {
                sub = delta;
                ans.clear();
                ans.add(Arrays.asList(arr[i], arr[i + 1]));
            } else if (delta == sub) {
                ans.add(Arrays.asList(arr[i], arr[i + 1]));
            }
        }
        return ans;
    }

    // begin 1
    private String makeNetGateIndex(int index) {
        StringBuilder sb = new StringBuilder();
        do {
            index--;
            sb.append ((char) ('a' + index % 26));
            index = index / 26;
        } while (index > 0);
        return sb.reverse().toString();
    }

    public int oddCells2(int m, int n, int[][] indices) {
        // 1252 ^ false is even. true is odd.
        boolean[] ms = new boolean[m];
        boolean[] ns = new boolean[n];
        for (int[] i : indices) {
            ms[i[0]] ^= true;
            ns[i[1]] ^= true;
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (ms[i] ^ ns[j]) {
                    ans++;
                }
            }
        }
        return ans;
    }
    public int oddCells3(int m, int n, int[][] indices) {
        // 1252 ^ false is even. true is odd.
        boolean[] ms = new boolean[m];
        boolean[] ns = new boolean[n];
        int mo = 0;
        int no = 0;
        for (int[] i : indices) {
            ms[i[0]] = !ms[i[0]];
            ns[i[1]] = !ns[i[1]];
            mo += ms[i[0]] ? 1 : -1;
            no += ns[i[1]] ? 1 : -1;
        }
        return mo * (n - no) + no * (m - mo);
    }
    public int oddCells4(int m, int n, int[][] ins) {
        // 1252 ^ long only m, n < 50
        long c1 = 0, c2 = 0;
        for (int[] info : ins) {
            c1 ^= 1L << info[0];
            c2 ^= 1L << info[1];
        }
        int a = 0, b = 0;
        for (int i = 0; i < m; i++)
            a += ((c1 >> i) & 1);
        for (int i = 0; i < n; i++)
            b += ((c2 >> i) & 1);
        return a * (n - b) + (m - a) * b;
    }

    public int minCostToMoveChips(int[] position) {
        // 1217
        int p1 = 0;
        int p2 = 0;
        for (int i : position) {
            if (i % 2 == 0)
                p2++;
            else
                p1++;
        }
        return Math.min(p1, p2);
    }

    class MovingAverage {
        // 346
        int[] array;
        int size;
        int use;
        int i;
        double sum;

        public MovingAverage(int size) {
            this.array = new int[size];
            this.size = size;
            this.use = 0;
            this.i = 0;
            this.sum = 0D;
        }

        public double next(int val) {
            if (use == size) {
                sum -= array[i];
            } else {
                use++;
            }
            sum += val;
            array[i] = val;
            i++;
            i %= size;
            return sum / use;
        }
    }

    public String generateTheString(int n) {
        // 1374
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n - 1; i++) {
            sb.append('a');
        }
        return n % 2 == 0 ? sb.append('b').toString() : sb.append('a').toString();
    }

    public static void main(String[] args) {
        try {
            // 26aa 51 az 52 ba
            for (int i = 1; i < 800; i++) {
                String x = new N().makeNetGateIndex(i);
                System.out.println(i + " = " + x);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
