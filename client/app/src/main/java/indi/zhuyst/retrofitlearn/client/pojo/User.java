package indi.zhuyst.retrofitlearn.client.pojo;

/**
 * Created by zhuyst on 2017/7/8.
 */

public class User {
    private String username;
    private String password;
    private String password_retry;
    private Boolean isRememberChecked;
    private Boolean isAutoLoginChecked;
    private String email;
    private String sex;

    public User() {}

    public User(String username){
        this.username = username;
    }

    public User(String username,String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword_retry() {
        return password_retry;
    }

    public void setPassword_retry(String password_retry) {
        this.password_retry = password_retry;
    }

    public Boolean getRememberChecked() {
        return isRememberChecked;
    }

    public void setRememberChecked(Boolean rememberChecked) {
        isRememberChecked = rememberChecked;
    }

    public Boolean getAutoLoginChecked() {
        return isAutoLoginChecked;
    }

    public void setAutoLoginChecked(Boolean autoLoginChecked) {
        isAutoLoginChecked = autoLoginChecked;
    }
}
