package reflect;

import java.text.NumberFormat;

public class Book {

    public String publicName;
    private String privateName;

    public Book(String publicName, String privateName) {
        this.publicName = publicName;
        this.privateName = privateName;
    }

    public void sayPublicName() {
        System.out.println("Hi，reflect.Book Public Name Is " + publicName);
    }

    private void sayPrivateName() {
        System.out.println("Hi，reflect.Book Private Name is " + privateName);
    }

    public String getPublicName() {
        return this.publicName;
    }

    @Override
    public String toString() {
        return "reflect.Book{" +
                "publicName='" + publicName + '\'' +
                ", privateName='" + privateName + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Double balanceNumber = 12345678.1234;
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        String balance = nf.format(balanceNumber);
        System.out.println(balance);

        String db = Double.toString(balanceNumber);
        System.out.println(db);
    }
}
