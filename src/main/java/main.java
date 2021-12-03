import java.math.BigDecimal;
import java.math.RoundingMode;

public class main {
    private static Double calc(Double d1, Double d2) {
        if (null == d1 || null == d2) {
            return null;
        }
        double d3 = (d2 - d1) / d1;
        return trim(d3);
    }

    /**
     * isNaN 意味 not a number
     * isInfinite 意味 无穷大或无穷小
     * isFinite 意味 有限的数字 等同于 ! isNaN && ! isInfinite
     */
    public static Double trim(Double d) {
        if (null == d) {
            return null;
        }
        if (Double.isNaN(d) || Double.isInfinite(d) || 0D == d) {
            return 0D;
        }
        if (Double.isFinite(d)) {
            return BigDecimal.valueOf(d).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
        }
        return 0D;
    }

    public static void main(String[] args) {
        calc(0D, 78.138D);
    }

}
