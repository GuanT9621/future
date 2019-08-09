package reflect;

import reflect.Book;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {

    public static void main(String[] args) {

        System.out.println("1 . Show Methods : ");
        Method[] methods = Book.class.getMethods();
        for (Method method : methods) {
            System.out.print(method.getName() + ", ");
        }

        System.out.println("\n\n2 . Show Declared Methods : ");
        Method[] declaredMethods = Book.class.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.print(method.getName() + ", ");
        }

        Book book = new Book("LeanGo", "goodBook");

        System.out.println("\n\n3 . Show Fields : ");
        Field[] fields = Book.class.getFields();
        for (Field field : fields) {
            try {
                System.out.print(field.getName() + " : " + field.get(book) + ", ");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n\n4 . Show Declared Fields : ");
        Field[] declaredFields = Book.class.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                field.set(book, "non-name");
                System.out.print(field.getName() + ":" + field.get(book) + ", ");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n\n5 . Invoke Declared Methods : ");
        for (Method method : declaredMethods) {
            method.setAccessible(true);
            System.out.println("invoke " + method.getName() + " : ");
            try {
                Object obj = method.invoke(book);
                System.out.println("invoke method return : " + obj + "\n");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n6 . Constructor : ");
        Constructor<Book> constructor = null;
        try {
            constructor = Book.class.getConstructor(String.class, String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (null == constructor)
            return;
        Book book1 = null;
        try {
            book1 = constructor.newInstance("LeanGo", "goodBook");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(book1);

        System.out.println("\n\n");
    }

}
