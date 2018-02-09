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
 * 	 |--------|---->CachedRowSet(����RowSet)
 * 						|-------->WebRowSet
 * 									 |-------->JoinRowSet
 * 									 |-------->FilteredRowSet
 * 
 * Java 7������RowSetProvider���RowSetFactory�ӿڣ�����RowSetProvider�ฺ�𴴽�
 * RowSetFactory,��RowSetFactory���ṩ�����·���������RowSetʵ��:
 * CachedRowSet crearteCachedRowSet():����һ��Ĭ�ϵ�CachedRowSet
 * FilteredRowSet createFilteredRowSet():����һ��Ĭ�ϵ�FilteredRowSet
 * JdbcRowSet createJdbcRowSet():����һ��Ĭ�ϵ�JdbcRowSet
 * JoinRowSet createJoinRowSet():����һ��Ĭ�ϵ�JoinRowSet
 * WebRowSet createWebRowSet():����һ��Ĭ�ϵ�WebRowSet
 * ͨ��ʹ��RowSetFactory�Ϳ��԰�Ӧ�ó�����RowSetʵ������뿪������ֱ��ʹ��JdbcRowSetImpl�ȷǹ���
 * ��API,Ҳ�������ں��ڵ���������չ.
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
		//����RowSet����
		RowSetFactory factory = RowSetProvider.newFactory();
		String sql="select * from tmp_tb";
		try(
			//�ù�������RowSet
			JdbcRowSet jdbcRS = factory.createJdbcRowSet();
		){
			//���ñ�Ҫ��������Ϣ
			jdbcRS.setUrl(url);
			jdbcRS.setUsername(user);
			jdbcRS.setPassword(pwd);
			jdbcRS.setCommand(sql);
			//ִ��SQL��ѯ���
			jdbcRS.execute();
			jdbcRS.afterLast();
			//��ǰ���������
			while(jdbcRS.previous()){
				System.out.println(jdbcRS.getString(1)  + "\t" +
						jdbcRS.getString(2) 			+ "\t" +
						jdbcRS.getInt(3)
						);
				if(jdbcRS.getInt(3)%5==0){
					//�޸�ָ����¼����
					//�����������������ļ�¼��Ϊ0
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
