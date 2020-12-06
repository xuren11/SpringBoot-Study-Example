/*
 * All rights Reserved, Designed By 农金圈 2016年10月9日 上午11:12:58
 */

package com.xuren.study.util;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 
 * @author: HuangXin
 */
public class MessageCode {

    private String code;

    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
