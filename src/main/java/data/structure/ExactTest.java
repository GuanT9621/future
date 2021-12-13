package data.structure;

/**
 * 溢出判断
 */
public class ExactTest {

    public static int addExact(int x, int y) {
        int r = x + y;
        if (x > 0 && y > 0 ) {
            boolean b = Integer.MAX_VALUE - x < y;
            if (b) {
                throw new ArithmeticException("integer overflow +");
            }
        }
        if (x < 0 && y < 0) {
            boolean b = Integer.MIN_VALUE - x > y;
            if (b) {
                throw new ArithmeticException("integer overflow -");
            }
        }
        return r;
    }

    public static void main(String[] args) {
        int x = Integer.MAX_VALUE;
        int y = 9;
        addExact(x, y);
    }
}
