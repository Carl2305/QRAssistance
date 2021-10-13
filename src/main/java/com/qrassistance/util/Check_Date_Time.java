package com.qrassistance.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Check_Date_Time {
	public static boolean isTimeWith_in_Interval(String valueToCheck) {
	    boolean isBetween = false;
	    try {
	        Date time1 = new SimpleDateFormat("HH:mm:ss").parse("17:30:00");
	        Date time2 = new SimpleDateFormat("HH:mm:ss").parse("08:30:00");
	        Date d = new SimpleDateFormat("HH:mm:ss").parse(valueToCheck);
	        if (time1.after(d) && time2.before(d)) {
	            isBetween = true;
	            System.out.println(isBetween);
	        }
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    System.out.println("final: "+isBetween);
	    return isBetween;
	}
	public static boolean isTimeDelayWhith_In_Internval(String valueToCheck) {
		boolean isBetween = false;
	    try {
	        Date time1 = new SimpleDateFormat("HH:mm:ss").parse("09:00:00");
	        Date time2 = new SimpleDateFormat("HH:mm:ss").parse("08:30:00");
	        Date d = new SimpleDateFormat("HH:mm:ss").parse(valueToCheck);
	        if (time1.after(d) && time2.before(d)) {
	            isBetween = true;
	        }
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    return isBetween;
	}
	
	public static boolean isDateWith_in_Interval(String ChekDay){
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	      Calendar cal= Calendar.getInstance();
	      try {
			cal.setTime(df.parse(ChekDay));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      int numeroDia=cal.get(Calendar.DAY_OF_WEEK);
	      switch (numeroDia) {
			case 1: return false;
			case 2: return true;
			case 3: return true;
			case 4: return true;
			case 5: return true;
			case 6: return true;
			case 7: return false;
			default:
				return false;
			}
	 }
	
}
