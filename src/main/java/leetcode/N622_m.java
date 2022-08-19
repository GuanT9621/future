package leetcode;

/**
 * wotamadetidoumeikandong
 */
public class N622_m {


    class MyCircularQueue {
        int[] array;
        int max;
        int p;

        public MyCircularQueue(int k) {
            array = new int[k];
            max = k;
            p = 0;
        }

        public boolean enQueue(int value) {
            if (isFull()) return false;
            array[p++] = value;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) return false;
            System.arraycopy(array, 1, array, 0, --p);
            return true;
        }

        public int Front() {
            if (isEmpty()) return -1;
            return array[0];
        }

        public int Rear() {
            if (isEmpty()) return -1;
            return array[p - 1];
        }

        public boolean isEmpty() {
            return p == 0;
        }

        public boolean isFull() {
            return max == p;
        }
    }

}
