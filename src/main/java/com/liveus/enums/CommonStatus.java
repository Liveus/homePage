package com.liveus.enums;


/**
 * @Desc: HTTP codes
 * @author: Lenovo
 * @Time: 2019/10/18 16:31
 * @Copyright: © 2018 杭州凯立通信有限公司 版权所有
 * @Warning: 本内容仅限于公司内部传阅, 禁止外泄或用于其它商业目的
 */
public enum  CommonStatus implements  RestStatus {

    // 登陆登出
    LOGIN_OK(200,"Login success"),
    LOGIN_ERROR(500,"Login error"),
    LOGIN_ERROR_PASSWORD(500,"Password isn't correct"),
    LOGIN_ERROR_USERNAME(500,"User don't exist"),
    LOGOUT_OK(200,"Logout success"),
    LOGOUT_FAILED(500,"Logout failed"),
    // 业务操作
    ADD_OK(200,"Add ok"),
    ADD_FAILED(500,"Add failed"),
    UPDATE_OK(200,"Update success"),
    UPDATE_FAILED(500,"Update failed"),
    DELETE_OK(200,"Delete success"),
    DELETE_FAILED(500,"Delete failed")
    ;

    private final int value;

    private final String reasonPhrase;

    CommonStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }

    public static CommonStatus valueOf(int statusCode){
        for (CommonStatus status : values()) {
            if (status.value == statusCode )
                return status;
        }
        throw new IllegalArgumentException("No matching constant for ["+statusCode+"]");
    }

    @Override
    public int value() {
        return this.value;
    }

    @Override
    public String reasonPhrase() {
        return this.reasonPhrase;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }
}
