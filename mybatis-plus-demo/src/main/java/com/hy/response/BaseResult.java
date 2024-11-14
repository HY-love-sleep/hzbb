package com.hy.response;

import com.hy.event.enums.BaseResultCodeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 基础的请求响应接口定义
 *
 * @author 王一飞
 * @since 2021/10/21
 */
@Getter
@Setter
public class BaseResult implements BaseResponse {

    /**
     * 返回标记 如果该标记为200则表明返回结果正确 否则都是错误消息
     */
    private int code = BaseResultCodeEnum.SUCCESS.getCode();

    /**
     * 返回消息 一般用于返回错误描述或者操作提示
     */
    private String msg = BaseResultCodeEnum.SUCCESS.getMsg();

    /**
     * 返回时间
     */
    private Long timestamp = System.currentTimeMillis();

    /**
     * 返回标记 对象
     */
    private Object data;

    /**
     * 返回标记 对象
     */
    private Boolean encrypted = false;

    public BaseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResult() {
    }

    public BaseResult(BaseResponse code) {
        this(code.getCode(), code.getMsg());
    }

    public static BaseResult success() {
        return success("");
    }

    public static BaseResult success(Object data) {
        BaseResult baseResult = new BaseResult();
        baseResult.setData(data);
        return baseResult;
    }

    public static BaseResult error(int code, String msg) {
        return new BaseResult(code, msg);
    }

    public static BaseResult error(BaseResponse baseResponse) {
        return new BaseResult(baseResponse.getCode(), baseResponse.getMsg());
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getData() {
        return null == data ? null : (T) data;
    }
}
