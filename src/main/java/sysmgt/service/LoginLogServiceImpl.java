package sysmgt.service;

import common.dao.BaseDAO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sysmgt.entity.LoginLogEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录日志服务接口的具体实现类
 *
 * @author yanghp1984
 * @version 1.0.0 2018-02-06
 */
@Lazy
@Service
public class LoginLogServiceImpl extends BaseDAO implements LoginLogService {
    /**
     * SQL 命名空间
     */
    private static final String SQL_NS = "mybatis.sysmgt.mapper.LoginLogMapper.";

    /**
     * 根据序号查询登录日志
     *
     * @param sid 序号
     * @return 登录日志
     */
    @Override
    public LoginLogEntity findLoginLog(String sid) {
        LoginLogEntity entity = null;
        if (StringUtils.isNotBlank(sid)) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("logSid", sid);
            entity = baseMyBatisDAO.findUnique(SQL_NS + "selectLoginLogByCondition", map);
        }
        return entity;
    }

    /**
     * 根据条件查询登录日志列表
     *
     * @param parameters 查询条件
     * @return 登录日志列表
     */
    @Override
    public List<LoginLogEntity> findLoginLogListByCondition(Map<String, String> parameters) {
        return baseMyBatisDAO.findForList(SQL_NS + "selectLoginLogByCondition", parameters);
    }

    /**
     * 添加登录日志
     *
     * @param entity 登录日志
     * @return 序号，null 表示失败。
     */
    @Transactional
    @Override
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
    @Transactional
    @Override
    public void updateLoginLog(LoginLogEntity entity) {
        baseMyBatisDAO.update(SQL_NS + "updateLoginLog", entity);
    }

    /**
     * 根据序号删除登录日志
     *
     * @param sid 序号
     */
    @Transactional
    @Override
    public void deleteLoginLog(String sid) {
        if (StringUtils.isNotBlank(sid)) {
            List<String> sidList = new ArrayList<String>();
            sidList.add(sid);
            baseMyBatisDAO.delete(SQL_NS + "deleteLoginLogList", sidList);
        }
    }

    /**
     * 根据序号列表删除登录日志
     *
     * @param sidList 序号列表
     */
    @Transactional
    @Override
    public void deleteLoginLogList(List<String> sidList) {
        if (CollectionUtils.isNotEmpty(sidList)) {
            baseMyBatisDAO.delete(SQL_NS + "deleteLoginLogList", sidList);
        }
    }
}
