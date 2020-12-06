
package com.xuren.study.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 电子转账凭证bean
 * 
 * @author:fanlinxin 2017年6月6日 上午10:36:25
 */
@ApiModel("电子转账凭证bean")
public class IouTransferVoucher {

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @ApiModelProperty("测试字段")
    private String test;

    @ApiModelProperty("放款时间")
    private String beginDate;

    @ApiModelProperty(value = "借据编号")
    private String iouCode;

    @ApiModelProperty(value = "收款方")
    private String payeeName;

    @ApiModelProperty(value = "收款账户号")
    private String payeeActNo;

    @ApiModelProperty(value = "收款银行")
    private String bankName;

    @ApiModelProperty(value = "放款金额大写")
    private String chineseMoney;

    @ApiModelProperty(value = "放款金额")
    private String money;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "制单")
    private String maker;

    @ApiModelProperty(value = "制单时间")
    private String sysdate;

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getIouCode() {
        return iouCode;
    }

    public void setIouCode(String iouCode) {
        this.iouCode = iouCode;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPayeeActNo() {
        return payeeActNo;
    }

    public void setPayeeActNo(String payeeActNo) {
        this.payeeActNo = payeeActNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getChineseMoney() {
        return chineseMoney;
    }

    public void setChineseMoney(String chineseMoney) {
        this.chineseMoney = chineseMoney;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getSysdate() {
        return sysdate;
    }

    public void setSysdate(String sysdate) {
        this.sysdate = sysdate;
    }

}
