package myjavax.swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class SortTable {
	JFrame jf = new JFrame("可按列排序的表格");
	Object[][] tableData = {
		new Object[]{"李清照",29,"女"},
		new Object[]{"苏格拉底",56,"男"},
		new Object[]{"李白",35,"男"},
		new Object[]{"弄玉",18,"女"},
		new Object[]{"虎头",2,"男"}
	};
	Object[] title = {"姓名","年龄","性别"};
	JTable table = new JTable(tableData,title);
	SortableTableModel sorterModel = new SortableTableModel(table.getModel());
	public void init(){
		//使用包装后的SortableTableModel对象作为JTable的Model对象
		table.setModel(sorterModel);
		//每列的列头增加鼠标监听事件 
		table.getTableHeader().addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				//如果不是双加,则直接返回
				if(evt.getClickCount()<2) return ;
				//找出鼠标双击所在的列索引
				int tableCol = table.columnAtPoint(evt.getPoint());
				//将Table中的列索引转换成对应TableModle中的列索引
				int modelCol = table.convertColumnIndexToModel(tableCol);
				//根据指定列进行排序
				sorterModel.sort(modelCol);
			}
		});
		jf.add(new JScrollPane(table));
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
	
	public static void main(String[] args){
		new SortTable().init();
	}
}

@SuppressWarnings("serial")
class SortableTableModel extends AbstractTableModel{
	private TableModel model;
	private int sortColumn;
	private Row[] rows;
	public SortableTableModel(TableModel m){
		model=m;
		rows = new Row[model.getRowCount()];
		//将原TableModel中的每行记录的索引用Row数组保存起来
		for(int i=0;i<rows.length;++i){
			rows[i] = new Row(i);
		}
	}
	
	private class Row implements Comparable<Row>{
		//该index保存着被封装model里每行记录的行索引
		public int index;
		public Row(int i){
			index =i;
		}
		//实现两行之间的大小比较
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public int compareTo(Row o) {
			Object a = model.getValueAt(index, sortColumn);
			Object b = model.getValueAt(o.index, sortColumn);
			if(a instanceof Comparable){
				return ((Comparable)a).compareTo(b);
			}else{
				return a.toString().compareTo(b.toString());
			}
		}
	}

	//实现根据指定列排序
	public void sort(int c){
		sortColumn =c;
		java.util.Arrays.sort(rows);
		fireTableDataChanged();
	}
	/* 下面三个方法需要访问model中的数据,所以涉及本modle中的数据和
	 * 被包装的Model数据中的索引转换,程序使用rows数组完成这种转换
	 */
	@Override
	public Object getValueAt(int r, int c) {
		return model.getValueAt(rows[r].index, c);
	}
	@Override
	public boolean isCellEditable(int r,int c){
		return model.isCellEditable(rows[r].index, c);
	}
	@Override
	public void setValueAt(Object value,int r,int c){
		model.setValueAt(value, rows[r].index, c);
	}
	
	//下面方法的实现把该model的方法委托该原封装的model来实现
	@Override
	public int getRowCount() {
		return model.getRowCount();
	}
	
	@Override
	public int getColumnCount() {
		return model.getColumnCount();
	}
	
	@Override
	public Class<?> getColumnClass(int c){
		return model.getColumnClass(c);
	}
	
	@Override
	public String getColumnName(int c){
		return model.getColumnName(c);
	}
}
