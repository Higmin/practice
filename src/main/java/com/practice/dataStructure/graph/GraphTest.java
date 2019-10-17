package com.practice.dataStructure.graph;

/**
 * @author Higmin
 * @date 2019/10/17 15:29
 * @Description: 测试  图的 广度优先遍历、深度优先遍历
 **/
public class GraphTest {
	private static void bfsTest(String start) {
		GraphMatrix<String> graphMatrix = new GraphMatrix<String>();
		graphMatrix.insert("S");
		graphMatrix.insert("G");
		graphMatrix.insert("F");
		graphMatrix.insert("E");
		graphMatrix.insert("D");
		graphMatrix.insert("C");
		graphMatrix.insert("B");
		graphMatrix.insert("A");
		graphMatrix.insert("S","A",new Edge(1,1));
		graphMatrix.insert("S","C",new Edge(1,2));
		graphMatrix.insert("S","D",new Edge(1,3));
		graphMatrix.insert("A","C",new Edge(1,4));
		graphMatrix.insert("A","E",new Edge(1,5));
		graphMatrix.insert("C","B",new Edge(1,6));
		graphMatrix.insert("D","B",new Edge(1,7));
		graphMatrix.insert("E","F",new Edge(1,8));
		graphMatrix.insert("E","G",new Edge(1,9));
		graphMatrix.insert("G","F",new Edge(1,10));
		graphMatrix.insert("G","B",new Edge(1,11));
		System.out.print("广度优先遍历结果:");
		graphMatrix.bfs(start, new Function<String>() {
			@Override
			public void discover(Vertex<String> vertex) {
				System.out.print(" "+vertex.getData());
			}
			@Override
			public void visit(Vertex<String> vertex) {
			}
		});
		System.out.println();
	}
	private static void dfsTest(String start) {
		GraphMatrix<String> graphMatrix = new GraphMatrix<String>();
		graphMatrix.insert("G");
		graphMatrix.insert("F");
		graphMatrix.insert("D");
		graphMatrix.insert("E");
		graphMatrix.insert("C");
		graphMatrix.insert("B");
		graphMatrix.insert("A");
		graphMatrix.insert("A","B",new Edge(1,1));
		graphMatrix.insert("A","F",new Edge(1,2));
		graphMatrix.insert("A","C",new Edge(1,3));
		graphMatrix.insert("B","C",new Edge(1,4));
		graphMatrix.insert("F","G",new Edge(1,5));
		graphMatrix.insert("G","C",new Edge(1,6));
		graphMatrix.insert("D","A",new Edge(1,7));
		graphMatrix.insert("D","E",new Edge(1,8));
		graphMatrix.insert("E","F",new Edge(1,9));
		graphMatrix.insert("G","A",new Edge(1,10));
		System.out.print("深度优先遍历结果（以 " + start + " 为起点）:");
		graphMatrix.dfs(start, new Function<String>() {
			@Override
			public void discover(Vertex<String> vertex) {
				System.out.print(" "+vertex.getData());
			}
			@Override
			public void visit(Vertex<String> vertex) {
			}
		});
		System.out.println();
	}
	public static void main(String[] args) {
		bfsTest("S");
		dfsTest("A");
		dfsTest("D");
	}
}
