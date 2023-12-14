package com.hy.exception;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description: 数据传输格式
 * Author: yhong
 * Date: 2023/12/6
 */
@NoArgsConstructor
@Data
public class ResultBody {
     private String code;
     private String message;
     private Object result;

     public ResultBody(BaseErrorInfoInterface errorInfo) {
         this.code = errorInfo.getResultCode();
         this.message = errorInfo.getResultMsg();
     }

    public static ResultBody success() {
        return success(null);
    }

    public static ResultBody success(Object data) {
         ResultBody rb = new ResultBody();
         rb.setCode(CommonEnum.SUCCESS.getResultCode());
         rb.setMessage(CommonEnum.SUCCESS.getResultMsg());
         rb.setResult(data);
         return rb;
    }

    public static ResultBody error(BaseErrorInfoInterface errorInfo) {
        ResultBody rb = new ResultBody();
        rb.setCode(errorInfo.getResultCode());
        rb.setMessage(errorInfo.getResultMsg());
        rb.setResult(null);
        return rb;
    }

    public static ResultBody error(String code, String msg) {
        ResultBody rb = new ResultBody();
        rb.setCode(code);
        rb.setMessage(msg);
        rb.setResult(null);
        return rb;
    }

    public static ResultBody error(String message) {
        ResultBody rb = new ResultBody();
        rb.setCode("-1");
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

    @Override
    public String toString() {
         return JSONObject.toJSONString(this);
    }
}
