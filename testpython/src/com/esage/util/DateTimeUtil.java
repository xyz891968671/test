package com.esage.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class DateTimeUtil {

	public static void main(String[] args) {
		String str=new Date("yyyy-MM-dd HH:mm:ss").toString();
		HashMap<String, String> date = getTime();
	}

	/*
	 * ����һ������ʱ���HashMap ����
	 * 
	 * @return
	 */
	public static HashMap<String, String> getTime() {

		HashMap<String, String> hm = new HashMap<String, String>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Calendar c = Calendar.getInstance();
		// ��ȥһ��
		c.setTime(new Date());
		c.add(Calendar.DATE, -1);
		Date t = c.getTime();
		String days = format.format(t);
		System.out.println("��ȥһ�죺" + days);
		hm.put("days", days);

		// ��ȥ����
		c.setTime(new Date());
		c.add(Calendar.DATE, -7);
		Date d = c.getTime();
		String day = format.format(d);
		System.out.println("��ȥ���죺" + day);
		hm.put("day", day);

		// ��ȥʮ��
		c.setTime(new Date());
		c.add(Calendar.DATE, -10);
		Date d10 = c.getTime();
		String day10 = format.format(d10);
		hm.put("day10", day10);

		// ��ȥһ��
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date m = c.getTime();
		String mon = format.format(m);
		System.out.println("��ȥһ���£�" + mon);
		hm.put("mon", mon);

		// ��ȥ������
		c.setTime(new Date());
		c.add(Calendar.MONTH, -3);
		Date m3 = c.getTime();
		String mon3 = format.format(m3);
		System.out.println("��ȥ�����£�" + mon3);
		hm.put("mon3", mon3);

		// ��ȥһ��

		c.setTime(new Date());
		c.add(Calendar.YEAR, -1);
		Date y = c.getTime();
		String year = format.format(y);
		System.out.println("��ȥһ�꣺" + year);
		hm.put("year", year);

		return hm;
	}

}
