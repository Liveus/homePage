package com.liveus.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "com.liveus")
@PropertySource("classpath:TestConfiguration.properties")
public class Configbean2 {
    private String username;
    private String passWord;
    @Override
    public String toString() {
        return "ConfigBean{" +
                "username='" + username + '\'' +
                ", password='" + passWord + '\'' +
                '}';
    }

    public void setusername(String username) {
        this.username = username;
    }

    public void setpassword(String password) {
        this.passWord = password;
    }
}
