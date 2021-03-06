package sysmgt.service;

import common.constant.GlobalConstant;
import common.util.StringEncrypter;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sysmgt.entity.UserEntity;

import java.util.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
        System.out.println("\nfindUser:");
        String sid = "c273e490-b0de-4acd-9522-f0ea5c53c296";
        UserEntity user = userService.findUser(sid);
        System.out.println(user);
        assertNotNull(user);
    }

    @Test
    public void testFindUserListByCondition() {
        UserService userService = (UserService) ctx.getBean("userServiceImpl");
        System.out.println("\nfindUserList:");
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("userName", "yh");
        List<UserEntity> lst = userService.findUserListByCondition(parameters);
        if (CollectionUtils.isNotEmpty(lst)) {
            for (UserEntity u : lst) {
                System.out.println(u);
            }
        }
    }

    @Test
    public void testAddUser() {
        UserService userService = (UserService) ctx.getBean("userServiceImpl");
        System.out.println("\naddUser:");
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

    @Test
    public void testUpdateUser() {
        UserService userService = (UserService) ctx.getBean("userServiceImpl");
        System.out.println("\nupdateUser:");
        UserEntity user = null;
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("userName", "yh");
        List<UserEntity> lst = userService.findUserListByCondition(parameters);
        if (CollectionUtils.isNotEmpty(lst)) {
            user = lst.get(0);
        }

        if (user != null) {
            System.out.println(user.getUserSid());
            user.setLastVisitIp("192.168.168.1");
            userService.updateUser(user);
            assertTrue(true);
        }
    }

    @Test
    public void testDeleteUser() {
        UserService userService = (UserService) ctx.getBean("userServiceImpl");
        System.out.println("\ndeleteUser:");
        UserEntity user = null;
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("userName", "yh");
        List<UserEntity> lst = userService.findUserListByCondition(parameters);
        if (CollectionUtils.isNotEmpty(lst) && lst.size() > 1) {
            user = lst.get(1);
        }

        if (user != null) {
            System.out.println(user.getUserSid());
            userService.deleteUser(user.getUserSid());
            assertTrue(true);
        }
    }

    @Test
    public void testAddLoginLog() {
        UserService userService = (UserService) ctx.getBean("userServiceImpl");
        System.out.println("\naddLoginLog:");
        String username = "admin";
        String passwordHash = StringEncrypter.getHashValue("admin", GlobalConstant.HASH_COMPUTE_TYPE);
        UserEntity userEntity = userService.findUserByLoginInfo(username, passwordHash);
        userService.addLoginLog(userEntity);
        assertNotNull(userEntity);
    }

    @Test
    public void testIsExisted() {
        UserService userService = (UserService) ctx.getBean("userServiceImpl");
        System.out.println("\nisExisted:");
        boolean flag = userService.isExisted("yh");
        System.out.println(flag);
        assertTrue(flag);
    }

    /**
     * 测试容器级的国际化信息资源
     */
    @Test
    public void testI18n() {
        System.out.println("\n测试容器级的国际化信息资源 testI18n:");
        Object[] params = {"John", new GregorianCalendar().getTime()};
        String str1 = ctx.getMessage("greeting.common", params, Locale.US);
        String str2 = ctx.getMessage("greeting.common", params, Locale.CHINA);
        System.out.println(str1);
        System.out.println(str2);
    }
}
