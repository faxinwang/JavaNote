package myjavax.swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class SortTable {
	JFrame jf = new JFrame("�ɰ�������ı��");
	Object[][] tableData = {
		new Object[]{"������",29,"Ů"},
		new Object[]{"�ո�����",56,"��"},
		new Object[]{"���",35,"��"},
		new Object[]{"Ū��",18,"Ů"},
		new Object[]{"��ͷ",2,"��"}
	};
	Object[] title = {"����","����","�Ա�"};
	JTable table = new JTable(tableData,title);
	SortableTableModel sorterModel = new SortableTableModel(table.getModel());
	public void init(){
		//ʹ�ð�װ���SortableTableModel������ΪJTable��Model����
		table.setModel(sorterModel);
		//ÿ�е���ͷ�����������¼� 
		table.getTableHeader().addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				//�������˫��,��ֱ�ӷ���
				if(evt.getClickCount()<2) return ;
				//�ҳ����˫�����ڵ�������
				int tableCol = table.columnAtPoint(evt.getPoint());
				//��Table�е�������ת���ɶ�ӦTableModle�е�������
				int modelCol = table.convertColumnIndexToModel(tableCol);
				//����ָ���н�������
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
		//��ԭTableModel�е�ÿ�м�¼��������Row���鱣������
		for(int i=0;i<rows.length;++i){
			rows[i] = new Row(i);
		}
	}
	
	private class Row implements Comparable<Row>{
		//��index�����ű���װmodel��ÿ�м�¼��������
		public int index;
		public Row(int i){
			index =i;
		}
		//ʵ������֮��Ĵ�С�Ƚ�
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

	//ʵ�ָ���ָ��������
	public void sort(int c){
		sortColumn =c;
		java.util.Arrays.sort(rows);
		fireTableDataChanged();
	}
	/* ��������������Ҫ����model�е�����,�����漰��modle�е����ݺ�
	 * ����װ��Model�����е�����ת��,����ʹ��rows�����������ת��
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
	
	//���淽����ʵ�ְѸ�model�ķ���ί�и�ԭ��װ��model��ʵ��
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
