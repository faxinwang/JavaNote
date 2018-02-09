package myjava.sql;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/* 以默认方式打开的ResultSet是不可更新的，如果希望创建课更新的ResultSet，则必须在创建Statement或
 * PreparedStatement时传入额外的参数,Connection在创建Statement或PreparedStatement时
 * 还可额外传入如下两个参数:
 * 1.ResultSetType:控制Sesult的类型,可取如下值
 *  	 ResultSet.TYPE_FORWARD_ONLY该常量控制记录指正只能往前移动，这是JDK1.4以前的默认值.
 *  	 ResultSet.TYPE_SCROLL_INSENSITIVE:该常量控制记录指正可以自由移动,但底层数据的改变
 *   		不会影响ResultSet的内容.
 *  	 ResultSet.TYPE_SCROLL_SENSITIVE:该常量控制记录指针可以自由移动，且底层数据的改变会
 *   		影响到ResultSet的内容.
 * 2.ResultSetConcurrency:控制ResultSet的并发类型，该参数可取如下常量:
 * 		 ResultSet.CONCUR_READ_ONLY:该常量指示ResultSet是只读的并发模式(默认).
 * 		 ResultSet.CONCUR_UPDATEABLE:该常量控制ResultSet是可更新的并发模式.
 *  需要注意的是，创建可更新的结果集还必须满足如下两个条件.
 *     所有数据都应该来自一个表.
 *     选出的数据集必须包含主键列.
 *     结果集不能调用函数
 */

public class ResultSetTest {
	private String driver;
	private String url;
	private String user;
	private String pwd;
	
	public void init()throws Exception{
		Properties pps = new Properties();
		pps.load(new FileInputStream("./lib/myjava/sql/mysql_conn_param"));
		driver = pps.getProperty("driver");
		url = pps.getProperty("url");
		user= pps.getProperty("user");
		pwd = pps.getProperty("pwd");
		
		Class.forName(driver);
	}
	public void query()throws Exception{
		String sql="select * from tmp_tb";
		try(
			Connection conn = DriverManager.getConnection(url,user,pwd);
			//创建控制结果集可滚动，可更新的Statement对象
			Statement stm = conn.createStatement( 
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rst=stm.executeQuery(sql);
		){
			//将结果集的记录指针定位到最后一行的后面
			rst.last();
			int rowCount=rst.getRow();
			for(int i = rowCount/4;i>0;--i){
				rst.absolute(i);//将结果集记录指针定位到第i行
				System.out.println(rst.getString(1)+"\t"
						+rst.getString(2));
				//修改记录指针所指记录的第二列的值
				rst.updateString(2, "姓名" + i);
				//提交修改
				rst.updateRow();
			}
		}
	}
	
	
	public static void main(String[] args)throws Exception{
		ResultSetTest test = new ResultSetTest();
		test.init();
		test.query();
	}
}
