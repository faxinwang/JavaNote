package myjava.sql;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Properties;

public class TransactionTest {
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
	public void insertInTransaction()throws Exception{
		try(
			Connection conn = DriverManager.getConnection(url,user,pwd);
		){
			//�ر��Զ��ύ����������
			conn.setAutoCommit(false);
			Savepoint point=null;
			try(
				Statement stm = conn.createStatement();
			){
				stm.executeUpdate("insert into my_test(Name,Age) values('test1',18)");
				stm.executeUpdate("insert into my_test(Name,Age) values('test2',19)");
				stm.executeUpdate("insert into my_test(Name,Age) values('test3',20)");
				//�ڴ˴�����һ���м��
				point = conn.setSavepoint(); 
				stm.executeUpdate("insert into my_test(Name,Age) values('test4',21)");
				//���������佫����������Ϊ����1�Ѿ�����
				stm.executeUpdate("insert into my_test values(1,'test4',21)");
			}catch(SQLException e){
				System.out.println(e.getMessage());
				//�ع���ָ���м���
				conn.rollback(point);
			}
			//�ύ����
			conn.commit();
		}
	}
	
	public static void main(String[] args)throws Exception{
		TransactionTest test = new TransactionTest();
		test.init();
		test.insertInTransaction();
	}
}
