package aop.advice;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class ControllablePerformanceMonitor extends DelegatingIntroductionInterceptor implements Monitorable {
    private ThreadLocal<Boolean> MonitorStatusMap = new ThreadLocal<Boolean>();

    public void setMonitorActive(boolean active) {
        MonitorStatusMap.set(active);
    }

    // 拦截方法

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Object object = null;
        // 对于支持性能监控，通过判断其状态决定是否开启性能监控功能
        if (MonitorStatusMap.get() != null && MonitorStatusMap.get()) {
            PerformanceMonitor.begin(mi.getClass().getName() + "."
                    + mi.getMethod().getName());
            object = super.invoke(mi);
            PerformanceMonitor.end();
        } else {
            object = super.invoke(mi);
        }
        return object;
    }
}
