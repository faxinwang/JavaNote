package myjava.sql;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0 {
	private static final ComboPooledDataSource ds =new ComboPooledDataSource();
	//初始化并设置连接池
	public static void init()throws Exception{
		Properties pps = new Properties();
		pps.load(new FileInputStream("./lib/myjava/sql/mysql_conn_param"));;
		String driver = pps.getProperty("driver");
		String url = pps.getProperty("url");
		String user= pps.getProperty("user");
		String pwd = pps.getProperty("pwd");
		//设置连接池连接数据库所需的驱动
		ds.setDriverClass(driver);
		//设置连接数据库的url
		ds.setJdbcUrl(url);
		//设置连接数据库的用户名和密码
		ds.setUser(user);
		ds.setPassword(pwd);
		//设置连接池的最大连接数
		ds.setMaxPoolSize(15);
		//设置连接池的最小连接数
		ds.setMinPoolSize(2);
		//设置连接池的初始连接数
		ds.setInitialPoolSize(3);
		//设置连接池的缓存Statement的最大数
		ds.setMaxStatements(20);
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
						rst.getString(3) + "\t"
						);
			}
		}
	}
	
	public static void main(String[] args)throws Exception{
		init();
		query();
		ds.close();
	}
}
