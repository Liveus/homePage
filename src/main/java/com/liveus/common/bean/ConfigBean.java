package com.liveus.common.bean;


//@ConfigurationProperties(prefix = "com.liveus")
public class ConfigBean {
    private String username = "Liveus11";
    private String password = "Shen11";

    public String getusername() {
        return username;
    }

    public String getpassword() {
        return password;
    }

    @Override
    public String toString() {
        return "ConfigBean{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setusername(String username) {
        this.username = username;
    }

    public void setpassword(String password) {
        this.password = password;
    }
}
