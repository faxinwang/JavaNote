package myjavax.swing;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class DefaultTableModelTest {
	JFrame jf = new JFrame("����������,������");
	final int COLUMN_COUNT=5;
	DefaultTableModel model;
	JTable table;
	//���ڱ��汻�ڲ��м���
	ArrayList<TableColumn> hiddenColumns = new ArrayList<>();
	public void init(){
		model = new DefaultTableModel(COLUMN_COUNT,COLUMN_COUNT);
		for(int i=0;i<COLUMN_COUNT;++i){
			for(int j=0;j<COLUMN_COUNT;++j){
				model.setValueAt("oldValue:"+i+" "+j,i,j);
			}
		}
		table =new JTable(model);
		jf.add(new JScrollPane(table));
		JMenuBar jmb = new JMenuBar();
		jf.setJMenuBar(jmb);
		JMenu tableMenu = new JMenu("����");
		jmb.add(tableMenu);
		JMenuItem hideColItem = new JMenuItem("����ѡ����");
		hideColItem.addActionListener(evt ->{
			//��ȡ����ѡ���е�����
			int[] selected = table.getSelectedColumns();
			TableColumnModel colModel = table.getColumnModel();
			//���ΰ�ÿһ��ѡ������������,��ʹ��list������Щ��
			for(int i=0;i<selected.length;++i){
				TableColumn col = colModel.getColumn(selected[i]);
				table.removeColumn(col);
				hiddenColumns.add(col);
			}
		});
		tableMenu.add(hideColItem);
		JMenuItem showColItem = new JMenuItem("��ʾ������");
		showColItem.addActionListener(evt ->{
			//���������ص���������ʾ����
			for(TableColumn tc:hiddenColumns){
				table.addColumn(tc);
			}
			//��ձ��������е�list
			hiddenColumns.clear();
		});
		tableMenu.add(showColItem);
		JMenuItem addColItem = new JMenuItem("����ѡ����");
		addColItem.addActionListener(evt ->{
			//��ȡ����ѡ�е���
			int[] selected = table.getSelectedColumns();
			TableColumnModel colModel = table.getColumnModel();
			//���ΰ�ѡ�е�����ӵ�JTable֮��
			for(int i=0;i<selected.length;++i){
				TableColumn col = colModel.getColumn(selected[i]);
				table.addColumn(col);
			}
		});
		tableMenu.add(addColItem);
		JMenuItem addRowItem = new JMenuItem("������");
		addRowItem.addActionListener(evt -> {
			//����һ��String������Ϊ������������
			String[] newCells = new String[COLUMN_COUNT];
			for(int i=0;i<newCells.length;++i){
				newCells[i] = "newValue" + model.getRowCount()+" "+i;
			}
			//��TableModel������һ��
			model.addRow(newCells);
		});
		tableMenu.add(addRowItem);
		JMenuItem removeRowItem = new JMenuItem("ɾ��ѡ����");
		removeRowItem.addActionListener(evt ->{
			int[] selected = table.getSelectedColumns();
			//����ɾ������ѡ�е���
			for(int i=selected.length-1;i>=0;--i){
				model.removeRow(selected[i]);
			}
		});
		tableMenu.add(removeRowItem);
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
	public static void main(String[] args){
		new DefaultTableModelTest().init();
	}
}
