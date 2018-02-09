package myjavax.swing;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

/* Swing�ṩ��JToolBar������������,
 * JToolBar(Sting name,int orientation):ָ�������������ƺͷ���
 * ������������,JToolBar�ṩ�����³��÷���:
 * JButton add(Action a):ͨ��Action����ΪJToolBar��Ӷ�Ӧ�Ĺ��߰�ť
 * void setFloatable(boolean b):���ù������Ƿ�ɸ���
 * void addSeparator(Dimension size):��ӷָ���
 * void setMargin(Insets m):���ù��߰�ť�빤����֮��ı߾�
 * void setOrientation(int o):���ù���������
 * void setRollover(boolean rollover):���ù�������rollover״̬
 */

public class JToolBarTest {
	JFrame jf = new JFrame("���Թ�����");
	JTextArea jta = new JTextArea(6,35);
	JToolBar jtb = new JToolBar();
	JMenuBar jmb = new JMenuBar();
	JMenu edit_mu = new JMenu("�༭");
	//��ȡϵͳ������
	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	//����"ճ��"Action,��action���ڴ����˵���,���߰�ť����ͨ��ť
	@SuppressWarnings("serial")
	Action pasteAction = new AbstractAction("ճ��",new ImageIcon("./src/icon/paste.png")){
		@Override
		public void actionPerformed(ActionEvent evt) {
			//����������а���stringFlavor����
			if(clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)){
				try{
					//ȡ���������е�����
					String content = (String)clipboard.getData(DataFlavor.stringFlavor);
					//��ѡ�������滻�ɼ������е�����
					jta.replaceRange(content,jta.getSelectionStart(),jta.getSelectionEnd());
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	};
	
	//����"����"action
	@SuppressWarnings("serial")
	Action copyAction  = new AbstractAction("����",new ImageIcon("./src/icon/copy.png")){
		@Override
		public void actionPerformed(ActionEvent e) {
			StringSelection contents = new StringSelection(jta.getSelectedText());
			//��StringSelection��������������
			clipboard.setContents(contents, null);
			//����������а���stringFlavor����,�ͽ�pasteAction����
			if(clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)){
				pasteAction.setEnabled(true);
			}
		}
	};
	
	public void init(){
		//����Ҽ��˵��ñ�������
		jta.setComponentPopupMenu(new MyUIManager(jf,jmb).getJPopupMenu());
		//pasteActionĬ�ϴ��ڲ�����״̬
		pasteAction.setEnabled(false);
		jf.add(new JScrollPane(jta));
		//��action������ť,������ť��ӵ�panel��
		JButton copyBn = new JButton(copyAction);
		JButton pasteBn = new JButton(pasteAction);
		JPanel jp = new JPanel();
		jp.add(copyBn);
		jp.add(pasteBn);
		jf.add(jp,BorderLayout.SOUTH);
		//�򹤾��������Action����,�ö�����Զ�ת���ɹ��߰�ť
		jtb.add(copyAction);
		jtb.addSeparator();
		jtb.add(pasteAction);
		//��˵������Action����,�ö�����Զ�ת���ɲ˵���
		edit_mu.add(copyAction);
		edit_mu.add(pasteAction);
		jmb.add(edit_mu);
		jf.setJMenuBar(jmb);
		//���ù������͹��߰�ť֮���ҳ�߾�
		jtb.setMargin(new Insets(2,3,2,2));
		//�򴰿�����ӹ�����,������Ĭ�ϴ��ڿɸ���״̬
		jf.add(jtb,BorderLayout.NORTH);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}
	
	public static void main(String[] args){
		new JToolBarTest().init();
	}
}
