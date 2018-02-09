package sysmgt.service;

import common.bean.Paging;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sysmgt.entity.LoginLogEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LoginLogServiceTest {
    private static final String configFilePattern = "classpath*:**/applicationContext-*.xml";
    private static ApplicationContext ctx;

    static {
        System.setProperty("spring.profiles.active", "development");
        ctx = new ClassPathXmlApplicationContext(configFilePattern);
    }

    @Test
    public void testAddLoginLog() {
        System.out.println("\nAddLoginLog:");
        LoginLogService service = (LoginLogService) ctx.getBean("loginLogServiceImpl");
        LoginLogEntity entity = new LoginLogEntity();
        entity.setUserSid("c273e490-b0de-4acd-9522-f0ea5c53c296");
        entity.setVisitIp("192.168.168.1");
        entity.setVisitTime(new Date());
        String sid = service.addLoginLog(entity);
        System.out.println(entity);
        assertNotNull(sid);
    }

    @Test
    public void testFindLoginLog() {
        System.out.println("\nFindLoginLog:");
        LoginLogService service = (LoginLogService) ctx.getBean("loginLogServiceImpl");
        String sid = "3775442f-363b-4d82-b676-59ce05027816";
        LoginLogEntity entity = service.findLoginLog(sid);
        System.out.println(entity);
        assertNotNull(entity);
    }

    @Test
    public void testFindLoginLogList() {
        System.out.println("\nFindLoginLogList:");
        LoginLogService service = (LoginLogService) ctx.getBean("loginLogServiceImpl");
        String userSid = "c273e490-b0de-4acd-9522-f0ea5c53c296";
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("userSid", userSid);
        List<LoginLogEntity> lst = service.findLoginLogListByCondition(parameters);
        if (CollectionUtils.isNotEmpty(lst)) {
            for (LoginLogEntity log : lst) {
                System.out.println(log);
            }
        }
        assertNotNull(lst);
    }

    @Test
    public void testUpdateLoginLog() {
        System.out.println("\nUpdateLoginLog:");
        LoginLogService service = (LoginLogService) ctx.getBean("loginLogServiceImpl");
        String userSid = "c273e490-b0de-4acd-9522-f0ea5c53c296";
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("userSid", userSid);
        List<LoginLogEntity> lst = service.findLoginLogListByCondition(parameters);
        if (CollectionUtils.isNotEmpty(lst)) {
            LoginLogEntity entity = lst.get(0);
            entity.setVisitIp("127.0.0.1");
            service.updateLoginLog(entity);
            System.out.println(entity.getLogSid());
            assertTrue(true);
        }
    }

    @Test
    public void testDeleteLoginLog() {
        System.out.println("\nDeleteLoginLog:");
        LoginLogService service = (LoginLogService) ctx.getBean("loginLogServiceImpl");
        String userSid = "c273e490-b0de-4acd-9522-f0ea5c53c296";
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("userSid", userSid);
        List<LoginLogEntity> lst = service.findLoginLogListByCondition(parameters);
        if (CollectionUtils.isNotEmpty(lst) && lst.size() > 1) {
            LoginLogEntity entity = lst.get(1);
            service.deleteLoginLog(entity.getLogSid());
            System.out.println(entity.getLogSid());
            assertTrue(true);
        }
    }

    @Test
    public void testFindLoginLogPageByCondition() {
        System.out.println("\nFindLoginLogPageByCondition:");
        LoginLogService service = (LoginLogService) ctx.getBean("loginLogServiceImpl");
        Paging page = new Paging();
        page.setPageSize(2);
        page.clearRows();

        LoginLogEntity log = new LoginLogEntity();
        log.setUserSid("c273e490-b0de-4acd-9522-f0ea5c53c296");
        List<LoginLogEntity> list = null;
        try {
            page = service.findLoginLogPageByCondition(log, page);
            list = (List<LoginLogEntity>) page.getRows();
            if (CollectionUtils.isNotEmpty(list)) {
                for (LoginLogEntity lg : list) {
                    System.out.println(lg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(list);
    }
}
