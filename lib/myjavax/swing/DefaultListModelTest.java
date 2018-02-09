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
	private JButton removeBn = new JButton("ɾ��ѡ��ͼ��");
	private JButton addBn = new JButton("���ָ��ͼ��");
	
	public void init(){
		bookModel.addElement("���java����");
		bookModel.addElement("������javaEE��ҵӦ��ʵս");
		bookModel.addElement("���android����");
		bookModel.addElement("���Ajax����");
		bookModel.addElement("����javaEE��ҵӦ��ʵս");
		//����DefaultListModel���󴴽�һ��JList����
		bookList = new JList<String>(bookModel);
		bookList.setVisibleRowCount(4);
		//����ֻ�ܵ�ѡ
		bookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//Ϊ��Ӱ�ť����¼�������
		addBn.addActionListener(evt->{
			if(jtf.getText().trim().length()!=0){
				//��bookModel�����һ��Ԫ��
				//ϵͳ���Զ���JList�����һ����Ӧ���б���
				bookModel.addElement(jtf.getText().trim());
			}
		});
		//Ϊɾ����ť����¼�������
		removeBn.addActionListener(evt ->{
			//��������Ѿ�ѡ��һ��
			int idx=bookList.getSelectedIndex();
			if(idx>=0){
				//��bookModel��ɾ��ָ����������Ԫ��
				//ϵͳ���Զ�ɾ��JList��Ӧ���б���
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
