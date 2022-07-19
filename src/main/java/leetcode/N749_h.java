package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 病毒扩散得很快，现在你的任务是尽可能地通过安装防火墙来隔离病毒。
 * 假设世界由 m x n 的二维矩阵 isInfected 组成， isInfected[i][j] == 0 表示该区域未感染病毒，而 isInfected[i][j] == 1 表示该区域已感染病毒。可以在任意 2 个相邻单元之间的共享边界上安装一个防火墙（并且只有一个防火墙）。
 * 每天晚上，病毒会从被感染区域向相邻未感染区域扩散，除非被防火墙隔离。现由于资源有限，每天你只能安装一系列防火墙来隔离其中一个被病毒感染的区域（一个区域或连续的一片区域），且该感染区域对未感染区域的威胁最大且 保证唯一 。
 * 你需要努力使得最后有部分区域不被病毒感染，如果可以成功，那么返回需要使用的防火墙个数; 如果无法实现，则返回在世界被病毒全部感染时已安装的防火墙个数。
 *
 * 思路 动态规划
 * 可以理解为是对病毒区域选择顺序的一个动态规划，难点在于数据的切分和处理。
 * 我们可以将隔离的病毒设为-1，无病毒设为0，可以扩散的病毒为1
 *
 * 思路 广度优先
 * 之所以不用动态规划，是因为 题目中说明了 "该感染区域对未感染区域的威胁最大且 保证唯一"
 * 所以要按照已有顺序进行隔离。
 * 1 每次查询出 威胁最大的区域，以及对应的隔离墙数量
 * 2 累加隔离墙数量，将该区域数字改为 -1
 * 3 进行依次传染（注意不要递归传染）
 * 4 循环1-3步
 */
public class N749_h {

    class Solution {

        int[][] grids = new int[][] {{1,0}, {0,1}, {-1,0}, {0,-1}};

        public int containVirus(int[][] isInfected) {
            Map<Integer, List<int[]>> split = split(isInfected);
            int ans = 0;
            while (!split.isEmpty()) {
                int max = 0;
                for (Integer key : split.keySet()) {
                    max = key;
                }
                List<int[]> ints = split.get(max);
                ans += max;
                for (int[] block : ints) {
                    isInfected[block[0]][block[1]] = -1;
                }
                expose(isInfected);
                split = split(isInfected);
            }
            return ans;
        }

