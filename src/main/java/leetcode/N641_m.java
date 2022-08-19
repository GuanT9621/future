package leetcode;

/**
 * @author guanhao02
 * @date 2022/8/15
 */
public class N641_m {


    class MyCircularDeque {
        int[] array;
        int start;
        int end;
        int size;

        public MyCircularDeque(int k) {
            array = new int[k];
            start = 1;
            end = 0;
            size = 0;
        }

        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }
            size++;
            start--;
            start = (start + array.length) % array.length;
            array[start] = value;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }
            size++;
            end++;
            end = (end + array.length) % array.length;
            array[end] = value;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }
            size--;
            start++;
            start = (start + array.length) % array.length;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }
            size--;
            end--;
            end = (end + array.length) % array.length;
            return true;
        }

        public int getFront() {
            if (isEmpty()) {
                return -1;
            }
            return array[start];
        }

        public int getRear() {
            if (isEmpty()) {
                return -1;
            }
            return array[end];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == array.length;
        }
    }


    public static void main(String[] args) {
        MyCircularDeque obj = new N641_m().new MyCircularDeque(5);
        boolean param_1 = obj.insertFront(7);
        boolean param_2 = obj.insertLast(0);
        System.out.println(obj.getFront());
        System.out.println(obj.getRear());
        boolean param_3 = obj.deleteFront();
        boolean param_4 = obj.deleteLast();
        System.out.println(obj.getFront());
        System.out.println(obj.getRear());
        boolean param_7 = obj.isEmpty();
        boolean param_8 = obj.isFull();
    }

}
