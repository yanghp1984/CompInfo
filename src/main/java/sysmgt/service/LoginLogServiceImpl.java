package sysmgt.service;

import common.bean.Paging;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import sysmgt.dao.LoginLogDAO;
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
public class LoginLogServiceImpl implements LoginLogService {
    @Autowired
    private LoginLogDAO loginLogDAO;

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
            List<LoginLogEntity> list = loginLogDAO.findLoginLogListByCondition(map);
            if (CollectionUtils.isNotEmpty(list)) {
                entity = list.get(0);
            }
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
        return loginLogDAO.findLoginLogListByCondition(parameters);
    }

    /**
     * 根据条件和分页参数，查询登录日志数据并分页
     *
     * @param entity 查询条件
     * @param paging 分页参数
     * @return 分页数据
     */
    @Override
    public Paging findLoginLogPageByCondition(LoginLogEntity entity, Paging paging) {
        return loginLogDAO.findLoginLogPageByCondition(entity, paging);
    }

    /**
     * 添加登录日志
     *
     * @param entity 登录日志
     * @return 序号，null 表示失败。
     */
    @Override
    public String addLoginLog(LoginLogEntity entity) {
        return loginLogDAO.addLoginLog(entity);
    }

    /**
     * 更新登录日志
     *
     * @param entity 登录日志
     */
    @Override
    public void updateLoginLog(LoginLogEntity entity) {
        loginLogDAO.updateLoginLog(entity);
    }

    /**
     * 根据序号删除登录日志
     *
     * @param sid 序号
     */
    @Override
    public void deleteLoginLog(String sid) {
        if (StringUtils.isNotBlank(sid)) {
            List<String> sidList = new ArrayList<String>();
            sidList.add(sid);
            loginLogDAO.deleteLoginLogList(sidList);
        }
    }

    /**
     * 根据序号列表删除登录日志
     *
     * @param sidList 序号列表
     */
    @Override
    public void deleteLoginLogList(List<String> sidList) {
        loginLogDAO.deleteLoginLogList(sidList);
    }
}
