package myjavax.swing;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;

public class TableModelTest {
	JFrame jf = new JFrame("数据表管理工具");
	private JScrollPane scrollPane;
	private ResultSetTableModel model;
	//用于装载数据表的JComboBox
	private JComboBox<String> tables = new JComboBox<>();
	private JTextArea changeMsg = new JTextArea(4,80);
	private ResultSet rs;
	private Connection conn;
	private Statement stmt;
	
	public void init(){
		//为JComboBxo添加事件监听器,当用户选择某个数据表时,触发该方法
		tables.addActionListener(event ->{
			try{
				if(scrollPane != null){
					jf.remove(scrollPane);
				}
				//从JComboBox中取出用户试图管理的数据表名
				String tableName = (String)tables.getSelectedItem();
				if(rs!=null){
					rs.close();
				}
				String query = "select * from "+ tableName;
				//查询用户选择的数据表
				rs = stmt.executeQuery(query);
				//使用查询到的ResutlSet创建TableModel对象
				model = new ResultSetTableModel(rs);
				//为tableModel添加监听器,监听用户的修改
				model.addTableModelListener( evt ->{
					int row = evt.getFirstRow();
					int col = evt.getColumn();
					changeMsg.append("["+row+","+col+"] 被修改为:\""+model.getValueAt(row, col)+"\"\n");
				});
				//使用TableModel创建JTable,并将对应表格添加到窗口中
				JTable table = new JTable(model);
				scrollPane = new JScrollPane(table);
				jf.add(scrollPane,BorderLayout.CENTER);
				jf.validate();
			}catch(SQLException e){e.printStackTrace();}
		});
		JPanel p = new JPanel();
		p.add(tables);
		p.setComponentPopupMenu(new MyUIManager(jf,null).getJPopupMenu());
		jf.add(p, BorderLayout.NORTH);
		jf.add(new JScrollPane(changeMsg), BorderLayout.SOUTH);
		
		try{
			conn = getConnection();
			DatabaseMetaData meta = conn.getMetaData();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			//查询当前数据库的全部数据表
			ResultSet rs = meta.getTables(null, null, null, new String[]{"TABLE"});
			//将全部数据添加到JComboBox中
			while(rs.next()){
				System.out.println(rs.getString(1)
				+" "+rs.getString(2)+" "+rs.getString(3));
				tables.addItem(rs.getString(3));
			}
			rs.close();
		}catch(Exception e){e.printStackTrace();}
		
		jf.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent evt){
				try{
					if(conn!=null){
						conn.close();
					}
				}catch(SQLException e){e.printStackTrace();}
			}
		});
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}
	
	private static Connection getConnection()throws Exception{
		Properties pps =new Properties();
		FileInputStream fin = new FileInputStream("./lib/myjava/sql/mysql_conn_param");
		pps.load(fin);
		fin.close();
		String driver = pps.getProperty("driver");
		String url = pps.getProperty("url");
		String username = pps.getProperty("user");
		String pwd = pps.getProperty("pwd");
		Class.forName(driver);
		return DriverManager.getConnection(url,username,pwd);
	}
	
	public static void main(String[] args){
		new TableModelTest().init();
	}
}


@SuppressWarnings("serial")
class ResultSetTableModel extends AbstractTableModel{
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	public ResultSetTableModel(ResultSet rs){
		this.rs = rs;
		try{
			rsmd=rs.getMetaData();
		}catch(SQLException e){e.printStackTrace();}
	}
	
	//该方法返回getColumnName方法,用于为该TableModel设置列名
	public String getColumnName(int c){
		try{
			return rsmd.getColumnName(c+1);
		}catch(SQLException e){
			e.printStackTrace();
			return "";
		}
	}
	//重写该方法返回true,让每个单元格可编辑
	public boolean isCellEditable(int row,int col){
		//可以添加一些限定条件,仅让满足条件的单元格可编辑
		return true;
	}
	//重写setValueAt()方法,当用户编辑单元格时,将会触发该方法
	public void setValueAt(Object value,int row,int col){
		try{
			//将记录指针定位到指定行
			rs.absolute(row+1);
			//修改当前行的记录
			rs.updateObject(col+1 ,value);
			//提交修改
			rs.updateRow();
			//触发单元格的修改事件
			fireTableCellUpdated(row,col);
		}catch(SQLException e){e.printStackTrace();}
	}
	
	//用于设置该TableModel的行数
	@Override
	public int getRowCount() {
		try{
			rs.last();
			return rs.getRow();
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//用于设置该TableModel的列数
	@Override
	public int getColumnCount() {
		try{
			return rsmd.getColumnCount();
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
		
	}
	//重写该方法,用于设置该TableModel指定单元格的值
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		try{
			rs.absolute(rowIndex+1);
			return rs.getObject(columnIndex+1);
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
}