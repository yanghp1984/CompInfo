package sysmgt.web;

import common.constant.GlobalConstant;
import common.util.StringEncrypter;

public class LoginCommand {
    private String userName;
    private String passwordHash;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setPassword(String password) {
        this.passwordHash = StringEncrypter.getHashValue(password, GlobalConstant.HASH_COMPUTE_TYPE);
    }
}
