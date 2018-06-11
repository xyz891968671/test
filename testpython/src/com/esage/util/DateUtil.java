package com.esage.util;

//public class DateUtil {
//
//}
//package com.posoftframework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * �ַ�������������
 * <br>�ṩ�����ַ�������Ĺ��÷���
 * <li> ����ָ����ʽ��ʽ�����ڲ���Ϊ�ַ�������
 * <li> ���ַ��������������ת��Ϊsql�����in�Ӿ����ʽ��
 * 
 * @author slx
 * @date 2009-5-14 ����05:21:42
 * @version 1.0
 */
public class DateUtil {

    private static DateFormat datefmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * ����ָ����ʽ���������ַ���
     * 
     * @author slx
     * @date 2009-5-14 ����05:20:06
     * @modifyNote
     * @param date
     *          ��Ҫ��ʽ��������
     * @param format
     *          ��ʽ���ַ�����Ϊ�������Ĭ��  yyyy-MM-dd hh:mm:ss�������ʽ���ַ������Ϸ����׳��쳣
     * @return
     *          ��ʽ����������ַ�����
     */
    public static String formatDate(Date date , String format){
        if(date == null)
            return null;
        String str_date = null;
        if(format != null){
            DateFormat formater = new SimpleDateFormat(format);
            str_date = formater.format(date);
        }else{
            str_date =datefmt.format(date);
        }

        return str_date;
    }

    public static Date parseToDate(String date , String format) throws ParseException{
        if(date == null)
            return null;
        Date dDate = null;
        if(format != null){
            DateFormat formater = new SimpleDateFormat(format);
            dDate = formater.parse(date);
        }else{
            DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            dDate =formater.parse(date);
        }

        return dDate;
    }

    /**
     * �Ƚ�����ʱ���Ƿ���ȡ�
     * @author slx
     * @date 2009-7-13 ����10:08:52
     * @modifyNote
     * @param d1  
     *          ʱ��1
     * @param d2
     *          ʱ��2 
     * @return
     *          �����true����Ϊ���ݿ��ж���������ΪTimestamp����(Date������)��
     * ������Date���ͽ��бȽ�ʱ,����Ϊfalse,��ʹ��ͬһ��ʱ��.���д���������,���ڼ������������͵�ʱ��Ƚ�.
     */
    public static boolean equalsDate(Date d1 , Date d2){
        if(d1 !=null && d2!= null){
            return d1.getTime() == d2.getTime();
        }
        return false;
    }

    /**
     * �жϺ����һ���Ƿ���ǰ��һ�����һ��
     * @author slx
     * @date 2009-7-8 ����04:46:38
     * @modifyNote
     * @param day   
     *          ��׼����
     * @param nextDay
     *          �Ƚ�����
     * @return
     *          ����Ƚ������ǻ�׼���ڵ���һ���򷵻�true������Ϊfalse
     */
    public static boolean isNextDay(Date day,Date nextDay){
        return ( getBetweenDays(day ,nextDay) == -1 );
    }

    /**
     * �ж����������Ƿ���ͬһ��
     * @author slx
     * @date 2009-11-10 ����04:32:07
     * @modifyNote
     * @param day
     * @param otherDay
     * @return
     */
    public static boolean isSameDay(Date day,Date otherDay){
        return ( getBetweenDays(day ,otherDay) == 0 );
    }

    /**
     * ��������������������.����24Сʱ������һ��
     * @author slx
     * @date 2009-7-10 ����03:15:54
     * @modifyNote
     * @param fDate     ����1
     * @param oDate     ����2
     * @return
     *      ����1 - ����2 �Ĳ�
     */
    public static int getBetweenDays(Date fDate, Date sDate) {
        int day=(int)((fDate.getTime()-sDate.getTime())/86400000L);//(24Сʱ * 60�� * 60�� * 1000���� = 1�������) 
        return day;
    }

    /**
     * �������ָ����
     * @author slx
     * @date 2009-9-10 ����10:26:22
     * @modifyNote
     * @param date
     *      ����
     * @param addYears
     *      Ҫ��ӵ�����
     * @return
     *      ��Ӻ������
     */
    public static Date addYears(Date date , int addYears){
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        calender.add(Calendar.YEAR, addYears);
        return calender.getTime();
    }
    /**
     * ��ָ����
     * @author slx
     * @date 2009-9-10 ����10:26:57
     * @modifyNote
     * @param date
     *      ����
     * @param addMonths
     *      ����
     * @return
     *      ��Ӻ������
     */
    public static Date addMonth(Date date , int addMonths){
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        calender.add(Calendar.MONTH, addMonths);
        return calender.getTime();
    }

