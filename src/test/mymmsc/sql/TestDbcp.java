/**
 * 
 */
package test.mymmsc.sql;

import java.sql.Connection;
import java.sql.SQLException;

import org.mymmsc.sql.SQLApi;
import org.mymmsc.sql.dbcp.BoneCP;
import org.mymmsc.sql.dbcp.BoneCPConfig;

/**
 * @author wangfeng
 * @date 2016年1月17日 下午11:25:29
 */
public class TestDbcp {

	/**
	 * 
	 */
	public TestDbcp() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		// load the DB driver
		Class.forName("com.mysql.jdbc.Driver");
		// create a new configuration object
		BoneCPConfig config = new BoneCPConfig();
		String urlJdbc = "jdbc:mysql://127.0.0.1:18036/ermas_tags_new?useUnicode=true&amp;characterEncoding=utf8&amp;zeroDateTimeBehavior=round";
		config.setJdbcUrl(urlJdbc);  
		config.setUsername("runtime");  
		config.setPassword("123456");
		BoneCP connectionPool;
		try {
			connectionPool = new BoneCP(config);
			Connection conn;
			conn = connectionPool.getConnection();
			int n = SQLApi.getOneRow(conn, int.class, "select count(*) from ermas_account");
			conn.close();
			connectionPool.shutdown();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
