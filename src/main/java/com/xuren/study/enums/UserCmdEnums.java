package com.xuren.study.enums;

import java.util.Objects;

/**
 * user_cmd 相关描述枚举类
 * @author xuren
 * @date 2020/11/06
 */
public enum UserCmdEnums {

//    public static String[] cmd_name = {"获取电表信息","拉闸", "合闸", "获取拉合闸时间","读取阈值",
//    "读取上报周期","设置阈值","设置上报周期","设置拉合闸时间","拉合闸状态变化"};

    GET_METER_INFO(1,"获取电表信息"),
    PULL(2,"拉闸"),
    CLOSE(3,"合闸"),
    GET_TIME_OF_PULL_ANG_CLOSE(4,"获取拉合闸时间"),
    READ_THRESHOLD(5,"读取阈值"),
    READ_UP_PERIOD(6,"读取上报周期"),
    SET_THRESHOLD(7,"设置阈值"),
    SET_UP_PERIOD(8,"设置上报周期"),
    SET_CLOSE_TIME(9,"设置拉合闸时间"),
    STATUS_OF_PULL_AND_CLOSE(10,"拉合闸状态变化");

    private int code;

    private String description;
    
    UserCmdEnums(int code , String description) {
        this.description = description;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static String getDescriptionByCode(Integer code){
        for (UserCmdEnums tradeStatus : UserCmdEnums.values()) {
            if (Objects.equals(code, tradeStatus.getCode())) {
                return tradeStatus.getDescription();
            }
        }
        return null;
    }

}
