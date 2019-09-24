package data.structure.lists;

import java.util.ArrayList;
import java.util.List;

public class Lists {


    public static void main(String[] args) {

        /**
         * ArrayList有无初始化的差别
         */
        int range = 10000000;

        List<Integer> initialList = new ArrayList<>(range);
        long s1 = System.nanoTime();
        for (int i=0; i<range; i++) {
            initialList.add(i);
        }
        System.out.println(System.nanoTime() - s1);

//        List<Integer> list = new ArrayList<>();
//        long s2 = System.nanoTime();
//        for (int i=0; i<range; i++) {
//            list.add(i);
//        }
//        System.out.println(System.nanoTime() - s2);

    }

}
