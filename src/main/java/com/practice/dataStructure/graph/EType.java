package com.practice.dataStructure.graph;

/**
 * @author Higmin
 * @date 2019/10/17 15:23
 * @Description: 边的类型
 **/
public enum EType {
	UNKNOWN,        //未知边
	TREE,           //树边
	CROSS,          //横跨边
	FORWARD,        //前向跨边
	BACKWARD;       //后向跨边
}
