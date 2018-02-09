package sysmgt.dao;

import common.bean.Paging;
import common.dao.BaseDAO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import sysmgt.entity.LoginLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 登录日志DAO
 *
 * @author yanghp1984
 * @version 1.0.0 2018-02-08
 */
@Lazy
@Repository
public class LoginLogDAO extends BaseDAO {
    /**
     * SQL 命名空间
     */
    private static final String SQL_NS = "mybatis.sysmgt.mapper.LoginLogMapper.";

    /**
     * 根据条件查询登录日志列表
     *
     * @param parameters 查询条件
     * @return 登录日志列表
     */
    public List<LoginLogEntity> findLoginLogListByCondition(Map<String, String> parameters) {
        return baseMyBatisDAO.findForList(SQL_NS + "selectLoginLogByCondition", parameters);
    }

    /**
     * 根据条件和分页参数，查询登录日志数据并分页
     *
     * @param entity 查询条件
     * @param paging 分页参数
     * @return 分页数据
     */
    public Paging findLoginLogPageByCondition(LoginLogEntity entity, Paging paging) {
        paging.setStringParameters(entity);
        paging = baseMyBatisDAO.findForPageHelper(SQL_NS + "selectLoginLogByCondition", paging);
        return paging;
    }

    /**
     * 添加登录日志
     *
     * @param entity 登录日志
     * @return 序号，null 表示失败。
     */
    public String addLoginLog(LoginLogEntity entity) {
        entity.setLogSid(null);
        baseMyBatisDAO.insert(SQL_NS + "insertLoginLog", entity);
        return entity.getLogSid();
    }

    /**
     * 更新登录日志
     *
     * @param entity 登录日志
     */
    public void updateLoginLog(LoginLogEntity entity) {
        baseMyBatisDAO.update(SQL_NS + "updateLoginLog", entity);
    }

    /**
     * 根据序号列表删除登录日志
     *
     * @param sidList 序号列表
     */
    public void deleteLoginLogList(List<String> sidList) {
        if (CollectionUtils.isNotEmpty(sidList)) {
            baseMyBatisDAO.delete(SQL_NS + "deleteLoginLogList", sidList);
        }
    }
}
