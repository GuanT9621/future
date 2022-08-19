package leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author guanhao02
 * @date 2022/8/11
 */
public class N2197_h {

    /** use deque */
    class Solution {

        public List<Integer> replaceNonCoprimes(int[] nums) {
            List<Integer> list1 = new ArrayList<>();
            for (int num : nums) {
                list1.add(num);
            }
            while (true) {
                List<Integer> ans = replaceNonCoprimes(list1);
                if (ans.size() == list1.size()) {
                    return ans;
                } else {
                    list1 = ans;
                }
            }
        }

        public List<Integer> replaceNonCoprimes(List<Integer> list) {
            Deque<Integer> deque = new LinkedList<>(list);
            List<Integer> result = new ArrayList<>();
            while (!deque.isEmpty()) {
                Integer a = deque.poll();
                Integer b = deque.poll();
                if (b == null) {
                    result.add(a);
                    continue;
                }
                if (a.equals(b) && a > 1) {
                    deque.push(b);
                    continue;
                }
                int gcd = gcd(a, b);
                if (gcd > 1) {
                    int lcm = lcm(a, b, gcd);
                    deque.push(lcm);
                } else {
                    result.add(a);
                    deque.push(b);
                }
            }
            return result;
        }

    }

    /** use stack with top operation */
    class Solution2 {

        public List<Integer> replaceNonCoprimes(int[] nums) {
            Stack<Integer> stack = new Stack<>();
            for (int num : nums) {
                if (stack.isEmpty()) {
                    stack.push(num);
                } else {
                    Integer peek = stack.peek();
                    if (peek.equals(num) && peek > 1) {
                        continue;
                    }
                    int gcd = gcd(peek, num);
                    if (gcd > 1) {
                        int lcm = lcm(peek, num, gcd);
                        stack.pop();
                        stack.push(lcm);
                    } else {
                        stack.push(num);
                    }
                }
            }
            return new ArrayList<>(stack);
        }

    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static int lcm(int a, int b, int gcd) {
        long ab = (long) a * b;
        return (int) (ab / gcd);
    }

    public static void main(String[] args) {
        int[] ints = {287,41,49,287,899,23,23,20677,5,825};
        List<Integer> list = new N2197_h().new Solution().replaceNonCoprimes(ints);
        list.forEach(System.out::println);
    }

}
