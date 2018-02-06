package sysmgt.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.constant.GlobalConstant;
import common.dao.BaseDAO;
import common.util.StringEncrypter;
import sysmgt.entity.UserEntity;

/**
 * 用户信息服务接口的具体实现类
 */
@Lazy
@Service
public class UserServiceImpl extends BaseDAO implements UserService {
    /**
     * SQL 命名空间
     */
    private static final String SQL_NS = "mybatis.sysmgt.mapper.UserMapper.";

    @Override
    public UserEntity findUser(String sid) {
        UserEntity user = null;
        if (StringUtils.isNotBlank(sid)) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("userSid", sid);
            user = baseMyBatisDAO.findUnique(SQL_NS + "selectUserByCondition", map);
        }
        return user;
    }

    @Override
    public List<UserEntity> findUserListByCondition(Map<String, String> parameters) {
        return baseMyBatisDAO.findForList(SQL_NS + "selectUserByCondition", parameters);
    }

    @Transactional
    @Override
    public String addUser(UserEntity entity) {
        entity.setUserSid(null);
        baseMyBatisDAO.insert(SQL_NS + "insertUser", entity);
        return entity.getUserSid();
    }

    @Transactional
    @Override
    public void updateUser(UserEntity entity) {
        baseMyBatisDAO.update(SQL_NS + "updateUser", entity);
    }

    @Transactional
    @Override
    public void deleteUser(String sid) {
        if (StringUtils.isNotBlank(sid)) {
            List<String> sidList = new ArrayList<String>();
            sidList.add(sid);
            baseMyBatisDAO.delete(SQL_NS + "deleteUserList", sidList);
        }
    }

    @Transactional
    @Override
    public void deleteUserList(List<String> sidList) {
        if (CollectionUtils.isNotEmpty(sidList)) {
            baseMyBatisDAO.delete(SQL_NS + "deleteUserList", sidList);
        }
    }

    @Override
    public boolean valid(String username, String password) {
        boolean flag = false;
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("userName", username);
            List<UserEntity> list = findUserListByCondition(map);
            if (CollectionUtils.isNotEmpty(list)) {
                UserEntity user = list.get(0);
                String pwd = StringEncrypter.getHashValue(password, GlobalConstant.HASH_COMPUTE_TYPE);
                if (pwd.equals(user.getPasswordHash())) {
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
            Map<String, String> map = new HashMap<String, String>();
            map.put("userName", username);
            List<UserEntity> list = findUserListByCondition(map);
            if (CollectionUtils.isNotEmpty(list)) {
                flag = true;
            }
        }
        return flag;
    }

}
