package cn.edu.scau.cmi.wuweijie.utils;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 数据库连接测试工具类
 * @author TESLA_CN
 *
 */
public class DatabaseConnectionTest {
	
	private static Log log = LogFactory.getLog(DatabaseConnectionTest.class);

	/**
	 * 如果 mysql数据库连接成功，返回true，否则会抛出异常
	 * @param url
	 * @param user
	 * @param password
	 * @return 数据库连接成功返回 true
	 * @throws Exception 数据库连接失败抛出异常
	 */
	public static boolean mysqlConnectionTest(String url, String user, String password) throws Exception {
		log.info("Test Mysql Connection: " + url + ", " + user + ", " + password);
		boolean connected = true;
		Class.forName("com.mysql.jdbc.Driver");
		try (Connection connection = DriverManager.getConnection(url, user, password)) {
		}
		return connected;
	}

}
