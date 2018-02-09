package myjavax.swing;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class DefaultListModelTest {
	private JFrame jf =new JFrame();
	private JList<String> bookList;
	private DefaultListModel<String> bookModel=new DefaultListModel<String>();
	private JTextField jtf =new JTextField(20);
	private JButton removeBn = new JButton("删除选中图书");
	private JButton addBn = new JButton("添加指定图书");
	
	public void init(){
		bookModel.addElement("疯狂java讲义");
		bookModel.addElement("轻量级javaEE企业应用实战");
		bookModel.addElement("疯狂android讲义");
		bookModel.addElement("疯狂Ajax讲义");
		bookModel.addElement("经典javaEE企业应用实战");
		//根据DefaultListModel对象创建一个JList对象
		bookList = new JList<String>(bookModel);
		bookList.setVisibleRowCount(4);
		//设置只能单选
		bookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//为添加按钮添加事件监听器
		addBn.addActionListener(evt->{
			if(jtf.getText().trim().length()!=0){
				//向bookModel中添加一个元素
				//系统会自动向JList中添加一个对应的列表项
				bookModel.addElement(jtf.getText().trim());
			}
		});
		//为删除按钮添加事件监听器
		removeBn.addActionListener(evt ->{
			//如果用于已经选中一项
			int idx=bookList.getSelectedIndex();
			if(idx>=0){
				//从bookModel中删除指定索引出的元素
				//系统会自动删除JList对应的列表项
				bookModel.removeElementAt(idx);
			}
		});
		JPanel p = new JPanel();
		p.add(jtf);
		p.add(addBn);
		p.add(removeBn);
		jf.add(new JScrollPane(bookList));
		jf.add(p,BorderLayout.SOUTH);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}

	public static void main(String[] args){
		new DefaultListModelTest().init();
	}
}
