package classic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 在集合中添加对象时，如果要保证对象不一样，要及时地 new 一个对象。
 * 因为集合存的是对象的引用。
 *
 */
public class ForeachTest {

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setName("A");
        list.add(user);
        list.add(user);

        /**
         * 该方法预期给每个User一个独一无二的ID
         * 但是可能会出现问题。
         */
        Random r = new Random(100);
        for (User u : list) {
            Long id = r.nextLong();
            System.out.println(id);
            u.setId(id);
        }
        for (User u : list) {
            System.out.println(user.toString());
        }
    }

    static class User {
        private Long id;
        private String name;

        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
        @Override
        public boolean equals(Object o) {
            return true;
        }
        @Override
        public int hashCode() {
            return 1;
        }
    }

}
