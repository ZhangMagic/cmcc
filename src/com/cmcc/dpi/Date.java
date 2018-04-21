package com.cmcc.dpi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date {
	
	public static void main(String[] args){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1); 
		java.util.Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		DateFormat midnight = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		System.out.println(df.format(date));
		System.out.println(midnight.format(date));

		} 
}
