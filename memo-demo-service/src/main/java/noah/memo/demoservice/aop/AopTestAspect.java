package noah.memo.demoservice.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 描述:
 * aop切面
 *
 * @author Noah
 * @create 2020-02-01 19:30
 */
@Component
@Aspect
public class AopTestAspect {

    @Around("@annotation(AopTest)")
    public Object aroundMethod(ProceedingJoinPoint pjp) {

        System.out.println("aop test");
        System.out.println("aop test");
        System.out.println("aop test");
        try {
            Object obj = pjp.proceed();

            return obj;
        } catch (Throwable e) {
            throw new RuntimeException("Exception thrown by CAT aop", e);
        } finally {

        }
    }
}
