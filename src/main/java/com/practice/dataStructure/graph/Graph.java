package com.practice.dataStructure.graph;

/**
 * @author Higmin
 * @date 2019/10/17 15:21
 * @Description: 图的操作接口
 **/
public interface Graph<Tv> {
	//顶点的操作
	int vertexNumber();
	boolean exist(Tv vertex);
	boolean insert(Tv vertex);
	Vertex<Tv> remove(Tv vertex);
	Vertex<Tv> find(Tv vertex);
	//边的操作
	int edgeNumber();
	boolean exist(Tv vertex1, Tv vertex2);
	boolean insert(Tv vertex1, Tv vertex2, Edge edge);
	Edge remove(Tv vertex1, Tv vertex2);
	Edge find(Tv vertex1, Tv vertex2);
	//图的基本算法
	void bfs(Tv vertex, Function<Tv> function);
	void dfs(Tv vertex, Function<Tv> function);
}
