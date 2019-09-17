package algorithm.normal;

/**
 * https://www.meiwen.com.cn/subject/ciweeqtx.html
 *
 * 有序数组中平方取值的种类
 * 给你一个有序整数数组，数组中的数可以是正数、负数、零，请实现一个函数，这个函数返回一个整数：返回这个数组所有数的平方值中有多少种不同的取值
 */
public class DoublePoint {

    public static int solution(int[] arr) {
        int i = 0;
        int j = arr.length-1;
        int count = 0;
        while (i <= j) {
            int valueI = Math.abs(arr[i]);
            int valueJ = Math.abs(arr[j]);
            if (valueI > valueJ) {
                count++;
                while (i <= j && Math.abs(arr[i]) == valueI) {
                    i++;
                }
            } else if (valueI < valueJ) {
                count++;
                while (i <= j && Math.abs(arr[j]) == valueJ) {
                    j--;
                }
            } else {
                count++;
                while (i <= j && Math.abs(arr[i]) == valueI) {
                    i++;
                }
                while (i <= j && Math.abs(arr[j]) == valueJ) {
                    j--;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] array = new int[]{-1,0,1,2,3};
        int result = solution(array);
        System.out.println("result = " + result);
    }
}
