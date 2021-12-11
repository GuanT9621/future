package leetcode;

/**
 * 计算化学表达式里某个元素的数量 如 HN3(H2O)4N3OH 中 H 元素的数量为 10
 * 思路：递归
 * 注意嵌套括号时的处理
 */
public class S_Chemistry {

    public static int countItem(char[] chars, char target) {
        if (0 == chars.length) return 0;
        if (1 == chars.length) return target == chars[0] ? 1 : 0;
        int count = 0 ;
        int loopCount = 0;
        for (int i=0; i<chars.length; i++) {
            if (target == chars[i]) {
                loopCount = 1;
            } else if ('(' == chars[i]) {
                //找到对应的 )
                int tmp = 0;
                for (int j=i; j<chars.length; j++) {
                    if ('(' == chars[j]) {
                        tmp = tmp + 1;
                    } else if (')' == chars[j]) {
                        tmp = tmp - 1;
                        //找到了对应的 )
                        if (0 == tmp) {
                            int index = 0;
                            char[] subC = new char[j-i-1];
                            for (int k=i+1; k<j; k++) {
                                subC[index] = chars[k];
                                index = index + 1;
                            }
                            loopCount = countItem(subC, target);
                            i = j;
                        }
                    }
                }
            }
            // 取系数
            if (0 != loopCount && i+1 < chars.length && (chars[i+1] >='0' && chars[i+1] <= '9') ) {
                int x = chars[i+1] - '0';
                loopCount = loopCount * x;
            }
            // 算和
            count = count + loopCount;
            loopCount = 0;
        }
        return count;
    }

    public static void main(String[] args) {
//        char[] chars = "HN3(H2O)4N3OH".toCharArray();
        char[] chars = "HN3((H2O)4N3OH)2NO".toCharArray();
        int count = countItem(chars, 'H');
        System.out.println(count);
    }
}
