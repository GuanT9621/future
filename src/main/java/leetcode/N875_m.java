package leetcode;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/koko-eating-bananas/
 * 爱吃香蕉的珂珂
 * 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
 * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。
 * 如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
 * 1 <= piles.length <= 10^4
 * piles.length <= h <= 10^9
 * 1 <= piles[i] <= 10^9
 *
 * 思路 二分查找 k
 * k 的范围在 [1, Max(⌈piles / h⌉)]
 */
public class N875_m {

    public int minEatingSpeed(int[] piles, int h) {
        int n = piles.length;
        if (n == 1) {
            return (piles[0] + h - 1) / h;
        }
        int min = 1;
        int max = 0;
        for (int pile : piles) {
            max = Math.max(max, pile);
        }
        if (n == h) {
            return max;
        }
        int res = max;
        while (min < max) {
            int speed = (max + min) / 2;
            long time = time(piles, speed);
            if (time <= h) {
                res = speed;
                max = speed;
            } else if (time > h) {
                min = speed + 1;
            }
        }
        return res;
    }

    private long time(int[] piles, int speed) {
        long time = 0;
        for (int pile : piles) {
            time += ((pile + speed - 1) / speed);
        }
        return time;
    }

}
