package myjava.sql;

import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class LoginFrame {
	private final String prop_file="./lib/myjava/sql/mysql_conn_param";
	private String driver;
	private String url;
	private String user;
	private String pwd;
	//��¼��������
	private JFrame jf = new JFrame("��¼");
	private JTextField username = new JTextField(20);
	private JTextField password = new JTextField(20);
	private JButton login = new JButton("��¼");
	
	public void init()throws Exception{
		Properties props = new Properties();
		props.load(new FileInputStream(prop_file));
		driver = props.getProperty("driver");
		url = props.getProperty("url");
		user = props.getProperty("user");
		pwd =props.getProperty("pwd");
		
		Class.forName(driver);
		
		login.addActionListener(e ->{
			try {
				if(validate1(username.getText(),password.getText())){
					JOptionPane.showMessageDialog(jf, "��¼�ɹ�");
				}else{
					JOptionPane.showMessageDialog(jf, "��¼ʧ��");
				}
			} catch (Exception e1) {
				System.out.println("��ȡ�ؼ��ı�ʧ��!");
				e1.printStackTrace();
			}
		});
		jf.add(username,BorderLayout.NORTH);
		jf.add(password);
		jf.add(login,BorderLayout.SOUTH);
		jf.pack();
		jf.setVisible(true);
	}
	
	private boolean validate1(String username,String password)throws Exception{
		//���е���Ϊ��ֵ���͵ģ�����ֵʱҲ������''��ֵ������
		String sql="select * from test1 "
				+ "where name='" + username + "' and tel='"+password+"'";
		System.out.println(sql);
		try(
			Connection conn = DriverManager.getConnection(url,user,pwd);
			Statement stm = conn.createStatement();
			ResultSet rst = stm.executeQuery(sql);
		){
			//�����ѯ����г���һ����¼,���¼�ɹ�
			if(rst.next()){
				return true;
			}
		}
		return false;
	}
	@SuppressWarnings("unused")
	private boolean validate2(String username,String password)throws Exception{
		String sql = "select * from test1 where name=? and tel=?";
		try(
			Connection conn = DriverManager.getConnection(url,user,pwd);
			PreparedStatement pstm = conn.prepareStatement(sql);
		){
			pstm.setString(1,username);
			pstm.setString(2, password);
			try(
				ResultSet rst = pstm.executeQuery();	
			){
				//�����ѯ����г���һ����¼,���¼�ɹ�
				if(rst.next()){
					return true;
				}
			}
			return false;
		}
	}
	
	public static void main(String[] args)throws Exception{
		new LoginFrame().init();
	}
}
