package Util;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    
    private static Logger logger = Logger.getLogger(DateUtils.class);

    public static String formatDateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static Date getDateFromString(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            logger.error(e);
            return null;
        }
    }
    
    public static long difference(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        if (date1.before(date2)) {
            return (date2.getTime() - date1.getTime()) / 1000;
        } else {
            return (date1.getTime() - date2.getTime()) / 1000;
        }
    }
    
    public static boolean dayBefore(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        calendar1.set(Calendar.MILLISECOND, 0);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        calendar2.set(Calendar.HOUR_OF_DAY, 0);
        calendar2.set(Calendar.MINUTE, 0);
        calendar2.set(Calendar.SECOND, 0);
        calendar2.set(Calendar.MILLISECOND, 0);
        return calendar1.getTime().before(calendar2.getTime());
    }

}
