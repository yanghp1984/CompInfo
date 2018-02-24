package aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class WorklogInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // 目标方法入参
        Object[] args = invocation.getArguments();
        String worklogId = null;
        if (args != null && args.length > 0) {
            if (args[0].getClass() == WorklogEntity.class) {
                WorklogEntity worklogEntity = (WorklogEntity) args[0];
                worklogId = worklogEntity.getWorklogId();
            } else {
                worklogId = args[0].toString();
            }
        }

        // 目标方法执行前调用
        System.out.println("Around Method Begin...");

        // 通过反射机制调用目标方法
        Object object = invocation.proceed();

        // 目标方法执行后调用
        System.out.println("Around Method End.");
        return object;
    }
}
