package myjava.sql;

import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Properties;

public class CallableStatementTest {
	private String driver;
	private String url;
	private String user;
	private String pwd;
	
	public void init()throws Exception{
		Properties pps = new Properties();
		pps.load(new FileInputStream("./lib/myjava/sql/mysql_conn_param"));
		driver = pps.getProperty("driver");
		url = pps.getProperty("url");
		user = pps.getProperty("user");
		pwd = pps.getProperty("pwd");
		
		Class.forName(driver);
	}
	public void callProcedure()throws Exception{
		try(
			Connection conn = DriverManager.getConnection(url,user,pwd);
			//使用Connnection创建一个CallableStatement对象
			CallableStatement cstm = conn.prepareCall("{call add_pro(?,?,?)}");
		){
			cstm.setInt(1, 4);
			cstm.setInt(2, 5);
			//注册CallableStatement的第三个参数为int的输出类型
			cstm.registerOutParameter(3, Types.INTEGER);
			//执行存储过程
			cstm.execute();
			//获取并输出存储过程传出参数的值
			System.out.println("执行结果:" + cstm.getInt(3));
		}
	}
	
	public static void main(String[] args)throws Exception{
		CallableStatementTest test = new CallableStatementTest();
		test.init();
		test.callProcedure();
	}
}
