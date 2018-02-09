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
			//关闭自动提交，开启事务
			conn.setAutoCommit(false);
			Savepoint point=null;
			try(
				Statement stm = conn.createStatement();
			){
				stm.executeUpdate("insert into my_test(Name,Age) values('test1',18)");
				stm.executeUpdate("insert into my_test(Name,Age) values('test2',19)");
				stm.executeUpdate("insert into my_test(Name,Age) values('test3',20)");
				//在此处设置一个中间点
				point = conn.setSavepoint(); 
				stm.executeUpdate("insert into my_test(Name,Age) values('test4',21)");
				//下面插入语句将会引发，因为主键1已经存在
				stm.executeUpdate("insert into my_test values(1,'test4',21)");
			}catch(SQLException e){
				System.out.println(e.getMessage());
				//回滚到指定中间点出
				conn.rollback(point);
			}
			//提交事务
			conn.commit();
		}
	}
	
	public static void main(String[] args)throws Exception{
		TransactionTest test = new TransactionTest();
		test.init();
		test.insertInTransaction();
	}
}
