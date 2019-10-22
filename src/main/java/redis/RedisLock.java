package redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 *
 * 本对象实现了一个普通的锁，lock tryLock unLock 方法。
 *
 *
 * Redis的setnx命令是当key不存在时设置key，但setnx不能同时完成expire设置失效时长，不能保证setnx和expire的原子性。
 * 我们可以使用set命令完成 setnx 和 expire 的操作，并且这种操作是原子操作。
 *
 * 下面是set命令的可选项：
 * set key value [EX seconds] [PX milliseconds] [NX|XX]
 * EX seconds：设置失效时长，单位秒
 * PX milliseconds：设置失效时长，单位毫秒
 * NX：key不存在时设置value，成功返回OK，失败返回(nil)
 * XX：key存在时设置value，成功返回OK，失败返回(nil)
 */
public class RedisLock {

    // 判断当前lock是否是redis锁的拥有者
    private boolean owner = false;
    private String key;
    private String value;
    private long aliveTime = 10;
    private TimeUnit timeUnit = TimeUnit.SECONDS;
    private RedisTemplate redisTemplate;

    public RedisLock() {
        key = "redisLock";
        value = this.toString();
    }

    public boolean lock() throws InterruptedException {
        ValueOperations vo = redisTemplate.opsForValue();
        boolean setOk = false;
        while (!setOk) {
            Boolean set = vo.setIfAbsent(key, value, aliveTime, timeUnit);
            if (null == set) {
                throw new NullPointerException();
            }
            setOk = set;
            Thread.sleep(500);
        }
        owner = true;
        return true;
    }

    public boolean tryLock(long seconds) throws InterruptedException {
        final long startTime = System.currentTimeMillis();
        ValueOperations vo = redisTemplate.opsForValue();
        Boolean setOk = false;
        long nowTime = System.currentTimeMillis();
        while ( !setOk && (nowTime - startTime) < seconds) {
            Boolean set = vo.setIfAbsent(key, value, aliveTime, timeUnit);
            if (null == set) {
                throw new NullPointerException();
            }
            setOk = set;
            nowTime = System.currentTimeMillis();
            Thread.sleep(500);
        }
        owner = setOk;
        return setOk;
    }

    public boolean unlock() {
        if (owner) {
            Boolean deletedOk = redisTemplate.delete(key);
            if (null == deletedOk) {
                throw new NullPointerException();
            }
            return deletedOk;
        }
        return false;
    }

}
