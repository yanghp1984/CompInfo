package sysmgt.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import sysmgt.entity.UserEntity;
import sysmgt.service.UserService;
import sysmgt.web.LoginCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Lazy
@RestController
public class LoginController {
    private Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index.form")
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/loginCheck.form")
    public ModelAndView loginCheck(HttpServletRequest request, LoginCommand loginCommand) {
        String username = loginCommand.getUserName();
        String passwordHash = loginCommand.getPasswordHash();

        if (StringUtils.isBlank(username) || StringUtils.isBlank(passwordHash)) {
            return new ModelAndView("login", "error", "用户名或者密码不能为空！");
        }

        try {
            UserEntity user = userService.findUserByLoginInfo(username, passwordHash);
            if (user == null) {
                return new ModelAndView("login", "error", "用户名或密码错误!");
            }

            user.setLastVisitIp(request.getLocalAddr());
            user.setLastVisitTime(new Date());
            user.setCredits(5 + user.getCredits());
            userService.addLoginLog(user);
            request.getSession().setAttribute("user", user);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ModelAndView("login", "error", "用户登录失败，系统异常!");
        }
        return new ModelAndView("welcome");
    }
}
