package leetcode;

public class N654_m {

    /** 递归 bfs */
    class Solution {
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            return bfs(nums, 0, nums.length - 1);
        }
        private TreeNode bfs(int[] nums, int s, int e) {
            int index = s;
            for (int i = s; i <= e; i++) {
                if (nums[i] > nums[index]) {
                    index = i;
                }
            }
            TreeNode root = new TreeNode(nums[index]);
            if (s <= index - 1) root.left = bfs(nums, s, index - 1);
            if (index + 1 <= e) root.right = bfs(nums, index + 1, e);
            return root;
        }
    }

}
