package myjava.awt;

import java.awt.CheckboxMenuItem;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuTest {
	Frame f = new Frame("AWT:MenuTest");
	Panel pl = new Panel();
	MenuBar mb = new MenuBar();
	Menu filem = new Menu("�ļ�");
	Menu editm = new Menu("�༭");
	MenuItem newItem = new MenuItem("�½�");
	MenuItem saveItem = new MenuItem("����");
	//����exitItem�˵���,ָ��ʹ�á�Ctrl + x����ݼ�
	MenuItem exitItem = new MenuItem("�˳�",new MenuShortcut(KeyEvent.VK_X));
	CheckboxMenuItem autoWrap = new CheckboxMenuItem("�Զ�����");
	MenuItem copyItem = new MenuItem("����");
	MenuItem pastItem = new MenuItem("ճ��");
	Menu format = new Menu("��ʽ");
	//����commentItem�˵��ָ��ʹ��"ctrl+shift+/"��ݼ�
	//�ڶ�������true��ʾ��Ҫshift������
	MenuItem commentItem = new MenuItem("ע��",new MenuShortcut(KeyEvent.VK_SLASH,true));
	MenuItem cancelItem = new MenuItem("ȡ��ע��");
	TextArea txts = new TextArea(6,40);
	
	public void init(){
		ActionListener menuListener = e ->{
			String cmd = e.getActionCommand();
			txts.append("���� ��" + cmd + "�� �˵�\n" );
			if(cmd.equals("�˳�"))
				System.exit(0);
		};
		commentItem.addActionListener(menuListener);
		exitItem.addActionListener(menuListener);
		//Ϊfile�˵���Ӳ˵���
		filem.add(newItem);
		filem.add(saveItem);
		filem.add(exitItem);
		//Ϊedit�˵���Ӳ˵���
		editm.add(autoWrap);
		//ʹ��addSeparator��������Ӳ˵��ָ���
		editm.addSeparator();
		editm.add(copyItem);
		editm.add(pastItem);
		//Ϊformat�˵���Ӳ˵���
		format.add(commentItem);
		format.add(cancelItem);
		//ʹ�����new MenuItem("-")�ķ�ʽ��Ӳ˵��ָ���
		editm.add(new MenuItem("-"));
		//��format�˵���ӵ�editm�˵��У��Ӷ��γɶ����˵�
		editm.add(format);
		//��filem,editm�˵���ӵ�mb�˵�����
		mb.add(filem);
		mb.add(editm);
		//Ϊf�������ò˵���
		f.setMenuBar(mb);
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		//��txts����Panel��,��Ϊpanel�����Ҽ��˵�
		addPopMenu();
		
		f.pack();
		f.setVisible(true);
	}
	void addPopMenu(){
		GridBagLayout bgl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		pl.setLayout(bgl);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		bgl.setConstraints(txts,gbc);
		pl.add(txts);
		f.add(pl);
		//�����Ҽ��˵�
		PopupMenu pop = new PopupMenu();
		pop.add(autoWrap);
		pop.addSeparator();
		pop.add(copyItem);
		pop.add(pastItem);
		pop.add(new MenuItem("-"));
		pop.add(format);//�����˵�
		pl.add(pop);
		//�һ��ı���ʱ�������Ҽ��˵�
		txts.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e){
				//����ͷŵ�������Ҽ�
				if(e.isPopupTrigger()){
					pop.show(pl, e.getX(), e.getY());
				}
			}
		});
	}
	public static void main(String[] args){
		new MenuTest().init();
	}
}
