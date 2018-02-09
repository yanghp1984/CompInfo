package sysmgt.service;

import java.util.*;

import common.bean.Paging;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.constant.GlobalConstant;
import common.dao.BaseDAO;
import common.util.StringEncrypter;
import sysmgt.dao.LoginLogDAO;
import sysmgt.dao.UserDAO;
import sysmgt.entity.LoginLogEntity;
import sysmgt.entity.UserEntity;

/**
 * 用户信息服务接口的具体实现类
 */
@Lazy
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private LoginLogDAO loginLogDAO;

    @Override
    public UserEntity findUser(String sid) {
        UserEntity user = null;
        if (StringUtils.isNotBlank(sid)) {
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("userSid", sid);
            List<UserEntity> list = userDAO.findUserListByCondition(parameters);
            if (CollectionUtils.isNotEmpty(list)) {
                user = list.get(0);
            }
        }
        return user;
    }

    @Override
    public List<UserEntity> findUserListByCondition(Map<String, String> parameters) {
        return userDAO.findUserListByCondition(parameters);
    }

    /**
     * 根据条件和查询参数，查询用户信息并分页
     *
     * @param entity 查询条件
     * @param paging 分页参数
     * @return 分页数据
     */
    @Override
    public Paging findUserPageByCondition(UserEntity entity, Paging paging) {
        return userDAO.findUserPageByCondition(entity, paging);
    }

    @Override
    public String addUser(UserEntity entity) {
        return userDAO.addUser(entity);
    }

    @Override
    public void updateUser(UserEntity entity) {
        userDAO.updateUser(entity);
    }

    @Override
    public void deleteUser(String sid) {
        if (StringUtils.isNotBlank(sid)) {
            List<String> sidList = new ArrayList<String>();
            sidList.add(sid);
            userDAO.deleteUserList(sidList);
        }
    }

    @Transactional
    @Override
    public void deleteUserList(List<String> sidList) {
        userDAO.deleteUserList(sidList);
    }

    /**
     * 验证用户身份，如果成功同时更新登录日志
     *
     * @param username 用户名
     * @param password 密码
     * @param ip IP地址
     * @return True 表示成功，False 表示失败。
     */
    @Override
    public boolean updateAndValidate(String username, String password, String ip) {
        boolean flag = false;
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("userName", username);
            List<UserEntity> list = userDAO.findUserListByCondition(map);
            if (CollectionUtils.isNotEmpty(list)) {
                UserEntity user = list.get(0);
                String pwd = StringEncrypter.getHashValue(password, GlobalConstant.HASH_COMPUTE_TYPE);
                if (pwd.equals(user.getPasswordHash())) {
                    // 身份验证成功, 更新用户信息和登录日志
                    Date time = new Date();
                    user.setLastVisitIp(ip);
                    user.setLastVisitTime(time);
                    userDAO.updateUser(user);

                    LoginLogEntity log = new LoginLogEntity();
                    log.setUserSid(user.getUserSid());
                    log.setVisitIp(ip);
                    log.setVisitTime(time);
                    loginLogDAO.addLoginLog(log);
                    flag = true;
                }
            }
        }
        return flag;
    }

    @Override
    public boolean isExisted(String username) {
        boolean flag = false;
        if (StringUtils.isNotBlank(username)) {
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("userName", username);
            List<UserEntity> list = userDAO.findUserListByCondition(parameters);
            if (CollectionUtils.isNotEmpty(list)) {
                flag = true;
            }
        }
        return flag;
    }

}
