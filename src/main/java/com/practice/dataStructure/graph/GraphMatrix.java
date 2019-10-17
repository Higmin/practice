package com.practice.dataStructure.graph;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Higmin
 * @date 2019/10/17 15:25
 * @Description: 用邻接矩阵表示图
 **/
public class GraphMatrix<Tv> implements Graph<Tv> {

	private int n;                   //当前顶点数量
	private int e;                   //当前边的数量
	private Vector<Vertex<Tv>> V;    //顶点集合
	private Vector<Vector<Edge>> E;  //边的集合（邻接矩阵）
	private Map<Tv, Integer> hashMap; //顶点的data和顶点在数组中的pos映射表

	/**
	 * 功能描述:重置顶点和边的信息
	 *
	 * @param:
	 * @return:
	 */
	private void reset() {
		for (int i = 0; i < n; i++) {
			Vertex vertex = V.get(i);
			vertex.setStatus(VStatus.UNDISCOVERED);
			vertex.setdTime(-1);
			vertex.setfTime(-1);
			vertex.setParent(-1);
			vertex.setPriority(Integer.MAX_VALUE);
			for (int j = 0; j < n; ++j) {
				if (exists(i, j)) {
					E.get(i).get(j).setType(EType.UNKNOWN);
				}
			}
		}
	}

	/**
	 * 功能描述:
	 *
	 * @param: 顶点位置
	 * @return: 指定顶点的第一个相邻顶点
	 */
	private int firstNeighbor(int i) {
		return nextNeighbor(i, n);
	}

	/**
	 * 功能描述:
	 *
	 * @param: i 顶点位置
	 * @param: j 上一个相邻顶点位置
	 * @return: 顶点i的下一个相邻顶点位置
	 */
	private int nextNeighbor(int i, int j) {
		while ((-1 < j) && (!exists(i, --j))) ;
		return j;
	}

	/**
	 * 功能描述:是否存在顶点i到顶点j的有向边
	 *
	 * @param: i 起始顶点位置
	 * @param: j 结束顶点位置
	 * @return: 是否存在
	 */
	private boolean exists(int i, int j) {
		return (0 <= i) && (i < n) && (0 <= j) && (j < n) && E.get(i).get(j) != null;
	}

	private int inDegree(int i) {
		return V.get(i).getInDegree();
	}

	private int outDegree(int i) {
		return V.get(i).getOutDegree();
	}

	private VStatus status(int i) {
		return V.get(i).getStatus();
	}

	private int dTime(int i) {
		return V.get(i).getdTime();
	}

	private int fTime(int i) {
		return V.get(i).getfTime();
	}

	private int parent(int i) {
		return V.get(i).getParent();
	}

	private void setParent(int i, int parent) {
		V.get(i).setParent(parent);
	}

	private int priority(int i) {
		return V.get(i).getPriority();
	}

	private void setPriority(int i, int priority) {
		V.get(i).setPriority(priority);
	}

	private int weight(int i, int j) {
		return E.get(i).get(j).getWeight();
	}

	private EType type(int i, int j) {
		return E.get(i).get(j).getType();
	}

	private Object info(int i, int j) {
		return E.get(i).get(j).getInfo();
	}

	public GraphMatrix() {
		n = 0;
		e = 0;
		V = new Vector<Vertex<Tv>>();
		E = new Vector<Vector<Edge>>();
		hashMap = new HashMap<Tv, Integer>();
	}

	@Override
	public int vertexNumber() {
		return n;
	}

	@Override
	public boolean exist(Tv vertex) {
		return hashMap.containsKey(vertex);
	}

	@Override
	public boolean insert(Tv vertex) {
		if (hashMap.containsKey(vertex)) return false;
		for (int j = 0; j < n; j++) E.get(j).add(null);
		n++;
		Vector<Edge> vector = new Vector<Edge>();
		for (int j = 0; j < n; j++) vector.add(null);
		E.add(vector);
		V.add(new Vertex<Tv>(vertex));
		hashMap.put(vertex, n - 1);
		return true;
	}

	@Override
	public Vertex<Tv> remove(Tv vertex) {
		Integer i = hashMap.get(vertex);
		if (i == null) return null;
		//删除第i行
		for (int j = 0; j < n; j++) {
			if (exists(i, j)) V.get(j).setInDegree(V.get(j).getInDegree() - 1);//顶点j的入度减1
		}
		int i1 = i;
		E.remove(i1);
		n--;
		//删除顶点
		Vertex<Tv> vector = V.get(i);
		V.remove(i1);
		//删除第i列
		for (int j = 0; j < n; j++) {
			if (exists(j, i)) V.get(j).setOutDegree(V.get(j).getOutDegree() - 1);//顶点j的入度减1
			E.get(j).remove(i1);                    //删除所有j->i的边
		}
		//重置hashMap
		for (int j = 0; j < n; j++) {
			hashMap.put(V.get(j).getData(), j);
		}
		return vector;
	}

