package leetcode;

/**
 * https://leetcode-cn.com/problems/construct-quad-tree/
 * 建立四叉树
 * 给你一个 n * n 矩阵 grid ，矩阵由若干 0 和 1 组成。请你用四叉树表示该矩阵 grid 。你需要返回能表示矩阵的 四叉树 的根结点。
 * 注意，当 isLeaf 为 False 时，你可以把 True 或者 False 赋值给节点，两种值都会被判题机制接受 。
 * 四叉树数据结构中，每个内部节点只有四个子节点。此外，每个节点都有两个属性：
 * val：储存叶子结点所代表的区域的值。1 对应 True，0 对应 False；
 * isLeaf: 当这个节点是一个叶子结点时为 True，如果它有 4 个子节点则为 False 。
 * 我们可以按以下步骤为二维区域构建四叉树：
 *  1 如果当前网格的值相同（即，全为 0 或者全为 1），将 isLeaf 设为 True ，将 val 设为网格相应的值，并将四个子节点都设为 Null 然后停止。
 *  2 如果当前网格的值不同，将 isLeaf 设为 False， 将 val 设为任意值，然后如下图所示，将当前网格划分为四个子网格。
 *  3 使用适当的子网格递归每个子节点。
 * 四叉树格式：
 * 输出为使用层序遍历后四叉树的序列化形式，其中 null 表示路径终止符，其下面不存在节点。
 * 它与二叉树的序列化非常相似。唯一的区别是节点以列表形式表示 [isLeaf, val] 。
 * 如果 isLeaf 或者 val 的值为 True ，则表示它在列表 [isLeaf, val] 中的值为 1 ；如果 isLeaf 或者 val 的值为 False ，则表示值为 0 。
 * 其中 n == grid.length == grid[i].length       n == 2^x 其中 0 <= x <= 6
 *
 * 解答
 * 1 如果不理解，最好看看示例
 * 2 注意处理 n = 1 的情况
 * 思路 遍历-深度优先DFS-递归
 *
 */
public class N472_m {

    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    };


    class Solution {
        public Node construct(int[][] grid) {
            int n = grid.length;
            return construct(grid, 0, n - 1, 0, n - 1);
        }
        private Node construct(int[][] grid, int xs, int xe, int ys, int ye) {
            if (xe == xs) {
                return new Node(grid[xs][ys] == 1,true);
            }
            int half = (xe - xs) / 2;
            Node topLeft = construct(grid, xs, xs + half, ys, ys + half);
            Node topRight = construct(grid, xs, xs + half, ye - half, ye);
            Node bottomRight = construct(grid, xe - half, xe, ye - half, ye);
            Node bottomLeft = construct(grid, xe - half, xe, ys, ys + half);
            boolean equals = topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf &&
                    ((topLeft.val && topRight.val && bottomLeft.val && bottomRight.val)
                    || (!topLeft.val && !topRight.val && !bottomLeft.val && !bottomRight.val));
            if (equals) {
                return new Node(topLeft.val, true);
            } else {
                return new Node(true, false, topLeft, topRight, bottomLeft, bottomRight);
            }
        }

    }

    public static void main(String[] args) {
        // [[0,1], [1,1],[1,0],[1,0],[1,1]] x

        // [[0,1], [1,0],[1,1],[1,1],[1,0]]  v
        int[][] grid = new int[][]
                {{0,1},{1,0}};
        Node construct = new N472_m().new Solution().construct(grid);
        System.out.println(construct.isLeaf);
    }

}
