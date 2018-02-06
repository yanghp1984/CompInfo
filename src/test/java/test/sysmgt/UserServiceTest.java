package test.sysmgt;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sysmgt.entity.UserEntity;
import sysmgt.service.UserService;

import java.util.Date;

import static org.junit.Assert.assertNotNull;

public class UserServiceTest {
    private static final String configFilePattern = "classpath*:**/applicationContext-*.xml";
    private static ApplicationContext ctx;
    static {
        System.setProperty("spring.profiles.active", "development");
        ctx = new ClassPathXmlApplicationContext(configFilePattern);
    }

    @Test
    public void testFindUser() {
        UserService userService = (UserService) ctx.getBean("userServiceImpl");
        System.out.println("findUser:");
        String sid = "c273e490-b0de-4acd-9522-f0ea5c53c296";
        UserEntity user = userService.findUser(sid);
        System.out.println(user);
        assertNotNull(user);
    }

    @Test
    public void testAddUser() {
        UserService userService = (UserService) ctx.getBean("userServiceImpl");
        System.out.println("addUser:");
        UserEntity user = new UserEntity();
        user.setUserName("yh");
        user.setCredits(0);
        user.setPassword("123456");
        user.setLastVisitIp("127.0.0.1");
        user.setLastVisitTime(new Date());
        String sid = userService.addUser(user);
        System.out.println(user);
        assertNotNull(sid);
    }
}
