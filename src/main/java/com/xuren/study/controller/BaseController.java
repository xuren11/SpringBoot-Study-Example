/*
 * All rights Reserved, Designed By 农金圈 2016年9月22日 下午3:21:50
 */

package com.xuren.study.controller;

import com.xuren.study.util.ContentTypeUtils;
import com.xuren.study.util.ExcelxlsReader;
import com.xuren.study.util.ExcelxlsxReader;
import com.xuren.study.util.ExcelxlsxWriter;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;

/**
 * LambdaBaseController
 * 
 * @author: rayping 2016年11月23日
 */
public class BaseController {

    public static final Logger log = LoggerFactory.getLogger(Class.class.getName());

//    @InitBinder
//    public void initBinder(WebDataBinder binder, WebRequest request) {
//        // BigDecimal格式处理
//        binder.registerCustomEditor(BigDecimal.class, new PropertyEditorSupport() {
//
//            @Override
//            public void setAsText(String text) throws IllegalArgumentException {
//                if (StringUtil.isBlank(text)) {
//                    setValue(null);
//                } else {
//                    setValue(new BigDecimal(text.trim().replace(",", "")));
//                }
//            }
//        });
//    }

    /**
     * 下载文件
     *
     * @param response
     * @param tempName
     * @param finalFileName
     * @throws IOException
     */
    protected void setFileResponse(HttpServletResponse response, String tempName, String finalFileName) throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream(tempName);
        // 以流的形式下载文件。
        if (null != inputStream) {
            // 清空response， 设置response的Header
            response.reset();
            response.setHeader("Content-disposition", "attachment;filename=" + java.net.URLEncoder.encode(finalFileName, "UTF-8"));
            response.addHeader("Content-Length", "" + inputStream.available());
            response.setContentType("application/octet-stream");

            // 循环取出流中的数据
            byte[] b = new byte[1024];
            int len;
            while ((len = inputStream.read(b)) > 0) {
                response.getOutputStream().write(b, 0, len);
            }
            inputStream.close();
        }
    }

    protected void setExcResponse(HttpServletResponse response, ExcelxlsxWriter<?> excel, String fileName) throws IOException {
        try(ServletOutputStream out = response.getOutputStream();){
            response.setContentType(ContentTypeUtils.contentType(FilenameUtils.getExtension(fileName)).getContentType());
            response.setHeader("Content-Disposition", "filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + "\";");
            excel.write(out);
            out.flush();
        }finally {
            if (null != excel) {
                excel.close();
            }
        }
    }


    /**
     * 从网络Url中下载文件
     * 
     * @param URL
     * @param fileName 文件名称+文件后缀
     */
    protected void downLoadFromUrl(HttpServletResponse response, String URL, String fileName, String fileExt, String contentType) {
        try {
            URL url = new URL(URL);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            downLoad(response, is, fileName, fileExt, contentType);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    protected void downLoad(HttpServletResponse response, InputStream is, String fileName, String fileExt, String contentType) {
        if (null != is) {
            response.reset(); // 必要地清除response中的缓存信息
            response.setContentType(contentType);
            try {
                String encodeFileName = java.net.URLEncoder.encode(fileName + "." + fileExt, "UTF-8");
                encodeFileName = encodeFileName.replaceAll("%28", "(");
                encodeFileName = encodeFileName.replaceAll("%29", ")");
                response.setHeader("Content-Disposition",
                    "attachment; filename=" + encodeFileName);
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }

            ServletOutputStream out;
            try {
                out = response.getOutputStream();
                byte[] content = new byte[1024]; // is.available()
                int length = 0;
                while ((length = is.read(content)) != -1) {
                    out.write(content, 0, length);
                }
                out.flush();
                out.close();
                is.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 
     *
     * @param fileExtension 文件后缀
     * @param inputStream 文件流
     * @return
     * @throws Exception
     */
    protected LinkedHashMap<String, LinkedHashMap<String, String>> realExcelToLinkedMap(String fileExtension,
        InputStream inputStream,String type) throws Exception {
        LinkedHashMap<String, LinkedHashMap<String, String>> linkedMap = null;
        try {
            ExcelxlsxReader excelxReader = new ExcelxlsxReader();
            if ("xlsx".equalsIgnoreCase(fileExtension) || "xlsm".equalsIgnoreCase(fileExtension)) {
                // EXCEL2007版
                if("MATCH_FLOW".equals(type)){
                    linkedMap = excelxReader.readExcelContent(inputStream,13);
                }else{
                    linkedMap = excelxReader.readExcelContent(inputStream);
                }
            } else {
                ExcelxlsReader excelReader = new ExcelxlsReader();
                if("MATCH_FLOW".equals(type)){
                    linkedMap = excelxReader.readExcelContent(inputStream,13);
                }else{
                    linkedMap = excelReader.readExcelContent(inputStream);
                }
            }
        } catch (Exception e) {
            log.error("imExlTempl .. {}", e);
            throw new Exception("Excel格式不正确,请确认再进行操作");
        }
        return linkedMap;
    }


    protected void writeResponseHeader(HttpServletResponse response, InputStream inputStream) throws IOException {
        if (null != inputStream) {
            response.setHeader("Content-Type", "image/png");
            byte[] b = new byte[1024];
            int len;
            while ((len = inputStream.read(b, 0, 1024)) != -1) {
                response.getOutputStream().write(b, 0, len);
            }
            inputStream.close();
        }
    }

}
