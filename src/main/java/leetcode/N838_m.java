package leetcode;

/**
 * https://leetcode-cn.com/problems/push-dominoes/
 * 推多米诺
 * n 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。在开始时，同时把一些多米诺骨牌向左或向右推。
 * 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
 * 如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。
 * 就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。
 * 给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：
 * dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
 * dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧，
 * dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。
 * 返回表示最终状态的字符串。
 *
 * 思路 双指针遍历
 * 1 遇到L 上个指针是L 则倒向左边；R 则倒向中间；
 * 2 遇到R 上个指针是R 倒向右边；
 * 3 遇到. 最后一位时，上个指针是R 倒向右边；
 *
 * 思路 广度优先搜索
 * 我们用一个队列 q 模拟搜索的顺序；数组 time 记录骨牌翻倒或者确定不翻倒的时间，翻倒的骨牌不会对正在翻倒或者已经翻倒的骨牌施加力；
 * 数组 force 记录骨牌受到的力，骨牌仅在受到单侧的力时会翻倒。
 */
public class N838_m {

    public String pushDominoes(String dominoes) {
        char[] chars = dominoes.toCharArray();
        int length = dominoes.length();
        int prev = 0;
        for (int i=0; i<length; i++) {
            if (chars[i] == 'L') {
                if (chars[prev] == 'R') { // prev=R 倒向中间
                    int lp = prev;
                    int rp = i;
                    while (lp < rp) {
                        chars[lp] = 'R';
                        chars[rp] = 'L';
                        lp++;
                        rp--;
                    }
                } else { // prev=L or . 倒向左边
                    for (int j=prev; j<i; j++)
                        chars[j] = 'L';
                }
                prev=i; // 重新定位prev
            } else if (chars[i] == 'R') {
                if (chars[prev] == 'R') { // prev=R 倒向右边
                    for (int j=prev; j<i; j++)
                        chars[j] = 'R';
                }  // prev=L or . 没有倒向
                prev=i; // 重新定位prev
            } else if (chars[i] == '.' && i == length-1) {
                if  (chars[prev] == 'R') { // prev=R 倒向右边
                    for (int j=prev; j<=i; j++)
                        chars[j] = 'R';
                }
            }
        }
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        String dominoes = ".L.R.";
        String s = new N838_m().pushDominoes(dominoes);
        System.out.println(s);
    }
}
