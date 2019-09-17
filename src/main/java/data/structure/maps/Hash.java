package data.structure.maps;

import java.util.HashMap;
import java.util.Objects;

/**
 * 解决哈希冲突的方案
 *
 * 1 拉链法（链接法、链地址法）
 *      基本思想: 将所有哈希地址为i的元素构成一个称为同义词链的单链表，并将单链表的头指针存在哈希表的第i个单元中，
 *      因而查找、插入和删除主要在同义词链中进行。链地址法适用于经常进行插入和删除的情况。
 *
 * 2 开放定址法（再散列法）
 *      基本思想是: 当关键字key的哈希地址p = H（key）出现冲突时，以p为基础，产生另一个哈希地址p1，如果p1仍然冲突，再以p为基础，产生另一个哈希地址p2，…，
 *      直到找出一个不冲突的哈希地址pi ，将相应元素存入其中。
 *      这种方法有一个通用的再散列函数形式：
 *
 *      Hi =（H（key）+ di）% m , i=1，2，…，n
 *
 *      其中H（key）为哈希函数，m 为表长，di称为增量序列。增量序列的取值方式不同，相应的再散列方式也不同。主要有以下三种：
 *   ① 线性探查
 *      di = 1，2，3，…，m-1；这种方法的特点是：冲突发生时，顺序查看表中下一单元，直到找出一个空单元或查遍全表。
 *      （使用例子：ThreadLocal里面的ThreadLocalMap）
 *   ② 二次探查
 *      di = 1^2，-1^2，2^2，-2^2，…，k^2，-k^2 ( k<=m/2 )；这种方法的特点是：冲突发生时，在表的左右进行跳跃式探测，比较灵活。
 *   ③ 伪随机探测
 *      di = 伪随机数序列；具体实现时，应建立一个伪随机数发生器，（如i=(i+p) % m），生成一个位随机序列，并给定一个随机数做起点，每次去加上这个伪随机数++就可以了。
 *
 * 3 再哈希法
 *      这种方法是同时构造多个不同的哈希函数：
 *      Hi=RH1（key） i=1，2，…，k
 *      当哈希地址Hi=RH1（key）发生冲突时，再计算Hi=RH2（key）……，直到冲突不再产生。这种方法不易产生聚集，但增加了计算时间。
 *
 * 4 建立公共溢出区
 *      基本思想: 将哈希表分为基本表和溢出表两部分，凡是和基本表发生冲突的元素，一律填入溢出表。
 *
 * hash容易碰撞怎么解决
 *      同时使用多个hash算法，计算出不同的hashcode，组合成为一个唯一标识。
 *
 */
public class Hash {

    int hash;
    /**
     * 不同类型的Hash算法
     */
    public Hash() {
        // boolean类型
        boolean b = true;
        hash = b ? 0 : 1;

        // byte、char、short、int类型
        int i = 9;
        hash = (int) i;

        // long类型
        long j = 123L;
        hash = (int) (j ^ (j >>> 32));

        // double类型
        double d = 53.22D;
        long temp = Double.doubleToLongBits(d);
        hash = (int) (temp ^ (temp >>> 32));
    }

    /**
     * 按照下面的公式，把上面得到的hash组合到result中：
     */
    public void hashcode() {
        int result = 5;
        result = 37 * result + hash;
    }

    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        ThreadLocal tl = new ThreadLocal();
    }

}
