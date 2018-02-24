package aop.advice;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class TestWorklogService {
    String configPath = "classpath*:**/applicationContext-test.xml";
    ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);

    @Test
    public void testBeforeAdvice() {
        WorklogService worklogService = (WorklogService) ctx.getBean("worklogService");
        worklogService.selectWorklog("log-1");
    }

    @Test
    public void testAfterAdvice() {
        WorklogService worklogService = (WorklogService) ctx.getBean("worklogService");
        WorklogEntity worklogEntity = new WorklogEntity();
        worklogEntity.setWorklogId("log-1");
        worklogService.addWorklog(worklogEntity);
    }

    @Test
    public void testInterceptor() {
        WorklogService worklogService = (WorklogService) ctx.getBean("worklogService");
        worklogService.deleteWorklog("log-1");
    }

    @Test
    public void testThrowsAdvice() {
        WorklogService worklogService = (WorklogService) ctx.getBean("worklogService");
        WorklogEntity worklogEntity = new WorklogEntity();
        worklogEntity.setWorklogId("log-1");
        try {
            worklogService.updateWorklog(worklogEntity);
        } catch (SQLException e) {
            // e.printStackTrace();
        }
    }

    @Test
    public void testIntroduce() {
        WorklogService worklogService = (WorklogService) ctx.getBean("worklogService");
        System.out.println("\n未开启性能监控：");
        worklogService.selectWorklog("log-1");

        System.out.println("\n开启性能监控：");
        Monitorable monitorable = (Monitorable) worklogService;
        monitorable.setMonitorActive(true);
        worklogService.selectWorklog("log-1");
    }
}
