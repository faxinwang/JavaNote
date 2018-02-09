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

/* CachedRowSet�ṩ�����·����������Ʒ�ҳ��
 * populate(ResultSet rst,int startRow):�Ӹ�����ResultSet�ĵ�startRow��ʼ��װcachedRowSet
 * setPageSize(int pageSize):����cachedRowSetÿ�η��ض�������¼
 * previousPage():�ڵײ�ResultSet���õ������,��CachedRowSet��ȡ��һҳ��¼
 * nextPage():�ڵײ�ResultSet���õ������,��CachedRowSet��ȡ��һҳ��¼
 */


public class CachedRowSetPage {
	private String driver;
	private String url;
	private String user;
	private String pwd;
	private static final int pageSize = 10;
	
	public void init()throws Exception{
		Properties pps = new Properties();
		pps.load(new FileInputStream("./lib/myjava/sql/mysql_conn_param"));
		driver = pps.getProperty("driver");
		url = pps.getProperty("url");
		user =pps.getProperty("user");
		pwd = pps.getProperty("pwd");
		Class.forName(driver);
	}
	public CachedRowSet query(String sql,int pageSize,int page)throws Exception{
		try(
			Connection conn = DriverManager.getConnection(url,user,pwd);
			Statement stm = conn.createStatement();
			ResultSet rst = stm.executeQuery(sql);
		){
			RowSetFactory factory = RowSetProvider.newFactory();
			CachedRowSet cachedRS = factory.createCachedRowSet();
			//����ÿҳ��ʾpageSize����¼
			cachedRS.setPageSize(pageSize);
			//ʹ��ResultSet��װRowSet,���ôӵڼ�����¼��ʼ
			cachedRS.populate(rst,(page-1)*pageSize + 1);
			return cachedRS;
		}
	}
	
	public static void main(String[] args)throws Exception{
		CachedRowSetPage test = new CachedRowSetPage();
		test.init();
		String sql="select * from tmp_tb";
		CachedRowSet cachedRS = test.query(sql, pageSize, 2);
		//�����������
		while(cachedRS.next()){
			System.out.println(cachedRS.getString(1) + "\t" +
					cachedRS.getString(2) + "\t" +
					cachedRS.getShort(3)
					);
		}
	}
}
