package com.xuren.study.service;

import com.xuren.study.bean.ExcelTestBean;
import com.xuren.study.util.ContentTypeUtils;
import com.xuren.study.util.ExcelTestBeanExcelWriterXlsx;
import com.xuren.study.util.ExcelxlsxWriter;
import com.xuren.study.util.Messages;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @author: xuren
 * @date 2018/1/18
 */
@Service
public class ExceloutputServiceImpl  {

    /**
     * Excel导出 测试
     */
    public ExcelxlsxWriter<ExcelTestBean> exportTestList() throws IOException {
        ExcelxlsxWriter<ExcelTestBean> exc = new ExcelTestBeanExcelWriterXlsx();
        exc.setFromIdx(1);
        exc.setFilename("Excel导出测试数据");
        try {
//            List<RechargeListResultBean> exportBean = rechargeAndWithdrawalService.selectRechargeBean(query).getList();
            List<ExcelTestBean>  list = new ArrayList<>();
            list.add(new ExcelTestBean("xu",25,"ANYWHERE"));
            list.add(new ExcelTestBean("xuREN",25,"ANYWHERE"));
            list.add(new ExcelTestBean("xuRENN",25,"ANYWHERE"));

            exc.build(this.getClass().getResourceAsStream("/excel/excel_out_template.xlsx"), list,
                    exc.getFilename() + ".xlsm", new HashMap<String, Object>());

//            ExcelxlsxWriter<CustFundSerial> exportExcel = acctFundGatewayService.exportCustFundSerials(queryBean);
//            this.setExcResponse(response, exportExcel, exportExcel.getFilename() + ".xlsm");
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
//                    Messages.getMessageCode("file.export.notexists", "/excel/excel_out_template.xlsx");
        } catch (IOException e) {
            throw new IOException();
        }

        return exc;

    }

//    /**
//     * 提现明细导出
//     *
//     * @param query
//     * @return
//     * @throws LambdaServiceException
//     */
//    @Override
//    public ExcelxlsxWriter<WithdrawalListResultBean> exportWithdrawList(WithdrawalListQueryBean query) throws LambdaServiceException {
//        ExcelxlsxWriter<WithdrawalListResultBean> exc = new WithdrawExcelWriterXlsx();
//        exc.setFromIdx(1);
//
//        query.setPageNo(1);
//        query.setPageSize(Integer.MAX_VALUE);
//
//        exc.setFilename("用户提现流水数据");
//        try {
//            List<WithdrawalListResultBean> exportBean = rechargeAndWithdrawalService.selectWithdrawalBean(query).getList();
//            exc.build(this.getClass().getResourceAsStream(FinanceConstant.FINANCE_WITHDRAW_REPORT_TEMP), exportBean,
//                    exc.getFilename() + ".xlsm", new HashMap<String, Object>());
//        } catch (FileNotFoundException e) {
//            throw new LambdaServiceException(
//                    Messages.getMessageCode("file.export.notexists", FinanceConstant.FINANCE_WITHDRAW_REPORT_TEMP));
//        } catch (IOException e) {
//            throw new LambdaServiceException(Messages.getMessageCode("platform.exception", e.getLocalizedMessage()));
//        }
//        return exc;
//    }


    public void setExcResponse(HttpServletResponse response, ExcelxlsxWriter<?> excel, String fileName)
            throws UnsupportedEncodingException, IOException {
        response.setContentType(ContentTypeUtils.contentType(FilenameUtils.getExtension(fileName)).getContentType());
        response.setHeader("Content-Disposition", "filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + "\";");
        ServletOutputStream out = response.getOutputStream();
        excel.getWorkbook().write(out);
        out.flush();
        out.close();
    }

}


