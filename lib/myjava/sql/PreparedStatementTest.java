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
			//�ڴ�Բ���ŵ�try����ﶨ�����Դ���󣬻��Զ��ر�
			Connection conn = DriverManager.getConnection(url,user,pwd);
			Statement stm = conn.createStatement();
		){
			//ɾ����ʱ��
	//		sql="drop table if exists tmp_tb";
	//		stm.execute(sql);
			//����һ����ʱ��
	//		sql="create table tmp_tb("
	//				+ "id int auto_increment primary key,"
	//				+ "name varchar(255),"
	//				+ "age tinyint)";
	//		stm.executeUpdate(sql);
			//ɾ��֮ǰ���������
			stm.execute("truncate tmp_tb");
			//ʹ��100��sql���������100����¼
			for(int i=0;i<100;++i){
				sql="insert into tmp_tb(name,age) "
						+ "values(" + "'name"+i+"',"+i+")";
				stm.executeUpdate(sql);
			}
			System.out.println("ʹ��Statement��ʱ:" + (System.currentTimeMillis()-start));
		}
	}
	
	public void insertUsePrepare()throws Exception{
		long start = System.currentTimeMillis();
		String sql="insert into tmp_tb(name,age) values(?,?)";
		try(
			Connection conn = DriverManager.getConnection(url,user,pwd);
			PreparedStatement pstm = conn.prepareStatement(sql);
		){
			//100��ΪPreparedStatement�Ĳ�����ֵ���Ϳ��Բ���100����¼
			for(int i=1;i<=100;++i){
				pstm.setString(1, "name"+i);//���õ�һ��վλ������ֵ
				pstm.setInt(2, i);//���õڶ���ռλ������ֵ
				pstm.executeUpdate();
			}
		}
		System.out.println("ʹ��PreparedStatement��ʱ:" +(System.currentTimeMillis()-start));
	}

	public static void main(String[] args)throws Exception{
		PreparedStatementTest test=new PreparedStatementTest();
		test.init();
		test.insertUseStatement();
		test.insertUsePrepare();
	}
}
/* ���Ǽ��β�������,���Է��ֵ������������ϴ�ʱʹ��PreparedStatement��������������
 * 	ʹ��Statement��ʱ:8059
 	ʹ��PreparedStatement��ʱ:6364
 * 
 * 	ʹ��Statement��ʱ:8234
	ʹ��PreparedStatement��ʱ:6013
	
	ʹ��Statement��ʱ:8069
	ʹ��PreparedStatement��ʱ:6165
 * 
 */

