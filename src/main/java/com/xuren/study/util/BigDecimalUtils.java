/*
 * All rights Reserved, Designed By 农金圈 2016年12月16日 上午9:30:59
 */

package com.xuren.study.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * 金额转换 处理
 * 
 * @author: XavierZz
 */
public class BigDecimalUtils {

    public final static BigDecimal PERCENTILE = new BigDecimal("0.01");// 百分比计算值

    /**
     * 45,345,646.00
     */
    public final static String FORMAT = "##,##0.00";

    /**
     * 可变长度的加法
     * 
     * @param val1
     * @param decimals
     * @return
     */
    public static BigDecimal add(BigDecimal val1, BigDecimal... decimals) {

        if (isNull(val1)) {
            val1 = new BigDecimal(0);
        }
        BigDecimal sum = val1;
        for (BigDecimal decimal : decimals) {
            if (isNull(decimal)) {
                decimal = new BigDecimal(0);
            }
            sum = sum.add(decimal);
        }

        return sum;
    }


    /**
     * 可变长度的减法
     * 
     * @param val1
     * @param decimals
     * @return
     */
    public static BigDecimal subtract(BigDecimal val1, BigDecimal... decimals) {

        if (isNull(val1)) {
            val1 = new BigDecimal(0);
        }
        BigDecimal subtract = val1;
        for (BigDecimal decimal : decimals) {
            if (isNull(decimal)) {
                decimal = new BigDecimal(0);
            }
            subtract = subtract.subtract(decimal);
        }
        return subtract;
    }

    /**
     * 除 val1 / val2
     * 
     * @param val1
     * @param val2
     * @param scale
     * @param roundingMode
     * @return
     */
    public static BigDecimal divide(BigDecimal val1, BigDecimal val2, int scale, int roundingMode) {

        if (isNull(val1) || isZero(val1) || isNull(val2) || isZero(val2)) {
            return new BigDecimal(0);
        }
        return val1.divide(val2, scale, roundingMode);
    }

    /**
     * 可变长度乘法
     * 
     * @param val1
     * @param decimals
     * @return
     */
    public static BigDecimal multiply(BigDecimal val1, BigDecimal... decimals) {

        if (isNull(val1)) {
            val1 = new BigDecimal(0);
        }
        BigDecimal result = val1;
        for (BigDecimal decimal : decimals) {
            if (isNull(decimal)) {
                decimal = new BigDecimal(0);
            }
            result = result.multiply(decimal);
        }
        return result;
    }

    /**
     * 百分比计算
     *
     * @param val
     * @return
     */
    public static BigDecimal percent(BigDecimal val) {
        if (isNull(val)) {
            return BigDecimal.ZERO;
        }
        return PERCENTILE.multiply(val);
    }

    /**
     * 判断是否为null
     * 
     * @param val
     * @return
     */
    public static boolean isNull(BigDecimal val) {
        return null == val;
    }

    public static BigDecimal nullToZero(BigDecimal val) {

        if (isNull(val)) {
            return new BigDecimal(0);
        }
        return val;
    }

    /**
     * 判断是否为0
     * 
     * @param val
     * @return
     */
    public static boolean isZero(BigDecimal val) {
        if (isNull(val)) {
            return true;
        }
        return val.compareTo(new BigDecimal(0)) == 0;
    }

    /**
     * 去掉多余的0，如0012.0500变成12.05,转换为字符串
     *
     * @param val
     * @return
     */
    public static String toString(BigDecimal val) {
        return nullToZero(val).stripTrailingZeros().toPlainString();
    }

    /**
     * 转换成后缀有元单位
     * @param val
     * @return
     */
    public static String toYuan(BigDecimal val){
        return DecimalFormatUtils.formatAmount2(val) + "元";
    }

    /**
     * 转换成后缀有 百分比单位
     * @param val
     * @return
     */
    public static String toPercent(BigDecimal val){
        return toString(val) + "%";
    }

    /**
     * max
     * 
     * @param val1
     * @param val2
     * @return the bigger one
     */
    public static BigDecimal max(BigDecimal val1, BigDecimal val2) {
        if (val1 == null) {
            val1 = BigDecimal.ZERO;
        }
        if (val2 == null) {
            val2 = BigDecimal.ZERO;
        }
        if (val1.compareTo(val2) > 0) {
            return val1;
        } else if (val1.compareTo(val2) < 0) {
            return val2;
        } else {
            return val1;
        }
    }

