package algorithm.前K个高频词;

import java.util.Arrays;

/**
 * 一个大文件，每行有一个单词，求出现频次最多的前 K 个单词
 * 思路：分治法 - 字典树 - 小顶堆
 *
 * 1 切分小文件，可以用hash方式
 * 2 存入 TrieTree 计算频次，每次插入后，都更新下小顶堆
 * 3 一个小顶堆，将每个分片的结果入堆，存入前 k 词频的元素。
 *
 * 其他
 * 小工作空间下的 "Heavy Hitter" 问题
 * 1 Misra-Gries 算法.
 * 2 Count-Min Sketch.
 * 3 Count-Sketch. 类似 Count-Min Sketch
 * 4 还有基于计数的 Frequent, LossyCounting, SpaceSaving 等算法.
 */
public class Frequency_k {

    static class Trie {
        char value; // 词
        int frequency; // frequency 表示以当前单词结尾的单词数量。
        private Trie[] children;

        public Trie(char c) {
            value = c;
            frequency = 0;
            children = new Trie[26];
        }
        // 返回频次
        public int insert(String word) {
            int result = 0;
            Trie node = this;
            for (int i=0; i<word.length(); i++) {
                char c = word.toCharArray()[i];
                int index = c - 'a';
                // 叶节点
                if (null == node.children[index]) {
                    node.children[index] = new Trie(c);
                }
                // 到达低端
                if (i == word.length()-1) {
                    node.children[index].frequency++;
                    result = node.children[index].frequency;
                }
                node = node.children[index];
            }
            return result;
        }
    }

    static class MinHeap {
        private int[] array; // todo 将int改为object 增加一个word信息
        private int size;

        public MinHeap(int k) {
            array = new int[k];
            size = 0;
        }
        /** 下沉 */
        private int fixDown() {
            int f = 0 ; //父节点的index
            int k ;  //较小者子节点的index
            while((k = (f << 1) + 1) < size) { //至少存在左子节点
                if(k < size - 1) {   //存在右子节点
                    if (array[k] > array[k + 1]) k++; //左右子节点进行比较。
                }
                if(array[f] <= array[k]) break; //父节点小于较小者子节点，则找到合适的位置，退出循环
                int temp = array[f] ; array[f] = array[k]; array[k] = temp ;
                f = k ;
            }
            return f;
        }
        /** 上浮 */
        private void fixUp() {
            int ci = size - 1;
            int pi = (ci - 1) / 2;
            int temp = array[ci];
            while (ci > 0 && temp < array[pi]) {
                array[ci] = array[pi];
                ci = pi;
                pi = (pi - 1) / 2;
            }
            array[ci] = temp;
        }
        /** 移除并获取一个堆顶元素 */
        public int pop() {
            if(size <= 0)
                throw new IllegalStateException("不存在元素");
            int value = array[0];
            array[0] = array[--size] ; //将最后一个元素提到堆顶
            array[size] = 0 ; //清空最后一个的数据
            fixDown(); //下沉操作
            return value;
        }
        /** 添加一个元素在最小堆中上 */
        public void push(int item) {
            if (size >= array.length)
                array = Arrays.copyOf(array, size << 1);
            array[size++] = item;
            fixUp();
        }
    }

    public static void main(String[] args) {
        String[] ss = new String[]{"a", "ab", "ab", "abc", "abc", "abc", "abcd", "abcd"};
        int k = 3;
        MinHeap minHeap = new MinHeap(k);
        Trie trie = new Trie(' ');
        for (String s : ss) {
            int f = trie.insert(s);
            System.out.println(f);
            if (f > minHeap.array[0]) {
                if (minHeap.size == minHeap.array.length) {
                    minHeap.pop();
                }
                minHeap.push(f);
            }
        }

        for (int i=0; i<minHeap.array.length; i++) {
            System.out.print(minHeap.array[i] + " ");
        }
    }
}
