package myjava.sql;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

public class PreparedStatementTest {
	private String driver;
	private String url;
	private String user;
	private String pwd;
	
	public void init()throws Exception{
		Properties props =new Properties();
		props.load(new FileInputStream("./lib/myjava/sql/mysql_conn_param"));
		driver = props.getProperty("driver");
		url = props.getProperty("url");
		user = props.getProperty("user");
		pwd = props.getProperty("pwd");
		
		Class.forName(driver);
	}
	
	public void insertUseStatement()throws Exception{
		long start = System.currentTimeMillis();
		String sql;
		try(
			//在带圆括号的try语句里定义的资源对象，会自动关闭
			Connection conn = DriverManager.getConnection(url,user,pwd);
			Statement stm = conn.createStatement();
		){
			//删除临时表
	//		sql="drop table if exists tmp_tb";
	//		stm.execute(sql);
			//建立一个临时表
	//		sql="create table tmp_tb("
	//				+ "id int auto_increment primary key,"
	//				+ "name varchar(255),"
	//				+ "age tinyint)";
	//		stm.executeUpdate(sql);
			//删除之前插入的数据
			stm.execute("truncate tmp_tb");
			//使用100条sql语句来插入100条记录
			for(int i=0;i<100;++i){
				sql="insert into tmp_tb(name,age) "
						+ "values(" + "'name"+i+"',"+i+")";
				stm.executeUpdate(sql);
			}
			System.out.println("使用Statement用时:" + (System.currentTimeMillis()-start));
		}
	}
	
	public void insertUsePrepare()throws Exception{
		long start = System.currentTimeMillis();
		String sql="insert into tmp_tb(name,age) values(?,?)";
		try(
			Connection conn = DriverManager.getConnection(url,user,pwd);
			PreparedStatement pstm = conn.prepareStatement(sql);
		){
			//100次为PreparedStatement的参数设值，就可以插入100条记录
			for(int i=1;i<=100;++i){
				pstm.setString(1, "name"+i);//设置第一个站位符处的值
				pstm.setInt(2, i);//设置第二个占位符处的值
				pstm.executeUpdate();
			}
		}
		System.out.println("使用PreparedStatement用时:" +(System.currentTimeMillis()-start));
	}

	public static void main(String[] args)throws Exception{
		PreparedStatementTest test=new PreparedStatementTest();
		test.init();
		test.insertUseStatement();
		test.insertUsePrepare();
	}
}
/* 这是几次测试数据,可以发现当插入数据量较大时使用PreparedStatement性能有明显提升
 * 	使用Statement用时:8059
 	使用PreparedStatement用时:6364
 * 
 * 	使用Statement用时:8234
	使用PreparedStatement用时:6013
	
	使用Statement用时:8069
	使用PreparedStatement用时:6165
 * 
 */

