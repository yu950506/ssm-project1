package cn.yhs.learn.aop;

import cn.yhs.learn.domain.SysLog;
import cn.yhs.learn.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.aop.ControllerLogAop
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/13 15:43
 * @Description: todo
 **/
@Component
@Aspect
@Slf4j
@EnableAspectJAutoProxy
public class ControllerLogAop {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;

    private String username;
    private String ip;
    private String url;
    private String clazz;// 当前切入点的类
    private String methodName;//当前切入点的方法名称
    private Date visitTime;
    private Long executionTime;
    private SysLog sysLog = new SysLog();

    //1. 配置要切面的对象，我们这里只对controller下的类做切面
    @Pointcut(value = "execution(* cn.yhs.learn.controller.*.*(..))")
    public void sysLog() {
    }

    /**
     * 2. 前置通知
     *
     * @param joinPoint 可以获取切入点的相关信息
     */
    @Before(value = "sysLog()")
    public void beforeLog(JoinPoint joinPoint) throws NoSuchMethodException {
        // a. 获取用户名,可以从session中获取也可以从securityContext对象中获取
        SecurityContext context = SecurityContextHolder.getContext();
        username = ((User) (context.getAuthentication().getPrincipal())).getUsername();
        sysLog.setUsername(username);
        // username = (String)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT.authentication.principal.username");
        // b. 获取用户的ip
        ip = request.getRemoteAddr();
        sysLog.setIp(ip);
        // c. 获取用户的开始时间
        visitTime = new Date();
        sysLog.setVisitTime(visitTime);
        // d. 获取当前执行类
        Class<?> aClass = joinPoint.getTarget().getClass();
        clazz = aClass.getName();
        sysLog.setClazz(clazz);
        // e. 获取当前执行的方法
        methodName = joinPoint.getSignature().getName();
        sysLog.setMethod(methodName);
        // f. 获取当前的url,通过注解进行获取
        StringBuilder sb = new StringBuilder();
        // 获取类上的注解值
        RequestMapping requestMapping = aClass.getAnnotation(RequestMapping.class);
        String pathOrValue = getPathOrValue(requestMapping);
        sb.append(pathOrValue);
        // 获取当前方法上的注解
        Object[] args = joinPoint.getArgs();
        Class[] classes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            classes[i] = args[i].getClass();
        }
        Method method = aClass.getMethod(methodName, classes);
        RequestMapping requestMapping1 = method.getAnnotation(RequestMapping.class);
        String pathOrValue1 = getPathOrValue(requestMapping1);
        sb.append(pathOrValue1);
        url = sb.toString();
        sysLog.setUrl(url);

    }

    /**
     * 3. 后置通知： 将获取的数据写进数据库
     */
    @After(value = "sysLog()")
    public void afterLog() {
        // d. 获取执行时长
        executionTime = System.currentTimeMillis() - visitTime.getTime();
        sysLog.setExecutionTime(executionTime);
        // 将数据保存到数据库
        if (!sysLog.getClazz().equals("cn.yhs.learn.controller.SysLogController"))//不记录系统Controller
        {
            log.info("AOP日志的数据库保存数据对象：{}", sysLog);
            sysLogService.save(sysLog);
        }

    }


    /**
     * 获取@RequestMapping中的注解的值（path / value）
     *
     * @param requestMapping
     * @return
     */
    private String getPathOrValue(RequestMapping requestMapping) {
        if (requestMapping == null) {
            throw new RuntimeException("requestMapping对象为null");
        }
        // 用户写的 @RequestMapping("/add") @RequestMapping(value="/add")
        if (requestMapping.value() != null && requestMapping.value().length > 0) {
            return requestMapping.value()[0];
        }
        // 用户写的 @RequestMapping(path="/add")
        if (requestMapping.path() != null && requestMapping.path().length > 0) {
            return requestMapping.path()[0];
        }
        return null;
    }


}
