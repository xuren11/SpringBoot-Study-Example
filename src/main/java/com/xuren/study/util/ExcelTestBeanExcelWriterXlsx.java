package com.xuren.study.util;

import com.xuren.study.bean.ExcelTestBean;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;
import java.util.Map;


/**
 *  @author: xuren
 * @date 2018/1/18
 * 充值流水明细导出xlsx
 */
public class ExcelTestBeanExcelWriterXlsx extends ExcelxlsxWriter<ExcelTestBean> {

    @Override
    public void buildHeader(Map<String, Object> headData) {
    }

    @Override
    public void buildBody(List<ExcelTestBean> data) {
        XSSFSheet sheet = getSheetAt(0);
        if(CollectionUtils.isEmpty(data)){
            return;
        }
        for(int i=0;i<data.size();i++){
            ExcelTestBean testBean=data.get(i);
            //从第二行开始，第一行是表头
            int rownum=i+1;
            XSSFRow row=getRow(sheet, rownum);
//            // 交易流水号
//            createCell(row, 0, rechargeBeanInfo.getRequestNo());
//            // 平台用户编号
//            createCell(row, 1, rechargeBeanInfo.getCustID());
//            // 用户名称
//            createCell(row, 2, rechargeBeanInfo.getCustName());
//            // 用户类型
//            createCell(row, 3, rechargeBeanInfo.getCustTypeDesc());
//            // 账号类型
//            createCell(row, 4, rechargeBeanInfo.getCustKindDesc());
//            // 手机号码
//            createCell(row, 5, rechargeBeanInfo.getCustPhoneNo());
//            // 证件类型
//            createCell(row, 6, rechargeBeanInfo.getIdCardTypeDesc());
//            // 证件账号
//            createCell(row, 7, rechargeBeanInfo.getIdCard());
//            // 充值金额
//            createCell(row, 8, BigDecimalUtils.nullToZero(rechargeBeanInfo.getAmount()));
//            // 操作渠道
//            createCell(row, 9, rechargeBeanInfo.getOperateWayDesc());
//            // 充值方式
//            createCell(row, 10, rechargeBeanInfo.getRechargeWayDesc());
//            // 订单状态
//            createCell(row, 11, rechargeBeanInfo.getStatusDesc());
//            // 银行
//            createCell(row, 12, rechargeBeanInfo.getBank());
//            // 银行卡号
//            createCell(row, 13, rechargeBeanInfo.getBankCardNo());
//            // 返回码
//            createCell(row, 14, rechargeBeanInfo.getErrorCode());
//            // 失败原因
//            createCell(row, 15, rechargeBeanInfo.getErrorMessage());
//            // 交易发起时间
//            createCell(row, 16, DateTimeUtils.formatYMD(rechargeBeanInfo.getCreateTime()));
//            // 交易完成时间
//            createCell(row, 17, DateTimeUtils.formatYMD(rechargeBeanInfo.getTransactionTime()));

//             交易流水号
            createCell(row, 0, testBean.getName());
            // 平台用户编号
            createCell(row, 1, testBean.getAge());
            // 用户名称
            createCell(row, 2, testBean.getAddress());

        }
    }

    @Override
    public void buildFoot() {
    }

}
