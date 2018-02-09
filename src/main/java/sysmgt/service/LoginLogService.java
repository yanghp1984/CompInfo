package sysmgt.service;

import common.bean.Paging;
import sysmgt.entity.LoginLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 登录日志服务接口
 *
 * @author yanghp1984
 * @version 1.0.0 2018-02-06
 */
public interface LoginLogService {
    /**
     * 根据序号查询登录日志
     *
     * @param sid 序号
     * @return 登录日志
     */
    LoginLogEntity findLoginLog(String sid);

    /**
     * 根据条件查询登录日志列表
     *
     * @param parameters 查询条件
     * @return 登录日志列表
     */
    List<LoginLogEntity> findLoginLogListByCondition(Map<String, String> parameters);

    /**
     * 根据条件和分页参数，查询登录日志数据并分页
     *
     * @param entity 查询条件
     * @param paging 分页参数
     * @return 分页数据
     */
    Paging findLoginLogPageByCondition(LoginLogEntity entity, Paging paging);

    /**
     * 添加登录日志
     *
     * @param entity 登录日志
     * @return 序号，null 表示失败。
     */
    String addLoginLog(LoginLogEntity entity);

    /**
     * 更新登录日志
     *
     * @param entity 登录日志
     */
    void updateLoginLog(LoginLogEntity entity);

    /**
     * 根据序号删除登录日志
     *
     * @param sid 序号
     */
    void deleteLoginLog(String sid);

    /**
     * 根据序号列表删除登录日志
     *
     * @param sidList 序号列表
     */
    void deleteLoginLogList(List<String> sidList);
}
