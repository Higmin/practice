package com.practice.dataStructure.graph;

/**
 * @author Higmin
 * @date 2019/10/17 15:22
 * @Description: 顶点
 **/
public class Edge {
	private Object info; //边包含的信息
	private int weight;  //权重
	private EType type; //边的类型
	public Edge(int weight) {
		this.info = null;
		this.weight = weight;
		this.type = EType.UNKNOWN;
	}
	public Edge(int weight,Object info) {
		this.info = info;
		this.weight = weight;
		this.type = EType.UNKNOWN;
	}
	public Object getInfo() {
		return info;
	}
	public void setInfo(Object info) {
		this.info = info;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public EType getType() {
		return type;
	}
	public void setType(EType type) {
		this.type = type;
	}
}
