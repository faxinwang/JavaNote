package myjava.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/* JDBC编程步骤:
 * 1加载驱动
 *   Class.forName(String driverClass);
 * 2.通过DriverManager获取数据库连接
 *   DriverMananger.getConnection(String url,String user,String pwd);
 * 3.通过Connection对象创建Statement对象。
 *   createStatement():创建基本的Statement对象.
 *   prepareStatement(String sql):根据传入的sql语句创建预编译的Statement对象.
 *   prepareCall(Strin sql):根据传入的sql语句创建CallableStatement对象.
 * 4.使用Statement对象来执行sql语句，所有的Statement对象都有如下三个方法来执行sql语句.
 *   execute():可以执行任何sql语句，但比较麻烦.
 *   executeUpdate():主要用于执行DML,DDL语句，分别返回受到影响的行数和0.
 *   executeQuery():只能执行查询语句，然后代表查询结果的结果集ResultSet.
 * 5.操作结果集.程序可以通过操作ResultSet对象来取出查询结果。ResultSet对象主要提供如下两类方法.
 *   next(),previous(),first(),last(),beforeFirst(),afterLast(),absolute()等移动记录指针的方法
 *   getXxx(int idx),getXxx(String colName)放回指定行，特定列的值.
 * 6.回收数据库资源,包括关闭ResultSet,Statement和Connection等资源.
 */

public class ConnMySQL {
	
	public static void main(String[] args)throws Exception{
		//1加载驱动，使用了反射知识
		Class.forName("com.mysql.jdbc.Driver");
		try(
			//2.使用DriverManager获取数据库连接
			Connection conn = 
				DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC",
					"wfx","");
			//3.使用Connection来创建一个Statement对象
			Statement stm = conn.createStatement();
			//执行sql语句。
			ResultSet rst = stm.executeQuery("select * from test2");
		){
			while(rst.next()){
				System.out.println(rst.getInt(1)	+"\t" +
								rst.getString(2)	+"\t" + 
								rst.getString(3)	+"\t" +
								rst.getInt(4)		+"\t" +
								rst.getString(5) 	+"\t" +
								rst.getInt(6) 		+"\t" +
								rst.getString(7)) ;
			}
		}
	}
}
