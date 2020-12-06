package com.xuren.study.util;/*
 * All rights Reserved, Designed By 农金圈
 * 2017年6月9日10:35:34
 */

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * pdf生成器
 * @author:fanlinxin 2017年6月5日 下午6:26:43
 */
public class PdfGenerateUtil<T> {
    
    protected static final Logger logger = LoggerFactory.getLogger(PdfGenerateUtil.class);
    
    /**
     * 使用业务数据填充模板，生成pdf文件
     * <p>有一个强制约定：</p>
     * <p>业务bean字段必须与模板字段一致，且业务bean字段类型必须都是String</p>
     *
     * @param templatePathAndName 模板classpath路径+文件名
     * @param outputFileName 生成pdf文件名
     * @param bizBean 业务bean
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static <T> File generatePdfFile(String templatePathAndName, String outputFileName, T bizBean) throws IOException, DocumentException {
        File pdfOutFile = new File(outputFileName);
        PdfReader reader = new PdfReader(PdfGenerateUtil.class.getResourceAsStream(templatePathAndName));
        PdfStamper ps = new PdfStamper(reader, new FileOutputStream(pdfOutFile));
        // 取出报表模板中的所有字段
        AcroFields fields = ps.getAcroFields();
        // "STSongStd-Light"为字体，"UniGB-UCS2-H"为编码
        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();
        fontList.add(bfChinese);
        fields.setSubstitutionFonts(fontList);
        fillData(fields, bizBean);
        // 必须要调用这个，否则文档不会生成的
        ps.setFormFlattening(true);
        ps.close();
        reader.close();
        return pdfOutFile;
    }
    
    private static <T> void  fillData(AcroFields fields, T bizBean) throws IOException, DocumentException {
        for (String field : fields.getFields().keySet()) {
            try {
                Object value = PropertyUtils.getProperty(bizBean, field);
                fields.setField(field, value == null ? "" : value.toString());
            } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                logger.error("PdfGenerateUtil fillData error", e);
            }
        }
    }

    //项目中 所有标红的地方都是引用 的 itextpdf-(5.5.9) 的jar包
}
