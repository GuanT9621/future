package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * https://leetcode-cn.com/problems/insert-delete-getrandom-o1/
 *
 * O(1) 时间插入、删除和获取随机元素
 * 实现RandomizedSet 类：
 * RandomizedSet() 初始化 RandomizedSet 对象
 * bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
 * bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
 * int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
 * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
 *
 * 思路 hashMap + arraylist
 * hashMap 实现 O1的 插入删除
 * arraylist 实现 O1的 随机获取
 */
public class N380_m {

    class RandomizedSet {
        ArrayList<Integer> list;
        HashMap<Integer, Integer> map;
        Random random;

        public RandomizedSet() {
            list = new ArrayList<>();
            map = new HashMap<>();
            random = new Random();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            map.put(val, list.size());
            list.add(val);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            Integer index = map.get(val);
            list.set(index, list.get(list.size() - 1));
            map.put(list.get(index), index);
            list.remove(list.size() - 1);
            map.remove(val);
            return true;
        }

        public int getRandom() {
            int randomIndex = random.nextInt(list.size());
            return list.get(randomIndex);
        }
    }

}
