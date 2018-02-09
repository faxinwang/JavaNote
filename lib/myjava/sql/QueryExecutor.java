package myjava.sql;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class QueryExecutor {
	JFrame jf = new JFrame("��ѯִ����");
	private JScrollPane scrollPane;
	private JButton exeBtn = new JButton("��ѯ");
	//�������ݲ�ѯ�����ı���
	private JTextField sqlField = new JTextField(45);
	private static Connection conn;
	private static Statement stm;
	//���þ�̬��ʼ��������ʼ��Connection,Statement����
	static{
		try{
			Properties pps = new Properties();
			pps.load(new FileInputStream("./lib/myjava/sql/mysql_conn_param"));
			String driver = pps.getProperty("driver");
			String url = pps.getProperty("url");
			String user = pps.getProperty("user");
			String pwd = pps.getProperty("pwd");
			//�������ݿ�����
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pwd);
			stm = conn.createStatement();
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	//------------------��ʼ������--------------------------
	public void init(){
		JPanel top = new JPanel();
		top.add(new JLabel("�����ѯ���!"));
		top.add(sqlField);
		top.add(exeBtn);
		//Ϊִ�а�ť�������ı�������¼�������
		exeBtn.addActionListener(new ExecListener());
		sqlField.addActionListener(new ExecListener());
		jf.add(top,BorderLayout.NORTH);
		jf.setSize(680, 480);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
	
	//���������
	class ExecListener implements ActionListener{
		public void actionPerformed(ActionEvent evt){
			//ɾ��ԭ����JTable(JTableʹ��scroll����װ)
			if(scrollPane!=null){
				jf.remove(scrollPane);
			}
			try(
				//�����û������sql�����ִ�в�ѯ
				ResultSet rst = stm.executeQuery(sqlField.getText());
			){
				//ȡ��ResultSet��MetaData
				ResultSetMetaData rsmd = rst.getMetaData();
				Vector<String> colNames = new Vector<>();
				Vector< Vector<String> > data = new Vector<>();
				//��ResultSet�����е�������ӵ�Vector����
				for(int i=1;i<=rsmd.getColumnCount();++i){
					colNames.add(rsmd.getColumnName(i));
				}
				//��ResultSet������������ӵ�Vector����
				while(rst.next()){
					Vector<String> v = new Vector<>();
					for(int i=1;i<=rsmd.getColumnCount();++i){
						v.add(rst.getString(i));
					}
					data.add(v);
				}
				//�����µ�JTable
				JTable table = new JTable(data,colNames);
				scrollPane = new JScrollPane(table);
				//����µ�Table
				jf.add(scrollPane);
				//����������
				jf.validate();
			}catch(Exception e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args){
		new QueryExecutor().init();
	}
}
