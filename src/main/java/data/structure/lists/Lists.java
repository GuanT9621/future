package data.structure.lists;

import java.util.ArrayList;
import java.util.List;

public class Lists {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(50);
        list.add("1");
        System.out.println("list size : " + list.size());
        System.out.println("list 33 element : " + list.get(33));
    }

}
