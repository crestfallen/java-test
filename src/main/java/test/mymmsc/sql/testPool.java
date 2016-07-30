/**
 * @(#)testPool.java 6.3.9 09/10/02
 * <p>
 * Copyright 2000-2010 MyMMSC Software Foundation (MSF), Inc. All rights reserved.
 * MyMMSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package test.mymmsc.sql;

import org.mymmsc.sql.SQLApi;
import org.mymmsc.sql.dbcp2.ConnectionFactory;
import org.mymmsc.sql.dbcp2.ConnectionParam;
import org.mymmsc.sql.dbcp2.FactoryParam;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class testPool {

    /**
     * 解析CSV格式文本行
     *
     * @param string csv格式文本行
     * @param args   接受一个布尔类型参数, 是否打开调试模式输出每个元素
     * @return 字符串组
     */
    public static List<String> parseCsv0(String string, Object... args) {

        boolean debug = false;
        List<String> lRet = null;
        if (args != null && args.length > 0) {
            Object obj = args[0];
            Class<?> cls = obj.getClass();
            if (cls == boolean.class || cls == Boolean.class) {
                debug = ((Boolean) args[0]).booleanValue();
            }
        }
        final String regex = "(?:^|,\\s{0,})([\"]?)\\s{0,}((?:.|\\n|\\r)*?)\\1(?=[,]\\s{0,}|$)";
        try {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(string);
            String value = null;
            while (m != null && m.find()) {
                for (int i = 2; i <= m.groupCount(); i++) {
                    value = m.group(i);
                    if (debug) {
                        System.out.println(value);
                    }
                    if (lRet == null) {
                        lRet = new ArrayList<>();
                    }
                    lRet.add(value);
                }
            }
        } catch (PatternSyntaxException e) {
            //
        }

        return lRet;
    }

    /**
     * 解析CSV格式文本行
     *
     * @param string  csv格式文本行
     * @param boolean 接受一个布尔类型参数, 是否打开调试模式输出每个元素
     * @return 字符串组
     */
    public static List<String> parseCsv(String string, boolean debug) {
        List<String> lRet = null;
        final String regex = "(?:^|,\\s{0,})([\"]?)\\s{0,}((?:.|\\n|\\r)*?)\\1(?=[,]\\s{0,}|$)";
        try {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(string);
            String value = null;
            while (m != null && m.find()) {
                for (int i = 2; i <= m.groupCount(); i++) {
                    value = m.group(i);
                    if (debug) {
                        System.out.println(value);
                    }
                    if (lRet == null) {
                        lRet = new ArrayList<String>();
                    }
                    lRet.add(value);
                }
            }
        } catch (PatternSyntaxException e) {
            //
        }

        return lRet;
    }

    /**
     * 解析CSV格式文本行
     *
     * @param string csv格式文本行
     * @return 字符串组
     */
    public static List<String> parseCsv(String string) {
        return parseCsv(string, false);
    }

    /**
     * 解析CSV格式文本行
     *
     * @param string csv格式文本行
     * @return 字符串组
     */
    public static String[] parseCsv2(String string) {
        String[] saRet = null;
        List<String> list = parseCsv(string, false);
        if (list != null) {
            saRet = list.toArray(new String[0]);
        }
        return saRet;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("-------------------");
        String str = "ff80808151aa289e0151cdeb263a0355,496868139724721488,501511253760833566,5417543269767979340001,13316555697,,广西  来宾  象州县,中平镇大架村民委长江村56号,,,,,,,,,,,,,,\"陈林聪，男，\"身份证号码452224198903193012,家庭住址广西象州县中平镇大架村民委长江村56号……陈林聪支付宝帐号，13316555697,QQ153950355,微信kfc153950355陈林聪手机号码13316555697\",20160121,1450858718,1453327312";
        //str = "1,2,\"234,\",\"\"23234,\",32343";
        System.out.println(str);
        List<String> ll = parseCsv(str);
        System.out.println("共[" + ll.size() + "]个元素");
        System.out.println("-------------------");
        for (int i = 0; i < ll.size(); i++) {
            System.out.println("[" + i + "]---< " + ll.get(i) + " >---");
        }
        System.out.println("-------------------");
        String[] as = parseCsv2(str);
        System.out.println("共[" + as.length + "]个元素");
        System.out.println("-------------------");
        for (int i = 0; i < as.length; i++) {
            System.out.println("[" + i + "]---< " + as[i] + " >---");
        }
        //System.out.println(str);
        //str = str.replaceAll(",", "，");
        //System.out.println(str);
        return;
        /*
         * testPool tp = new testPool(); tp.test1();
		 * System.out.println(Api.md5("0eTong/EMPP1.3.3")); String user =
		 * "labs"; String password = "123456"; String url =
		 * "jdbc:mysql://192.168.1.200:180366/mysql"; String driver =
		 * "com.mysql.jdbc.Driver"; ConnectionParam param = new
		 * ConnectionParam(driver, url, user, password);
		 * System.out.println("-------" + param.getDriver()); ConnectionFactory
		 * cf = null; try { cf = new ConnectionFactory(param, new
		 * FactoryParam()); Connection conn1 = null; long time =
		 * System.currentTimeMillis(); for (int i = 0; i < 1000; i++) { conn1 =
		 * cf.getFreeConnection(); Statement stmt = conn1.createStatement();
		 * ResultSet rs = stmt.executeQuery("select * from sys_admin"); if
		 * (rs.next()) { System.out.println("conn1 y"); } else {
		 * System.out.println("conn1 n"); } conn1.close(); }
		 * System.out.println("pool:" + (System.currentTimeMillis() - time));
		 * time = System.currentTimeMillis();
		 * Class.forName(param.getDriver()).newInstance(); for (int i = 0; i <
		 * 10; i++) { conn1 = DriverManager.getConnection(param.getUrl(),
		 * param.getUser(), param.getPassword()); Statement stmt =
		 * conn1.createStatement(); ResultSet rs = stmt.executeQuery(
		 * "select * from sys_admin"); if (rs.next()) { System.out.println(
		 * "conn1 y"); System.out.println(rs.getString(1));
		 * System.out.println(rs.getString(2)); } else { System.out.println(
		 * "conn1 n"); } conn1.close(); } System.out .println("no pool:" +
		 * (System.currentTimeMillis() - time)); } catch (Exception e) {
		 * e.printStackTrace(); } finally { try { cf.close(); } catch (Exception
		 * e) { e.printStackTrace(); } }
		 */
    }

    @SuppressWarnings("unused")
    public void test1() {
        String user = "hl_dsp_stat";
        String password = "09h2i3r90h8ioaiuu";
        String url = "jdbc:mysql://192.168.1.200:18036/mysql?&useUnicode=true&characterEncoding=gbk&autoReconnect=true&failOverReadOnly=false";
        url = "jdbc:mysql://db1.dsp.haolan.com:3306/hl_commom?&useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false";
        String driver = "com.mysql.jdbc.Driver";

        ConnectionParam param = new ConnectionParam(driver, url, user, password);
        ConnectionFactory cf = null;
        Integer a = new Integer(0);
        // new ConnectionFactory(param,new FactoryParam());
        try {
            cf = new ConnectionFactory(param, new FactoryParam());
            Connection conn1 = cf.getFreeConnection();
            Connection conn2 = cf.getFreeConnection();
            Connection conn3 = cf.getFreeConnection();
            int c = SQLApi.getOneRow(conn1, Integer.class, "select count(*) from user");
            int c1 = (int) c;
            TestObj d = SQLApi.getOneRow(conn1, TestObj.class, "select count(*) as count from user");
            Statement stmt = conn1.createStatement();
            ResultSet rs = stmt.executeQuery("select * from ADMINISTRATION");
            if (rs.next()) {
                System.out.println("conn1 y");
            } else {
                System.out.println("conn1 n");
            }
            stmt.close();
            conn1.close();
            Connection conn4 = cf.getFreeConnection();
            Connection conn5 = cf.getFreeConnection();

            stmt = conn5.createStatement();
            rs = stmt.executeQuery("select * from ADMINISTRATION");
            if (rs.next()) {
                System.out.println("conn5 y");
            } else {
                System.out.println("conn5 n");
            }

            conn2.close();
            conn3.close();
            conn4.close();
            conn5.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                cf.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
