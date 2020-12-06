/*
 * All rights Reserved, Designed By 农金圈 2016年10月25日 下午5:49:25
 */

package com.xuren.study.util;

import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 字符串处理拓展类
 * 
 * @author: rayping
 */
public class StringUtil extends org.apache.commons.lang3.StringUtils {

    /**
     * 字符串转成整型数字
     * @param str
     * @return
     */
    public static Integer stringToInt(String str){
        return stringToInt(str, 0);
    }
    public static Integer stringToInt(String str, Integer defaultValue){
        try {
            return Integer.valueOf(str);
        }catch (NumberFormatException e){
            return defaultValue;
        }
    }

    /**
     * split string to list, will trim
     * @param str a string to be parsed.
     * @param delim the delimiters.
     * @return
     */
    public static List<String> stringToList(String str, String delim) {
        if (isBlank(str)) {
            return null;
        }

        String[] strs = split(str, delim);
        if (null != strs && strs.length > 0) {
            return Arrays.asList(strs).stream().map(String::trim).collect(Collectors.toList());
        }

        return null;
    }

    /**
     * 默认使用半角逗号分隔字符串
     * @param str
     * @return
     */
    public static List<String> stringToList(String str) {
        return stringToList(str, ",");
    }

    /**
     * concat each element of collection with delim, if no elements will return empty string
     * @param collection
     * @param delim
     * @return
     */
    public static String concatCollection(Collection<String> collection, String delim) {
        if (CollectionUtils.isNotEmpty(collection)) {
            return collection.stream().collect(Collectors.joining(delim));
        }
        return "";
    }

    /**
     * 每个字符之间插入指定的分隔符 separator
     * 
     * @param str
     * @param separator 分隔符
     * @return
     */
    public static String insertSeparator(String str, String separator) {
        if(isBlank(str)){
            return str;
        }
        str=str.trim();
        StringBuffer sb=new StringBuffer("");
        for(int i=0;i<str.length();i++){
            if(i>0){
                sb.append(separator);
            }
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    /**
     * 判断字符串是否全部为字母
     *
     * @param str
     * @return
     */
    public static boolean isLetter(String str) {
        if (isBlank(str)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!isLetter(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isLetter(Character c) {
        return c != null && ((c <= 'Z' && c >= 'A') || (c <= 'z' && c >= 'a'));
    }

    /**
     * 长度超过size则截取前面size个字符返回，否则返回原字符串， 防止数据库char字段过长时使用
     *
     * @param str
     * @param size
     * @return
     */
    public static String subString(String str, int size) {
        if (str != null && str.length() > size) {
            return str.substring(0, size);
        }
        return str;
    }
    
    /**
     * 判断字符串是否超过1000个字符
     *
     * @param str
     * @return
     */
    public static boolean isOutOfLimit(String str) {
        if (isBlank(str)) {
            return false;
        }
        int len = str.length();
        if(len>1000){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 截取字符串：返回从左边第一个indexOfLeftStr字符之前的字符串
     *
     * @param str
     * @param indexOfLeftStr
     * @return
     */
    public static String subLeftString(String str, String indexOfLeftStr) {
        if (str == null) {
            return str;
        }
        int endIndex = str.indexOf(indexOfLeftStr);
        if (endIndex < 0) {
            return str;
        }
        return str.substring(0, endIndex);
    }

    /**
     * 将整数转换成字符串，如果整数对象为空，则使用默认值
     * @param val
     * @param defVal
     * @return
     */
    public static String toStr(Integer val, String defVal){
        if(val == null){
            return defVal;
        }
        
        return val.toString();
    }

    /**
     * 如果传入参数str为null，则返回空字符串
     * @param str
     * @return
     */
    public static String null2Str(String str) {
        if(str == null){
            return "";
        }
        
        return str;
    }

    /**
     * 转大写
     * @param str
     * @return
     */
    public static String toUpperCase(String str){ 
    	
    	if(str == null){
            return "";
        }

        char[] ch = str.toCharArray();  
        StringBuffer sbf = new StringBuffer();  
        for(int i=0; i< ch.length; i++){  
        	sbf.append(charToUpperCase(ch[i]));  
        }  
        return sbf.toString();  
    }  
    
    /**
     * 转小写
     * @param str
     * @return
     */
    public static String toLowerCase(String str){ 
    	
    	if(str == null){
            return "";
        }

        char[] ch = str.toCharArray();  
        StringBuffer sbf = new StringBuffer();  
        for(int i=0; i< ch.length; i++){  
        	sbf.append(charToLowerCase(ch[i]));  
        }  
        return sbf.toString();  
    }
    
    /**转大写**/  
    private static char charToUpperCase(char ch){  
        if(ch <= 122 && ch >= 97){  
            ch -= 32;  
        }  
        return ch;  
    }  
    
    /***转小写**/  
    private static char charToLowerCase(char ch){  
        if(ch <= 90 && ch >= 65){  
            ch += 32;  
        }  
        return ch;  
    }

    /**
     * 对银行卡号进行加星号处理
     * @param bankAccount
     * @return
     */
    public static String maskBankAccount(String bankAccount) {
        return maskMiddle(bankAccount, 6, 4);
    }

    /**
     * 身份证号码加星处理，只显示前6位和后4位
     * @param idCardNo
     * @return
     */
    public static String maskIdCardNo(String idCardNo) {
        return maskMiddle(idCardNo, 6, 4);
    }

    /**
     * 对手机号进行加星号处理，只显示前3位和后4位
     * @param mobilePhone
     * @return
     */
    public static String maskMobilePhone(String mobilePhone) {
        return maskMiddle(mobilePhone, 3, 4);
    }

    /**
     * 对text 做*处理。比如maskMiddle("13570927312", 3, 4)将返回135****7312, 最多显示6个星号
     * @param text 要处理的text
     * @param startLength 头部不用处理的长度
     * @param endLength 尾部不用处理的长度
     * @return
     */
    public static String maskMiddle(String text, int startLength, int endLength) {
        if (isBlank(text)) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        // 最多展示几个星号
        final int maxAsteriskCount = 6;
        int asteriskCount = 0;
        for (int i = 0, len = text.length(); i < len; i++) {
            if (i < startLength || i >= len - endLength) {
                result.append(text.charAt(i));
            } else {
                // 最多展示几个星号
                asteriskCount++;
                if (asteriskCount <= maxAsteriskCount) {
                    result.append("*");
                }
            }
        }
        return result.toString();
    }

    /**
     * 是截取银行卡号后四位
     *
     * @param cardNo
     * @return
     */
    public static String subBankCardNo(String cardNo) {
        return (isBlank(cardNo) || cardNo.length() <= 4) ? cardNo : substring(cardNo, cardNo.length() - 4);
    }

    public static String insertStrWithMark(String arg, String mark) {
        if (isBlank(arg)) {
            return arg;
        }
        // 去除字符串包含的所有空格
        String ss = arg.replaceAll(" ", "");
        StringBuilder sb = new StringBuilder(mark);
        for (int i = 0; i < ss.length(); i++) {
            sb.append(ss.charAt(i)).append(mark);
        }
        return sb.toString();
    }
}
