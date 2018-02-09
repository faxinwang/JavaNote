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
	JFrame jf = new JFrame("���ݱ������");
	private JScrollPane scrollPane;
	private ResultSetTableModel model;
	//����װ�����ݱ��JComboBox
	private JComboBox<String> tables = new JComboBox<>();
	private JTextArea changeMsg = new JTextArea(4,80);
	private ResultSet rs;
	private Connection conn;
	private Statement stmt;
	
	public void init(){
		//ΪJComboBxo����¼�������,���û�ѡ��ĳ�����ݱ�ʱ,�����÷���
		tables.addActionListener(event ->{
			try{
				if(scrollPane != null){
					jf.remove(scrollPane);
				}
				//��JComboBox��ȡ���û���ͼ��������ݱ���
				String tableName = (String)tables.getSelectedItem();
				if(rs!=null){
					rs.close();
				}
				String query = "select * from "+ tableName;
				//��ѯ�û�ѡ������ݱ�
				rs = stmt.executeQuery(query);
				//ʹ�ò�ѯ����ResutlSet����TableModel����
				model = new ResultSetTableModel(rs);
				//ΪtableModel��Ӽ�����,�����û����޸�
				model.addTableModelListener( evt ->{
					int row = evt.getFirstRow();
					int col = evt.getColumn();
					changeMsg.append("["+row+","+col+"] ���޸�Ϊ:\""+model.getValueAt(row, col)+"\"\n");
				});
				//ʹ��TableModel����JTable,������Ӧ�����ӵ�������
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
			//��ѯ��ǰ���ݿ��ȫ�����ݱ�
			ResultSet rs = meta.getTables(null, null, null, new String[]{"TABLE"});
			//��ȫ��������ӵ�JComboBox��
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
	
	//�÷�������getColumnName����,����Ϊ��TableModel��������
	public String getColumnName(int c){
		try{
			return rsmd.getColumnName(c+1);
		}catch(SQLException e){
			e.printStackTrace();
			return "";
		}
	}
	//��д�÷�������true,��ÿ����Ԫ��ɱ༭
	public boolean isCellEditable(int row,int col){
		//�������һЩ�޶�����,�������������ĵ�Ԫ��ɱ༭
		return true;
	}
	//��дsetValueAt()����,���û��༭��Ԫ��ʱ,���ᴥ���÷���
	public void setValueAt(Object value,int row,int col){
		try{
			//����¼ָ�붨λ��ָ����
			rs.absolute(row+1);
			//�޸ĵ�ǰ�еļ�¼
			rs.updateObject(col+1 ,value);
			//�ύ�޸�
			rs.updateRow();
			//������Ԫ����޸��¼�
			fireTableCellUpdated(row,col);
		}catch(SQLException e){e.printStackTrace();}
	}
	
	//�������ø�TableModel������
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
	
	//�������ø�TableModel������
	@Override
	public int getColumnCount() {
		try{
			return rsmd.getColumnCount();
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
		
	}
	//��д�÷���,�������ø�TableModelָ����Ԫ���ֵ
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