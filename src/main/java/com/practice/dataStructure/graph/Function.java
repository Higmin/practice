package com.practice.dataStructure.graph;

/**
 * @author Higmin
 * @date 2019/10/17 15:29
 * @Description: 双连通域分解算法(bcc)结果
 **/
public interface Function<Tv> {
	void discover(Vertex<Tv> vertex);
	void visit(Vertex<Tv> vertex);
}
