package com.liveus.common.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 系统配置bean------------------暂未使用
 */
@Configuration
@ConfigurationProperties(prefix = "com.liveus")
@PropertySource("classpath:Configuration.properties")
public class ConfigBean {

    @Value("${openId}")
    private String openId;

    @Value("${token.expired.time}")
    private long tokenExpiredTime;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwtId}")
    private String jwtId;

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOpenId() {
        return openId;
    }

    public long getTokenExpiredTime() {
        return tokenExpiredTime;
    }

    public void setTokenExpiredTime(long tokenExpiredTime) {
        this.tokenExpiredTime = tokenExpiredTime;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }

    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public String getJwtId() {
        return jwtId;
    }

    public void setJwtId(String jwtId) {
        this.jwtId = jwtId;
    }

    @Override
    public String toString() {
        return "ConfigBean{" +
                "openId='" + openId + '\'' +
                ", tokenExpiredTime=" + tokenExpiredTime +
                ", jwtSecret='" + jwtSecret + '\'' +
                ", jwtId='" + jwtId + '\'' +
                '}';
    }
}
