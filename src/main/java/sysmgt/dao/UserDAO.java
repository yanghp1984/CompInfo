package sysmgt.dao;

import common.bean.Paging;
import common.dao.BaseDAO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import sysmgt.entity.UserEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户信息DAO
 *
 * @author yanghp1984
 * @version 1.0.0 2018-02-08
 */
@Lazy
@Repository
public class UserDAO extends BaseDAO {
    /**
     * SQL 命名空间
     */
    private static final String SQL_NS = "mybatis.sysmgt.mapper.UserMapper.";

    /**
     * 根据条件查询用户信息列表
     *
     * @param parameters 查询条件
     * @return 用户信息列表
     */
    public List<UserEntity> findUserListByCondition(Map<String, String> parameters) {
        return baseMyBatisDAO.findForList(SQL_NS + "selectUserByCondition", parameters);
    }

    /**
     * 根据条件和分页参数，查询用户信息并分页
     * @param entity
     * @param page
     * @return
     */
    public Paging findUserPageByCondition(UserEntity entity, Paging page) {
        page.setStringParameters(entity);
        page = baseMyBatisDAO.findForPageHelper(SQL_NS + "selectUserByCondition", page);
        return page;
    }

    /**
     * 添加用户信息
     *
     * @param entity 用户信息
     * @return 序号，null 表示失败。
     */
    public String addUser(UserEntity entity) {
        entity.setUserSid(null);
        baseMyBatisDAO.insert(SQL_NS + "insertUser", entity);
        return entity.getUserSid();
    }

    /**
     * 修改用户信息
     *
     * @param entity 用户信息
     */
    public void updateUser(UserEntity entity) {
        baseMyBatisDAO.update(SQL_NS + "updateUser", entity);
    }

    /**
     * 根据序号列表删除用户信息
     *
     * @param sidList 序号列表
     */
    public void deleteUserList(List<String> sidList) {
        if (CollectionUtils.isNotEmpty(sidList)) {
            baseMyBatisDAO.delete(SQL_NS + "deleteUserList", sidList);
        }
    }
}
