package sysmgt.controller;

import common.bean.ResultJsonWrap;
import common.constant.GlobalConstant;
import common.controller.BaseController;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sysmgt.entity.UserEntity;
import sysmgt.service.UserService;

import javax.servlet.http.HttpSession;

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
     * 用户登录
     *
     * @param user    用户信息
     * @param session 用户会话
     * @return 结果数据
     */
    @RequestMapping(value = "/login")
    public ResponseEntity<ResultJsonWrap> loginPage(UserEntity user, HttpSession session) {
        ResultJsonWrap result = new ResultJsonWrap();
        String username = user.getUserName();
        String password = user.getPasswordHash();

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            result.setErrorMsg("用户名或者密码不能为空！");
        } else {
            try {
                UserEntity u = userService.findUserByLoginInfo(username, password);
                if (u == null) {
                    result.setErrorMsg("用户名或者密码错误！");
                } else {

                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                result.setErrorMsg("用户登录失败，系统异常！");
            }
        }
        return new ResponseEntity<ResultJsonWrap>(result, headers, HttpStatus.OK);
    }
}