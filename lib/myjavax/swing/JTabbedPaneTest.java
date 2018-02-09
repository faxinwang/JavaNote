package myjavax.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;

public class JTabbedPaneTest {
	String src = "./src/img/";
	JFrame jf =new JFrame("����Tabҳ��");
	//����һ��Tabҳ��ı�ǩ�������,���û��в��ֲ���
	JTabbedPane tabPane = new JTabbedPane(JTabbedPane.LEFT,JTabbedPane.WRAP_TAB_LAYOUT);
	ImageIcon icon = new ImageIcon("./src/icon/open.png");
	String[] layouts = {"���в���","����������"};
	String[] positions={"���","����","�ұ�","�ײ�"};
	Map<String,String> books = new LinkedHashMap<>();
	
	public void init(){
		books.put("���Java����","wfx.jpg");
		books.put("������Java EE��ҵӦ��ʵս", "ljq.jpg");
		books.put("���Ajax����", "sz.jpg");
		books.put("���Android����","ljf.jpg");
		books.put("����Java EE��ҵӦ��ʵս","qzj.jpg");
		String tip = "�鿴������Ƭ";
		//��JTabbedPane�����5����ǩҳ,ָ���˱���,ͼ�����ʾ,
		//���ñ�ǩҳ�����Ϊnull
		for(String bookName : books.keySet()){
			tabPane.addTab(bookName, icon,null,tip);
		}
		jf.add(tabPane,BorderLayout.CENTER);
		//ΪJTabbedPane����¼�������
		tabPane.addChangeListener(event ->{
			//�����ѡ��������Ȼ�ǿ�
			if(tabPane.getSelectedComponent()==null){
				//��ȡ��ѡ��ı�ǩҳ
				int page = tabPane.getSelectedIndex();
				loadTab(page);
			}
		});
		//ϵͳĬ��ѡ���һҳ,���ص�һҳ������
		loadTab(0);
		tabPane.setPreferredSize(new Dimension(500,300));
		//���ӿ��Ʊ�ǩҳ����,��ǩλ�õĵ�ѡ��ť
		JPanel buttonPanel = new JPanel();
		ChangeAction action= new ChangeAction();
		buttonPanel.add(new ButtonPanel(action,"ѡ�񲼾ַ�ʽ",layouts));
		buttonPanel.add(new ButtonPanel(action,"ѡ���ǩλ��",positions));
		jf.add(buttonPanel, BorderLayout.SOUTH);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}
	
	//Ϊָ����ǩҳ��������
	private void loadTab(int n){
		String title = tabPane.getTitleAt(n);
		//���ݱ�ǩҳ�ı����ȡ��Ӧͼ�����
		ImageIcon bookImage = new ImageIcon(src+books.get(title));
		System.out.println(src+books.get(title));
		tabPane.setComponentAt(n, new JLabel(bookImage));
		//�ı��ǩҳ��ͼ��
		tabPane.setIconAt(n, new ImageIcon("./src/icon/edit.png"));
	}
	
	//����ı��ǩҳ���ֲ���,����λ�õļ�����
	class ChangeAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent evt) {
			JRadioButton source = (JRadioButton)evt.getSource();
			String selection = source.getActionCommand();
			//���ñ�ǩҳ�Ĳ��ֲ���
			if(selection.equals(layouts[0])){
				tabPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
			}else if(selection.equals(layouts[1])){
				tabPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
			}
			//���ñ�ǩҳ����λ��
			else if(selection.equals(positions[0])){
				tabPane.setTabPlacement(JTabbedPane.LEFT);
			}else if(selection.equals(positions[1])){
				tabPane.setTabPlacement(JTabbedPane.TOP);
			}else if(selection.equals(positions[2])){
				tabPane.setTabPlacement(JTabbedPane.RIGHT);
			}else if(selection.equals(positions[3])){
				tabPane.setTabPlacement(JTabbedPane.BOTTOM);
			}
		}
	}
	
	@SuppressWarnings("serial")
	//����һ��JPanel��,����Ķ����������������е�JRadioButton�ؼ�
	//��JPanel��չ�����ָ��һ���ַ�����ΪTitledBorder
	class ButtonPanel extends JPanel{
		private ButtonGroup group;
		public ButtonPanel(JTabbedPaneTest.ChangeAction action,String title,String[] labels){
			setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),title));
			setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
			group = new ButtonGroup();
			for(int i=0;labels!=null && i<labels.length;++i){
				JRadioButton b =new JRadioButton(labels[i]);
				b.setActionCommand(labels[i]);
				add(b);
				//����¼�������
				b.addActionListener(action);
				group.add(b);
				b.setSelected(i==0);
			}
		}
	}
	public static void main(String[] args){
		new JTabbedPaneTest().init();
	}
}
