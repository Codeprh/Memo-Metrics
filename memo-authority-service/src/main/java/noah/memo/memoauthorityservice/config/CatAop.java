package noah.memo.memoauthorityservice.config;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class CatAop {

    @Around("@annotation(noah.memo.memoframework.annotation.CatAnnotation)")
    public Object aroundMethod(ProceedingJoinPoint pjp) {
        MethodSignature joinPointObject = (MethodSignature) pjp.getSignature();
        Method method = joinPointObject.getMethod();

        Transaction t = Cat.newTransaction("method", method.getName());

        try {
            Object obj = pjp.proceed();
            t.setStatus("0");
            return obj;
        } catch (Throwable e) {
            t.setStatus(e);
            Cat.logError(e);
            throw new RuntimeException("Exception thrown by CAT aop", e);
        } finally {
            t.complete();
        }
    }

}