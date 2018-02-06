package sysmgt.entity;

import common.bean.ToStringBean;
import common.constant.GlobalConstant;
import common.util.DateUtils;

import java.util.Date;

/**
 * 登录日志的实体类
 *
 * @author yanghp1984
 * @version 1.0.0 2018-02-06
 */
public class LoginLogEntity extends ToStringBean {
    private static final long serialVersionUID = 6468211086624383078L;
    private String logSid;
    private String userSid;
    private String visitIp;
    private String visitTime;

    /**
     * 获取日志序号
     *
     * @return 日志序号
     */
    public String getLogSid() {
        return logSid;
    }

    /**
     * 设置日志序号
     *
     * @param logSid 日志序号
     */
    public void setLogSid(String logSid) {
        this.logSid = logSid;
    }

    /**
     * 获取用户序号
     *
     * @return 用户序号
     */
    public String getUserSid() {
        return userSid;
    }

    /**
     * 设置用户序号
     *
     * @param userSid 用户序号
     */
    public void setUserSid(String userSid) {
        this.userSid = userSid;
    }

    /**
     * 获取登录IP地址
     *
     * @return IP地址
     */
    public String getVisitIp() {
        return visitIp;
    }

    /**
     * 设置登录IP地址
     *
     * @param visitIp IP地址
     */
    public void setVisitIp(String visitIp) {
        this.visitIp = visitIp;
    }

    /**
     * 获取登录时间
     *
     * @return 登录时间
     */
    public String getVisitTime() {
        return visitTime;
    }

    /**
     * 设置登录时间
     *
     * @param visitTime 登录时间
     */
    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    /**
     * 设置登录时间
     *
     * @param visitTime 登录时间
     */
    public void setVisitTime(Date visitTime) {
        this.setVisitTime(DateUtils.dateToString(visitTime, GlobalConstant.DATE_TIME_PATTERN));
    }
}