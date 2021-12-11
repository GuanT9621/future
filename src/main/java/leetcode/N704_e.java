package leetcode;

/**
 * https://leetcode-cn.com/problems/binary-search/
 * 二分查找
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值target ，写一个函数搜索nums中的 target，如果目标值不存在返回下标，否则返回 -1。
 * 递归算法，先写边界，然后写递归逻辑
 * 迭代算法，每次判断然后缩小边界
 */
public class N704_e {

    public static int search2(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int num = nums[mid];
            if (num == target) {
                return mid;
            } else if (num > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static int search(int[] nums, int target) {
        return search(nums, 0, nums.length-1, target);
    }

    private static int search(int[] nums, int si, int ei, int target) {
        if (si > ei) {
            return -1;
        }
        int mi = (ei - si) / 2 + si;
        int num = nums[mi];
        if (num == target)
            return mi;
        else if (num < target)
            return search(nums, mi+1, ei, target);
        else
            return search(nums, si, mi-1, target);
    }

    public static void main(String[] args) {
        System.out.println(search(new int[]{-1,0,3,5,9,12}, 8));
    }
}
