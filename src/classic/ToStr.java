package classic;

public class ToStr {

    @Override
    public String toString() {
        System.out.printf("toString : " + this);
        return "return";
    }

    public static void main(String[] args) {
        ToStr toStr = new ToStr();
        System.out.printf(toStr.toString());
    }

}
