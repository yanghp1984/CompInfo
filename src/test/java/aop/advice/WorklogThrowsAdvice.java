package aop.advice;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * 异常抛出增强
 * 方法名必须为 afterThrowing。可以在同一个异常抛出增强中定义多个afterThrowing()方法，Spring会自动选择最匹配的增强方法。
 */
public class WorklogThrowsAdvice implements ThrowsAdvice {
    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable {
        System.out.println("--------------- method: " + method.getName() + " ---------------");
        System.out.println("Exception: " + ex.getMessage());
        System.out.println("成功回滚事务。");
    }
}
