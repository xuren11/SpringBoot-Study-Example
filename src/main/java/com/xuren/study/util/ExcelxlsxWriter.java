/*
 * All rights Reserved, Designed By 农金圈 2016年11月25日 下午7:36:45
 */

package com.xuren.study.util;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author: XavierZz
 */
public abstract class ExcelxlsxWriter<T> {

    protected Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 替换掉 特殊字符
     * 
     * @return
     */
    private final static String SPECIAL_CHAR_TO_REPLACE_REG_EX =
        "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】《》'；：”“’‘。，、？\\-]";

    private final static String EXCEL_CONTENT_TYPE = "application/vnd.ms-excel";

    protected XSSFWorkbook workbook;

    private int fromIdx = 1;

    private String filename = "";

    private List<T> list;

    /**
     * xls file 文件
     *
     * @param xlsIs
     * @param list
     * @param sheetName
     * @param headData
     * @throws IOException
     * @throws IOException
     */
    public void build(java.io.InputStream xlsIs, List<T> list, String sheetName, Map<String, Object> headData) throws IOException {
        workbook = new XSSFWorkbook(xlsIs);
        buildHeader(headData);
        buildBody(list);
        buildFoot();
        workbook.setSheetName(0, sheetName);
        xlsIs.close();
    }

    /**
     * xls file 文件, 暂时保留，与上面build方法一样..
     *
     * @param xlsIs
     * @param data
     * @param headData
     * @param sheetName
     * @throws IOException
     * @throws IOException
     */
    @Deprecated
    public void build(java.io.InputStream xlsIs, List<T> data, Map<String, Object> headData, String sheetName) throws IOException {
        build(xlsIs, data, sheetName, headData);
    }

    public XSSFSheet getSheetAt(int index) {
        return workbook.getSheetAt(index);
    }

    /**
     * 构造excel头部说明
     *
     * @param headData
     */
    abstract public void buildHeader(Map<String, Object> headData);

    /**
     * 构造excel主体说明
     *
     * @param data
     */
    abstract public void buildBody(List<T> data);

    /**
     * 构造excel底部说明
     */
    abstract public void buildFoot();

    public XSSFRow getRow(XSSFSheet sheet, int rownum) {
        XSSFRow row = sheet.getRow(rownum);
        if (row == null) {
            row = sheet.createRow(rownum);
        }
        return row;
    }

    public void createCell(XSSFRow row, int columIndex, BigDecimal content) {
        XSSFCell cell = row.createCell(columIndex);
        cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
        if(content != null){
            cell.setCellValue(content.doubleValue());
        }
    }

    public void createCell(XSSFRow row, int columIndex, Double content) {
        XSSFCell cell = row.createCell(columIndex);
        cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
        if(content != null){
            cell.setCellValue(content);
        }
    }

    public void createCell(XSSFRow row, int columIndex, Date content) {
        XSSFCell cell = row.createCell(columIndex);
        cell.setCellType(XSSFCell.CELL_TYPE_STRING);
        if(content != null){
            cell.setCellValue(DateTimeUtils.format(content, DateTimeUtils.FORMAT_SHORT));
        }
    }


    public void createCell(XSSFRow row, int columIndex, String content) {
        XSSFCell cell = row.createCell(columIndex);
        cell.setCellType(XSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(content);
    }
    
    public void createCell(XSSFRow row, int columIndex, Long content) {
        XSSFCell cell = row.createCell(columIndex);
        cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
        cell.setCellValue(content);
    }

    public void createCell(XSSFRow row, int columIndex, Integer content) {
        if (content != null) {
            XSSFCell cell = row.createCell(columIndex);
            cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue(content);
        } else {
            XSSFCell cell = row.createCell(columIndex);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("");
        }
    }

    public void createCell(XSSFRow row, int columIndex, int content) {
        if (content != 0) {
            XSSFCell cell = row.createCell(columIndex);
            cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue(content);
        } else {
            XSSFCell cell = row.createCell(columIndex);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("");
        }
    }

    public void setCell(XSSFRow row, int columIndex, double content) {

        XSSFCell cell = row.getCell(columIndex);
        cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
        cell.setCellValue(content);
    }

    public void setCellFormula(XSSFRow row, int columIndex, double content) {
        XSSFCell cell = row.getCell(columIndex);
        cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
        cell.setCellValue(content);
    }



    public void setCell(XSSFRow row, int columIndex, String content, boolean isFormula) {
        XSSFCell cell = row.getCell(columIndex);
        if (isFormula) {
            cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
        } else {
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
        }
        cell.setCellValue(content);
    }

    /**
     * 设置单元格公式
     * 
     * @param row
     * @param columIndex
     * @param formula
     */
    public void setCellFormula(XSSFRow row, int columIndex, String formula) {
        XSSFCell cell = row.getCell(columIndex);
        cell.setCellFormula(formula);
    }

    /**
     * 拼接公式
     */
    protected String contractFormula() {
        return null;
    }

    /**
     * @return the workbook
     */
    public XSSFWorkbook getWorkbook() {
        return workbook;
    }

    /**
     * @param out
     */
    public void write(OutputStream out) throws IOException {
        workbook.write(out);
    }

    /**
     * @throws IOException
     */
    public void close() throws IOException {
        workbook.close();
    }


    /**
     * @param workbook the workbook to set
     */
    public void setWorkbook(XSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public static String replaceAllSpecChar(String srcStr) {
        if (srcStr == null || "".equals(srcStr)) {
            return srcStr;
        }

        Pattern pattern = Pattern.compile(SPECIAL_CHAR_TO_REPLACE_REG_EX);
        Matcher matcher = pattern.matcher(srcStr);

        StringBuffer ret = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(ret, "");
        }
        return ret.toString();
    }


    /**
     * 向excel中插入图片
     * 
     * @param sheet 操作对象
     * @param filePath 文件路径
     * @param imgFormat 图片格式
     * @param col1 起始列
     * @param row1 起始行
     * @param col2 结束列
     * @param row2 结束行 by.LanWei
     */
    public void insertImage(HSSFSheet sheet, String filePath, String imgFormat, short col1, int row1, short col2, int row2) {
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        BufferedImage bufferImg;
        try {
            bufferImg = ImageIO.read(new File(filePath));
            ImageIO.write(bufferImg, imgFormat, byteArrayOut);
        } catch (IOException e) {
            log.error("插入图片异常", e);
        }

        HSSFPatriarch patriarch = (HSSFPatriarch) sheet.createDrawingPatriarch();
        // excel默认单元格宽1023，高255
        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, col1, row1, col2, row2);
        patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
    }


    /**
     * @return the fromIdx
     */
    public int getFromIdx() {
        return fromIdx;
    }

    /**
     * @param fromIdx the fromIdx to set
     */
    public void setFromIdx(int fromIdx) {
        this.fromIdx = fromIdx;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return the eXCEL_CONTENT_TYPE
     */
    public static String getExcelContentType() {
        return EXCEL_CONTENT_TYPE;
    }


    /**
     * @return: List<T> <br>
     */
    public List<T> getList() {
        return list;
    }


    /**
     * @return: List<T> <br>
     */
    public void setList(List<T> list) {
        this.list = list;
    }
}
