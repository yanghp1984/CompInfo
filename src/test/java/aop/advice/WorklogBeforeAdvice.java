package aop.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class WorklogBeforeAdvice implements MethodBeforeAdvice {
    /**
     * Callback before a given method is invoked.
     *
     * @param method method being invoked
     * @param args   arguments to the method
     * @param target target of the method invocation. May be {@code null}.
     * @throws Throwable if this object wishes to abort the call.
     *                   Any exception thrown will be returned to the caller if it's
     *                   allowed by the method signature. Otherwise the exception
     *                   will be wrapped as a runtime exception.
     */
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        String worklogId = null;
        if (args != null && args.length > 0) {
            if (args[0] instanceof WorklogEntity) {
                WorklogEntity worklogEntity = (WorklogEntity) args[0];
                worklogId = worklogEntity.getWorklogId();
            } else {
                worklogId = args[0].toString();
            }
        }
        System.out.println("Before Method: worklogId = " + worklogId);
    }
}
