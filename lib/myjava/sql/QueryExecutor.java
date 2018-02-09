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
	JFrame jf = new JFrame("查询执行器");
	private JScrollPane scrollPane;
	private JButton exeBtn = new JButton("查询");
	//用于数据查询语句的文本框
	private JTextField sqlField = new JTextField(45);
	private static Connection conn;
	private static Statement stm;
	//采用静态初始化块来初始化Connection,Statement对象
	static{
		try{
			Properties pps = new Properties();
			pps.load(new FileInputStream("./lib/myjava/sql/mysql_conn_param"));
			String driver = pps.getProperty("driver");
			String url = pps.getProperty("url");
			String user = pps.getProperty("user");
			String pwd = pps.getProperty("pwd");
			//加载数据库驱动
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pwd);
			stm = conn.createStatement();
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	//------------------初始化界面--------------------------
	public void init(){
		JPanel top = new JPanel();
		top.add(new JLabel("输入查询语句!"));
		top.add(sqlField);
		top.add(exeBtn);
		//为执行按钮，单行文本框添加事件监听器
		exeBtn.addActionListener(new ExecListener());
		sqlField.addActionListener(new ExecListener());
		jf.add(top,BorderLayout.NORTH);
		jf.setSize(680, 480);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
	
	//定义监听器
	class ExecListener implements ActionListener{
		public void actionPerformed(ActionEvent evt){
			//删除原来的JTable(JTable使用scroll来包装)
			if(scrollPane!=null){
				jf.remove(scrollPane);
			}
			try(
				//根据用户输入的sql语句来执行查询
				ResultSet rst = stm.executeQuery(sqlField.getText());
			){
				//取出ResultSet的MetaData
				ResultSetMetaData rsmd = rst.getMetaData();
				Vector<String> colNames = new Vector<>();
				Vector< Vector<String> > data = new Vector<>();
				//把ResultSet所有列的列名添加到Vector里面
				for(int i=1;i<=rsmd.getColumnCount();++i){
					colNames.add(rsmd.getColumnName(i));
				}
				//把ResultSet的所有数据添加到Vector里面
				while(rst.next()){
					Vector<String> v = new Vector<>();
					for(int i=1;i<=rsmd.getColumnCount();++i){
						v.add(rst.getString(i));
					}
					data.add(v);
				}
				//创建新的JTable
				JTable table = new JTable(data,colNames);
				scrollPane = new JScrollPane(table);
				//添加新的Table
				jf.add(scrollPane);
				//更新主窗口
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