    /**
     * min
     * 
     * @param val1
     * @param val2
     * @return the smaller one
     */
    public static BigDecimal min(BigDecimal val1, BigDecimal val2) {
        if (val1 == null) {
            val1 = BigDecimal.ZERO;
        }
        if (val2 == null) {
            val2 = BigDecimal.ZERO;
        }
        if (val1.compareTo(val2) > 0) {
            return val2;
        } else if (val1.compareTo(val2) < 0) {
            return val1;
        } else {
            return val1;
        }
    }

    /**
     * 设置传入对象 o 的BigDecimal属性的精度
     * 
     * @param o
     * @param scale
     */
    public static void setObjectBigDecimalScale(Object o, int scale) {
        Field[] fields = o.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getType().getName().equals("java.math.BigDecimal")) {
                try {
                    String fieldName = fields[i].getName();
                    String firstLetter = fieldName.substring(0, 1).toUpperCase();
                    String getter = "get" + firstLetter + fieldName.substring(1);
                    Method method = o.getClass().getMethod(getter, new Class[] {});
                    Object value = method.invoke(o, new Object[] {});

                    String setter = "set" + firstLetter + fieldName.substring(1);
                    Method methodSet = o.getClass().getMethod(setter, new Class[] {BigDecimal.class});
                    BigDecimal val = ((BigDecimal) value).setScale(scale, BigDecimal.ROUND_CEILING);
                    methodSet.invoke(o, new Object[] {val});
                } catch (Exception e) {

                }
            }
        }
    }

    /**
     * 大小比较判断
     *
     * @param val1
     * @param val2
     * @return
     */
    public static int compareTo(BigDecimal val1, BigDecimal val2) {
        return nullToZero(val1).compareTo(nullToZero(val2));
    }
    
    /**
     * 等值判断
     *
     * @param val1
     * @param val2
     * @return
     */
    public static boolean equalsTo(BigDecimal val1, BigDecimal val2) {
        return compareTo(val1, val2) == 0;
    }

    /**
     * 小于0
     *
     * @param val
     * @return
     */
    public static boolean lessThanZero(BigDecimal val) {
        return compareTo(val, BigDecimal.ZERO) < 0;
    }

    /**
     * 小于等于0
     *
     * @param val
     * @return
     */
    public static boolean lessEqZero(BigDecimal val) {
        return compareTo(val, BigDecimal.ZERO) <= 0;
    }

    /**
     * 大于0
     *
     * @param val
     * @return
     */
    public static boolean greaterThanZero(BigDecimal val) {
        return compareTo(val, BigDecimal.ZERO) > 0;
    }

    /**
     * 大于等于0
     *
     * @param val
     * @return
     */
    public static boolean greaterEqZero(BigDecimal val) {
        return compareTo(val, BigDecimal.ZERO) >= 0;
    }

    public static boolean equalsToZero(BigDecimal val) {
        return compareTo(val, BigDecimal.ZERO) == 0;
    }

    /**
     * 将负数返回为0
     *
     * @param val1
     * @return
     */
    public static BigDecimal minus2Zero(BigDecimal val1) {
        if(BigDecimal.ZERO.compareTo(val1) > 0){
            return BigDecimal.ZERO;
        }
        
        return val1;
    }
    
    /**
     * 当amt>0时，保留scale位小数，向上进位。
     * 其他情况下，直接返回，即不处理。例如：当amt空、小于、等于0时
     * @param amt
     * @param scale
     * @return
     */
    public static BigDecimal setScale(BigDecimal amt, int scale){
     if(amt!=null && amt.compareTo(BigDecimal.ZERO)>0){
      return amt.setScale(scale, BigDecimal.ROUND_CEILING);
     }
     return amt;
    }

    /**
     * 取相反数
     *
     * @param amt
     * @return
     */
    public static BigDecimal opposite(BigDecimal amt) {
        return subtract(BigDecimal.ZERO, amt);
    }
}