    /**
     * ��ָ������
     * @author slx
     * @date 2009-9-10 ����10:27:22
     * @modifyNote
     * @param date
     *      ����
     * @param addDays
     *      ����
     * @return
     *      ��Ӻ������
     */
    public static Date addDay(Date date , int addDays){
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        calender.add(Calendar.DAY_OF_YEAR, addDays);
        return calender.getTime();
    }

    /**
     * �õ�һ��ĵ�һ��
     * @author slx
     * @date 2009-9-10 ����11:14:23
     * @modifyNote
     * @param year
     *      ��
     * @return
     *      һ��ĵ�һ��
     */
    public static Date getFirstDateOfYear(int year){
        Calendar calender = Calendar.getInstance();
        calender.set(Calendar.YEAR,year);
        calender.set(Calendar.DAY_OF_YEAR, calender.getActualMinimum(Calendar.DAY_OF_YEAR));
        setStartTimeOfDay(calender);
        return calender.getTime();
    }

    /**
     * �õ�һ������һ��
     * @author slx
     * @date 2009-9-10 ����11:14:42
     * @modifyNote
     * @param year
     *      ��
     * @return
     *      һ������һ��
     */
    public static Date getLastDateOfYear(int year){
        Calendar calender = Calendar.getInstance();
        calender.set(Calendar.YEAR,year);
        calender.set(Calendar.DAY_OF_YEAR, calender.getActualMaximum(Calendar.DAY_OF_YEAR));
        setEndTimeOfDay(calender);
        return calender.getTime();
    }

