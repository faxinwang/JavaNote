package myjavax.swing;

import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

class Book{
	private String name;
	private Icon imageIcon;
	private String description;
	public Book(String name,Icon icon,String desc){
		this.name = name;
		imageIcon = icon;
		description = desc;
	}
	public Icon getIcon(){
		return imageIcon;
	}
	public String getDesc(){
		return description;
	}
	public String toString(){
		return name;
	}
}

public class JSplitPaneTest {
	String src = "./src/img/";
	Book[] books = new Book[]{
		new Book("疯狂java讲义",new ImageIcon(src+"wfx.jpg"),"国内关于java编程最全面的图书\n看得懂,学的会"),
		new Book("轻量级Java EE企业应用实战",new ImageIcon(src+"ljq.jpg"),"SSH整合开发的图书\n你值得拥有"),
		new Book("疯狂Android讲义",new ImageIcon(src+"qzj.jpg"),"全面介绍Android平台应用程序\n开发的各方面知识")
	};
	JFrame jf = new JFrame("测试JSplitPane");
	JList<Book> bookList = new JList<>(books);
	JLabel bookCover = new JLabel();
	JTextArea bookDesc = new JTextArea();
	
	public void init(){
		//为三个组件设置最佳大小
		bookList.setPreferredSize(new Dimension(150,300));
		bookCover.setPreferredSize(new Dimension(300,150));
		bookDesc.setPreferredSize(new Dimension(300,150));
		//为下拉列表添加事件监听器
		bookList.addListSelectionListener(event ->{
			Book bk = bookList.getSelectedValue();
			bookCover.setIcon(bk.getIcon());
			bookDesc.setText(bk.getDesc());
		});
		//创建一个垂直分布的面板
		JSplitPane left = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				true,bookCover,new JScrollPane(bookDesc));
		//打开"一触即展"特性
		left.setOneTouchExpandable(true);
		//设置分隔条的大小
		//left.setDividerSize(50);
		//设置该分割面板根据所包含组件的最佳大小来调整布局
		left.resetToPreferredSizes();
		//创建一个水平分割面板
		//将left组件放在左边,bookList组件放在右边
		JSplitPane content = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,left,bookList);
		jf.add(content);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}
	
	public static void main(String[] args){
		new JSplitPaneTest().init();
	}
}
