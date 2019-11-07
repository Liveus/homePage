package com.liveus.core.user.pojo.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.liveus.common.bean.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Desc: 登录日志
 * @author: Lenovo
 * @Time: 2019/11/7 16:58
 * @Copyright: © 2018 杭州凯立通信有限公司 版权所有
 * @Warning: 本内容仅限于公司内部传阅, 禁止外泄或用于其它商业目的
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_log_login")
@NoArgsConstructor
public class LoginLogEntity extends BaseEntity {

    @Id
    @TableId(type= IdType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "登录名")
    private String login_name;

    @ApiModelProperty(value = "登陆密码")
    private String login_pass;

    @ApiModelProperty(value = "返回消息")
    private String response;

    @ApiModelProperty(value = "客户端IP")
    private String client_ip;

    public LoginLogEntity(String login_name, String login_pass, String response, String client_ip, String createBy, Date CreateTime) {
        this.login_name = login_name;
        this.login_pass = login_pass;
        this.response = response;
        this.client_ip = client_ip;
        super.setCreateBy(createBy);
        super.setCreateTime(CreateTime);
    }
}
