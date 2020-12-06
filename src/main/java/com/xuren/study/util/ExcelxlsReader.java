
package com.xuren.study.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;



/**
 * 操作Excel表格的功能类
 * 
 * @author：
 * @version 1.0
 */
public class ExcelxlsReader {


    private POIFSFileSystem fs = null;

    private HSSFWorkbook wb = null;

    /**
     * 读取Excel表格表头的内容，默认从第0行开始读标题栏
     * 
     * @param InputStream
     * @return String 表头内容的数组
     * @throws IOException
     * 
     */
    public String[] readExcelTitle(InputStream is) throws IOException {
        return readExcelTitle(is, 0);
    }

    /**
     * 读取Excel表格表头的内容，从第几行开始读标题栏
     * 
     * @param InputStream
     * @param start
     * @return String 表头内容的数组
     * @throws IOException
     * 
     */
    public String[] readExcelTitle(InputStream is, int start) {
        String[] title = null;
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row = sheet.getRow(start);
            if (null != row) {
                // 标题总列数
                int colNum = row.getPhysicalNumberOfCells();
                title = new String[colNum];
                for (int i = 0; i < colNum; i++) {
                    title[i] = getStringCellValue(row.getCell(i));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return title;
    }

    /**
     * 读取Excel数据内容,要求大于两行,第一列的数据不能为空,如果为空就停止读取
     * 
     * @param InputStream
     * @return Map 包含单元格数据内容的Map对象
     * @throws IOException
     */
    public LinkedHashMap<String, LinkedHashMap<String, String>> readExcelContent(InputStream is) {
        LinkedHashMap<String, LinkedHashMap<String, String>> rows = new LinkedHashMap<String, LinkedHashMap<String, String>>();// 保证顺序
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row = sheet.getRow(0);
            if (null != row) {
                // 得到总行数
                int rowNum = sheet.getLastRowNum();
                int colNum = row.getPhysicalNumberOfCells();
                // 正文内容应该从第二行开始,第一行为表头的标题
                for (int i = 0; i <= rowNum; i++) {
                    row = sheet.getRow(i);
                    if (row != null) {
                        int j = 0;
                        LinkedHashMap<String, String> columns = new LinkedHashMap<String, String>();
                        while (j < colNum) {
                            // 判断是否是日期格式
                            columns.put(colNumber(j), getStringCellValue(row.getCell(j)));
                            j++;
                        }
                        rows.put("r" + i, columns);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return rows;
    }

    /**
     * 读取Excel数据内容,要求大于两行,第一列的数据不能为空,如果为空就停止读取
     * 
     * @param InputStream
     * @return Map 包含单元格数据内容的Map对象
     * @throws IOException
     */
    public LinkedHashMap<String, LinkedHashMap<String, String>> readExcelSpecifiesWidthColumnContent(InputStream is, int colNum) {
        LinkedHashMap<String, LinkedHashMap<String, String>> rows = new LinkedHashMap<String, LinkedHashMap<String, String>>();// 保证顺序
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row = sheet.getRow(1);
            if (null != row) {
                // 得到总行数
                int rowNum = sheet.getLastRowNum();
                // 正文内容应该从第二行开始,第一行为表头的标题
                for (int i = 1; i <= rowNum; i++) {
                    row = sheet.getRow(i);
                    if (null != row) {
                        int j = 0;
                        LinkedHashMap<String, String> columns = new LinkedHashMap<String, String>();
                        while (j < colNum) {
                            columns.put(colNumber(j), getStringCellValue(row.getCell(j)));
                            j++;
                        }
                        rows.put("r" + i, columns);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return rows;
    }

    /**
     * 读取Excel数据内容,多sheet
     * 
     * @param InputStream
     * @return Map 包含单元格数据内容的Map对象
     * @throws IOException
     */
    public List<LinkedHashMap<String, LinkedHashMap<String, String>>> readMoreExcelContent(InputStream is, int start) {
        List<LinkedHashMap<String, LinkedHashMap<String, String>>> obj =
            new ArrayList<LinkedHashMap<String, LinkedHashMap<String, String>>>();
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
            for (int k = 0; k < wb.getNumberOfSheets(); k++) {
                HSSFSheet sheet = wb.getSheetAt(k);
                HSSFRow row = sheet.getRow(start);
                if (null != null) {
                    LinkedHashMap<String, LinkedHashMap<String, String>> rows =
                        new LinkedHashMap<String, LinkedHashMap<String, String>>();// 保证顺序
                    // 得到总行数
                    int rowNum = sheet.getLastRowNum();
                    int colNum = row.getPhysicalNumberOfCells();
                    for (int i = 0; i < rowNum; i++) {
                        row = sheet.getRow(i);
                        if (null != row) {
                            int j = 0;
                            LinkedHashMap<String, String> columns = new LinkedHashMap<String, String>();
                            while (j < colNum) {
                                columns.put(colNumber(j), getStringCellValue(row.getCell(j)));
                                j++;
                            }
                            rows.put("r" + i, columns);
                        }
                    }
                    obj.add(rows);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return obj;
    }

    /**
     * 读取Excel数据内容,要求大于两行,第一列的数据不能为空,如果为空就停止读取
     * 
     * @param InputStream
     * @return Map 包含单元格数据内容的Map对象
     * @throws IOException
     */
    public LinkedHashMap<String, LinkedHashMap<String, String>> readExcelContent(InputStream is, int start) {
        LinkedHashMap<String, LinkedHashMap<String, String>> rows = new LinkedHashMap<String, LinkedHashMap<String, String>>();// 保证顺序
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            // 取colNum需根据表头来取行数，当数据有空值时取出列值不正确
            HSSFRow row = sheet.getRow(start - 1);
            if (null != row) {
                int colNum = row.getPhysicalNumberOfCells();
                // 得到总行数
                int rowNum = sheet.getLastRowNum();
                // 正文内容应该从第二行开始,第一行为表头的标题
                for (int i = start; i <= rowNum; i++) {
                    row = sheet.getRow(i);
                    if (null != row) {
                        int j = 0;
                        LinkedHashMap<String, String> columns = new LinkedHashMap<String, String>();
                        while (j < colNum) {
                            columns.put(colNumber(j), getStringCellValue(row.getCell(j)));
                            j++;
                        }
                        rows.put("r" + i, columns);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return rows;
    }

    /**
     * 读取EXCEL内容
     * 
     * @param xlsFile
     * @param fromRow
     * @param fromColumn
     * @return
     * @throws IOException
     */
    public Map<String, String> readExcelContent(File xlsFile, int fromRow, int fromColumn) {
        Map<String, String> data = new HashMap<String, String>();
        InputStream is = null;
        try {
            is = new FileInputStream(xlsFile);
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);

            HSSFSheet sheet = wb.getSheetAt(0);
            // 得到总行数
            int rowNum = sheet.getLastRowNum();
            if (rowNum < fromRow) {
                return data;// 不足行直接返回，以免报错
            }

            // 正文内容应该从第二行开始,第一行为表头的标题
            for (int currentRow = fromRow, i = 1; currentRow <= rowNum; currentRow++, i++) {
                HSSFRow row = sheet.getRow(currentRow);
                if (row != null) {
                    int colNum = row.getPhysicalNumberOfCells();
                    for (int currentColumn = fromColumn, j = 1; currentColumn < colNum; currentColumn++, j++) {
                        String cellValue = getStringCellValue(row.getCell(currentColumn));
                        if (StringUtil.isNotBlank(cellValue)) {
                            data.put("" + i + "," + j, cellValue);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }

    /**
     * 获取单元格数据内容为字符串类型的数据
     * 
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private String getStringCellValue(HSSFCell cell) {
        String strCell = "";
        if (cell == null)
            return "";
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                strCell = cell.getRichStringCellValue().toString();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                boolean isDate = HSSFDateUtil.isCellDateFormatted(cell);
                if (isDate) {
                    strCell = DateTimeUtils.format(cell.getDateCellValue(), DateTimeUtils.FORMAT_LONG);
                    break;
                }
                double numericCellValue = cell.getNumericCellValue();
                strCell = DecimalFormatUtils.formatAmount2(BigDecimal.valueOf(numericCellValue));
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                strCell = "";
                break;
            case XSSFCell.CELL_TYPE_FORMULA:
                FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
                CellValue cellValue = evaluator.evaluate(cell);
                switch (cellValue.getCellType()) {
                    case Cell.CELL_TYPE_BOOLEAN:
                        strCell = String.valueOf(cellValue.getBooleanValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        strCell = String.valueOf(cellValue.getNumberValue());
                        break;
                    case Cell.CELL_TYPE_STRING:
                        strCell = cellValue.getStringValue();
                        break;
                    case Cell.CELL_TYPE_BLANK:
                    case Cell.CELL_TYPE_ERROR:
                    case Cell.CELL_TYPE_FORMULA:
                        strCell = "";
                        break;
                    default:
                        strCell = "";
                        break;
                }
                break;
            default:
                strCell = "";
                break;
        }

        if (StringUtil.isBlank(strCell)) {
            return "";
        }

        return strCell.trim();
    }

    private String colNumber(int colNumber) {
        return "c" + colNumber;
    }

    private void close() {
        if (null != wb) {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (null != fs) {
            try {
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
