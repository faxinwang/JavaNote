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
	JFrame jf = new JFrame("��������п�");
	JMenuBar jmb = new JMenuBar();
	JMenu adjustModeMenu=new JMenu("������ʽ");
	JMenu selectUintMenu=new JMenu("ѡ��Ԫ");
	JMenu selectModeMenu=new JMenu("ѡ��ģʽ");
	//����5����ѡ��ť,���ڿ��Ʊ���ѡ��ʽ
	JRadioButtonMenuItem[] adjustModeItems= new JRadioButtonMenuItem[5];
	//����3����ѡ��ť,���ڿ��Ʊ���ѡ��ʽ
	JRadioButtonMenuItem[] selectModeItems= new JRadioButtonMenuItem[3];
	JCheckBoxMenuItem rowsItem = new JCheckBoxMenuItem("ѡ����");
	JCheckBoxMenuItem colsItem = new JCheckBoxMenuItem("ѡ����");
	JCheckBoxMenuItem cellItem = new JCheckBoxMenuItem("��Ԫ��");
	ButtonGroup adjustBg = new ButtonGroup();
	ButtonGroup selectBg = new ButtonGroup();
	//����һ��int����,���ڱ��������еĿ�ȵ�����ʽ
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
	//�����ά������Ϊ�������
	Object[][] tableData={
		new Object[]{"������",29,"Ů"},
		new Object[]{"�ո�����",56,"��"},
		new Object[]{"���",35,"��"},
		new Object[]{"Ū��",18,"Ů"},
		new Object[]{"��ͷ",2,"��"}
	};
	//����һά������Ϊ�б���
	Object[] colTitle={"����","����","�Ա�"};
	
	public void init(){
		//�Զ�ά�����һά����������һ��JTable����
		table = new JTable(tableData,colTitle);
		/**********Ϊ���ڰ�װ��������ʽ�Ĳ˵�**************/
		adjustModeItems[0]=new JRadioButtonMenuItem("ֻ�������");
		adjustModeItems[1]=new JRadioButtonMenuItem("ֻ������һ��");
		adjustModeItems[2]=new JRadioButtonMenuItem("ƽ������������");
		adjustModeItems[3]=new JRadioButtonMenuItem("ֻ�������һ��");
		adjustModeItems[4]=new JRadioButtonMenuItem("ƽ������������");
		jmb.add(adjustModeMenu);
		/**********Ϊ���ڰ�װ����ѡ��ʽ�Ĳ˵�**************************/
		selectModeItems[0]=new JRadioButtonMenuItem("������");
		selectModeItems[1]=new JRadioButtonMenuItem("������������");
		selectModeItems[2]=new JRadioButtonMenuItem("��ѡ");
		jmb.add(selectModeMenu);
		/**********Ϊ���ڰ�װ���ñ��ѡ��Ԫ�Ĳ˵�**************************/
		rowsItem.setSelected(table.getRowSelectionAllowed());
		colsItem.setSelected(table.getColumnSelectionAllowed());
		cellItem.setSelected(table.getCellSelectionEnabled());
		selectUintMenu.add(rowsItem);
		selectUintMenu.add(colsItem);
		selectUintMenu.add(cellItem);
		jmb.add(selectUintMenu);
		
		jf.setJMenuBar(jmb);
		//�ֱ��ȡ�������������,���������е���С��,��ѿ�,�����
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
		//�����Ƶ�����ʽ�Ĳ˵���ӵ��˵���
		for(int i=0;i<adjustModeItems.length;++i){
			//Ĭ��ѡ�������,����Ӧ���Ĭ�ϵĿ�ȵ�����ʽ
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
		//�����Ʊ��ѡ��ʽ�Ĳ˵�����ӵ��˵���
		for(int i=0;i<selectModeItems.length;++i){
			//Ĭ��ѡ���һ��,������Ĭ��ѡ��ʽ
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
			//����ò˵����ѡ��״̬,���ñ���ѡ��Ԫ����
			table.setRowSelectionAllowed(rowsItem.isSelected());
			//���ѡ����,ѡ����ͬʱ��ѡ��,��ʵ����ѡ��Ԫ��
			cellItem.setSelected(table.getCellSelectionEnabled());
		});
		colsItem.addActionListener(evt ->{
			table.clearSelection();
			//����ò˵����ѡ��״̬,���ñ���ѡ��Ԫ����
			table.setColumnSelectionAllowed(colsItem.isSelected());
			//���ѡ����,ѡ����ͬʱ��ѡ��,��ʵ����ѡ��Ԫ��
			cellItem.setSelected(table.getCellSelectionEnabled());
		});
		cellItem.addActionListener(evt ->{
			table.clearSelection();
			//����ò˵����ѡ��״̬,���ñ���ѡ��Ԫ�ǵ�Ԫ��
			table.setCellSelectionEnabled(cellItem.isSelected());
			//��ѡ��ĸı��ͬʱӰ��ѡ����,ѡ���������˵�
			rowsItem.setSelected(table.getRowSelectionAllowed());
			colsItem.setSelected(table.getColumnSelectionAllowed());
		});
	}
	
	public static void main(String[] arsg){
		new JTableTest().init();
	}
}
