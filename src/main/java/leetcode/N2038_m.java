package leetcode;

/**
 * https://leetcode-cn.com/problems/remove-colored-pieces-if-both-neighbors-are-the-same-color/
 * 如果相邻两个颜色均相同则删除当前颜色
 * 总共有 n 个颜色片段排成一列，每个颜色片段要么是 'A' 要么是 'B' 。给你一个长度为 n 的字符串 colors ，其中 colors[i] 表示第 i 个颜色片段的颜色。
 * Alice 和 Bob 在玩一个游戏，他们 轮流 从这个字符串中删除颜色。Alice 先手 。
 * 如果一个颜色片段为 'A' 且相邻两个颜色都是颜色 'A' ，那么 Alice 可以删除该颜色片段。Alice 不可以 删除任何颜色 'B' 片段。
 * 如果一个颜色片段为 'B' 且相邻两个颜色都是颜色 'B' ，那么 Bob 可以删除该颜色片段。Bob 不可以 删除任何颜色 'A' 片段。
 * Alice 和 Bob 不能 从字符串两端删除颜色片段。
 * 如果其中一人无法继续操作，则该玩家 输 掉游戏且另一玩家 获胜 。
 * 假设 Alice 和 Bob 都采用最优策略，如果 Alice 获胜，请返回 true，否则 Bob 获胜，返回 false。
 *
 * 思路 模拟
 * 1 不考虑两头字符，字符串中不存在两个以上的A、B 时，一方获胜。
 * 2 转换下，我们只需要考虑两人删除的次数即可，次数多的人必胜。
 * 3 因为最短的情况为 AAA 删除 A 得到 AA，就不能在删除了，所以不会导致合并，所以可以简单统计 AAA 和 BBB 即可。
 */
public class N2038_m {

    public boolean winnerOfGame(String colors) {
        char[] chars = colors.toCharArray();
        int n = chars.length;
        int a = 0;
        int b = 0;
        for (int i=1; i < n - 1; i++) {
            if (chars[i - 1] == chars[i] && chars[i] ==  chars[i + 1]) {
                if (chars[i] == 'A') {
                    a++;
                } else {
                    b++;
                }
            }
        }
        return a > b;
    }

    public static void main(String[] args) {
        String colors = "AAABABB";
        boolean b = new N2038_m().winnerOfGame(colors);
        System.out.println(b);
    }

}
