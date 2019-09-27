package data.structure.lists.forloop;

import java.util.ArrayList;
import java.util.List;

/**
 * foreach 只受到 size 影响， 不受到 elementData.length 影响
 */
public class ForeachTest {

    public static void main(String[] args) {
        List<Object> list = new ArrayList<>(20);
        for (int i=0; i<10; i++) {
            list.add(null);
        }
        for (Object obj : list) {
            System.out.println(obj);
        }
    }
}
