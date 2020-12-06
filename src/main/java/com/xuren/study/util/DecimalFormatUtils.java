/*
 * All rights Reserved, Designed By 农金圈
 * 
 * @Title: GetBigDecimalValue.java
 * 
 * @date: 2016年6月13日 下午3:36:24
 */

package com.xuren.study.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @Description:数值空值转换
 * @author: Wister
 */
public class DecimalFormatUtils {

    public static final DecimalFormat AMOUNT_SPLIT_FORMAT = new DecimalFormat("#,##,##0.00");

    public static final DecimalFormat AMOUNT_FORMAT = new DecimalFormat("#0.##");

    public static final DecimalFormat FORMAT = new DecimalFormat("##0.00");


    /**
     * 如果值为null则将值转为0
     * 
     * @Description: TODO
     * @param number
     */
    public static BigDecimal convertBigDecimalNullToZero(BigDecimal number) {
        if (null == number) {
            return BigDecimal.ZERO;
        } else {
            return number;
        }
    }

    /**
     * 如果值为null则将值转为0
     * 
     * @Description: TODO
     * @param number
     */
    public static Integer convertIntegerNullToZero(Integer number) {
        if (null == number) {
            return 0;
        } else {
            return number;
        }
    }

    public static String formatAmount(BigDecimal number) {
        if (number == null) {
            return "";
        }
        return AMOUNT_SPLIT_FORMAT.format(number);
    }

    /**
     * 格式化数字。 当有小数时保留2位小数 当无小数时精确到个位数字。
     * 
     * @param number
     * @return
     */
    public static String formatAmount2(BigDecimal number) {
        if (number == null) {
            return "";
        }
        return AMOUNT_FORMAT.format(number);
    }

}
