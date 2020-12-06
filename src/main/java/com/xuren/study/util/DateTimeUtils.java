
package com.xuren.study.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 作者 rayping E-mail: leiyongping@nongfadai.com
 * @version 创建时间：2016年9月18日 下午8:28:07 时间格式化工具类
 */
public class DateTimeUtils extends DateFormatUtils {

    private DateTimeUtils() {}

    /**
     * 英文简写（默认）如：2010-12-01
     */
    public final static String FORMAT_SHORT = "yyyy-MM-dd";

    /**
     * 英文简写（默认）如：20101201
     */
    public final static String FORMAT_SHORT_2 = "yyyyMMdd";

    /**
     * 英文简写（默认）如：201012
     */
    public final static String FORMAT_SHORT_3 = "yyyyMM";

    /**
     * 英文简写（默认）如：2010
     */
    public final static String FORMAT_SHORT_4 = "yyyy";

    /**
     * 英文简写（默认）如：2010
     */
    public final static String FORMAT_SHORT_5 = "yyyy/MM/dd";

    /**
     * 使用 yyyyMMddHHmmss（如 20141213123456）的格式。时区采用北京时间（GMT+8:00）
     */
    public final static String FORMAT_SHORT_6 = "yyyyMMddHHmmss";

    /**
     * 效果：
     * 2018/5
     * 2018/12
     */
    public final static String FORMAT_SHORT_7 = "yyyy/M";

    /**
     * 英文全称 如：2010-12-01 23:15:06
     */
    public final static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

    /**
     * 英文全称 如：2010/12/01 23:15:06
     */
    public final static String FORMAT_LONG_2 = "yyyy/MM/dd HH:mm:ss";

    /**
     * 精确到毫秒的完整时间 如：yyyy-MM-dd HH:mm:ss.S
     */
    public final static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 中文简写 如：2010年12月01日
     */
    public final static String FORMAT_SHORT_CN = "yyyy年MM月dd日";

    /**
     * 中文全称 如：2010年12月01日 23时15分06秒
     */
    public final static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";

    /**
     * 精确到毫秒的完整中文时间
     */
    public final static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

    /**
     * cron 表达式
     */
    public final static String FORMAT_CRON = "* m H * * ?";

    /**
     * 格式化时间
     */
    public final static String FORMAT_HOUR_MINUTE="HH:mm";

    /**
     * 获得默认的 date pattern
     */
    public static String getDatePattern() {
        return FORMAT_LONG;
    }

    /**
     * 根据预设格式返回当前日期
     * 
     * @return
     */
    public static String getNow() {
        return format(new Date());
    }

    /**
     * 根据用户格式返回当前日期
     * 
     * @param format
     * @return
     */
    public static String getNow(String format) {
        return format(new Date(), format);
    }

    /**
     * 当前时间
     * 
     * @return
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 根据当前日期构建6个月日期
     * 
     * @param monthLen 多个月份
     * @param format 格式
     * @param isExtend 生成规则，前还是后， true 前
     * @return
     */
    public static String[] generateFormBeforMonths(int monthLen, String format, boolean isExtend, Date nowDate) {

        String[] beforMonth = new String[monthLen];
        for (int i = 0; i < monthLen; i++) {
            int monthParam;
            if (isExtend) { // 往前
                monthParam = i;
            } else {
                monthParam = -i;
            }
            Date addMonth = DateTimeUtils.addMonth(nowDate, monthParam);
            beforMonth[i] = DateTimeUtils.format(addMonth, format);
        }
        return beforMonth;
    }

    /**
     * 使用预设格式格式化日期
     * @param date
     * @return  yyyy-MM-dd HH:mm:ss
     */
    public static String format(Date date) {
        return format(date, getDatePattern());
    }
    /**
     * 格式化日期
     * @param date
     * @return yyyy-MM-dd
     */
    public static String formatYMD(Date date) {
        return format(date, FORMAT_SHORT);
    }

    public static String formatYMD2(Date date) {
        return format(date, FORMAT_SHORT_2);
    }

    public static String formatYMD5(Date date) {
        return format(date, FORMAT_SHORT_5);
    }

