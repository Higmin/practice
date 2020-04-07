package com.practice.algorithm.leetcode_passing;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Higmin
 * @date 2020/3/25 14:10
 *
 *  最近最久未使用（LRU）算法
 * LRU是Least Recently Used的缩写，即最近最少使用，是一种常用的淘汰机制算法。
 *
 * 常见的使用该算法的有：Redis缓存淘汰机制
 * 下面我们用 java 手写一个 LRU 算法 ：我们采用 哈希表 + 双向链表 的数据结构实现。
 *
 **/
public class N_145_LruSort<K,V>  extends LinkedHashMap<K,V> {
	private int cacheSize;

	public N_145_LruSort(int cacheSize) {
		super(10,0.75f,true); // true 表示让 linkedHashMap 按照访问顺序来进行排序，最近访问的放在头部，最老访问的放在尾部
		this.cacheSize = cacheSize;
	}

	@Override
	public V get(Object key) {
		// 注意这里不能直接使用 get 方法，因为 key 可能已经被删除了，所以我们使用 getOrDefault
		// 如果map中含有指定的key，就返回该key对应的value，否则使用该方法的第二个参数作为默认值返回
		return super.getOrDefault(key, null);
	}

	@Override
	public V put(K key, V value) {
		return super.put(key, value);
	}

	/**
	 * 通过覆盖这个方法，加入一定的条件，满足条件返回true。当put进新的值, 方法返回true时，便移除该map中最老的键和值。
	 * @param eldest
	 * @return
	 */
	@Override
	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		//当map中数量大于指定缓存个数的时候，自动删除最近最久未使用的数据
		return size() > cacheSize;
	}
}
