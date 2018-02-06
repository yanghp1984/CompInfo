package sysmgt.service;

import sysmgt.entity.LoginLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 登录日志服务接口的具体实现类
 *
 * @author yanghp1984
 * @version 1.0.0 2018-02-06
 */
public class LoginLogServiceImpl implements LoginLogService {
    /**
     * 根据序号查询登录日志
     *
     * @param sid 序号
     * @return 登录日志
     */
    @Override
    public LoginLogEntity findLoginLog(String sid) {
        return null;
    }

    /**
     * 根据条件查询登录日志列表
     *
     * @param parameters 查询条件
     * @return 登录日志列表
     */
    @Override
    public List<LoginLogEntity> findLoginLogListByCondition(Map<String, String> parameters) {
        return null;
    }

    /**
     * 添加登录日志
     *
     * @param entity 登录日志
     * @return 序号，null 表示失败。
     */
    @Override
    public String addLoginLog(LoginLogEntity entity) {
        return null;
    }

    /**
     * 更新登录日志
     *
     * @param entity 登录日志
     */
    @Override
    public void updateLoginLog(LoginLogEntity entity) {

    }

    /**
     * 根据序号删除登录日志
     *
     * @param sid 序号
     */
    @Override
    public void deleteLoginLog(String sid) {

    }

    /**
     * 根据序号列表删除登录日志
     *
     * @param sidList 序号列表
     */
    @Override
    public void deleteLoginLogList(List<String> sidList) {

    }
}
