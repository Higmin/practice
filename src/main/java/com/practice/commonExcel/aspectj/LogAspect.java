package com.practice.commonExcel.aspectj;

import com.zdhs.dev.common.annotation.Log;
import com.zdhs.dev.common.utils.ServletUtils;
import com.zdhs.dev.common.utils.StringUtils;
import com.zdhs.dev.common.utils.json.JSONUtil;
import com.zdhs.dev.domain.DcOperLog;
import com.zdhs.dev.service.impl.DcOperLogServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private DcOperLogServiceImpl operLogService;

    @Pointcut("@annotation(com.zdhs.dev.common.annotation.Log)")
    public void pointcut() {
    }

    @AfterReturning("pointcut()")
    public void doAfterReturning(JoinPoint joinPoint) {
        Log log = getMethod(joinPoint);
        if (log == null) {
            return;
        }
        saveOperLog(log, joinPoint, null);
    }

    @AfterThrowing(value = "pointcut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        Log log = getMethod(joinPoint);
        if (log == null) {
            return;
        }
        saveOperLog(log, joinPoint, e);
    }

    public Log getMethod(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method == null) {
            return null;
        }
        return method.getAnnotation(Log.class);
    }

    public void saveOperLog(Log log, JoinPoint joinPoint, Exception e) {
        DcOperLog operLog = new DcOperLog();
        operLog.setRequestMethod(ServletUtils.getRequest().getMethod());
        operLog.setOperUrl(ServletUtils.getRequest().getServletPath());
        operLog.setMethod(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
        try {
            operLog.setOperParam(JSONUtil.marshal(ServletUtils.getRequest().getParameterMap()));
        } catch (Exception e1) {
            operLog.setOperParam("解析参数异常");
            e1.printStackTrace();
        }
//        operLog.setOperName(ShiroUtil.getDcUser().getNickName());
        operLog.setOperTime(new Date());
        operLog.setTitle(log.title());
        operLog.setBusinessType(log.businessType().ordinal());
        operLog.setStatus(1);
        if (e != null) {
            operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            operLog.setStatus(0);
        }
        operLogService.insertDcOperLog(operLog);
    }
}
