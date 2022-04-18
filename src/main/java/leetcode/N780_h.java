package leetcode;

/**
 * https://leetcode-cn.com/problems/reaching-points/
 * 到达终点
 * 给定四个整数 sx , sy ，tx 和 ty，如果通过一系列的转换可以从起点 (sx, sy) 到达终点 (tx, ty)，则返回 true，否则返回 false。
 * 从点 (x, y) 可以转换到 (x, x+y)  或者 (x+y, y)。
 *
 * 思路 逆向倒推
 *  更项减损
 *  辗转相除
 */
public class N780_h {

    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx > sx || ty > sy) {
            if (tx == ty) {
                break;
            }
            if (tx > ty) {
                tx = tx - ty;
            } else {
                ty = ty - tx;
            }
        }
        return tx == sx && ty == sy;
    }

    public boolean reachingPoints2(int sx, int sy, int tx, int ty) {
        while (tx > sx && ty > sy) {
            if (tx == ty) {
                break;
            }
            if (tx > ty) {
                tx = tx % ty;
            } else {
                ty = ty % tx;
            }
        }
        if (tx == sx && ty == sy) {
            return true;
        } else if (tx == sx) {
            return ty > sy && (ty - sy) % tx == 0;
        } else if (ty == sy) {
            return tx > sx && (tx - sx) % ty == 0;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        boolean b = new N780_h().reachingPoints(9, 10, 9, 19);
        System.out.println(b);
    }

}
