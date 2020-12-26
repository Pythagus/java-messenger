package fr.insa.messenger.utils;

import java.util.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 * @author Damien MOLINA
 */
public class DateUtils {

    /**
     * Date format.
     * Example: 22/01/1999
     */
    public static final String DATE_FORMAT = "dd/MM/yyyy" ;

    /**
     * Date with time format.
     * Example: 22/01/1999 à 18:36
     */
    public static final String DATE_TIME_FORMAT = DateUtils.DATE_FORMAT + " à " + DateUtils.TIME_FORMAT ;

    /**
     * Time format.
     * Example: 18:36
     */
    public static final String TIME_FORMAT = "HH:mm" ;

    /**
     * Format the given date to the given
     * format.
     *
     * @param date : date instance.
     * @param format : format regex.
     * @return the formatted string.
     */
    public static String format(Date date, String format) {
        return new SimpleDateFormat(format).format(date) ;
    }

    /**
     * Get the date of today.
     *
     * @param hours : today's hour.
     * @param minutes : today's minute.
     * @param seconds : today's second.
     * @return the Date instance.
     */
    public static Date today(int hours, int minutes, int seconds) {
        Calendar c = Calendar.getInstance();

        c.set(Calendar.HOUR_OF_DAY, hours) ;
        c.set(Calendar.MINUTE, minutes) ;
        c.set(Calendar.SECOND, seconds) ;
        c.set(Calendar.MILLISECOND, 0) ;

        return c.getTime();
    }

    /**
     * Get the date of today.
     *
     * @return the Date instance.
     */
    public static Date today() {
        return DateUtils.today(0, 0, 0) ;
    }

    /**
     * Get the timestamp of the given date.
     *
     * @param date : date to convert.
     * @return the timestamp.
     */
    public static long timestamp(String date) {
        return Timestamp.valueOf(date).getTime() ;
    }

}
