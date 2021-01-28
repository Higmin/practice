package com.practice.spring.springbootAndRedis.distributedLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author Higmin
 * @date 2020/4/15 16:25
 * <p>
 * 利用redis 模拟实现分布式锁
 * <p>
 * 思路：
 * 这个主要利用redis的setnx命令进行，setnx："set if not exists"就是如果不存在则成功设置缓存同时返回1，否则返回0 。
 * 在集群模式下，如果多台机器同时对某条数据进行操作，可能会引起数据不一致的情况，比如: 定时任务的执行，多台机器都在跑定时Job，容易发生重复处理数据的情况，
 * 我们采用分布式锁类保证定时任务不会重复执行，当然结合具体业务，我们可以给这个lock加一个过期时间，比如说30分钟执行一次的定时任务，
 * 那么这个过期时间设置为小于30分钟的一个时间 就可以，这个与定时任务的周期以及定时任务执行消耗时间相关。 结合过期时间主要是防止死锁的出现。
 * <p>
 * 下面以订单单号为例：思路大致相同
 * <p>
 * 拿到锁的线程则继续处理业务逻辑，设置过期时间，处理业务逻辑，处理成功则单号 + 1 ，并释放锁。
 * 没有拿到锁的线程进入自旋状态，不断去查询最新的单号值，直到拿到锁为止。
 **/
public class RedisDistributedLockTest {

    private static final Logger logger = LoggerFactory.getLogger(RedisDistributedLockTest.class);

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    // redis 分布式锁前缀
    private static final String REDIS_LOCK_PREFIX = "redis:distributed:lock:";
    // redis 分布式锁时间为 4 小时
    private static final int REDIS_LOCK_TIMEOUT = 14400000;
    // redis 缓存前缀
    private static final String REDIS_KEY_PREFIX = "redis:cache:";
    // redis 缓存过期时间为 12 小时
    private static final int REDIS_KEY_EXPIRE = 12;
    private static final TimeUnit REDIS_KEY_EXPIRE_UNIT = TimeUnit.HOURS;

    public void doSomeThing() {
        String keySuffer = "test_distributed_lock";
        RedisDistributedLock redisDistributedLock = new RedisDistributedLock(redisTemplate);

        try {
            // 1.获取锁，获取失败则返回。
            if (!redisDistributedLock.lock(REDIS_LOCK_PREFIX + keySuffer, REDIS_LOCK_TIMEOUT, 0)) {
                logger.info("not get distributed lock, lock already occupied.");
            }

            // 2. 获取到分布式锁之后，模仿业务逻辑处理。
            logger.info("doing...");
            Thread.sleep(3000);

            String redis_val = "redis_val"; // 模拟处理结果。

            // 3. 将处理结果放入缓存。 放入前需要判断分布式锁还在不在（若发生异常可能会导致分布式锁过期，需要做判断）。
            if (!redisDistributedLock.checkDistributedLock(REDIS_LOCK_PREFIX + keySuffer)) {
                logger.warn("distributed lock not exist");
                throw new UnsupportedOperationException("lock not exist, do not execute continue.");
            }

            // 4.将结果放入缓存
            redisTemplate.opsForValue().set(REDIS_KEY_PREFIX + keySuffer, redis_val, REDIS_KEY_EXPIRE, REDIS_KEY_EXPIRE_UNIT);
            logger.info("redis result value has be set successfully.");
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("this is InterruptedException", e);
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
            logger.error("this is UnsupportedOperationException", e);
        } finally {
            // 5.处理完之后，释放锁。
            redisDistributedLock.unLock(REDIS_LOCK_PREFIX + keySuffer);
            logger.info("distributed lock emancipated.");
        }
    }
}
