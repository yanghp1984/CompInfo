package sysmgt.service;

import java.util.List;
import java.util.Map;

import common.bean.Paging;
import sysmgt.entity.UserEntity;

/**
 * 用户信息服务接口
 *
 * @author yanghp1984
 * @version 1.0.0 2018-02-06
 */
public interface UserService {
    /**
     * 根据序号查询用户信息
     *
     * @param sid 序号
     * @return 用户信息
     */
    UserEntity findUser(String sid);

    /**
     * 根据条件查询用户信息列表
     *
     * @param parameters 查询条件
     * @return 用户信息列表
     */
    List<UserEntity> findUserListByCondition(Map<String, String> parameters);

    /**
     * 根据条件和查询参数，查询用户信息并分页
     *
     * @param entity 查询条件
     * @param paging 分页参数
     * @return 分页数据
     */
    Paging findUserPageByCondition(UserEntity entity, Paging paging);

    /**
     * 添加用户信息
     *
     * @param entity 用户信息
     * @return 序号，null 表示失败。
     */
    String addUser(UserEntity entity);

    /**
     * 修改用户信息
     *
     * @param entity 用户信息
     */
    void updateUser(UserEntity entity);

    /**
     * 根据序号删除用户信息
     *
     * @param sid 序号
     */
    void deleteUser(String sid);

    /**
     * 根据序号列表删除用户信息
     *
     * @param sidList 序号列表
     */
    void deleteUserList(List<String> sidList);

    /**
     * 验证用户身份，如果成功同时更新登录日志
     *
     * @param username 用户名
     * @param password 密码
     * @param ip IP地址
     * @return True 表示成功，False 表示失败。
     */
    boolean updateAndValidate(String username, String password, String ip);

    /**
     * 验证用户是否已经存在
     *
     * @param username 用户名
     * @return 表示已存在，False 表示不存在。
     */
    boolean isExisted(String username);
}
