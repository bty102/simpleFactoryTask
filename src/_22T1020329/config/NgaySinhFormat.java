package _22T1020329.config;

import java.text.SimpleDateFormat;

public class NgaySinhFormat {
	
	private static String format = "yyyy/MM/dd";
	private static SimpleDateFormat simpleDateFormat = null;
	
	public static SimpleDateFormat getSimpleDateFormat() {
		if(simpleDateFormat == null) {
			simpleDateFormat = new SimpleDateFormat(format);
		}
		return simpleDateFormat;
	}
}
