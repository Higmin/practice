package com.practice.algorithm.consistentHash;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author Higmin
 * @date 2020/4/8 14:36
 *
 * 进阶：1.考虑到路由（RouteHandle）算法有很多种：一致性哈希、随机路由、轮询、LRU   ==> 我们可以采用策略模式来实现不同的机制
 *      2. 如果每种算法的实现方式有多种，我们可以通过选择提供配置来决定哪一种实现
 **/
public class TestHash {

	/**
	 * 待添加入Hash环的服务器列表
	 */
	private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111",
			"192.168.0.3:111", "192.168.0.4:111"};
	/**
	 * key表示服务器的hash值，value表示服务器的名称
	 */
	private static SortedMap<Integer, String> sortedMap = new TreeMap<Integer, String>();

	/**
	 * 程序初始化，将所有的服务器放入sortedMap中
	 */
	static {
		for (int i = 0; i < servers.length; i++) {
			int hash = getHash(servers[i]);
			System.out.println("[" + servers[i] + "]加入服务器集合中, 其Hash值为：" + hash);
			sortedMap.put(hash, servers[i]);
		}
		System.out.println();
	}

	/**
	 * 自定义 hash算法，目的是为了保证散列值尽可能分散分布。
	 * 使用FNV1_32_HASH算法计算服务器的Hash值,这里不使用重写hashCode的方法，最终效果没区别
	 */
	private static int getHash(String str) {
		final int p = 16777619;
		int hash = (int) 2166136261L;
		for (int i = 0; i < str.length(); i++)
			hash = (hash ^ str.charAt(i)) * p;
		hash += hash << 13;
		hash ^= hash >> 7;
		hash += hash << 3;
		hash ^= hash >> 17;
		hash += hash << 5;

		// 如果算出来的值为负数则取其绝对值
		if (hash < 0)
			hash = Math.abs(hash);
		return hash;
	}

	/**
	 * 得到应当路由到的结点
	 */
	private static String getServer(String node) {
		String hostName;
		// 得到带路由的结点的Hash值
		int hash = getHash(node);
		// 得到大于该Hash值的所有Map
		SortedMap<Integer, String> subMap = sortedMap.tailMap(1764547047);
		// 无列表时取第一个节点（形成环）
		if (subMap.size() == 0){
			hostName = sortedMap.get(sortedMap.firstKey());
		}else{
			// 第一个Key就是顺时针过去离node最近的那个结点
			// 返回对应的服务器名称
			hostName =subMap.get(subMap.firstKey());
		}
		return hostName;
	}

	public static void main(String[] args) {
		String[] nodes = {"127.0.0.1:1111", "127.0.0.2:1111","221.226.0.1:2222", "10.211.0.1:3333"};
		for (int i = 0; i < nodes.length; i++)
			System.out.println("[" + nodes[i] + "]的hash值为" + getHash(nodes[i]) + ", 被路由到节点[" + getServer(nodes[i]) + "]");
	}

}
