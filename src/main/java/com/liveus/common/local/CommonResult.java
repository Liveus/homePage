package com.liveus.common.local;

import com.liveus.common.Constant.Constant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Package: com.liveus.common.local
 * @Author: shen2
 * @Description:
 * @Date: 2020/9/3 13:58
 */
@Data
@Component
public class CommonResult<T> {
//    private static LocaleMessageService localeMessageService;
//
//    @Autowired
//    @SuppressFBWarnings("ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD")
//    public CommonResult(LocaleMessageService localeMessageService) {
//        CommonResult.localeMessageService = localeMessageService;
//    }

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "兼容app接口状态")
    private boolean success=true;

    @ApiModelProperty(value = "兼容app接口错误状态")
    private String errCode;

    @ApiModelProperty(value = "兼容app接口错误信息")
    private String errMsg;

    @ApiModelProperty(value = "状态信息")
    private String info;

    @ApiModelProperty(value = "数据")
    private T data;

    public CommonResult() {
    }

    public CommonResult(String status) {
        this.status = status;
        this.errCode =status;
        if (status ==Constant.STATUS_FAILURE)
            this.success=false;
    }

    public CommonResult(String status, String info, T data) {
        if (status ==Constant.STATUS_FAILURE)
            this.success=false;
        this.errCode=status;
        this.errMsg=info;
        this.status = status;
        this.info = info;
        this.data = data;
    }

    public static <T> CommonResult<T> getSuccess() {
        return new CommonResult<>(Constant.STATUS_SUCCESS, null, null);
    }

    public static <T> CommonResult<T> getSuccess(String info, T data) {
        return new CommonResult<>(Constant.STATUS_SUCCESS, info, data);
    }

    public static <T> CommonResult<T> getFailure() {
        return new CommonResult<>(Constant.STATUS_FAILURE, null, null);
    }

    public static <T> CommonResult<T> getFailure(String info, T data) {
        return new CommonResult<>(Constant.STATUS_FAILURE, info, data);
    }

//    public static <T> CommonResult<T> noData() {
//        return new CommonResult<>(Constant.STATUS_FAILURE, localeMessageService.getMessage("notice.no_data"), null);
//    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
