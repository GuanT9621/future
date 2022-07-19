package leetcode;

/**
 * 有 3n + 1 个 100个 灯泡，每个灯泡有打开和关闭状态，
 * 按下一次中间灯泡变为 暗-亮-暗，再按变为 亮-暗-亮，
 * 设计算法，使最后所以灯泡全部亮，输出每次按的灯泡位置。
 * 一开始灯泡的状态随机
 *
 * 思路 向后移0
 * 结束的上一个状态依次为
 * 1100011
 * 1011011
 * 可以认为"000"等于"111"，"0110"等于"1111"
 */
public class S_lightAll {


    public void light(int[] lights) {
        int n = lights.length;
        while (true) {
            boolean end = true;
            for (int i = 0; i < n; i++) {
                if (lights[i] == 0) {
                    lights[i] = 1;
                    lights[(i - 1 + n) % n] = 1 - lights[(i - 1 + n) % n];
                    lights[(i + 1 + n) % n] = 1 - lights[(i + 1 + n) % n];
                    end = false;
                }
            }
            for (int light : lights) {
                System.out.print(light + ",");
            }
            System.out.println();
            if (end) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        int[] lights = new int[] {0, 1, 1, 0, 1, 0, 1, 0, 0, 1};
        new S_lightAll().light(lights);
        for (int light : lights) {
            System.out.print(light + ", ");
        }
    }

}
