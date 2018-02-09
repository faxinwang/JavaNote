package myjava.sql;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBCP{
	private static final BasicDataSource ds = new BasicDataSource();
	static void init()throws Exception{
		Properties pps = new Properties();
		pps.load(new FileInputStream("./lib/myjava/sql/mysql_conn_param"));
		String driver = pps.getProperty("driver");
		String url = pps.getProperty("url");
		String user = pps.getProperty("user");
		String pwd = pps.getProperty("pwd");
		//设置连接池所需的驱动
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(pwd);
		
		/*Sets the maximum total number of idle and borrows connections that 
		 * can be active at the same time. Use a negative value for no limit.
		 */
		ds.setMaxTotal(15);
		//设置初始连接数
		ds.setInitialSize(3);
		//设置最小空闲连接数
		ds.setMinIdle(2);
	}
	
	public static void query()throws Exception{
		String sql="select * from my_test";
		try(
			Connection conn = ds.getConnection();
			Statement stm = conn.createStatement();
			ResultSet rst = stm.executeQuery(sql);
		){
			while(rst.next()){
				System.out.println(rst.getString(1) + "\t" + 
						rst.getString(2) + "\t" +
						rst.getString(3)
						);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		init();
		query();
	}
}
