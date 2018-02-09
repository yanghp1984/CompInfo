package sysmgt.controller;

import common.controller.BaseController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sysmgt.entity.UserEntity;
import sysmgt.service.UserService;

/**
 * 用户信息管理
 */
@Lazy
@RestController
@RequestMapping(value = "/sysmgt/*")
public class UserController extends BaseController {
    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    /**
     * 用户登录成功，更新登录日志
     */
    public void loginSuccess(UserEntity user) {

    }
}
