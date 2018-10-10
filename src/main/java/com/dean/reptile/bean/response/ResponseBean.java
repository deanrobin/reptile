package com.dean.reptile.bean.response;

import com.alibaba.fastjson.JSON;
import com.dean.reptile.constant.ResponseStatus;

import java.io.Serializable;

public class ResponseBean implements Serializable {

    private String message;
    private Integer status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public static ResponseBean getSuccess() {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setMessage("request success!");
        responseBean.setStatus(ResponseStatus.SUCCESS.getstatus());
        return responseBean;
    }

}
