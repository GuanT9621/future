package algorithm.normal;

/**
 * 将 IP 转为 Integer
 * 方便对 IP 进行范围查找等工作。
 *
 * 对于IPv4地址0.0.0.0的字符串，就需要7个字节，IPv4为255.255.255.255 的字符串，需要15个字节，也就是说存储一个ip需要占用7~15个字节。
 *
 * IPv4的地址本质上就是32位的二进制串，而一个int类型的数字刚好为4个字节32个bit位，所以刚好可以用一个int类型的数字转表示IPv4地址。
 * 所以，我们可以用4个字节的int数字表示一个ip地址，这样可以大大节省空间。
 *
 */
public class IP2Integer {

    /**
     * 对于ipv4地址： 192.168.1.3：
     * 每段都用二进制表示： 192(10) = 11000000(2) ; 168(10) = 10101000(2) ; 1(10) = 00000001(2) ; 3(10) = 00000011(2) 。
     * 所以连在一起就是：11000000101010000000000100000011，对应的int数字就是-1062731775 。
     *
     * 具体算法分析：
     * 192左移24位： 11000000 00000000 00000000 00000000
     * 168左移16位： 00000000 10101000 00000000 00000000
     * 001左移08位： 00000000 00000000 00000001 00000000
     * 003左移00位： 00000000 00000000 00000000 00000011
     * 按位或结果 ： 11000000 10101000 00000001 00000011
     * 即 -1062731517
     */
    public static int ip2Int(String ip) {
        String[] ips = ip.split("\\.");
        int ip1 = Integer.parseInt(ips[0]);
        int ip2 = Integer.parseInt(ips[1]);
        int ip3 = Integer.parseInt(ips[2]);
        int ip4 = Integer.parseInt(ips[3]);
        return (ip1 << 24) | (ip2 << 16) | (ip3 << 8) | (ip4);
    }

    public static String int2Ip(int ip) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        boolean needPoint = false;
        for (int i = 0; i < 4; i++) {
            if (needPoint) {
                sb.append('.');
            }
            needPoint = true;
            int offset = 8 * (3 - i);
            num = (ip >> offset) & 0xff;
            sb.append(num);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String ip = "192.168.1.3";

        int iip = ip2Int(ip);

        System.out.println(iip);

        System.out.println(int2Ip(iip));
    }

}
