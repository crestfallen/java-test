/**
 *
 */
package test.mymmsc.api.http;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author wangfeng
 * @date 2014年11月7日 上午6:19:43
 */
public class HttpDate {
    private final static String RFC1123_DATE_PATTERN = "EEE, dd MMM yyyy HH:mm:ss zzz";
    private final static String RFC850_DATE_PATTERN = "EEEE, dd-MMM-yy HH:mm:ss z";
    private final static String ASCTIME_DATE_PATTERN = "EEE MMM d HH:mm:ss yyyy";

    /**
     * @param args
     */
    public static void main(String[] args) {
        Locale locale = new Locale("en_US");
        TimeZone tz = TimeZone.getTimeZone("GMT");
        SimpleDateFormat dateFormat = new SimpleDateFormat(RFC1123_DATE_PATTERN, locale);
        dateFormat.setTimeZone(tz);
        Date date = new Date(1234);
        String str = dateFormat.format(date);
        Date date2 = null;
        try {
            date2 = dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("date=" + date + "; " + date.getTime());
        System.out.println("str=" + str);
        System.out.println("date2=" + date2 + "; " + date2.getTime());

    }

}
