package com.rogrand.demo.bean;

/**
 * 登录
 */
public class LoginBean {

    /**
     * user_id :
     * access_token : 5a232a7a00e178db7bdab59258d95cc8
     * refresh_token : 03dcdc3b9a1ad49242207052591e3b28
     * expires_in : 2592000
     * auth_state : 1
     * token_type :
     */

    private String user_id;
    private String access_token;
    private String refresh_token;
    private int expires_in;
    private int auth_state;
    private String token_type;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public int getAuth_state() {
        return auth_state;
    }

    public void setAuth_state(int auth_state) {
        this.auth_state = auth_state;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "user_id='" + user_id + '\'' +
                ", access_token='" + access_token + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", expires_in='" + expires_in + '\'' +
                ", auth_state='" + auth_state + '\'' +
                ", token_type='" + token_type + '\'' +
                '}';
    }
}
