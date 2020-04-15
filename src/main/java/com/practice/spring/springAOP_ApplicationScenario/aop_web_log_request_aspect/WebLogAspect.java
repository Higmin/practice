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
 *
 * 关于切入点表达式：下面给出一些通用切入点表达式的例子。
 * 任意公共方法的执行：
 * execution（public * *（..））
 * 任何一个名字以“set”开始的方法的执行：
 * execution（* set*（..））
 * AccountService接口定义的任意方法的执行：
 * execution（* com.xyz.service.AccountService.*（..））
 * 在service包中定义的任意方法的执行：
 * execution（* com.xyz.service.*.*（..））
 * 在service包或其子包中定义的任意方法的执行：
 * execution（* com.xyz.service..*.*（..））
 * 在service包中的任意连接点（在Spring AOP中只是方法执行）：
 * within（com.xyz.service.*）
 * 在service包或其子包中的任意连接点（在Spring AOP中只是方法执行）：
 * within（com.xyz.service..*）
 * 实现了 AccountService接口的代理对象的任意连接点 （在Spring AOP中只是方法执行）：
 * this（com.xyz.service.AccountService）
 * 'this'在绑定表单中更加常用：- 请参见后面的通知一节中了解如何使得代理对象在通知体内可用。
 * 实现 AccountService接口的目标对象的任意连接点 （在Spring AOP中只是方法执行）：
 * target（com.xyz.service.AccountService）
 * 'target'在绑定表单中更加常用：- 请参见后面的通知一节中了解如何使得目标对象在通知体内可用。
 * 任何一个只接受一个参数，并且运行时所传入的参数是 Serializable 接口的连接点（在Spring AOP中只是方法执行）
 * args（java.io.Serializable）
 * 'args'在绑定表单中更加常用：- 请参见后面的通知一节中了解如何使得方法参数在通知体内可用。
 * 请注意在例子中给出的切入点不同于  execution(* *(java.io.Serializable))： args版本只有在动态运行时候传入参数是Serializable时才匹配，而execution版本在方法签名中声明只有一个  Serializable类型的参数时候匹配。
 * 目标对象中有一个  @Transactional 注解的任意连接点 （在Spring AOP中只是方法执行）
 * @target（org.springframework.transaction.annotation.Transactional）
 * '@target'在绑定表单中更加常用：- 请参见后面的通知一节中了解如何使得注解对象在通知体内可用。
 * 任何一个目标对象声明的类型有一个  @Transactional 注解的连接点 （在Spring AOP中只是方法执行）：
 * @within（org.springframework.transaction.annotation.Transactional）
 * '@within'在绑定表单中更加常用：- 请参见后面的通知一节中了解如何使得注解对象在通知体内可用。
 * 任何一个执行的方法有一个  @Transactional 注解的连接点 （在Spring AOP中只是方法执行）
 * @annotation（org.springframework.transaction.annotation.Transactional）
 * '@annotation'在绑定表单中更加常用：- 请参见后面的通知一节中了解如何使得注解对象在通知体内可用。
 * 任何一个只接受一个参数，并且运行时所传入的参数类型具有 @Classified 注解的连接点（在Spring AOP中只是方法执行）
 * @args（com.xyz.security.Classified）
 * '@args'在绑定表单中更加常用：- 请参见后面的通知一节中了解如何使得注解对象在通知体内可用。
 * 任何一个在名为' tradeService'的Spring bean之上的连接点 （在Spring AOP中只是方法执行）：
 * bean（tradeService）
 * 任何一个在名字匹配通配符表达式' *Service'的Spring bean之上的连接点 （在Spring AOP中只是方法执行）：
 * bean（*Service）
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

