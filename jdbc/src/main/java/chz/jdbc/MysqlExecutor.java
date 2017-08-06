package chz.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class MysqlExecutor {
	
	public DataSource createDataSource() {
		Properties properties = getProperties();
		String url = properties.getProperty(DbConstants.DB_MYSQL_KEY_URL);
		String user = properties.getProperty(DbConstants.DB_MYSQL_KEY_USER);
		String password = properties.getProperty(DbConstants.DB_MYSQL_KEY_PASSWORD);
		MysqlDataSource ds = new MysqlDataSource();
		ds.setURL(url);
		ds.setUser(user);
		ds.setPassword(password);
		try {
			ds.setLogWriter(new PrintWriter(System.out));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ds;
	}
	
	private Properties getProperties() {
		Properties properties = new Properties();
		InputStream ins = this.getClass().getClassLoader().getResourceAsStream("db.properties");
		try {
			properties.load(ins);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	public ResultSet query(String sql) {
		ResultSet rs = null;
		
		try {
			DataSource ds = createDataSource();
			Connection conn = ds.getConnection();
			Statement statement = conn.createStatement();
			rs = statement.executeQuery(sql);
		} catch (SQLException sqlEx) {
			
		}
		
		return rs;
	}
	
	public static void main(String args[]) {
		MysqlExecutor executor = new MysqlExecutor();
		String sql = "select * from user";
		ResultSet rs = executor.query(sql);
		try {
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				System.out.println(id + "..." + name);
			}
		} catch (SQLException sqlEx) {
			
		}
	}
}
