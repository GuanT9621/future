package algorithm.回溯算法;

/**
 * 数独问题，同样是一个经典的回溯算法
 * 在 9 x 9 的格子里填数字，满足横竖都为1-9即可。
 *
 */
public class Sudoku {

    public void sudokuAns(int[][] table) {

    }

    private void backtracking() {

    }

    public static void main(String[] args) {
        int[][] table = new int[9][9];
        new Sudoku().sudokuAns(table);
        for (int[] ints : table) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

}
