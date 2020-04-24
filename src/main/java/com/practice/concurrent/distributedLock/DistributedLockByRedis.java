package com.practice.concurrent.distributedLock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Higmin
 * @date 2020/4/15 16:25
 *
 * 利用redis 模拟实现分布式锁
 *
 * 思路：
 * 这个主要利用redis的setnx命令进行，setnx："set if not exists"就是如果不存在则成功设置缓存同时返回1，否则返回0 。
 * 在集群模式下，如果多台机器同时对某条数据进行操作，可能会引起数据不一致的情况，比如: 定时任务的执行，多台机器都在跑定时Job，容易发生重复处理数据的情况，
 * 我们采用分布式锁类保证定时任务不会重复执行，当然结合具体业务，我们可以给这个lock加一个过期时间，比如说30分钟执行一次的定时任务，
 * 那么这个过期时间设置为小于30分钟的一个时间 就可以，这个与定时任务的周期以及定时任务执行消耗时间相关。 结合过期时间主要是防止死锁的出现。
 *
 * 下面以订单单号为例：思路大致相同
 *
 *  拿到锁的线程则继续处理业务逻辑，设置过期时间，处理业务逻辑，处理成功则单号 + 1 ，并释放锁。
 *  没有拿到锁的线程进入自旋状态，不断去查询最新的单号值，直到拿到锁为止。
 *
 **/
public class DistributedLockByRedis {
	public static void main(String[] args) {
		JedisPoolConfig config = new JedisPoolConfig();
		//最大空闲连接数
		config.setMaxIdle(10);
		//设置最大连接数（100个足够用了，没必要设置太大）
		config.setMaxTotal(100);
		config.setMaxWaitMillis(50 * 1000);
		//在将连接放回池中前，自动检验连接是否有效
		config.setTestOnReturn(true);
		//自动测试池中的空闲连接是否都是可用连接
		config.setTestWhileIdle(true);
		JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379, 2000, "root");
		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Jedis jedis = jedisPool.getResource();
					try {
						while (true) {
							long num = Long.valueOf(jedis.get("num"));
							long ordernum = num + 1;
							Long val = jedis.setnx(Long.toString(ordernum), "这里存：时间戳+机器节点");
//							Long val = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime); // 用这个实现最好，set的时候就加过期时间
							if (val == 1L) {
								System.out.println("线程 " + Thread.currentThread().getName() + " 获得锁!10s后过期 --- 开始执行 --> ");
								jedis.expire(Long.toString(ordernum), 10);
								System.out.println("线程 " + Thread.currentThread().getName() + " 执行中 ... --- 请等待...");
								//							try {
								//								Thread.sleep(2000); // 模拟实现业务逻辑处理时长，为了直观，这里假设2S
								//							} catch (InterruptedException e) {
								//								e.printStackTrace();
								//							}
								jedis.incrBy("num", 1);
								jedis.del(Long.toString(ordernum));
								System.out.println("线程 " + Thread.currentThread().getName() + " 执行完毕! --- 删除lock");
								break;
							}
						}
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}finally {
						if (jedis != null) jedis.close();
					}
				}
			}, "Thread - " + i).start();
		}
	}
}
