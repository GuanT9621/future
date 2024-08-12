package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://leetcode-cn.com/problems/erect-the-fence/">leetcode题目</a>
 * <a href="https://oi-wiki.org/geometry/convex-hull/">OIWiki</a>
 * 安装栅栏
 * 在一个二维的花园中，有一些用 (x, y) 坐标表示的树。由于安装费用十分昂贵，你的任务是先用最短的绳子围起所有的树。
 * 只有当所有的树都被绳子包围时，花园才能围好栅栏。你需要找到正好位于栅栏边界上的树的坐标。
 *
 * 答案一 Jarvis 算法
 * 答案二 Graham 算法
 * 答案三 Andrew 算法
 */
public class N587_h {

    public static int[][] outerTreesJarvis(int[][] trees) {
        return new int[0][0];
    }

    public static int[][] outerTreesGraham(int[][] trees) {
        return new int[0][0];
    }

    public static int[][] outerTreesAndrew(int[][] trees) {
        return new int[0][0];
    }

    public static int[][] outerTrees(int[][] trees) {
        int top = 0, down = Integer.MAX_VALUE;
        int left = Integer.MAX_VALUE, right = 0;
        int tl = Integer.MAX_VALUE, dl = Integer.MAX_VALUE, ld = Integer.MAX_VALUE, rd = Integer.MAX_VALUE;
        int tr = -1, dr = -1, lt = -1, rt = -1;
        for (int[] tree : trees) {
            int x = tree[0];
            int y = tree[1];
            if (x < left) {
                left = x;
                ld = y;
                lt = y;
            } else if (x == left) {
                ld = Math.min(ld, y);
                lt = Math.max(lt, y);
            }
            if (x > right) {
                right = x;
                rd = y;
                rt = y;
            } else if (x == right) {
                rd = Math.min(rd, y);
                rt = Math.max(rt, y);
            }
            if (y < down) {
                down = y;
                dl = x;
                dr = x;
            } else if (y == down) {
                dl = Math.min(dl, x);
                dr = Math.max(dr, x);
            }
            if (y > top) {
                top = y;
                tl = x;
                tr = x;
            } else if (y == top) {
                tl = Math.min(tl, x);
                tr = Math.max(tr, x);
            }
        }
        List<int[]> list = new ArrayList<>();
        for (int[] tree : trees) {
            int x = tree[0];
            int y = tree[1];
            // 边上
            if (y == down || y == top || x == left || x == right) {
                list.add(tree); continue;
            }
            // 边外，第 1 象限
            if (x >= tr && y >= rt) {
                if ((right - tr) / (top - rt) <= (x - tr) / (top - y)) {
                    list.add(tree);
                }
                continue;
            }
            // 边外，第 2 象限
            if (x <= tl && y >= lt) {
                if ((tl - left) / (top - lt) <= (tl - x) / (top - y)) {
                    list.add(tree);
                }
                continue;
            }
            // 边外，第 3 象限
            if (x <= dl && y <= ld) {
                if ((dl - left) / (ld - down) <= (dl - x) / (y - down)) {
                    list.add(tree);
                }
                continue;
            }
            // 边外，第 4 象限
            if (x >= dr && y <= rd) {
                if ((right - dr) / (rd - down) <= (x - dr) / (y - down)) {
                    list.add(tree);
                }
                continue;
            }
        }
        int[][] ans = new int[list.size()][2];
        for (int i=0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        // 输入: points = [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
        // 输出: [[1,1],[2,0],[3,3],[2,4],[4,2]]
        int[][] input = new int[][] {{1,1},{2,2},{2,0},{2,4},{3,3},{4,2}};
        int[][] output = outerTrees(input);
        for (int[] anInt : output) {
            System.out.println(Arrays.toString(anInt));
        }
    }
}
