package aop.advice;

public class MethodPerformance {
    private long beginTimeMills;
    private long endTimeMills;
    private String methodName;

    public MethodPerformance(String methodName) {
        this.methodName = methodName;
        // 记录目标类方法开始执行的时间
        this.beginTimeMills = System.currentTimeMillis();
    }

    public void printPerformance() {
        // 记录目标类方法执行结束的时间
        endTimeMills = System.currentTimeMillis();
        System.out.println(methodName + "花费" + (endTimeMills - beginTimeMills) + "毫秒。");
    }
}
