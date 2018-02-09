package myjavax.swing;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class JListTest {
	private JFrame jf=new JFrame("测试列表项");
	String[] books = new String[]{
		"疯狂java EE企业应用实战",
		"疯狂android讲义",
		"疯狂Ajax讲义",
		"疯狂java讲义",
		"经典Java EE企业应用实战"
	};
	//用一个字符串数组来创建一个JList对象
	JList<String> bookList = new JList<>(books);
	JComboBox<String> bookSelector;
	//定义布局选择按钮所在面板
	JPanel layoutPanel = new JPanel();
	ButtonGroup layoutGroup =new ButtonGroup();
	//定义选择模式按钮所在的面板
	JPanel selectModePanel = new JPanel();
	ButtonGroup selectModeGroup = new ButtonGroup();
	JTextArea favoriate = new JTextArea(4,40);
	public void init(){
		//设置JList的可视高度可同时显示3个列表项
		bookList.setVisibleRowCount(4);
		//默认选中第3到第5项(第一项索引是0)
		bookList.setSelectionInterval(2,4);
		addLayoutButton("纵向滚动",JList.VERTICAL);
		addLayoutButton("纵向换行",JList.VERTICAL_WRAP);
		addLayoutButton("横向换行",JList.HORIZONTAL_WRAP);
		addSelectModelButton("无限制",ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		addSelectModelButton("单选",ListSelectionModel.SINGLE_SELECTION);
		addSelectModelButton("单范围",ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		Box listBox = new Box(BoxLayout.Y_AXIS);
		listBox.add(new JScrollPane(bookList));
		listBox.add(layoutPanel);
		listBox.add(selectModePanel);
		//为JList添加事件监听器
		bookList.addListSelectionListener( evt ->{
			List<String> books = bookList.getSelectedValuesList();
			favoriate.setText("");
			for(String bk:books){
				favoriate.append(bk + "\n");
			}
		});
		Vector<String> bookColl = new Vector<>();
		bookColl.add("疯狂java讲义");
		bookColl.add("轻量级javaEE企业应用实战");
		bookColl.add("疯狂android讲义");
		bookColl.add("疯狂Ajax讲义");
		bookColl.add("经典JavaEE企业应用实战");
		//用一个Vector来创建一个JComboBox对象
		bookSelector = new JComboBox<String>(bookColl);
		bookSelector.addActionListener(evt ->{
			Object book = bookSelector.getSelectedItem();
			favoriate.setText(book.toString());
		});
		//设置可以直接编辑
		bookSelector.setEditable(true);
		//设置下拉列表框的可视高度可同时显示4个列表项
		bookSelector.setMaximumRowCount(4);
		JPanel p = new JPanel();
		p.add(bookSelector);
		Box box = new Box(BoxLayout.X_AXIS);
		box.add(listBox);
		box.add(p);
		jf.add(box);
		JPanel favoriatePanel = new JPanel();
		favoriatePanel.setLayout(new BorderLayout());
		favoriatePanel.add(new JScrollPane(favoriate));
		favoriatePanel.add(new JLabel("你喜欢的图书:"),BorderLayout.NORTH);
		jf.add(favoriatePanel,BorderLayout.SOUTH);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}
	
	private void addLayoutButton(String label,final int orientation){
		layoutPanel.setBorder(new TitledBorder(new EtchedBorder(),"确定布局选项"));
		JRadioButton jrb =new JRadioButton(label);
		//把该单选按钮添加到LayoutPane中
		layoutPanel.add(jrb);
		//默认选中第一个按钮
		layoutGroup.add(jrb);
		if(layoutGroup.getButtonCount()==1)
			jrb.setSelected(true);
		jrb.addActionListener(event ->bookList.setLayoutOrientation(orientation));
	}
	private void addSelectModelButton(String label,final int selectModel){
		selectModePanel.setBorder(new TitledBorder(new EtchedBorder(),"确定选择模式"));
		JRadioButton jrb = new JRadioButton(label);
		//把该单选按钮添加到selectModePanel中
		selectModePanel.add(jrb);
		//默认选中第一个按钮
		selectModeGroup.add(jrb);
		if(selectModeGroup.getButtonCount()==1)
			jrb.setSelected(true);
		layoutGroup.add(jrb);
		jrb.addActionListener(evt -> bookList.setSelectionMode(selectModel));
	}
	public static void main(String[] args){
		new JListTest().init();
	}
}
