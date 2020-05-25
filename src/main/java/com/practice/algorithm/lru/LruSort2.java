package com.practice.algorithm.lru;

/**
 * @author Higmin
 * @date 2020/5/19 9:51
 *
 * 最近最久未使用（LRU）算法
 * LRU是Least Recently Used的缩写，即最近最少使用，是一种常用的淘汰机制算法。
 *
 * 常见的使用该算法的有：Redis缓存淘汰机制
 * 下面我们用 java 手写一个 LRU 算法 ：我们采用 哈希表 + 双向链表 的数据结构实现。
 *
 * 方法 2：哈希表 + 双向链表 实现
 *
 **/

import java.util.concurrent.ConcurrentHashMap;

/**
 * 1.定义列表节点
 */
class LinkedNode{
	int key;
	int value;
	LinkedNode pre;
	LinkedNode next;

	public LinkedNode() {
	}

	public LinkedNode(int key, int value) {
		this.key = key;
		this.value = value;
	}
}

/**
 * 2.定义双向链表
 */
class DLinked{
	private LinkedNode head;
	private LinkedNode tail;
	private int size;

	public DLinked() {
		head = new LinkedNode(-1, -1); // 定义一个假头，方便使用
		tail = new LinkedNode(-1, -1);// 定义一个假尾，方便使用
		head.next = tail;
		tail.pre = head;
		size = 0;
	}

	/**
	 * 添加节点 => 总是从头部添加节点
	 * @param node
	 */
	public void addFirst(LinkedNode node){
		node.next = head.next;
		node.pre = head;
		head.next.pre = node;
		head.next = node;
		size++;
	}

	/**
	 * 删除节点 => 前提是节点一定存在
	 * @param node
	 */
	public void remove(LinkedNode node){
		node.pre.next = node.next;
		node.next.pre = node.pre;
		size--;
	}

	/**
	 * 将访问的节点移动到第一个元素
	 * @param node
	 */
	public void moveToFirst(LinkedNode node){
		remove(node);
		addFirst(node);
	}

	/**
	 * 删除链表的最后一个节点，并返回该节点
	 * @return
	 */
	public LinkedNode removeLast(){
		if (tail.pre == head) return null;
		LinkedNode last = tail.pre;
		remove(last);
		return last;
	}

	public int getSize(){
		return size;
	}

}


/**
 * 3.哈希表 + 双向链表 实现 LRU
 */
public class LruSort2 {

	private ConcurrentHashMap<Integer, LinkedNode> cache; // 哈希表：考虑线程安全 所以使用 ConcurrentHashMap
	private DLinked dLinked; // 双向列表
	private int capacity; // 最大容量

	public LruSort2() {
		this.cache = new ConcurrentHashMap<>();
		this.dLinked = new DLinked();
		this.capacity = 12; // 默认最大容量是12
	}

	public LruSort2(int capacity) { // 自己设定最大容量
		this.cache = new ConcurrentHashMap<>();
		this.dLinked = new DLinked();
		this.capacity = capacity;
	}

	public void put(int key, int value){
		LinkedNode node = new LinkedNode(key, value);
		// 如果有次节点，那么将此节点提前
		if (cache.containsKey(key)){
			LinkedNode getNode = cache.get(key);
			// 删除旧的节点
			dLinked.remove(getNode);
		} else if (capacity <= dLinked.getSize()){ // 如果到达最大容量，则删除最近最近未使用的节点（即最后一个节点）
			LinkedNode last = dLinked.removeLast();
			cache.remove(last.key); // todo 这里用到了节点中存储的key，所以这就是：为什么要在链表中同时存储 key 和 val，而不是只存储 val
		}
		// 添加新节点到链表头部
		dLinked.addFirst(node);
		cache.put(key,node);
	}

	public int get(int key){
		if (!cache.containsKey(key)){
			return -1;
		}
		LinkedNode node = cache.get(key);
		int val = node.value;
		// 将此节点提前到头部
		dLinked.moveToFirst(node);
		return val;
	}
}
