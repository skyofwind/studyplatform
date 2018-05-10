package org.studyplatform.model;

/**
 * Created by dzj on 2017/6/21.
 */
public class UpdataPsw {
    String yuanpassword;

    String password;

    String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public String getYuanpassword() {
        return yuanpassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setYuanpassword(String yuanpassword) {
        this.yuanpassword = yuanpassword;
    }
}
