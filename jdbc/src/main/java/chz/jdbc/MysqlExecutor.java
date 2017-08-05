package chz.jdbc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class MysqlExecutor {
	
	public DataSource createDataSource() {
		String url = "jdbc:mysql://127.0.0.1:3306/test";
		String user = "root";
		String password = "root";
		MysqlDataSource ds = new MysqlDataSource();
		try {
			ds.setLogWriter(new PrintWriter(System.out));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ds.setURL(url);
		ds.setUser(user);
		ds.setPassword(password);
		return ds;
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
		MysqlExecutor mysqlUtil = new MysqlExecutor();
		String sql = "select * from user";
		ResultSet rs = mysqlUtil.query(sql);
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
