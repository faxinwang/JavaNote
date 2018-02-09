package myjava.sql;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0 {
	private static final ComboPooledDataSource ds =new ComboPooledDataSource();
	//��ʼ�����������ӳ�
	public static void init()throws Exception{
		Properties pps = new Properties();
		pps.load(new FileInputStream("./lib/myjava/sql/mysql_conn_param"));;
		String driver = pps.getProperty("driver");
		String url = pps.getProperty("url");
		String user= pps.getProperty("user");
		String pwd = pps.getProperty("pwd");
		//�������ӳ��������ݿ����������
		ds.setDriverClass(driver);
		//�����������ݿ��url
		ds.setJdbcUrl(url);
		//�����������ݿ���û���������
		ds.setUser(user);
		ds.setPassword(pwd);
		//�������ӳص����������
		ds.setMaxPoolSize(15);
		//�������ӳص���С������
		ds.setMinPoolSize(2);
		//�������ӳصĳ�ʼ������
		ds.setInitialPoolSize(3);
		//�������ӳصĻ���Statement�������
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