    /**
     * �жϵ�ǰ�����Ƿ��������·ݵ����һ��
     * @author slx
     * @date 2009-9-10 ����10:54:36
     * @modifyNote
     * @param date
     *      ����
     * @return
     *      �����һ��Ϊ true
     */
    public static boolean isLastDayOfMonth(Date date) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        int day = calender.get(Calendar.DAY_OF_MONTH);
        int lastDay = calender.getActualMaximum(Calendar.DAY_OF_MONTH);
        return day == lastDay ;
    }

    /**
     * �õ�ָ���µ����һ��
     * @author slx
     * @date 2009-9-10 ����11:09:56
     * @modifyNote
     * @param year
     *      ��
     * @param month
     *      ��
     * @return
     *      ���һ��
     */
    public static Date getLastDayOfMonth(int year , int month){
        Calendar calender = Calendar.getInstance();
        calender.set(year, month-1, 1);
        calender.set(Calendar.DAY_OF_MONTH, calender.getActualMaximum(Calendar.DAY_OF_MONTH));
        setEndTimeOfDay(calender);
        return calender.getTime();
    }

    /**
     * �õ����������µ����һ��
     * @author slx
     * @date 2009-9-10 ����10:54:25
     * @modifyNote
     * @param date
     *      ����
     * @return
     *      �����µ����һ��
     */
    public static Date getLastDayOfMonth(Date date){
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        calender.set(Calendar.DAY_OF_MONTH, calender.getActualMaximum(Calendar.DAY_OF_MONTH));
        setEndTimeOfDay(calender);
        return calender.getTime();
    }

    /**
     * ���õ���ǰ�µ����ʱ��
     * @author slx
     * @date 2010-10-18 ����11:04:56
     * @modifyNote
     * @param calender
     */
    private static void setEndTimeOfDay(Calendar calender){
        calender.set(Calendar.HOUR_OF_DAY, calender.getActualMaximum(Calendar.HOUR_OF_DAY));
        calender.set(Calendar.MINUTE, calender.getActualMaximum(Calendar.MINUTE));
        calender.set(Calendar.SECOND, calender.getActualMaximum(Calendar.SECOND));
        calender.set(Calendar.MILLISECOND, calender.getActualMaximum(Calendar.MILLISECOND));
    }

    /**
     * �õ�ָ���µĵ�һ��
     * @author slx
     * @date 2009-9-10 ����11:09:56
     * @modifyNote
     * @param year
     *      ��
     * @param month
     *      ��
     * @return
     *      ��һ��
     */
    public static Date getFirstDayOfMonth(int year , int month){
        Calendar calender = Calendar.getInstance();
        calender.set(year, month-1, 1);
        calender.set(Calendar.DAY_OF_MONTH, calender.getActualMinimum(Calendar.DAY_OF_MONTH));
        setStartTimeOfDay(calender);
        return calender.getTime();
    }

    /**
     * �õ�ָ�����������µĵ�һ��
     * @author slx
     * @date 2009-9-10 ����11:09:56
     * @modifyNote
     * @param date
     *      ����
     * @return
     *      ��һ��
     */
    public static Date getFirstDayOfMonth(Date date){
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        calender.set(Calendar.DAY_OF_MONTH, calender.getActualMinimum(Calendar.DAY_OF_MONTH));
        setStartTimeOfDay(calender);
        return calender.getTime();
    }

    /**
     * ���õ��·ݿ�ʼ��ʱ��
     * @author slx
     * @date 2010-10-18 ����11:06:12
     * @modifyNote
     * @param calender
     */
    private static void setStartTimeOfDay(Calendar calender){
        calender.set(Calendar.HOUR_OF_DAY, calender.getActualMinimum(Calendar.HOUR_OF_DAY));
        calender.set(Calendar.MINUTE, calender.getActualMinimum(Calendar.MINUTE));
        calender.set(Calendar.SECOND, calender.getActualMinimum(Calendar.SECOND));
        calender.set(Calendar.MILLISECOND, calender.getActualMinimum(Calendar.MILLISECOND));
    }

    public static Date getStartTimeOfDay(Date date){
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        setStartTimeOfDay(calender);
        return calender.getTime();
    }

    public static Date getEndTimeOfDay(Date date){
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        setEndTimeOfDay(calender);
        return calender.getTime();

    }

    /**
     * �õ���ǰ����
     * 
     * @author yongtree
     * @date 2008-11-22 ����11:25:24
     * @return ��ʽ��2008-11
     * @throws ParseException
     */
    public static  String getThisYearMonth() throws ParseException {
        return getYearMonth(new Date());
    }

    /**
     * �õ�����
     * 
     * @author slx
     * @date 2010��4��16��13:09:23
     * @return ��ʽ��2008-11
     * @throws ParseException
     */
    public static  String  getYearMonth(Date date){
        Calendar today = Calendar.getInstance();
        today.setTime(date);
        return (today.get(Calendar.YEAR)) + "-" + ((today.get(Calendar.MONTH)+1)>=10?(today.get(Calendar.MONTH)+1):("0"+(today.get(Calendar.MONTH) + 1)));
    }

    /**
     * ������������֮�������·���
     * <br> ����˳�򲻷��Ⱥ󲻻᷵�ظ���
     * <br> ����һ���²�����һ����
     * @author slx
     * @date 2010��4��16��11:32:51
     * @modifyNote
     * @param date1
     *      ����1
     * @param date2
     *      ����2
     * @return
     *      ����
     */
    public static int getBetweenMonths(Date date1, Date date2){      
        int iMonth = 0;      
        int flag = 0;      
        Calendar objCalendarDate1 = Calendar.getInstance();      
        objCalendarDate1.setTime(date1);      

        Calendar objCalendarDate2 = Calendar.getInstance();      
        objCalendarDate2.setTime(date2);      

        if (objCalendarDate2.equals(objCalendarDate1))      
            return 0;      
        if (objCalendarDate1.after(objCalendarDate2)){      
            Calendar temp = objCalendarDate1;      
            objCalendarDate1 = objCalendarDate2;      
            objCalendarDate2 = temp;      
        }      
        if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1.get(Calendar.DAY_OF_MONTH))      
            flag = 1;      

        if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR))      
            iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR))      
                    * 12 + objCalendarDate2.get(Calendar.MONTH) - flag)      
                    - objCalendarDate1.get(Calendar.MONTH);      
        else     
            iMonth = objCalendarDate2.get(Calendar.MONTH)      
                    - objCalendarDate1.get(Calendar.MONTH) - flag;      

        return iMonth;      
    }

    /**
     * ������������֮�����������
     * <br> ����˳�򲻷��Ⱥ󲻻᷵�ظ���
     * <br> ����һ���겻����һ����
     * @author slx
     * @date 2010��4��16��12:01:46
     * @modifyNote
     * @param date1
     *      ����1
     * @param date2
     *      ����2
     * @return
     *      ����
     */
    public static int getBetweenYears(Date date1, Date date2){  
        return getBetweenMonths(date1 ,date2) / 12;
    }

    public static void main(String[] args) throws Exception {
//      Date d1 = parseToDate("2009-11-29", null);
//      Date d2 = parseToDate("2007-12-29", null);
        System.out.println(formatDate(getFirstDayOfMonth(2010,10),"yyyy-MM-dd HH:mm:ss.SSS"));

        System.out.println(formatDate(getLastDateOfYear(2009),"yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println(formatDate(getFirstDateOfYear(2009),"yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println(formatDate(getEndTimeOfDay(new Date()),"yyyy-MM-dd HH:mm:ss.SSS"));
    }
}
