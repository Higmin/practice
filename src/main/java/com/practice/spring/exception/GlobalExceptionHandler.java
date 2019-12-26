package com.practice.spring.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @author Higmin
 * @date 2019/12/25 17:43
 *
 * 全局异常处理 = > 异常处理
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * post请求参数校验抛出的异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Result methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
		//获取异常中随机一个异常信息
		String defaultMessage = e.getBindingResult().getFieldError().getDefaultMessage();
		log.error(e.getMessage());
		return ResultUtil.fail(defaultMessage);
	}

	/**
	 * get请求参数校验抛出的异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BindException.class)
	public Result bindExceptionHandler(BindException e){
		//获取异常中随机一个异常信息
		String defaultMessage = e.getBindingResult().getFieldError().getDefaultMessage();
		log.error(e.getMessage());
		return ResultUtil.fail(defaultMessage);
	}

	/**
	 * 请求方法中校验抛出的异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public Result constraintViolationExceptionHandler(ConstraintViolationException e){
		//获取异常中第一个错误信息
		String message = e.getConstraintViolations().iterator().next().getMessage();
		log.error(e.getMessage());
		return ResultUtil.fail(message);
	}

	/**
	 * 处理空指针的异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value =NullPointerException.class)
	public Result exceptionHandler(NullPointerException e){
		log.error("发生空指针异常！原因是:", e);
		String message = e.getMessage();
		return ResultUtil.fail("发生空指针异常" + message);
	}

	/**
	 * 其他异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public Result exceptionHandler(Exception e){
		//获取异常中的错误信息
		log.error("其他异常 : " + e);
		return ResultUtil.fail(e.toString());
	}

}