	@Override
	public Vertex<Tv> find(Tv vertex) {
		Integer i = hashMap.get(vertex);
		if (i == null) return null;
		return V.get(i);
	}

	@Override
	public int edgeNumber() {
		return e;
	}

	@Override
	public boolean exist(Tv vertex1, Tv vertex2) {
		return find(vertex1, vertex1) != null;
	}

	@Override
	public boolean insert(Tv vertex1, Tv vertex2, Edge edge) {
		Integer i = hashMap.get(vertex1);
		Integer j = hashMap.get(vertex2);
		if (i == null || j == null) return false;
		if (!exists(i, j)) {
			E.get(i).set(j, edge);
			++e;
			V.get(i).setOutDegree(V.get(i).getOutDegree() + 1);
			V.get(j).setInDegree(V.get(j).getInDegree() + 1);
		}
		return true;
	}

	@Override
	public Edge remove(Tv vertex1, Tv vertex2) {
		Integer i = hashMap.get(vertex1);
		Integer j = hashMap.get(vertex2);
		if (i == null || j == null || !exists(i, j)) return null;
		Edge edge = E.get(i).get(j);
		E.get(i).set(j, null);
		--e;
		V.get(i).setOutDegree(V.get(i).getOutDegree() - 1);
		V.get(j).setInDegree(V.get(j).getInDegree() - 1);
		return edge;
	}

	@Override
	public Edge find(Tv vertex1, Tv vertex2) {
		Integer i = hashMap.get(vertex1);
		Integer j = hashMap.get(vertex2);
		if (i == null || j == null || !exists(i, j)) return null;
		return E.get(i).get(j);
	}

	@Override
	public void bfs(Tv vertex, Function<Tv> function) {
		Integer i = hashMap.get(vertex);
		if (i == null) return;
		reset();
		AtomicInteger clock = new AtomicInteger(0);
		int v = i;
		do {
			if (status(v) == VStatus.UNDISCOVERED) BFS(v, clock, function);
		} while (i != (v = (++v % n)));
	}

	private void BFS(int i, AtomicInteger clock, Function<Tv> function) {
		Queue<Integer> queue = new LinkedList<Integer>();
		function.discover(V.get(i));
		V.get(i).setStatus(VStatus.DISCOVERED);
		queue.add(i);
		while (!queue.isEmpty()) {
			i = queue.poll();//取出对首顶点v
			V.get(i).setdTime(clock.addAndGet(1));//v顶点的时钟加1
			for (int u = firstNeighbor(i); -1 < u; u = nextNeighbor(i, u)) {
				if (status(u) == VStatus.UNDISCOVERED) {
					Vertex<Tv> vertex = V.get(u);
					function.discover(vertex);
					vertex.setStatus(VStatus.DISCOVERED);
					queue.add(u);
					E.get(i).get(u).setType(EType.TREE);
					vertex.setParent(i);
				} else {
					E.get(i).get(u).setType(EType.CROSS);
				}
			}
			function.visit(V.get(i));
			V.get(i).setStatus(VStatus.VISITED);
		}
	}

	@Override
	public void dfs(Tv data, Function<Tv> function) {
		Integer i = hashMap.get(data);
		if (i == null) return;
		reset();
		AtomicInteger clock = new AtomicInteger(0);
		int v = i;
		List<Vertex<Tv>> list = new ArrayList<Vertex<Tv>>();
		do {
			if (status(v) == VStatus.UNDISCOVERED) DFS(v, clock, function);
		} while (i != (v = (++v % n)));
	}

	private void DFS(int i, AtomicInteger clock, Function<Tv> function) {
		Vertex<Tv> vertex = V.get(i);
		vertex.setdTime(clock.addAndGet(1));
		function.discover(vertex);//发现顶点
		vertex.setStatus(VStatus.DISCOVERED);
		for (int j = firstNeighbor(i); -1 < j; j = nextNeighbor(i, j)) {
			if (status(j) == VStatus.UNDISCOVERED) {
				E.get(i).get(j).setType(EType.TREE);
				V.get(j).setParent(i);
				DFS(j, clock, function);
			} else if (status(j) == VStatus.DISCOVERED) {
				E.get(i).get(j).setType(EType.BACKWARD);
			} else {
				E.get(i).get(j).setType(dTime(i) < dTime(j) ? EType.FORWARD : EType.CROSS);
			}
		}
		function.visit(vertex);
		vertex.setStatus(VStatus.VISITED);
		vertex.setfTime(clock.addAndGet(1));
	}
}