package algorithm.回溯算法;

/**
 * n 皇后问题 经典的回溯问题 如何将 n 个皇后放置在 n×n 的棋盘上，有多少种解法
 * 解决该问题需要使用 深度优先搜索法 DFS + 回溯法，配合使用，尝试出全部的解法。
 * 其实单纯的搜索法也是可以找出全部的解法，不过耗时极高，在发现当前的分支已经不可能是解时，回溯到上一步能节省时间。
 *
 * 关于'回溯'和'剪枝'的区别：
 * 你可以认为'回溯'就像走迷宫，发现堵住了就回退；
 * 而剪枝是一种优化手段，就是让整个递归过程减少无效的计算；参考 https://zhuanlan.zhihu.com/p/345181624
 *
 * 回溯的模板
 * // 其中 choices 表示我们能做出的选择，selected 表示已选择的路径
 * void backtracking(List<int> choices, List<int> selected) {
 *     if (end) return;
 *     for (int choice : choices) {
 *         // 进行选择
 *         selected.add(choice);
 *         // 继续前行
 *         backtracking(choices, selected);
 *         // 撤销选择
 *         selected.remove(choice);
 *     }
 * }
 */
public class NQueues {

    int max;
    int[] array;
    int count;

    public NQueues(int queues) {
        this.max = queues;
        this.array = new int[queues];
        this.count = 0;
    }

    // 给第 n 行放皇后
    public void backtracking(int n) {
        if (n == max) {
            print();
            count++;
            return;
        }
        // 尝试在这一行的每一个位置放置皇后，如果可以放就进入下一行。
        for (int i = 0; i < max; i++) {
            array[n] = i;
            if (judge(n)) {
                backtracking(n + 1);
            }
        }
    }

    private boolean judge(int n) {
        // 遍历这行之前的全部行，判断当前局面是否Ok
        for (int i = 0; i < n; i++) {
            // 是不是在同一列 是不是在同一条斜线
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    private void print() {
        for (int j : array) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        NQueues nQueues = new NQueues(9);
        nQueues.backtracking(0);
        System.out.println(nQueues.count);
    }

}
