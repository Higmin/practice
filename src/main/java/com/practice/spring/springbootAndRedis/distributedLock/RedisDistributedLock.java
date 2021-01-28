package com.practice.spring.springbootAndRedis.distributedLock;

import io.lettuce.core.ScriptOutputType;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.cluster.api.async.RedisAdvancedClusterAsyncCommands;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * the distributed lock class for redis.
 *
 * @author Higmin
 * @version 1.0, 2021/01/28
 * @since practice 1.0.0
 */
public class RedisDistributedLock {

    private static final String UNLOCK_LUA;

    static {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        stringBuffer.append("then ");
        stringBuffer.append("    return redis.call(\"del\",KEYS[1]) ");
        stringBuffer.append("else ");
        stringBuffer.append("    return 0 ");
        stringBuffer.append("end ");

        UNLOCK_LUA = stringBuffer.toString();
    }

    private String lockValue = UUID.randomUUID().toString();

    private RedisTemplate<String, Object> redisTemplate;

    public RedisDistributedLock(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean lock(String lockKey, long timeout) {
        return lock(lockKey, timeout, 0, 0);
    }

    public boolean lock(String lockKey, long timeout, long retryTimes) {
        return lock(lockKey, timeout, retryTimes, 1000);
    }

    public boolean lock(String lockKey, long timeout, long retryTimes, long sleepMillis) {
        boolean isLocked = false;

        while (!isLocked && retryTimes >= 0) {
            isLocked = set(new StringRedisSerializer().serialize(lockKey), new StringRedisSerializer().serialize(lockValue), timeout);

            try {
                TimeUnit.MILLISECONDS.sleep(sleepMillis);
            } catch (InterruptedException e) {
                return false;
            }

            retryTimes--;
        }

        return isLocked;
    }

    public boolean unLock(String lockKey) {
        return del(new StringRedisSerializer().serialize(lockKey), new StringRedisSerializer().serialize(lockValue));
    }

    private boolean set(byte[] lockKey, byte[] lockValue, long timeout) {
        String result = redisTemplate.execute((RedisCallback<String>) connection -> {
            Object nativeConnection = connection.getNativeConnection();

            if (nativeConnection instanceof RedisAsyncCommands) {
                return ((RedisAsyncCommands) nativeConnection).getStatefulConnection().sync().set(lockKey, lockValue, SetArgs.Builder.nx().px(timeout));
            }

            if (nativeConnection instanceof RedisAdvancedClusterAsyncCommands) {
                return ((RedisAdvancedClusterAsyncCommands) nativeConnection).getStatefulConnection().sync().set(lockKey, lockValue, SetArgs.Builder.nx().px(timeout));
            }

            return null;
        });

        return StringUtils.isNotBlank(result);
    }

    private boolean del(byte[] lockKey, byte[] lockValue) {
        Long result = redisTemplate.execute((RedisCallback<Long>) connection -> {
            Object nativeConnection = connection.getNativeConnection();

            if (nativeConnection instanceof RedisAsyncCommands) {
                return (Long) ((RedisAsyncCommands) nativeConnection).getStatefulConnection().sync().eval(UNLOCK_LUA, ScriptOutputType.INTEGER, Collections.singletonList(lockKey).toArray(), Collections.singletonList(lockValue).toArray());
            }

            if (nativeConnection instanceof RedisAdvancedClusterAsyncCommands) {
                return (Long) ((RedisAdvancedClusterAsyncCommands) nativeConnection).getStatefulConnection().sync().eval(UNLOCK_LUA, ScriptOutputType.INTEGER, Collections.singletonList(lockKey).toArray(), Collections.singletonList(lockValue).toArray());
            }

            return 0L;
        });

        return result != null && result > 0L;
    }

    public boolean checkDistributedLock(String key) {
        Boolean hasKey = redisTemplate.hasKey(key);
        return hasKey != null && hasKey;
    }
}
