package com.practice.dataStructure.graph;

/**
 * @author Higmin
 * @date 2019/10/17 15:22
 * @Description: 边
 **/
public class Vertex<Tv> {
	private Tv data; //顶点包含的数据
	private int inDegree, outDegree; //入度和出度
	private VStatus status;//顶点状态
	private int dTime, fTime;//时间标签
	private int parent;//父顶点位置
	private int priority;//优先级，用于优先级搜索
	public Vertex(Tv data) {
		this.data = data;
		this.inDegree = 0;
		this.outDegree = 0;
		this.status = VStatus.UNDISCOVERED;
		this.dTime = -1;
		this.fTime = -1;
		this.parent = -1;
		this.priority = Integer.MAX_VALUE;
	}
	public Tv getData() {
		return data;
	}
	public void setData(Tv data) {
		this.data = data;
	}

	public int getInDegree() {
		return inDegree;
	}
	public void setInDegree(int inDegree) {
		this.inDegree = inDegree;
	}
	public int getOutDegree() {
		return outDegree;
	}
	public void setOutDegree(int outDegree) {
		this.outDegree = outDegree;
	}
	public VStatus getStatus() {
		return status;
	}
	public void setStatus(VStatus status) {
		this.status = status;
	}
	public int getdTime() {
		return dTime;
	}
	public void setdTime(int dTime) {
		this.dTime = dTime;
	}
	public int getfTime() {
		return fTime;
	}
	public void setfTime(int fTime) {
		this.fTime = fTime;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	@Override
	public String toString() {
		return data+"";
	}
}
