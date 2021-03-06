package de.stylextv.maple.util.time;

import java.util.Date;

public class TimeFormat {
	
	public static String formatDate(long time) {
		return new Date(time).toString();
	}
	
	public static String formatDuration(long time) {
		String s = null;
		
		while(time != 0 || s == null) {
			
			TimeUnit u = TimeUnit.getUnit(time);
			
			long amount = u.getAmount(time);
			
			time -= amount * u.getDuration();
			
			String s2 = amount + " " + u.getName(amount);
			
			if(s != null) {
				s += " and " + s2;
				
				break;
			}
			
			s = s2;
		}
		
		return s;
	}
	
}
