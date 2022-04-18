package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/array-of-doubled-pairs/
 * 二倍数对数组
 * 给定一个长度为偶数的整数数组 arr，只有对 arr 进行重组后可以满足 “对于每个 0 <= i < len(arr) / 2，
 * 都有 arr[2 * i + 1] = 2 * arr[2 * i]” 时，返回 true；否则，返回 false。
 *
 * 思路 排序遍历 哈希表
 */
public class N954_m {

    public boolean canReorderDoubled(int[] arr) {
        Arrays.sort(arr);
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.getOrDefault(num * 2, 0) > 0) {
                map.put(num * 2, map.get(num * 2) - 1);
                continue;
            }
            if (num % 2 == 0) {
                if (map.getOrDefault(num / 2, 0) > 0) {
                    map.put(num / 2, map.get(num / 2) - 1);
                    continue;
                }
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return map.values().stream().mapToInt(Integer::intValue).sum() == 0;
    }

}