        private void expose(int[][] isInfected) {
            for (int i = 0; i < isInfected.length; i++) {
                for (int j = 0; j < isInfected[0].length; j++) {
                    if (isInfected[i][j] == 1) {
                        for (int[] grid : grids) {
                            int i1 = i + grid[0];
                            int j1 = j + grid[1];
                            if (0 <= i1 && i1 < isInfected.length && 0 <= j1 && j1 < isInfected[0].length) {
                                if (isInfected[i1][j1] == 0) {
                                    isInfected[i1][j1] = 2;
                                }
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < isInfected.length; i++) {
                for (int j = 0; j < isInfected[0].length; j++) {
                    if (isInfected[i][j] == 2) {
                        isInfected[i][j] = 1;
                    }
                }
            }
        }

        private Map<Integer, List<int[]>> split(int[][] isInfected) {
            int[][] clone = copy(isInfected);
            int zero = 0;
            // value is points, key is block wall number
            Map<Integer, List<int[]>> map = new HashMap<>();
            for (int i = 0; i < clone.length; i++) {
                for (int j = 0; j < clone[0].length; j++) {
                    if (clone[i][j] == 1) {
                        List<int[]> tmp = new ArrayList<>();
                        int wall = 0;
                        Set<String> zeroSet = new HashSet<>();
                        Deque<int[]> deque = new LinkedList<>();
                        deque.offer(new int[]{i, j});
                        while (!deque.isEmpty()) {
                            int[] poll = deque.poll();
                            if (clone[poll[0]][poll[1]] != 1) {
                                continue;
                            }
                            clone[poll[0]][poll[1]] = -1;
                            tmp.add(new int[]{poll[0], poll[1]});
                            for (int[] grid : grids) {
                                int i1 = poll[0] + grid[0];
                                int j1 = poll[1] + grid[1];
                                if (0 <= i1 && i1 < clone.length && 0 <= j1 && j1 < clone[0].length) {
                                    if (clone[i1][j1] == 1) {
                                        deque.add(new int[]{i1, j1});
                                    } else if (clone[i1][j1] == 0) {
                                        wall++;
                                        zeroSet.add(i1 + "," + j1);
                                    }
                                }
                            }
                        }
                        if (zeroSet.size() > zero) {
                            zero = zeroSet.size();
                            map.clear();
                            map.put(wall, tmp);
                        }
                    }
                }
            }
            return map;
        }

        private int[][] copy(int[][] o) {
            int[][] ints = new int[o.length][o[0].length];
            for (int i = 0; i < o.length; i++) {
                for (int j = 0; j < o[0].length; j++) {
                    ints[i][j] = o[i][j];
                }
            }
            return ints;
        }

    }


    public static void main(String[] args) {
        // 4 13 38
        int[][] isInfected = new int[][] {
                {1,1,1},
                {1,0,1},
                {1,1,1} };
//        int[][] isInfected = new int[][] {
//                {1,1,1,0,0,0,0,0,0},
//                {1,0,1,0,1,1,1,1,1},
//                {1,1,1,0,0,0,0,0,0} };

//        int[][] isInfected = new int[][] {
//                {0,1,0,1,1,1,1,1,1,0},
//                {0,0,0,1,0,0,0,0,0,0},
//                {0,0,1,1,1,0,0,0,1,0},
//                {0,0,0,1,1,0,0,1,1,0},
//                {0,1,0,0,1,0,1,1,0,1},
//                {0,0,0,1,0,1,0,1,1,1},
//                {0,1,0,0,1,0,0,1,1,0},
//                {0,1,0,1,0,0,0,1,1,0},
//                {0,1,1,0,0,1,1,0,0,1},
//                {1,0,1,1,0,1,0,1,0,1}};
        int i = new N749_h().new Solution().containVirus(isInfected);
        System.out.println(i);
    }

    class Solution2 {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public int containVirus(int[][] isInfected) {
            int m = isInfected.length, n = isInfected[0].length;
            int ans = 0;
            while (true) {
                List<Set<Integer>> neighbors = new ArrayList<Set<Integer>>();
                List<Integer> firewalls = new ArrayList<Integer>();
                for (int i = 0; i < m; ++i) {
                    for (int j = 0; j < n; ++j) {
                        if (isInfected[i][j] == 1) {
                            Queue<int[]> queue = new ArrayDeque<int[]>();
                            queue.offer(new int[]{i, j});
                            Set<Integer> neighbor = new HashSet<Integer>();
                            int firewall = 0, idx = neighbors.size() + 1;
                            isInfected[i][j] = -idx;

                            while (!queue.isEmpty()) {
                                int[] arr = queue.poll();
                                int x = arr[0], y = arr[1];
                                for (int d = 0; d < 4; ++d) {
                                    int nx = x + dirs[d][0], ny = y + dirs[d][1];
                                    if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                                        if (isInfected[nx][ny] == 1) {
                                            queue.offer(new int[]{nx, ny});
                                            isInfected[nx][ny] = -idx;
                                        } else if (isInfected[nx][ny] == 0) {
                                            ++firewall;
                                            neighbor.add(getHash(nx, ny));
                                        }
                                    }
                                }
                            }
                            neighbors.add(neighbor);
                            firewalls.add(firewall);
                        }
                    }
                }

                if (neighbors.isEmpty()) {
                    break;
                }

                int idx = 0;
                for (int i = 1; i < neighbors.size(); ++i) {
                    if (neighbors.get(i).size() > neighbors.get(idx).size()) {
                        idx = i;
                    }
                }
                ans += firewalls.get(idx);
                for (int i = 0; i < m; ++i) {
                    for (int j = 0; j < n; ++j) {
                        if (isInfected[i][j] < 0) {
                            if (isInfected[i][j] != -idx - 1) {
                                isInfected[i][j] = 1;
                            } else {
                                isInfected[i][j] = 2;
                            }
                        }
                    }
                }
                for (int i = 0; i < neighbors.size(); ++i) {
                    if (i != idx) {
                        for (int val : neighbors.get(i)) {
                            int x = val >> 16, y = val & ((1 << 16) - 1);
                            isInfected[x][y] = 1;
                        }
                    }
                }
                if (neighbors.size() == 1) {
                    break;
                }
            }
            return ans;
        }

        public int getHash(int x, int y) {
            return (x << 16) ^ y;
        }
    }

}
