package myjava.sql;

import java.io.FileInputStream;
import java.util.Properties;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

/* ResultSet
 * 	 |
 * RowSet	JoinAble
 	 |		  |
 * 	 |--------|---->JdbcRowSet
 * 	 |--------|---->CachedRowSet(离线RowSet)
 * 						|-------->WebRowSet
 * 									 |-------->JoinRowSet
 * 									 |-------->FilteredRowSet
 * 
 * Java 7新增了RowSetProvider类和RowSetFactory接口，其中RowSetProvider类负责创建
 * RowSetFactory,而RowSetFactory则提供了如下方法来创建RowSet实例:
 * CachedRowSet crearteCachedRowSet():创建一个默认的CachedRowSet
 * FilteredRowSet createFilteredRowSet():创建一个默认的FilteredRowSet
 * JdbcRowSet createJdbcRowSet():创建一个默认的JdbcRowSet
 * JoinRowSet createJoinRowSet():创建一个默认的JoinRowSet
 * WebRowSet createWebRowSet():创建一个默认的WebRowSet
 * 通过使用RowSetFactory就可以把应用程序与RowSet实现类分离开，避免直接使用JdbcRowSetImpl等非公开
 * 的API,也更有利于后期的升级和扩展.
 */

public class RowSetFactoryTest {
	private String driver;
	private String url;
	private String user;
	private String pwd;
	
	public RowSetFactoryTest init()throws Exception{
		Properties pps = new Properties();
		pps.load(new FileInputStream("./lib/myjava/sql/mysql_conn_param"));
		driver = pps.getProperty("driver");
		url = pps.getProperty("url");
		user = pps.getProperty("user");
		pwd = pps.getProperty("pwd");
		Class.forName(driver);
		return this;
	}
	public void update()throws Exception{
		//创建RowSet工厂
		RowSetFactory factory = RowSetProvider.newFactory();
		String sql="select * from tmp_tb";
		try(
			//用工厂创建RowSet
			JdbcRowSet jdbcRS = factory.createJdbcRowSet();
		){
			//设置必要的连接信息
			jdbcRS.setUrl(url);
			jdbcRS.setUsername(user);
			jdbcRS.setPassword(pwd);
			jdbcRS.setCommand(sql);
			//执行SQL查询语句
			jdbcRS.execute();
			jdbcRS.afterLast();
			//向前滚动结果集
			while(jdbcRS.previous()){
				System.out.println(jdbcRS.getString(1)  + "\t" +
						jdbcRS.getString(2) 			+ "\t" +
						jdbcRS.getInt(3)
						);
				if(jdbcRS.getInt(3)%5==0){
					//修改指定记录的行
					//将年龄列满足条件的记录该为0
					jdbcRS.updateInt(3,0);
					jdbcRS.updateRow();
				}
			}
		}
	}
	
	public static void main(String[] args)throws Exception{
		new RowSetFactoryTest().init().update();
	}
}
