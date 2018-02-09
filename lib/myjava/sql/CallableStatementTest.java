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
			//ʹ��Connnection����һ��CallableStatement����
			CallableStatement cstm = conn.prepareCall("{call add_pro(?,?,?)}");
		){
			cstm.setInt(1, 4);
			cstm.setInt(2, 5);
			//ע��CallableStatement�ĵ���������Ϊint���������
			cstm.registerOutParameter(3, Types.INTEGER);
			//ִ�д洢����
			cstm.execute();
			//��ȡ������洢���̴���������ֵ
			System.out.println("ִ�н��:" + cstm.getInt(3));
		}
	}
	
	public static void main(String[] args)throws Exception{
		CallableStatementTest test = new CallableStatementTest();
		test.init();
		test.callProcedure();
	}
}
