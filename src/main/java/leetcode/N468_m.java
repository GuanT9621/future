package leetcode;

/**
 * https://leetcode-cn.com/problems/validate-ip-address/
 * 验证IP地址
 * 给定一个字符串 queryIP。如果是有效的 IPv4 地址，返回 "IPv4" ；如果是有效的 IPv6 地址，返回 "IPv6" ；如果不是上述类型的 IP 地址，返回 "Neither" 。
 *
 * 有效的IPv4地址 是 “x1.x2.x3.x4” 形式的IP地址。 其中 0 <= xi <= 255 且 xi 不能包含 前导零。
 * 例如: “192.168.1.1” 、 “192.168.1.0” 为有效IPv4地址， “192.168.01.1”、“192.168.1.00”、“192.168@1.1” 为无效IPv4地址。
 *
 * 一个有效的IPv6地址 是一个格式为“x1:x2:x3:x4:x5:x6:x7:x8” 的IP地址，其中:1 <= xi.length <= 4
 * xi 是一个 十六进制字符串 ，可以包含数字、小写英文字母( 'a' 到 'f' )和大写英文字母( 'A' 到 'F' )。在 xi 中允许前导零。
 * 比如，2001:0db8:85a3::8A2E:0370:7334 和 2001:db8:85a3:0:0:8A2E:0370:7334 是有效的 IPv6 地址。而 2001:0db8:85a3::8A2E:037j:7334 和 02001:0db8:85a3:0000:0000:8a2e:0370:7334 是无效的。
 *
 * 思路一
 * 分情况处理即可
 *
 */
public class N468_m {

    public static String validIPAddress(String queryIP) {
        if (queryIP.contains(":")) {
            return validIPv6Address(queryIP);
        } else {
            return validIPv4Address(queryIP);
        }
    }

    private static String validIPv4Address(String queryIP) {
        if (queryIP.chars().filter(ch -> ch == '.').count() != 3)
            return "Neither";
        String[] nums = queryIP.split("\\.", -1);
        for (String x : nums) {
            if (x.length() == 0 || x.length() > 3) return "Neither";
            if (x.charAt(0) == '0' && x.length() != 1) return "Neither";
            for (char ch : x.toCharArray()) {
                if (! Character.isDigit(ch)) return "Neither";
            }
            if (Integer.parseInt(x) > 255) return "Neither";
        }
        return "IPv4";
    }

    private static String validIPv6Address(String queryIP) {
        if (queryIP.chars().filter(ch -> ch == ':').count() != 7)
            return "Neither";
        String[] nums = queryIP.split(":", -1);
        String hexdigits = "0123456789abcdefABCDEF";
        for (String x : nums) {
            if (x.length() == 0 || x.length() > 4) return "Neither";
            for (Character ch : x.toCharArray()) {
                if (hexdigits.indexOf(ch) == -1) return "Neither";
            }
        }
        return "IPv6";
    }

    public static void main(String[] args) {
        String ip = "2001:db8:85a3:0::8a2E:0370:7334";
        String s = validIPAddress(ip);
        System.out.println(s);
    }

}
