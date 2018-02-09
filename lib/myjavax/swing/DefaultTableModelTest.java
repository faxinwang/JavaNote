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
	JFrame jf = new JFrame("管理数据行,数据列");
	final int COLUMN_COUNT=5;
	DefaultTableModel model;
	JTable table;
	//用于保存被掩藏列集合
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
		JMenu tableMenu = new JMenu("管理");
		jmb.add(tableMenu);
		JMenuItem hideColItem = new JMenuItem("隐藏选中列");
		hideColItem.addActionListener(evt ->{
			//获取所有选中列的索引
			int[] selected = table.getSelectedColumns();
			TableColumnModel colModel = table.getColumnModel();
			//依次把每一个选中列隐藏起来,并使用list保存这些列
			for(int i=0;i<selected.length;++i){
				TableColumn col = colModel.getColumn(selected[i]);
				table.removeColumn(col);
				hiddenColumns.add(col);
			}
		});
		tableMenu.add(hideColItem);
		JMenuItem showColItem = new JMenuItem("显示隐藏列");
		showColItem.addActionListener(evt ->{
			//把所有隐藏的列依次显示出来
			for(TableColumn tc:hiddenColumns){
				table.addColumn(tc);
			}
			//清空保存隐藏列的list
			hiddenColumns.clear();
		});
		tableMenu.add(showColItem);
		JMenuItem addColItem = new JMenuItem("插入选中列");
		addColItem.addActionListener(evt ->{
			//获取所有选中的列
			int[] selected = table.getSelectedColumns();
			TableColumnModel colModel = table.getColumnModel();
			//依次把选中的列添加到JTable之后
			for(int i=0;i<selected.length;++i){
				TableColumn col = colModel.getColumn(selected[i]);
				table.addColumn(col);
			}
		});
		tableMenu.add(addColItem);
		JMenuItem addRowItem = new JMenuItem("增加行");
		addRowItem.addActionListener(evt -> {
			//创建一个String数组作为新增的行内容
			String[] newCells = new String[COLUMN_COUNT];
			for(int i=0;i<newCells.length;++i){
				newCells[i] = "newValue" + model.getRowCount()+" "+i;
			}
			//向TableModel中增加一行
			model.addRow(newCells);
		});
		tableMenu.add(addRowItem);
		JMenuItem removeRowItem = new JMenuItem("删除选中行");
		removeRowItem.addActionListener(evt ->{
			int[] selected = table.getSelectedColumns();
			//依次删除所有选中的行
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
