package myjava.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/* JDBC��̲���:
 * 1��������
 *   Class.forName(String driverClass);
 * 2.ͨ��DriverManager��ȡ���ݿ�����
 *   DriverMananger.getConnection(String url,String user,String pwd);
 * 3.ͨ��Connection���󴴽�Statement����
 *   createStatement():����������Statement����.
 *   prepareStatement(String sql):���ݴ����sql��䴴��Ԥ�����Statement����.
 *   prepareCall(Strin sql):���ݴ����sql��䴴��CallableStatement����.
 * 4.ʹ��Statement������ִ��sql��䣬���е�Statement��������������������ִ��sql���.
 *   execute():����ִ���κ�sql��䣬���Ƚ��鷳.
 *   executeUpdate():��Ҫ����ִ��DML,DDL��䣬�ֱ𷵻��ܵ�Ӱ���������0.
 *   executeQuery():ֻ��ִ�в�ѯ��䣬Ȼ������ѯ����Ľ����ResultSet.
 * 5.���������.�������ͨ������ResultSet������ȡ����ѯ�����ResultSet������Ҫ�ṩ�������෽��.
 *   next(),previous(),first(),last(),beforeFirst(),afterLast(),absolute()���ƶ���¼ָ��ķ���
 *   getXxx(int idx),getXxx(String colName)�Ż�ָ���У��ض��е�ֵ.
 * 6.�������ݿ���Դ,�����ر�ResultSet,Statement��Connection����Դ.
 */

public class ConnMySQL {
	
	public static void main(String[] args)throws Exception{
		//1����������ʹ���˷���֪ʶ
		Class.forName("com.mysql.jdbc.Driver");
		try(
			//2.ʹ��DriverManager��ȡ���ݿ�����
			Connection conn = 
				DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC",
					"wfx","");
			//3.ʹ��Connection������һ��Statement����
			Statement stm = conn.createStatement();
			//ִ��sql��䡣
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
