package myjavax.swing;

import java.awt.BorderLayout;
import java.awt.event.InputEvent;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

public class SwingComponent {
	String src = "./src/icon/";
	JFrame jf = new JFrame("����");
	//����һ����ť,��Ϊ��ָ��ͼ��
	Icon okIcon = new ImageIcon(src+"ok.png");
	JButton okBt = new JButton("ȷ��",okIcon);
	//����һ����ѡ��ť,��ʼ����ѡ��״̬
	JRadioButton maleBt = new JRadioButton("��",true);
	JRadioButton femaleBt = new JRadioButton("Ů",false);
	ButtonGroup bg = new ButtonGroup();
	//����һ����ѡ��,��ʼ����û��ѡ��״̬
	JCheckBox married = new JCheckBox("�Ƿ��ѻ�?",false);
	String[] colors = new String[]{"��ɫ","��ɫ","��ɫ"};
	//����һ������ѡ���
	JComboBox<String> colorChooser = new JComboBox<>(colors);
	//����һ��ѡ���б��
	JList<String> colorList = new JList<>(colors);
	//����һ��8��,20�еĶ����ı���
	JTextArea jta = new JTextArea(8,20);
	//����һ��40�еĵ����ı���
	JTextField jtf = new JTextField(40);
	JMenuBar jmb = new JMenuBar();
	JMenu file_mu = new JMenu("�ļ�");
	JMenu edit_mu  =new JMenu("�༭");
	//����"�½�"�˵���,��Ϊָ֮��ͼ��
	Icon newIcon = new ImageIcon(src+"new.png");
	JMenuItem new_item = new JMenuItem("�½�",newIcon);
	//��������˵���,��Ϊָ֮��ͼ��
	Icon saveIcon = new ImageIcon(src+"save.png");
	JMenuItem save_item = new JMenuItem("����",saveIcon);
	//�����˳��˵���,��Ϊָ֮��ͼ��
	Icon exitIcon = new ImageIcon(src+"exit.png");
	JMenuItem exit_item = new JMenuItem("�˳�",exitIcon);
	
	JCheckBoxMenuItem autoWrap = new JCheckBoxMenuItem("�Զ�����");
	//�������Ʋ˵���,��λ��ָ��ͼ��
	JMenuItem copy_item = new JMenuItem("����",new ImageIcon(src+"copy.png"));
	JMenuItem paste_item =new JMenuItem("ճ��",new ImageIcon(src+"paste.png"));
	
	JMenu format_mu = new JMenu("��ʽ");
	JMenuItem comment_item = new JMenuItem("ע��");
	JMenuItem uncomment_item = new JMenuItem("ȡ��ע��");
	
	//����һ���Ҽ��˵��������ó�����
	JPopupMenu pop = null;
	
	/*********************ִ�н����ʼ��*********************************/
	public void init(){
		//����һ��װ�����ı���,��ť��JPanel
		JPanel bottom = new JPanel();
		bottom.add(jtf);
		bottom.add(okBt);
		jf.add(bottom,BorderLayout.SOUTH);
		
		//����һ��װ���������б��,����JCheckBox��JPanel
		JPanel checkPanel = new JPanel();
		checkPanel.add(colorChooser);
		bg.add(maleBt);
		bg.add(femaleBt);
		checkPanel.add(maleBt);
		checkPanel.add(femaleBt);
		checkPanel.add(married);
		
		//����һ����ֱ���е�Box���,ʢװ�����ı���JPanel
		Box topLeft = Box.createVerticalBox();
		topLeft.add(new JScrollPane(jta));
		topLeft.add(checkPanel);
		//����һ��˵���������Box,ʢװtopLeft,colorList
		Box top = Box.createHorizontalBox();
		top.add(topLeft);
		top.add(colorList);
		//��top������ӵ���������
		jf.add(top);
		//------------------���濪ʼ��ϲ˵�------------------------//
		//Ϊnew_item�˵������ÿ�ݼ�,���ÿ�ݼ�ʱҪʹ�ô�д��ĸ
		new_item.setAccelerator(KeyStroke.getKeyStroke('N',InputEvent.CTRL_MASK));
		//Ϊfile_mu��Ӳ˵���
		file_mu.add(new_item);
		file_mu.add(save_item);
		file_mu.add(exit_item);
		//Ϊedit_mu��Ӳ˵���
		edit_mu.add(autoWrap);
		edit_mu.addSeparator();//��Ӳ˵��ָ���
		edit_mu.add(copy_item);
		edit_mu.add(paste_item);
		//Ϊcomment_item��������ʾ��Ϣ
		comment_item.setToolTipText("���������ע������");
		//Ϊformat_mu��Ӳ˵���
		format_mu.add(comment_item);
		format_mu.add(uncomment_item);
		//ʹ�����new JMenuItem("-")�ķ�ʽ������Ӳ˵��ָ���
		edit_mu.add(new JMenuItem("-"));
		//��format_mu��ӵ�edit_mu�˵���,�Ӷ��γɶ����˵�
		edit_mu.add(format_mu);
		//��file_mu,edit_mu��ӵ�mbar�˵�����
		jmb.add(file_mu);
		jmb.add(edit_mu);
		//Ϊjf�������ò˵���
		jf.setJMenuBar(jmb);
		
		MyUIManager manager = new MyUIManager(jf,jmb);
		//���һ�������˵������޸ĳ��������
//		pop.add(manager.getJPopupMenu());
		pop = manager.getJPopupMenu();
		//���ø÷������������Ҽ��˵�,����ʹ���¼�����
		jta.setComponentPopupMenu(pop);
		//���ùرմ���ʱ�˳�����
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setResizable(false);
		jf.setVisible(true);
		
		add_actions();
	}

	public void add_actions(){
		//Ϊnew_item����¼�������
		new_item.addActionListener(ac->{
			jta.append("�û�������\"�½�\"�˵���\n");
		});
	}

	static void getInstalledLookAndFeel(){
		System.out.println("��ǰϵͳ���õ�����LAF:");
		for(UIManager.LookAndFeelInfo info:UIManager.getInstalledLookAndFeels()){
			System.out.println(info.getName()+"----->" +info);
		}
	}
	
	public static void main(String[] args){
		//����Swing����ʹ��Java���
	//	JFrame.setDefaultLookAndFeelDecorated(true);
		getInstalledLookAndFeel();
		new SwingComponent().init();
	}
}
