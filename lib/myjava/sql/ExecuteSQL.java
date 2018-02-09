package myjava.sql;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ExecuteSQL {
	private static String driver;
	private static String url;
	private static String user;
	private static String pwd;
	private static Connection conn;
	private static Statement stm;

	public static void init()throws Exception{
		//ʹ��Properties�������������ļ�
		Properties props = new Properties();
		try(
			FileInputStream fin = new FileInputStream("./lib/myjava/sql/mysql_conn_param");
		){
			props.load(fin);
		}
		driver = props.getProperty("driver");
		url=props.getProperty("url");
		user=props.getProperty("user");
		pwd=props.getProperty("pwd");
//		System.out.println(driver);
//		System.out.println(url);
//		System.out.println(user);
//		System.out.println(pwd);
		
		Class.forName(driver);//thorws ClassNotFoundException
		conn = DriverManager.getConnection(url,user,pwd);//throws SQLExcepton
		stm = conn.createStatement();//throws SQLException
	}
	
	public static void closeResource(){
		try{
			if(!stm.isClosed()){
				stm.close();
				System.out.println("111");
			}
			if(!conn.isClosed()){
				conn.close();
				System.out.println("222");
			}
			
		}catch(SQLException e){
			System.out.println(e.getMessage());
			System.out.println(e.getSQLState());
		}
	}
	
	public static void executeSql(String sql)throws Exception{
		//ִ��SQL��䣬����booleanֵ��ʾ�Ƿ����ResultSet����
		boolean hasResultSet = stm.execute(sql);	
		if(hasResultSet){
			try(
				//��ȡ�����
				ResultSet rst = stm.getResultSet();
			){
				//ResultSetMetaData�����ڷ����������Ԫ���ݽӿ�
				ResultSetMetaData rsmd = rst.getMetaData();
				int cols = rsmd.getColumnCount();//��ȡ�����������
				//�������ResultSet����
				while(rst.next()){
					for(int i=0;i<cols;++i){
						System.out.print(rst.getString(i+1) + "\t");
					}
					System.out.println();
				}
			}
		}else{
			System.out.println("��SQL���Ӱ��ļ�¼��" + stm.getUpdateCount() + "��");
		}
	}
	
	public static void main(String[] args)throws Exception{
		//Ϊִ��sql����ʼ������
		init();
		String sql;
		
		sql="drop table if exists my_test";
		System.out.println("ִ��ɾ�����DDL���:\n"+sql);
		executeSql(sql);
		
		sql="create table my_test("
				+ "Id int auto_increment primary key,"
				+ "Name varchar(255),"
				+ "Age int not null)";
		System.out.println("ִ�н����DDL���:\n" + sql);
		executeSql(sql);
		
		sql="insert into my_test(Name,Age) "
				+ "select Name,Age from test1";
		System.out.println("ִ�в������ݵ�DML���:\n"+sql);
		executeSql(sql);
		
		sql="select * from my_test";
		System.out.println("ִ�в�ѯ��DML:\n"+sql);
		executeSql(sql);
		
		closeResource();
	}
}
