package leetcode;

/**
 * https://leetcode-cn.com/problems/maximum-number-of-achievable-transfer-requests/
 * 最多可达成的换楼请求数目
 * 我们有 n 栋楼，编号从 0 到 n - 1 。每栋楼有若干员工。由于现在是换楼的季节，部分员工想要换一栋楼居住。
 * 给你一个数组 requests ，其中 requests[i] = [fromi, toi] ，表示一个员工请求从编号为 fromi 的楼搬到编号为 toi 的楼。
 * 一开始 所有楼都是满的，所以从请求列表中选出的若干个请求是可行的需要满足 每栋楼员工净变化为 0 。意思是每栋楼 离开 的员工数目 等于 该楼 搬入 的员工数数目。
 * 比方说 n = 3 且两个员工要离开楼 0 ，一个员工要离开楼 1 ，一个员工要离开楼 2 ，如果该请求列表可行，
 * 应该要有两个员工搬入楼 0 ，一个员工搬入楼 1 ，一个员工搬入楼 2 。
 * 请你从原请求列表中选出若干个请求，使得它们是一个可行的请求列表，并返回所有可行列表中最大请求数目。
 * 提示：1 <= n <= 20      1 <= requests.length <= 16      requests[i].length == 2     0 <= fromi, toi < n
 *
 * 思路 深度遍历 + 回溯
 * 该题的思考：类似于判断有向图是否有环 、环的个数、环中元素等问题。
 *          区别是环中的两个节点线只有一条，而本题中两个节点可能有多条连线。
 *
 *
 */
public class N1601_h {

    public int maximumRequests(int n, int[][] requests) {
        int ans = 0;
        int[] marked = new int[requests.length];
        for (int i=0; i < requests.length; i++) {
            int[] request  = requests[i];
            if (request[0] == request[1]) {
                ans++;
                marked[i] = 1;
            } else {

            }
        }
        return ans;
    }

}
