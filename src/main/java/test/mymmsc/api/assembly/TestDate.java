package test.mymmsc.api.assembly;

import org.mymmsc.api.assembly.Api;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TestDate {

    public static <T> T t1(Class<T> clazz) {
        T obj = null;
        obj = Api.valueOf(clazz, null);
        return obj;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        int x = 24 * 60 * 60 * 1000;
        x = t1(int.class);

        //int y = 1452096000000;
        System.out.println();
        System.out.println(Locale.getDefault());
        System.out.println(x * 46);
        String s = "2015-12-29 12:00:00";
        java.sql.Date d1 = Api.valueOf(java.sql.Date.class, s);
        System.out.println(Api.toString(d1));
        java.sql.Timestamp ts = Api.getNow();
        System.out.println(ts);
        long xt = 1451404799L;
        xt *= 1000;
        Date tmpDate = new Date(d1.getTime());
        tmpDate = new Date(xt);
        String sTime = Api.toString(Api.addDate(tmpDate, Calendar.DATE, 1), "yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(sTime);
        sTime = Api.toString(Api.addDate(tmpDate, Calendar.DATE, 8), "yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(sTime);
        sTime = Api.toString(Api.addDate(tmpDate, Calendar.DATE, 16), "yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(sTime);
        sTime = Api.toString(Api.addDate(tmpDate, Calendar.DATE, 46), "yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(sTime);
        sTime = Api.toString(Api.addDate(tmpDate, Calendar.DATE, 76), "yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(sTime);
        sTime = Api.toString(Api.addDate(tmpDate, Calendar.DATE, 100), "yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(sTime);
        sTime = Api.toString(new Date(), "yyyy-MM-dd-hh");
        System.out.println(sTime);
        System.out.println(Api.getWeekDay(new Date()));
        long aa = Api.getTimeInMillis(Api.toDate("20060112012739000",
                "yyyyMMddhhmmssSSS"));
        long a = Api.getTimeInMillis(new Date());

        System.out.println("aa = " + aa + ", a = " + a);
        java.util.Calendar date = java.util.Calendar.getInstance();

        // System.out.println(date.getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.println(Api.getField(Api.getFirstDayOfWeek(date.getTime()),
                java.util.Calendar.DAY_OF_MONTH));
        java.util.Date date1 = Api.toDate("20150201080910", "yyyyMMddhhmmss");
        java.util.Date date2 = Api.addDate(date1,
                java.util.Calendar.DAY_OF_YEAR, +10000);
        System.out.println(Api.getField(Api.getLastDayOfMonth(date1),
                java.util.Calendar.SECOND));
        System.out.println(Api.toString(date2, "yyyy-MM-dd hh:mm:ss.SSS"));
        System.out.println(Api.diffDays(date1, date2));
    }

}
