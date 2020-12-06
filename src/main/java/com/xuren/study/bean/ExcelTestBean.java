package com.xuren.study.bean;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel("Excel数据导出 模型Bean")
public class ExcelTestBean implements Serializable {

    private static final long serialVersionUID = -7853201004625654335L;

    String name;

    int age;

    String address;

    public ExcelTestBean(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

