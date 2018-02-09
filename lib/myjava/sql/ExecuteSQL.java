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
		//使用Properties类来加载属性文件
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
		//执行SQL语句，返回boolean值表示是否包含ResultSet对象
		boolean hasResultSet = stm.execute(sql);	
		if(hasResultSet){
			try(
				//获取结果集
				ResultSet rst = stm.getResultSet();
			){
				//ResultSetMetaData是用于分析结果集的元数据接口
				ResultSetMetaData rsmd = rst.getMetaData();
				int cols = rsmd.getColumnCount();//获取结果集的列数
				//迭代输出ResultSet对象
				while(rst.next()){
					for(int i=0;i<cols;++i){
						System.out.print(rst.getString(i+1) + "\t");
					}
					System.out.println();
				}
			}
		}else{
			System.out.println("该SQL语句影响的记录有" + stm.getUpdateCount() + "条");
		}
	}
	
	public static void main(String[] args)throws Exception{
		//为执行sql做初始化工作
		init();
		String sql;
		
		sql="drop table if exists my_test";
		System.out.println("执行删除表的DDL语句:\n"+sql);
		executeSql(sql);
		
		sql="create table my_test("
				+ "Id int auto_increment primary key,"
				+ "Name varchar(255),"
				+ "Age int not null)";
		System.out.println("执行建表的DDL语句:\n" + sql);
		executeSql(sql);
		
		sql="insert into my_test(Name,Age) "
				+ "select Name,Age from test1";
		System.out.println("执行插入数据的DML语句:\n"+sql);
		executeSql(sql);
		
		sql="select * from my_test";
		System.out.println("执行查询的DML:\n"+sql);
		executeSql(sql);
		
		closeResource();
	}
}