    /**
     * 使用用户格式格式化日期
     * 
     * @param date 日期
     * @param pattern 日期格式
     * @return
     */
    public static String format(Date date, String pattern) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return returnValue;
    }

    /**
     * 使用预设格式提取字符串日期
     * 
     * @param strDate 日期字符串
     * @return
     */
    public static Date parse(String strDate) {
        return parse(strDate, getDatePattern());
    }
    
    public static Date parseYMD(String strDate){
        return parse(strDate, FORMAT_SHORT);
    }

    /**
     * 使用用户格式提取字符串日期
     * 
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     */
    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date parsrPhoteTime(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        try {
            Date parse = dateFormat.parse(dateStr);
            dateFormat = new SimpleDateFormat(FORMAT_LONG);
            String str = dateFormat.format(parse);
            return dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
        /**
     * @Description: 截断日期，精确到日，去掉时分秒。
     * @param date 
     * @return date
     */
    public static Date truncateDateYMD(Date date){
//        Calendar instance = Calendar.getInstance();
//        instance.setTime(date);
//        
//        instance.set(Calendar.HOUR_OF_DAY, 0);
//        instance.set(Calendar.SECOND, 0);
//        instance.set(Calendar.MINUTE, 0);
//        return instance.getTime();
        return DateUtils.truncate(date, Calendar.DATE);
    }
    public static Date parseLocalDate(String str) {

        LocalDate parse = LocalDate.parse(str, DateTimeFormatter.BASIC_ISO_DATE);

        Calendar instance = Calendar.getInstance();
        instance.set(instance.YEAR, parse.getYear());
        instance.set(instance.MONDAY, parse.getMonthValue());

        return instance.getTime();
    }

    /**
     * 在日期上增加数个整月
     * 
     * @param date 日期
     * @param n 要增加的月数
     * @return
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

    /**
     * 在日期上增加数个年
     * 
     * @param date 日期
     * @param n 要增加的年
     * @return
     */
    public static Date addYear(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, n);
        return cal.getTime();
    }

    /**
     * 在日期上增加天数
     * 
     * @param date 日期
     * @param n 要增加的天数
     * @return
     */
    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();
    }

    /**
     * 在日期上增加 分钟
     * 
     * @param date 日期
     * @param minute 分钟
     * @return
     */
    public static Date getDateAddMinute(Date date, int minute) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(Calendar.MINUTE, minute);
        return cl.getTime();
    }

    /**
     * 获取时间戳
     */
    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    /**
     * 获取日期年份
     * 
     * @param date 日期
     * @return
     */
    public static String getYear(Date date) {
        return format(date).substring(0, 4);
    }

    /**
     * 返回当前时间年份值
     * 
     * @return
     */
    public static int getThisYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        return year;
    }

    /**
     * 按默认格式的字符串距离今天的天数
     * 
     * @param date 日期字符串
     * @return
     */
    public static int countDays(String date) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
    }
    
    /** 
     *计算两日期之间的天数。
     *  当beginDate 小于 endDate时，正数，
     *  当beginDate 等于 endDate时，0，
     *  当beginDate 大于 endDate时，负数，
     * @param beginDate  2017-03-10
     * @param endDate    2017-03-12
     * @return 返回2天
     */
    public static int countDays(Date beginDate,Date endDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(endDate);
        long t = c.getTime().getTime();
        Calendar c1 = Calendar.getInstance();
        c1.setTime(beginDate);
        long t1 = c1.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
    }

    /** 
     *计算两日期之间的月份数 
     *  当beginDate<endDate时，正数，
     *  当beginDate=endDate时，0，
     *  当beginDate>endDate时，负数，
     * @param beginDate  2017-03
     * @param endDate    2017-04
     * @return  返回1个月
     */
    public static int countMonths(Date beginDate, Date endDate) {
        Calendar c = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c.setTime(beginDate);
        c2.setTime(endDate);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int year2 = c2.get(Calendar.YEAR);
        int month2 = c2.get(Calendar.MONTH);

        int result = 0;
        if (year == year2) {
            result = month2 - month;// 两个日期相差几个月，即月份差
        } else {
            result = 12 * (year2 - year) + month2 - month;// 两个日期相差几个月，即月份差
        }
        return result;
    }

    /**
     * 按用户格式字符串距离今天的天数
     * 
     * @param date 日期字符串
     * @param format 日期格式
     * @return
     */
    public static int countDays(String date, String format) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date, format));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
    }

    /**
     * 获取每月最后一天时间
     * 
     * @param sDate1
     * @return
     */
    public static Date getLastDayOfMonth(Date sDate1) {
        Calendar cDay1 = Calendar.getInstance();
        cDay1.setTime(sDate1);
        final int lastDay = cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date lastDate = cDay1.getTime();
        lastDate.setDate(lastDay);
        return lastDate;
    }

    /**
     * 获取下一个月第一天凌晨1点
     */
    public static Date nextMonthFirstDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 1); // 设置为每月凌晨1点
        calendar.set(Calendar.DAY_OF_MONTH, 1); // 设置为每月1号
        calendar.add(Calendar.MONTH, 1); // 月份加一，得到下个月的一号
        // calendar.add(Calendar.DATE, -1); 下一个月减一为本月最后一天
        return calendar.getTime();
    }

    /**
     * 获取第二天凌晨0点毫秒数
     * 
     * @return
     */
    public static Date nextDayFirstDate() throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    public static Date getDateNotTime() {
        Calendar instance = Calendar.getInstance();
        instance.setTime(DateTimeUtils.getNowDate());
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        return instance.getTime();
    }


    /**
     * 获取当前时间到下个月凌晨1点相差秒数
     * 
     * @return
     */
    public static Long getSecsToEndOfCurrentMonth() {
        Long secsOfNextMonth = nextMonthFirstDate().getTime();
        // 将当前时间转为毫秒数
        Long secsOfCurrentTime = System.currentTimeMillis();
        // 将时间转为秒数
        Long distance = (secsOfNextMonth - secsOfCurrentTime) / 1000;
        if (distance != null && distance > 0) {
            return distance;
        }
        return Long.valueOf(0);
    }

    /**
     * 获取当前时间到明天凌晨0点相差秒数
     * 
     * @return
     */
    public static Long getSecsToEndOfCurrentDay() throws ParseException {
        Long secsOfNextDay = nextDayFirstDate().getTime();
        // 将当前时间转为毫秒数
        Long secsOfCurrentTime = System.currentTimeMillis();
        // 将时间转为秒数
        Long distance = (secsOfNextDay - secsOfCurrentTime) / 1000;
        if (distance != null && distance > 0) {
            return distance;
        }
        return Long.valueOf(0);
    }
    
    /**
     * 获取当前时间到明天凌晨0点相差秒数
     * 
     * @return
     */
    public static Long getSecsToCurrentDate(Date beginDate) {
        Long secsOfBeginDate = beginDate.getTime();
        // 将当前时间转为毫秒数
        Long secsOfCurrentTime = System.currentTimeMillis();
        // 将时间转为秒数
        Long distance = (secsOfCurrentTime - secsOfBeginDate) / 1000;
        if (distance != null && distance > 0) {
            return distance;
        }
        return Long.valueOf(0);
    }

    /**
     *
     * @return
     */

    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
    
    /**
     * 判断当前日期是星期几 
     * @param date         日期
     * @return             1表示星期一，5星期五，6星期六，7星期天
     * @throws Exception
     */
    public static int dayForWeek(Date date) throws Exception {
     Calendar c = Calendar.getInstance();
     c.setTime(date);
     int dayForWeek = 0;
     if (c.get(Calendar.DAY_OF_WEEK) == 1) {
      dayForWeek = 7;
     } else {
      dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
     }
     return dayForWeek;
    }

    /**
     * 返回指定年份的n年后的年份
     *
     * @param year
     * @param n
     * @return
     */
    public static int getYear(String year, int n) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_SHORT_4);
        Calendar cl = Calendar.getInstance();
        try {
            cl.setTime(sdf.parse(year));
            cl.add(Calendar.YEAR, n);
            return cl.get(Calendar.YEAR);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    /**
     * 两个日期比较大小，如果f<s则-1，如果f=s则0，如果f>s则1 
     * @param firstDate
     * @param secondDate
     * @return -1, 0, 1
     */
    public static int compare(Date firstDate, Date secondDate){
        return DateUtils.truncatedCompareTo(firstDate, secondDate, Calendar.DATE);
    }

    /**
     * 判断两个日期的大小，当firstDate>secondDate时返回true，其他情况（等于或小于）都返回false
     * @param firstDate 
     * @param secondDate
     * @return  true或false
     */
    public static boolean compareDate(Date firstDate, Date secondDate) {
        int num = compare(firstDate, secondDate);
        if (num == 0) { // 等于
            return false;
        } else if (num == 1) { // 大于
            return true;
        } else {
            return false; // 小于
        }
    }

    public static Date formatLocalTime(String str) {
        LocalTime local = LocalTime.parse(str);
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, local);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    /**
     * 比较时间大小
     * @param t1   begin time  example : 22:00
     * @param t2    end time    example : 21:00
     * @return t1 > t2 return 1 , t1 == t2  return 0 ,else return -1
     */
    public static int compareToLocalTime(LocalTime t1,LocalTime t2){
        return t1.compareTo(t2);
    }

    /**
     * 比较时间大小
     * @param t1  begin time  example : 22:00
     * @param t2  end time    example : 21:00
     * @return   if t1 > t2 return true ,else return false
     */
    public static boolean compareToTime(LocalTime t1,LocalTime t2){
        int i = compareToLocalTime(t1, t2);
        return i <= 0 ? false:true;
    }

    /**
     * 获取格式化时间  HH:mm
     * @param pattern
     * @return
     */
    public static LocalTime getFormatLocalTime(String pattern){
        DateTimeFormatter dt = DateTimeFormatter.ofPattern(pattern);
        String format = LocalTime.now().format(dt);
        return LocalTime.parse(format);
    }

    public static String getCron(String timeStr) {
        Date formatLocalTime = DateTimeUtils.formatLocalTime(timeStr);
        SimpleDateFormat sdf = new SimpleDateFormat(DateTimeUtils.FORMAT_CRON);
        return sdf.format(formatLocalTime);
    }

    /**
     * 在日期上增加石小时数
     *
     * @param date 日期
     * @param n    要增加的小时数
     * @return
     */
    public static Date addHour(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, n);
        return cal.getTime();
    }

    public static void main(String[] args) {
        System.out.println(getTimeString());
        System.out.println(addHour(new Date(), -11));

    }
}
