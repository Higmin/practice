package com.practice.spring.springAOP_ApplicationScenario.aop_web_log_request_aspect;

import java.lang.annotation.*;
/**
 *  5步实现自定义注解
 */

@Target(ElementType.METHOD)          // 1: 注解用于什么地方，我们定义为作用于方法上
@Retention(RetentionPolicy.RUNTIME)  // 2: 什么时候使用该注解，我们定义为运行时；
@Documented                          // 3: 注解是否将包含在 JavaDoc 中
public @interface WebLog {           // 4: 注解名为 WebLog;

	String description() default ""; // 5: 定义一个属性，默认为空字符串
}
