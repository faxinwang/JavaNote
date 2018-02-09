package myjavax.swing;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;

public class JTableTest {
	JFrame jf = new JFrame("调整表格列宽");
	JMenuBar jmb = new JMenuBar();
	JMenu adjustModeMenu=new JMenu("调整方式");
	JMenu selectUintMenu=new JMenu("选择单元");
	JMenu selectModeMenu=new JMenu("选择模式");
	//定义5个单选框按钮,用于控制表格的选择方式
	JRadioButtonMenuItem[] adjustModeItems= new JRadioButtonMenuItem[5];
	//定义3个单选框按钮,用于控制表格的选择方式
	JRadioButtonMenuItem[] selectModeItems= new JRadioButtonMenuItem[3];
	JCheckBoxMenuItem rowsItem = new JCheckBoxMenuItem("选择行");
	JCheckBoxMenuItem colsItem = new JCheckBoxMenuItem("选择列");
	JCheckBoxMenuItem cellItem = new JCheckBoxMenuItem("单元格");
	ButtonGroup adjustBg = new ButtonGroup();
	ButtonGroup selectBg = new ButtonGroup();
	//定义一个int数组,用于保存表格所有的宽度调整方式
	int[] adjustModes = new int[]{
		JTable.AUTO_RESIZE_OFF,
		JTable.AUTO_RESIZE_NEXT_COLUMN,
		JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS,
		JTable.AUTO_RESIZE_LAST_COLUMN,
		JTable.AUTO_RESIZE_ALL_COLUMNS
	};
	int[] selectModes = new int[]{
		ListSelectionModel.MULTIPLE_INTERVAL_SELECTION,
		ListSelectionModel.SINGLE_INTERVAL_SELECTION,
		ListSelectionModel.SINGLE_SELECTION
	};
	JTable table;
	//定义二维数组作为表格数据
	Object[][] tableData={
		new Object[]{"李清照",29,"女"},
		new Object[]{"苏格拉底",56,"男"},
		new Object[]{"李白",35,"男"},
		new Object[]{"弄玉",18,"女"},
		new Object[]{"虎头",2,"男"}
	};
	//定义一维数组作为列标题
	Object[] colTitle={"姓名","年龄","性别"};
	
	public void init(){
		//以二维数组和一维数组来创建一个JTable对象
		table = new JTable(tableData,colTitle);
		/**********为窗口安装表格调整方式的菜单**************/
		adjustModeItems[0]=new JRadioButtonMenuItem("只调整表格");
		adjustModeItems[1]=new JRadioButtonMenuItem("只调整下一列");
		adjustModeItems[2]=new JRadioButtonMenuItem("平均调整余下列");
		adjustModeItems[3]=new JRadioButtonMenuItem("只调整最后一列");
		adjustModeItems[4]=new JRadioButtonMenuItem("平均调整所有列");
		jmb.add(adjustModeMenu);
		/**********为窗口安装设置选择方式的菜单**************************/
		selectModeItems[0]=new JRadioButtonMenuItem("无限制");
		selectModeItems[1]=new JRadioButtonMenuItem("单独的连续区");
		selectModeItems[2]=new JRadioButtonMenuItem("单选");
		jmb.add(selectModeMenu);
		/**********为窗口安装设置表格选择单元的菜单**************************/
		rowsItem.setSelected(table.getRowSelectionAllowed());
		colsItem.setSelected(table.getColumnSelectionAllowed());
		cellItem.setSelected(table.getCellSelectionEnabled());
		selectUintMenu.add(rowsItem);
		selectUintMenu.add(colsItem);
		selectUintMenu.add(cellItem);
		jmb.add(selectUintMenu);
		
		jf.setJMenuBar(jmb);
		//分别获取表格的三个表格列,并这是三列的最小宽,最佳宽,最大宽度
		TableColumn nameCol = table.getColumn(colTitle[0]);
		nameCol.setMinWidth(40);
		TableColumn ageCol = table.getColumn(colTitle[1]);
		ageCol.setPreferredWidth(50);
		TableColumn genderCol =table.getColumn(colTitle[2]);
		genderCol.setMaxWidth(50);
		jf.add(new JScrollPane(table));
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
		add_actions();
	}
	
	private void add_actions(){
		//将控制调整方式的菜单项加到菜单中
		for(int i=0;i<adjustModeItems.length;++i){
			//默认选择第三项,即对应表格默认的宽度调整方式
			if(i==2){
				adjustModeItems[i].setSelected(true);
			}
			adjustBg.add(adjustModeItems[i]);
			adjustModeMenu.add(adjustModeItems[i]);
			final int idx = i;
			adjustModeItems[i].addActionListener(evt->{
				if(adjustModeItems[idx].isSelected()){
					table.setAutoResizeMode(adjustModes[idx]);
				}
			});
		}
		//将控制表格选择方式的菜单项添加到菜单中
		for(int i=0;i<selectModeItems.length;++i){
			//默认选择第一项,即表格的默认选择方式
			if(i==0){
				selectModeItems[i].setSelected(true);
			}
			selectBg.add(selectModeItems[i]);
			selectModeMenu.add(selectModeItems[i]);
			final int idx = i;
			selectModeItems[i].addActionListener(evt ->{
				if(selectModeItems[idx].isSelected()){
					table.getSelectionModel().setSelectionMode(selectModes[idx]);
				}
			});
		}
		
		rowsItem.addActionListener(evt ->{
			table.clearSelection();
			//如果该菜单项处于选中状态,设置表格的选择单元是行
			table.setRowSelectionAllowed(rowsItem.isSelected());
			//如果选择行,选择列同时被选中,其实质是选择单元格
			cellItem.setSelected(table.getCellSelectionEnabled());
		});
		colsItem.addActionListener(evt ->{
			table.clearSelection();
			//如果该菜单项处于选中状态,设置表格的选择单元是列
			table.setColumnSelectionAllowed(colsItem.isSelected());
			//如果选择行,选择列同时被选中,其实质是选择单元格
			cellItem.setSelected(table.getCellSelectionEnabled());
		});
		cellItem.addActionListener(evt ->{
			table.clearSelection();
			//如果该菜单项处于选中状态,设置表格的选择单元是单元格
			table.setCellSelectionEnabled(cellItem.isSelected());
			//该选项的改变会同时影响选择行,选择列两个菜单
			rowsItem.setSelected(table.getRowSelectionAllowed());
			colsItem.setSelected(table.getColumnSelectionAllowed());
		});
	}
	
	public static void main(String[] arsg){
		new JTableTest().init();
	}
}
