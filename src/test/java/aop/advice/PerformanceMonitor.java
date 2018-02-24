package aop.advice;

public class PerformanceMonitor {
    // 通过一个 ThreadLocal保存与调用线程相关的性能监控信息
    // ThreadLocal 是将非线程安全类改造为线程安全类的“法宝”
    private static ThreadLocal<MethodPerformance> performanceThreadLocal = new ThreadLocal<MethodPerformance>();

    // 启动对某一目标方法的性能监控
    public static void begin(String methodName) {
        System.out.println("begin monitor ...");
        MethodPerformance mp = new MethodPerformance(methodName);
        performanceThreadLocal.set(mp);
    }

    public static void end() {
        System.out.println("end monitor.");
        MethodPerformance mp = performanceThreadLocal.get();
        // 打印监控结果
        mp.printPerformance();
    }
}
