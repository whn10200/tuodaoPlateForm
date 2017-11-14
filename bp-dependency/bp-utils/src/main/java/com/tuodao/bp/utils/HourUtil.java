package com.tuodao.bp.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class HourUtil {

	public static final long dayMil = 86400000L;

	public static String withdrawCash() {
		String msg = "实时到账";

		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.HOUR_OF_DAY, 9);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		Date d9 = c1.getTime();

		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.HOUR_OF_DAY, 21);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 0);
		Date d21 = c2.getTime();

		Date d = new Date();

		if (d.before(d9)) {
			msg = "当日9点到账";
		}

		if (d.after(d21)) {
			msg = "次日9点到账";
		}

		return msg;
	}

	public static boolean biddingBid() {
		boolean msg = true;

		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 5);
		c1.set(Calendar.SECOND, 0);
		Date d9 = c1.getTime();

		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.HOUR_OF_DAY, 23);
		c2.set(Calendar.MINUTE, 55);
		c2.set(Calendar.SECOND, 0);
		Date d21 = c2.getTime();

		Date d = new Date();

		if (d.before(d9)) {
			msg = false;
		}

		if (d.after(d21)) {
			msg = false;
		}

		return msg;
	}

	public static long getDay(long timeMillis) {
		Calendar c1 = new GregorianCalendar();
		c1.setTimeInMillis((timeMillis + 28800000L) / 86400000L * 86400000L);

		return c1.getTime().getTime();
	}

	public static long getNatureDay2(long beforeMil, long afterMil) {
		long l = (afterMil + 28800000L) / 86400000L - (beforeMil + 28800000L) / 86400000L;
		return l;
	}
	
	public static long getNatureDay(long beforeMil, long afterMil) {
		Calendar c1x = new GregorianCalendar();
		c1x.setTimeInMillis(beforeMil);
		c1x.set(Calendar.HOUR_OF_DAY, 0);
		c1x.set(Calendar.MINUTE, 0);
		c1x.set(Calendar.SECOND, 0);
		c1x.set(Calendar.MILLISECOND, 0);
		Long t1 = c1x.getTime().getTime();
		
		Calendar c2x = new GregorianCalendar();
		c2x.setTimeInMillis(afterMil);
		c2x.set(Calendar.HOUR_OF_DAY, 0);
		c2x.set(Calendar.MINUTE, 0);
		c2x.set(Calendar.SECOND, 0);
		c2x.set(Calendar.MILLISECOND, 0);
		Long t2 = c2x.getTime().getTime();
		
		long l = (t2-t1) / 86400000L;
		
		return l;
	}

	public static long getNatureDayForSec(long beforeSec, long afterSec) {
		return getNatureDay(beforeSec * 1000, afterSec * 1000);
	}

	public static void main1(String[] args) throws Exception {
		/*
		 * System.out.println(System.currentTimeMillis());
		 * System.out.println(new Date(getDay(1502941515000L)));
		 * System.out.println(new Date(getDay(1504861640000L)));
		 * System.out.println(getNatureDay(1502941515000L,1504861640000L));
		 */

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Long t1 = 1502965841000L;
		System.out.println(formatter.format(t1));
		Long t2 = formatter.parse("2017-09-09 12:13:21").getTime();
		System.out.println(formatter.format(t2));

		System.out.println(getNatureDay(t1, t2));
	}

	public static void main(String[] args) throws Exception {
		/*
		 * System.out.println(System.currentTimeMillis());
		 * System.out.println(new Date(getDay(1502941515000L)));
		 * System.out.println(new Date(getDay(1504861640000L)));
		 * System.out.println(getNatureDay(1502941515000L,1504861640000L));
		 */

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Long t1 = 1502965841000L;
		// System.out.println(formatter.format(t1));
		Long t2x = formatter.parse("2017-08-17 00:15:00").getTime();
		Long t3x = formatter.parse("2017-09-10 00:17:00").getTime();
		/*
		 * Era era2 = new Era(t1); Era era3 = new Era(t3);
		 */
		for (int i = 0; i < 24; i++) {
			Long t2 = t2x + new Long(i * 60 * 60 * 1000);
			for (int j = 0; j < 24; j++) {
				Long t3 = t3x + new Long(j * 60 * 60 * 1000);
				Long a = getNatureDay(t2, t3);
				String s = a + "||" + formatter.format(t2) + "||" + formatter.format(t3);
				//System.out.println(s);
				if(a!=24)
					System.out.println(0000);
			}
		}
		
		for (int i = 0; i < 24; i++) {
			Long t2 = t2x + new Long(i * 60 * 60 * 1000);
			for (int j = 0; j < 24; j++) {
				Long t3 = t3x + new Long(j * 60 * 60 * 1000);
				Long a = getNatureDay2(t2, t3);
				String s = a + "||" + formatter.format(t2) + "||" + formatter.format(t3);
				//System.out.println(s);
				if(a!=24)
					System.out.println(0000);
			}
		}
	}

	public static void main2(String[] args) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Long t1 = 1502965841000L;
		Long t2x = formatter.parse("2017-08-17 00:05:00").getTime();
		Long t3x = formatter.parse("2017-09-10 00:47:00").getTime();
		Calendar c1x = new GregorianCalendar();
		c1x.setTimeInMillis(t2x);
		c1x.set(Calendar.HOUR_OF_DAY, 0);
		c1x.set(Calendar.MINUTE, 5);
		c1x.set(Calendar.SECOND, 0);
		Long d9 = c1x.getTime().getTime();
		
		System.out.println(formatter.format(d9));
	}
}
