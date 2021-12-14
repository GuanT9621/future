package leetcode;

import java.util.Stack;

/**
 * 和 N1299_e 有异曲同工之妙
 *
 * 求一个无序数组每一个元素的右侧第一个大于它的元素(否则-1)，返回一个新数组。
 * 如输入[6,2,1,4,8,5]
 * 输出[8,4,4,-1,-1]
 *
 * 思路：单调栈，即栈内元素保持一定单调性（单调递增或单调递减）的栈
 *
 * 我们用栈来保存未找到右边第一个比它大的元素的索引（保存索引是因为后面需要靠索引来给新数组赋值），初始时，栈里放的是第一个元素的索引0值。
 *
 * 参考 https://blog.csdn.net/smileiam/article/details/88732245?spm=1001.2101.3001.6650.1&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7Edefault-1.highlightwordscore&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7Edefault-1.highlightwordscore
 */
public class S_firstMax {

    public static int[] firstMax(int[] array) {
        int[] result = new int[array.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int index = 0;
        while (index < array.length) {
            if (!stack.isEmpty() && array[index] > array[stack.peek()]) {
                Integer pop = stack.pop();
                result[pop] = array[index];
            } else {
                stack.push(index);
                index++;
            }
        }
        while (!stack.isEmpty()) {
            result[stack.pop()] = -1;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] ints = firstMax(new int[]{6, 2, 1, 4, 8, 5});
        for (int i : ints) {
            System.out.print(i + ", ");
        }
        // [8,4,4,8,-1,-1]
    }

}
