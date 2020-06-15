package cn.yhs.learn.aspect;

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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.aspect.SysLogAspect
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/14 18:07
 * @Description: Controller层的日志记录功能
 **/
@Component
@Aspect
@EnableAspectJAutoProxy
@Slf4j
public class SysLogAspect {
    //request对象 需要通过web.xml启动的时候创建对象，所有在配置文件中进行配置
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;
    // 日志对象
    private SysLog sysLog = new SysLog();
    private String username;
    private String ip;
    private String url;
    private String clazz;
    private String method;
    private Date visitTime;
    private Long executionTime;

    // 1. 配置切入点,controller 层
    @Pointcut(value = "execution(* cn.yhs.learn.controller.*.*(..)))")
    public void pointCut() {
    }


    /**
     * 2. 配置前置通知，获取相关信息，并封装成日志对象
     *
     * @param joinPoint 切面对象
     */
    @Before(value = "pointCut()")
    public void initSysLog(JoinPoint joinPoint) throws NoSuchMethodException {
        // 2.1 todo 封装用户名,从Session中获取
        // username = "test";
        SecurityContext securityContext = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        //  自己去找默认的实现类
        User user = (User) securityContext.getAuthentication().getPrincipal();
        username = user.getUsername();
        sysLog.setUsername(username);
        // 2.2 封装ip
        ip = request.getRemoteHost();
        sysLog.setIp(ip);
        // 2.3 封装类的字符串形式
        Class<?> objectClass = joinPoint.getTarget().getClass();
        clazz = objectClass.getName();
        sysLog.setClazz(clazz);
        // 2.4 封装method
        method = joinPoint.getSignature().getName();
        sysLog.setMethod(method);
        // 2.5 封装开始时间
        visitTime = new Date();
        sysLog.setVisitTime(visitTime);
        // 2.6 封装url:类上的注解值(@RequestMapping(value="/user"))+方法上的注解值(@RequestMapping(value="/findAll.do")) /user/findAll.do
        StringBuilder sb = new StringBuilder();
        // 2.6.1 获取类上的注解
        RequestMapping classRequestMapping = objectClass.getDeclaredAnnotation(RequestMapping.class);
        sb.append(getValueOrPath(classRequestMapping));
        // 2.6.1 获取方法上的注解
        // 先获取当前方法的参数类型
        Object[] args = joinPoint.getArgs();
        Class[] classes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            classes[i] = args[i].getClass();
        }
        // 再获取当前执行的方法
        Method method = objectClass.getMethod(this.method, classes);
        RequestMapping methodRequestMapping = method.getDeclaredAnnotation(RequestMapping.class);
        sb.append(getValueOrPath(methodRequestMapping));
        url = sb.toString();
        sysLog.setUrl(url);
    }


    // 3. 配置后置通知,将日志信息保存到数据库
    @After(value = "pointCut()")
    public void saveSysLog() {
        // 2.8 封装执行时长
        executionTime = System.currentTimeMillis() - visitTime.getTime();
        sysLog.setExecutionTime(executionTime);
        log.info("AOP切面封装的日志对象:{}", sysLog);
        // 2.9 将数据保存到数据库
        // todo 如果是sysLogController给过滤掉
        if (!"cn.yhs.learn.controller.SysLogController".equals(clazz))
            sysLogService.save(sysLog);
    }

    /**
     * 获取RequestMapping注解中的value 或者path 的值
     *
     * @param requestMapping
     * @return
     */
    public String getValueOrPath(RequestMapping requestMapping) {
        if (requestMapping.path().length > 0) {
            return requestMapping.path()[0];
        }
        if (requestMapping.value().length > 0) {
            return requestMapping.value()[0];
        }
        return null;

    }

    ;

}
