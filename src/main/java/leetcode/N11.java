package leetcode;

/**
 * https://leetcode-cn.com/problems/container-with-most-water/
 * 盛最多水的容器，具体题目可以看链接。
 * 思路：双指针。将start的指针和end的指针，一起向对方缩小，每次缩小 X 轴都会-1。
 * 面积公式为 S(i,j)=min(h[i],h[j])×(j−i)
 * 若向内 移动短板 ，水槽的短板 min(h[i], h[j])min(h[i],h[j]) 可能变大，因此下个水槽的面积 可能增大 。
 * 若向内 移动长板 ，水槽的短板 min(h[i], h[j])min(h[i],h[j])​ 不变或变小，因此下个水槽的面积 一定变小 。
 * 因此，初始化双指针分列水槽左右两端，循环每轮将短板向内移动一格，并更新面积最大值，直到两指针相遇时跳出；即可获得最大面积
 */
public class N11 {

    public static int maxArea(int[] height) {
        if (height.length == 0) return 0;
        int s = 0;
        int e = height.length - 1;
        int maxArea = 0;
        while (s < e) {
            int min = Math.min(height[s], height[e]);
            maxArea = Math.max(maxArea, min * (e - s));
            if (height[s] == min) {
                s++;
            } else {
                e--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] height = {4,3,2,1,4};
        int max = maxArea(height);
        System.out.println(max);
    }

}
