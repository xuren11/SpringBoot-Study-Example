package com.xuren.study.controller;

import com.xuren.study.bean.ExcelTestBean;
import com.xuren.study.service.ExceloutputServiceImpl;
import com.xuren.study.util.ContentTypeUtils;
import com.xuren.study.util.ExcelxlsxWriter;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    ExceloutputServiceImpl exceloutputService;

    @GetMapping(value = "/exporttest")
    public void selectSerialsExport(HttpServletResponse response) throws IOException {

        try {
            ExcelxlsxWriter<ExcelTestBean> exportExcel = exceloutputService.exportTestList();
            this.setExcResponse(response, exportExcel, exportExcel.getFilename() + ".xlsm");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedEncodingException(); // Messages.getMessageCode("unsupport.encoding")
        } catch (IOException e) {
            throw new IOException(); //Messages.getMessageCode("platform.exception", e.getLocalizedMessage())
        }
    }

    protected void setExcResponse(HttpServletResponse response, ExcelxlsxWriter<?> excel, String fileName) throws IOException {
        try (ServletOutputStream out = response.getOutputStream();) {
            response.setContentType(ContentTypeUtils.contentType(FilenameUtils.getExtension(fileName)).getContentType());
            response.setHeader("Content-Disposition", "filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + "\";");
            excel.write(out);
            out.flush();
        } finally {
            if (null != excel) {
                excel.close();
            }
        }
    }

}
