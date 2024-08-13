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
 * 答案 Andrew 算法
 * 答案 Jarvis 算法（又称 Gift wrapping algorithm 礼物包装算法）
 * 答案 Graham 算法
 *
 * 叉积、即向量积、外积、叉乘；这里我们说的是二维矢量 等同于行列式乘法（矩阵积）
 * 1 xy坐标中有线段 A 坐标 (1, 1) (2, 2) 线段 B 坐标 (2, 2) (2, 3) 求AB叉积
 * 2 首先将坐标转为向量，我们用 V 表示向量
 *      向量A从(1,1) 指向 (2,2)，表示：Va = (2-1, 2-1) = (1,1)
 *      向量B从(2,2) 指向 (2,3)，表示：Vb = (2-2, 3-2) = (0,1)
 * 3 向量A和向量B的叉积：Va x Vb = x1·y2 - y1·x2，表示： 1 - 0 = 1
 * 4 Va和Vb叉积大于 0: 意味着 Vb 在 Va 的逆时针方向上，从 Va 到 Vb 转动时，转动方向是逆时针。
 *   Va和Vb叉积等于 0: 意味着 Vb 和 Va 共线，二者可能是平行的（同向或反向）；它们位于同一条直线上，但不能确定它们是否重合或是否有相同的方向。
 *   Va和Vb叉积小于 0: 意味着 Vb 在 Va 的顺时针方向上，从 Va 到 Vb 转动时，转动方向是顺时针。
 *   注意：两个向量的向量积（叉积）是与顺序相关的。具体来说，向量A叉乘向量B 与 向量B叉乘向量A 的结果是互为相反数的。
 */
public class N587_h {

    /**
     * 首先把所有点以横坐标为第一关键字，纵坐标为第二关键字排序。
     * 显然排序后最小的元素和最大的元素一定在凸包上。而且因为是凸多边形，我们如果从一个点出发逆时针走，轨迹总是「左拐」的，一旦出现右拐，就说明这一段不在凸包上。因此我们可以用一个单调栈来维护上下凸壳。
     * 因为从左向右看，上下凸壳所旋转的方向不同，为了让单调栈起作用，我们首先 升序枚举 求出下凸壳，然后 降序 求出上凸壳。
     * 求凸壳时，一旦发现即将进栈的点（P）和栈顶的两个点（S1,S2，其中 S1 为栈顶）行进的方向向右旋转，即叉积小于 0 则弹出栈顶，回到上一步，继续检测，直到 叉积大于 0 或者栈内仅剩一个元素为止。
     */
    public static int[][] outerTreesAndrew(int[][] trees) {
        return new int[0][0];
    }

    public static int[][] outerTreesJarvis(int[][] trees) {
        return new int[0][0];
    }

    public static int[][] outerTreesGraham(int[][] trees) {
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
