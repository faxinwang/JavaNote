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
	private JFrame jf=new JFrame("�����б���");
	String[] books = new String[]{
		"���java EE��ҵӦ��ʵս",
		"���android����",
		"���Ajax����",
		"���java����",
		"����Java EE��ҵӦ��ʵս"
	};
	//��һ���ַ�������������һ��JList����
	JList<String> bookList = new JList<>(books);
	JComboBox<String> bookSelector;
	//���岼��ѡ��ť�������
	JPanel layoutPanel = new JPanel();
	ButtonGroup layoutGroup =new ButtonGroup();
	//����ѡ��ģʽ��ť���ڵ����
	JPanel selectModePanel = new JPanel();
	ButtonGroup selectModeGroup = new ButtonGroup();
	JTextArea favoriate = new JTextArea(4,40);
	public void init(){
		//����JList�Ŀ��Ӹ߶ȿ�ͬʱ��ʾ3���б���
		bookList.setVisibleRowCount(4);
		//Ĭ��ѡ�е�3����5��(��һ��������0)
		bookList.setSelectionInterval(2,4);
		addLayoutButton("�������",JList.VERTICAL);
		addLayoutButton("������",JList.VERTICAL_WRAP);
		addLayoutButton("������",JList.HORIZONTAL_WRAP);
		addSelectModelButton("������",ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		addSelectModelButton("��ѡ",ListSelectionModel.SINGLE_SELECTION);
		addSelectModelButton("����Χ",ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		Box listBox = new Box(BoxLayout.Y_AXIS);
		listBox.add(new JScrollPane(bookList));
		listBox.add(layoutPanel);
		listBox.add(selectModePanel);
		//ΪJList����¼�������
		bookList.addListSelectionListener( evt ->{
			List<String> books = bookList.getSelectedValuesList();
			favoriate.setText("");
			for(String bk:books){
				favoriate.append(bk + "\n");
			}
		});
		Vector<String> bookColl = new Vector<>();
		bookColl.add("���java����");
		bookColl.add("������javaEE��ҵӦ��ʵս");
		bookColl.add("���android����");
		bookColl.add("���Ajax����");
		bookColl.add("����JavaEE��ҵӦ��ʵս");
		//��һ��Vector������һ��JComboBox����
		bookSelector = new JComboBox<String>(bookColl);
		bookSelector.addActionListener(evt ->{
			Object book = bookSelector.getSelectedItem();
			favoriate.setText(book.toString());
		});
		//���ÿ���ֱ�ӱ༭
		bookSelector.setEditable(true);
		//���������б��Ŀ��Ӹ߶ȿ�ͬʱ��ʾ4���б���
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
		favoriatePanel.add(new JLabel("��ϲ����ͼ��:"),BorderLayout.NORTH);
		jf.add(favoriatePanel,BorderLayout.SOUTH);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}
	
	private void addLayoutButton(String label,final int orientation){
		layoutPanel.setBorder(new TitledBorder(new EtchedBorder(),"ȷ������ѡ��"));
		JRadioButton jrb =new JRadioButton(label);
		//�Ѹõ�ѡ��ť��ӵ�LayoutPane��
		layoutPanel.add(jrb);
		//Ĭ��ѡ�е�һ����ť
		layoutGroup.add(jrb);
		if(layoutGroup.getButtonCount()==1)
			jrb.setSelected(true);
		jrb.addActionListener(event ->bookList.setLayoutOrientation(orientation));
	}
	private void addSelectModelButton(String label,final int selectModel){
		selectModePanel.setBorder(new TitledBorder(new EtchedBorder(),"ȷ��ѡ��ģʽ"));
		JRadioButton jrb = new JRadioButton(label);
		//�Ѹõ�ѡ��ť��ӵ�selectModePanel��
		selectModePanel.add(jrb);
		//Ĭ��ѡ�е�һ����ť
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
