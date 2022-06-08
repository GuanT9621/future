package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/serialize-and-deserialize-bst/
 * 序列化和反序列化二叉搜索树
 * 序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。
 * 设计一个算法来序列化和反序列化 二叉搜索树 。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。
 * 编码的字符串应尽可能紧凑。
 *
 * 思路
 * 序列化 bfs 用 # 占位
 * 反序列化 n 的左子在 2n + 1 右子在 2n + 2 （从 0 开始计数）
 */
public class N449_m {


    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode poll = queue.poll();
                if (poll == null) {
                    sb.append('#');
                } else {
                    sb.append(poll.val);
                    queue.offer(poll.left);
                    queue.offer(poll.right);
                }
                sb.append(',');
            }
            return sb.subSequence(0, sb.length() - 1).toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] nums = data.split(",", -1);
            return des(nums, 0);
        }

        private TreeNode des(String[] nums, int index) {
            if (index >= nums.length) {
                return null;
            }
            String num = nums[index];
            if ("#".equals(num)) {
                return null;
            }
            TreeNode node = new TreeNode();
            node.val = Integer.parseInt(num);
            node.left = des(nums, 2 * index + 1);
            node.right = des(nums, 2 * index + 2);
            return node;
        }

    }

}
