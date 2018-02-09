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
		new Book("���java����",new ImageIcon(src+"wfx.jpg"),"���ڹ���java�����ȫ���ͼ��\n���ö�,ѧ�Ļ�"),
		new Book("������Java EE��ҵӦ��ʵս",new ImageIcon(src+"ljq.jpg"),"SSH���Ͽ�����ͼ��\n��ֵ��ӵ��"),
		new Book("���Android����",new ImageIcon(src+"qzj.jpg"),"ȫ�����Androidƽ̨Ӧ�ó���\n�����ĸ�����֪ʶ")
	};
	JFrame jf = new JFrame("����JSplitPane");
	JList<Book> bookList = new JList<>(books);
	JLabel bookCover = new JLabel();
	JTextArea bookDesc = new JTextArea();
	
	public void init(){
		//Ϊ�������������Ѵ�С
		bookList.setPreferredSize(new Dimension(150,300));
		bookCover.setPreferredSize(new Dimension(300,150));
		bookDesc.setPreferredSize(new Dimension(300,150));
		//Ϊ�����б�����¼�������
		bookList.addListSelectionListener(event ->{
			Book bk = bookList.getSelectedValue();
			bookCover.setIcon(bk.getIcon());
			bookDesc.setText(bk.getDesc());
		});
		//����һ����ֱ�ֲ������
		JSplitPane left = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				true,bookCover,new JScrollPane(bookDesc));
		//��"һ����չ"����
		left.setOneTouchExpandable(true);
		//���÷ָ����Ĵ�С
		//left.setDividerSize(50);
		//���ø÷ָ��������������������Ѵ�С����������
		left.resetToPreferredSizes();
		//����һ��ˮƽ�ָ����
		//��left����������,bookList��������ұ�
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
