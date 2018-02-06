package sysmgt.entity;

import common.bean.ToStringBean;
import common.constant.GlobalConstant;
import common.util.DateUtils;
import common.util.StringEncrypter;

import java.util.Date;

/**
 * 用户实体类
 *
 * @author yanghp1984
 * @version 1.0.0 2018-02-06
 */
public class UserEntity extends ToStringBean {
    private static final long serialVersionUID = -1236782117855421675L;
    private String userSid;
    private String userName;
    private String passwordHash;
    private Integer credits;
    private String lastVisitIp;
    private String lastVisitTime;

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
     * 获取用户名
     *
     * @return 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取用户密码的HASH值
     *
     * @return 用户密码的HASH值
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * 设置用户密码的HASH值
     *
     * @param passwordHash 用户密码的HASH值
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * 设置用户密码
     *
     * @param password 用户密码
     */
    public void setPassword(String password) {
        this.setPasswordHash(StringEncrypter.getHashValue(password, GlobalConstant.HASH_COMPUTE_TYPE));
    }

    /**
     * 获取用户积分
     *
     * @return 用户积分
     */
    public Integer getCredits() {
        return credits;
    }

    /**
     * 设置用户积分
     *
     * @param credits 用户积分
     */
    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    /**
     * 获取最近访问的IP地址
     *
     * @return 最近访问的IP地址
     */
    public String getLastVisitIp() {
        return lastVisitIp;
    }

    /**
     * 设置最近访问的IP地址
     *
     * @param lastVisitIp 最近访问的IP地址
     */
    public void setLastVisitIp(String lastVisitIp) {
        this.lastVisitIp = lastVisitIp;
    }

    /**
     * 获取最近访问时间
     *
     * @return 最近访问时间
     */
    public String getLastVisitTime() {
        return lastVisitTime;
    }

    /**
     * 设置最近访问时间
     *
     * @param lastVisitTime 最近访问时间
     */
    public void setLastVisitTime(String lastVisitTime) {
        this.lastVisitTime = lastVisitTime;
    }

    /**
     * 设置最近访问时间
     *
     * @param lastVisitTime 最近访问时间
     */
    public void setLastVisitTime(Date lastVisitTime) {
        this.setLastVisitTime(DateUtils.dateToString(lastVisitTime, GlobalConstant.DATE_TIME_PATTERN));
    }
}
