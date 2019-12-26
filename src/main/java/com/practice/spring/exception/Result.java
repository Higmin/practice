package com.practice.spring.exception;

import lombok.Data;

/**
 * @author Higmin
 * @date 2019/12/26 8:31
 *
 * 全局异常处理 = > 异常处理返回值实体类
 **/
@Data
public class Result {

	private String code;

	private Object data;

	private String msg;
}
