package com.yc.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class StringUtil {

	public static String getVid() {
		// TODO Auto-generated method stub
		return new Date().getTime()+""+new Random().nextInt(1000);
	}

	public static String getStartDate() {
		// TODO Auto-generated method stub
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");;
		return sdf.format(date);
	}

	public static String getEndDate() {
		// TODO Auto-generated method stub
		long time = new Date().getTime()+7*24*60*60*1000;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");;
		return sdf.format(time);
	}

}
