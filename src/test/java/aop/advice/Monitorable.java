package aop.advice;

/**
 * 标识是否支持性能监控的接口
 */
public interface Monitorable {
    void setMonitorActive(boolean active);
}
