package jvm.clinit;

public class SlotGc {

    public static void main(String[] args) {

        {
            byte[] placeHolder = new byte[64 * 1024 * 1024];
        }

        int a = 1;

        System.gc();

    }

}
