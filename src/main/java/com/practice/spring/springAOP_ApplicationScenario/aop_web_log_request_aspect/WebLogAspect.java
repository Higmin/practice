package com.practice.spring.springAOP_ApplicationScenario.aop_web_log_request_aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author Higmin
 * @date 2020/4/9 14:10
 *
 * 定义切面实现：使用自定义注解 和 AOP 切面统一打印出入参日志
 **/
@Aspect
@Component
@Profile({"dev", "test"})
public class WebLogAspect {

	private final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
	/**
	 * 换行符
	 */
	private static final String LINE_SEPARATOR = System.lineSeparator();

	/**
	 * 以自定义 @WebLog 注解为切点
	 */
	@Pointcut("@annotation(com.practice.spring.springAOP_ApplicationScenario.aop_web_log_request_aspect.WebLog)")
	public void webLog() {
	}

	/**
	 * 在切点之前织入
	 *
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 开始打印请求日志
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// 获取 @WebLog 注解的描述信息
		String methodDescription = getAspectLogDescription(joinPoint);

		// 打印请求相关参数
		logger.info("========================================== Start ==========================================");
		// 打印请求 url
		logger.info("URL            : {}", request.getRequestURL().toString());
		// 打印描述信息
		logger.info("Description    : {}", methodDescription);
		// 打印 Http method
		logger.info("HTTP Method    : {}", request.getMethod());
		// 打印调用 controller 的全路径以及执行方法
		logger.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
		// 打印请求的 IP
		logger.info("IP             : {}", request.getRemoteAddr());
		// 打印请求入参
		logger.info("Request Args   : {}", JSON.toJSONString(joinPoint.getArgs()));
	}

	/**
	 * 在切点之后织入
	 *
	 * @throws Throwable
	 */
	@After("webLog()")
	public void doAfter() throws Throwable {
		// 接口结束后换行，方便分割查看
		logger.info("=========================================== End ===========================================" + LINE_SEPARATOR);
	}

	/**
	 * 环绕
	 *
	 * @param proceedingJoinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("webLog()")
	public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object result = proceedingJoinPoint.proceed(); // 目标方法执行。简单理解：环绕通知=前置+目标方法执行+后置通知，proceed方法就是用于启动目标方法执行的.
		// 打印出参
		logger.info("Response Args  : {}", JSON.toJSONString(result));
		// 执行耗时
		logger.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
		return result;
	}


	/**
	 * 获取切面注解的描述
	 *
	 * @param joinPoint 切点
	 * @return 描述信息
	 * @throws Exception
	 */
	public String getAspectLogDescription(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		StringBuilder description = new StringBuilder("");
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description.append(method.getAnnotation(WebLog.class).description());
					break;
				}
			}
		}
		return description.toString();
	}

}

