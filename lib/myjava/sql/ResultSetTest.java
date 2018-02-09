package myjava.sql;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/* ��Ĭ�Ϸ�ʽ�򿪵�ResultSet�ǲ��ɸ��µģ����ϣ�������θ��µ�ResultSet��������ڴ���Statement��
 * PreparedStatementʱ�������Ĳ���,Connection�ڴ���Statement��PreparedStatementʱ
 * ���ɶ��⴫��������������:
 * 1.ResultSetType:����Sesult������,��ȡ����ֵ
 *  	 ResultSet.TYPE_FORWARD_ONLY�ó������Ƽ�¼ָ��ֻ����ǰ�ƶ�������JDK1.4��ǰ��Ĭ��ֵ.
 *  	 ResultSet.TYPE_SCROLL_INSENSITIVE:�ó������Ƽ�¼ָ�����������ƶ�,���ײ����ݵĸı�
 *   		����Ӱ��ResultSet������.
 *  	 ResultSet.TYPE_SCROLL_SENSITIVE:�ó������Ƽ�¼ָ����������ƶ����ҵײ����ݵĸı��
 *   		Ӱ�쵽ResultSet������.
 * 2.ResultSetConcurrency:����ResultSet�Ĳ������ͣ��ò�����ȡ���³���:
 * 		 ResultSet.CONCUR_READ_ONLY:�ó���ָʾResultSet��ֻ���Ĳ���ģʽ(Ĭ��).
 * 		 ResultSet.CONCUR_UPDATEABLE:�ó�������ResultSet�ǿɸ��µĲ���ģʽ.
 *  ��Ҫע����ǣ������ɸ��µĽ��������������������������.
 *     �������ݶ�Ӧ������һ����.
 *     ѡ�������ݼ��������������.
 *     ��������ܵ��ú���
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
			//�������ƽ�����ɹ������ɸ��µ�Statement����
			Statement stm = conn.createStatement( 
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rst=stm.executeQuery(sql);
		){
			//��������ļ�¼ָ�붨λ�����һ�еĺ���
			rst.last();
			int rowCount=rst.getRow();
			for(int i = rowCount/4;i>0;--i){
				rst.absolute(i);//���������¼ָ�붨λ����i��
				System.out.println(rst.getString(1)+"\t"
						+rst.getString(2));
				//�޸ļ�¼ָ����ָ��¼�ĵڶ��е�ֵ
				rst.updateString(2, "����" + i);
				//�ύ�޸�
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
