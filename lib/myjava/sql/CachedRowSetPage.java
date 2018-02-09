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

/* CachedRowSet提供了如下方法用来控制分页。
 * populate(ResultSet rst,int startRow):从给定的ResultSet的第startRow开始填装cachedRowSet
 * setPageSize(int pageSize):设置cachedRowSet每次返回多少条记录
 * previousPage():在底层ResultSet可用的情况下,让CachedRowSet读取上一页记录
 * nextPage():在底层ResultSet可用的情况下,让CachedRowSet读取下一页记录
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
			//设置每页显示pageSize条记录
			cachedRS.setPageSize(pageSize);
			//使用ResultSet填装RowSet,设置从第几条记录开始
			cachedRS.populate(rst,(page-1)*pageSize + 1);
			return cachedRS;
		}
	}
	
	public static void main(String[] args)throws Exception{
		CachedRowSetPage test = new CachedRowSetPage();
		test.init();
		String sql="select * from tmp_tb";
		CachedRowSet cachedRS = test.query(sql, pageSize, 2);
		//向后滚动结果集
		while(cachedRS.next()){
			System.out.println(cachedRS.getString(1) + "\t" +
					cachedRS.getString(2) + "\t" +
					cachedRS.getShort(3)
					);
		}
	}
}
