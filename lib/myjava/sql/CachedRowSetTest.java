package myjava.sql;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class CachedRowSetTest {
	private static String driver;
	private static String url;
	private static String user;
	private static String pwd;
	
	public void init()throws Exception{
		Properties pps = new Properties();
		pps.load(new FileInputStream("./lib/myjava/sql/mysql_conn_param"));
		driver = pps.getProperty("driver");
		url = pps.getProperty("url");
		user = pps.getProperty("user");
		pwd = pps.getProperty("pwd");
		Class.forName(driver);
	}
	public CachedRowSet query(String sql)throws Exception{
		Connection conn = DriverManager.getConnection(url,user,pwd);
		Statement stm = conn.createStatement();
		ResultSet rst = stm.executeQuery(sql);
		RowSetFactory factory = RowSetProvider.newFactory();
		CachedRowSet cachedRS = factory.createCachedRowSet();
		//ʹ��ResultSet��װCachedRowSet
		cachedRS.populate(rst);
		//�ر�������Դ
		rst.close();
		stm.close();
		conn.close();
		return cachedRS;
	}
	
	public static void main(String[] args)throws Exception{
		CachedRowSetTest test = new CachedRowSetTest();
		test.init();
		String sql = "select * from tmp_tb";
		CachedRowSet cachedRS = test.query(sql);
		//��ǰ���������
		cachedRS.afterLast();
		while(cachedRS.previous()){
			System.out.println(cachedRS.getString(1) + "\t" +
						cachedRS.getString(2) + "\t" +
						cachedRS.getString(3)
						);
			if(cachedRS.getInt(1)%5==0){
				//�޸�ָ����¼��
				cachedRS.updateInt("age", 1);
				cachedRS.updateRow();
			}
		}
		//���»�ȡ���ݿ�����
		Connection conn = DriverManager.getConnection(url,user,pwd);
		conn.setAutoCommit(false);
		//�Ѷ�RowSet�������޸�ͬ�����ײ����ݿ�
		cachedRS.acceptChanges(conn);
	}
}
