package leetcode;

/**
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 * 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * 中位数 是按顺序排列的一组数据中居于中间位置的数
 * 思路：看到复杂度为 log 时，就可以猜到算法应该是 二分查找法
 * 方法1： 使用归并的方式，合并两个有序数组，得到一个大的有序数组。大的有序数组的中间位置的元素，即为中位数。
 * 方法2： 不需要合并两个有序数组，只要找到中位数的位置即可。由于两个数组的长度已知，因此中位数对应的两个数组的下标之和也是已知的。
 *          维护两个指针，初始时分别指向两个数组的下标 00 的位置，每次将指向较小值的指针后移一位
 *          （如果一个指针已经到达数组末尾，则只需要移动另一个数组的指针），直到到达中位数的位置。
 */
public class N4_h {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int length = length1 + length2;
        if (length == 0) return 0;
        if (length == 1) {
            return length1 == 0 ? nums2[0] : nums1[0];
        }
        if (length == 2) {
            if (length1 == 0) {
                return (double) (nums2[0] + nums2[1]) / 2;
            }
            if (length2 == 0) {
                return (double) (nums1[0] + nums1[1]) / 2;
            }
            return (double) (nums1[0] + nums2[0]) / 2;
        }
        int i1 = 0, i2 = 0, iCur = 1, lu = 0;
        boolean d = 0 == length % 2;
        int stop = d ? length / 2 : length /2 + 1;
        while (true) {
            // 结束查找
            if (iCur == stop) {
                // 双数/2
                if (d) {
                    if (i1 < length1 && i2 == 0) {
                        if (i1 == length1 - 1) {
                            return (double) (nums1[i1] + nums2[0]) / 2;
                        } else {
                            return (double) (nums1[i1] + nums1[i1 + 1]) / 2;
                        }
                    }
                    if (i2 < length2 && i1 == 0) {
                        if (i2 == length2 - 1) {
                            return (double) (nums1[0] + nums2[i2]) / 2;
                        } else {
                            return (double) (nums2[i2] + nums2[i2 + 1]) / 2;
                        }
                    }
                    return (double) (nums1[i1] + nums2[i2]) / 2;
                } else {
                    // 单数
                    return lu == 2 ? nums1[i1] : nums2[i2];
                }
            }
            // 继续查找
            if (i1 == length1 - 1) {
                i2 = i2 + 1;
                lu = 2;
            } else if (i2 == length2 - 1) {
                i1 = i1 + 1;
                lu = 1;
            } else if (nums1[i1] < nums2[i2]) {
                i1 = i1 + 1;
                lu = 1;
            } else {
                i2 = i2 + 1;
                lu = 2;
            }
            iCur = iCur + 1;
        }
    }

    public static void main(String[] args) {
        double medianSortedArrays = findMedianSortedArrays(new int[]{0, 0, 0, 0, 0}, new int[]{-1, 0, 0, 0, 0, 0, 1});
        System.out.println(medianSortedArrays);
    }
}
