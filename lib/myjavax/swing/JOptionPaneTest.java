package myjavax.swing;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/* JOptionPane�ṩ������4�������������Ի���
 * showMessageDialog/showInternalMessageDialog:
 * 		��Ϣ�Ի���,��֪�û�ĳ���ѷ���,�û�ֻ�ܵ���"ȷ��",�޷���ֵ
 * showConfirmDialog/showInternalMessageDialog:
 * 		ȷ�϶Ի���,���û�ȷ��ĳ������,�û�����ѡ��yes,no,cancel��ѡ��.�������ڵ������Ǹ���ť
 * showInputDialog/showInternalMessageDialog:
 * 		����Ի���,��ʾҪ������ĳЩ��Ϣ,�����û�������ַ���
 * showOptionDialog/showInternalOptionDialog:
 * 		�Զ���ѡ��Ի���,����ʹ���Զ���ѡ��,����ȡ��showConfirmDialog�������ĶԻ���,ֻ��������������,����һ�����������û���ѡ��,
 * 		����û�ѡ���˵�һ��ѡ��,�򷵻�0,���ѡ���˵ڶ���,�򷵻�1...��������.
 */

public class JOptionPaneTest {
	private JFrame jf = new JFrame("����JOptionPane");
	private String src ="./src/icon/";
	//����6�����,�ֱ����ڶ���Ի���ļ���ѡ��
	private ButtonPanel messagePanel;
	private ButtonPanel messageTypePanel;
	private ButtonPanel msgPanel;
	private ButtonPanel confirmPanel;
	private ButtonPanel optionsPanel;
	private ButtonPanel inputPanel;
	private String msg_String ="��Ϣ������";
	private Icon msg_Icon = new ImageIcon(src+"img.png");
	private Object msg_Object = new Date();
	private Component msg_Component = new JButton("�����Ϣ");
	private JButton msgBn = new JButton("��Ϣ�Ի���");
	private JButton confirmBn = new JButton("ȷ�϶Ի���");
	private JButton inputBn = new JButton("����Ի���");
	private JButton optionBn = new JButton("ѡ��Ի���");
	
	public void init(){
		JPanel top = new JPanel();
		top.setBorder(new TitledBorder(
				new EtchedBorder(),"�Ի����ͨ��ѡ��",TitledBorder.CENTER,TitledBorder.TOP));
		top.setLayout(new GridLayout(1,2));
		//��Ϣ����Panel,��Panel�е�ѡ�;����Ի����е�ͼ��
		messageTypePanel = new ButtonPanel("ѡ����Ϣ������",new String[]{
			"ERROR_MESSAGE","INFORMATION_MESSAGE","WARNING_MESSAGE",
			"QUESTION_MESSAGE","PLAIN_MESSAGE"	
		});
		//��Ϣ��������Panel,��Panel�е�ѡ������Ի�����Ϣ��������
		messagePanel = new ButtonPanel("ѡ����Ϣ���ݵ�����",new String[]{
			"�ַ�����Ϣ","ͼ����Ϣ","�����Ϣ","��ͨ������Ϣ","Object[]��Ϣ"
		});
		top.add(messageTypePanel);
		top.add(messagePanel);
		
		JPanel bottom = new JPanel();
		bottom.setBorder(new TitledBorder( new EtchedBorder(),
				"������ͬ�ĶԻ���",TitledBorder.CENTER,TitledBorder.TOP));
		bottom.setLayout(new GridLayout(1,4));
		//�������ڵ�����Ϣ�Ի����Panel
		msgPanel = new ButtonPanel("��Ϣ�Ի���",null);
		msgBn.addActionListener(new ShowAction());
		msgPanel.add(msgBn);
		//�������ڵ���ȷ�϶Ի����Panel
		confirmPanel = new ButtonPanel("ȷ�϶Ի���",new String[]{
			"DEFAULT_OPTION","YES_NO_OPTION","OK_CANCEL_OPTION","YES_NO_CANCEL_OPTION"
		});
		confirmBn.addActionListener(new ShowAction());
		confirmPanel.add(confirmBn);
		//�������ڵ�������Ի����Panel
		inputPanel = new ButtonPanel("����Ի���",new String[]{
			"�����ı���","�����б��ı���"
		});
		inputBn.addActionListener(new ShowAction());
		inputPanel.add(inputBn);
		//�������ڵ���ѡ��Ի����Panel
		optionsPanel = new ButtonPanel("ѡ��Ի���",new String[]{
			"�ַ���ѡ��","ͼ��ѡ��","����ѡ��"
		});
		optionBn.addActionListener(new ShowAction());
		optionsPanel.add(optionBn);
		
		bottom.add(msgPanel);
		bottom.add(confirmPanel);
		bottom.add(inputPanel);
		bottom.add(optionsPanel);
		Box box = new Box(BoxLayout.Y_AXIS);
		box.add(top);
		box.add(bottom);
		
		jf.add(box);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}
	//�����û�ѡ�񷵻�ѡ������
	private int getOptionType(){
		switch(confirmPanel.getSelection()){
		case "DEFAULT_OPTION":
			return JOptionPane.DEFAULT_OPTION;
		case "YES_NO_OPTION":
			return JOptionPane.YES_NO_OPTION;
		case "YES_NO_CANCEL_OPTION":
			return JOptionPane.YES_NO_CANCEL_OPTION;
		default:
			return JOptionPane.OK_CANCEL_OPTION;
		}
	}
	//�����û�ѡ�񷵻���Ϣ
	private Object getMessage(){
		switch(messagePanel.getSelection()){
		case "�ַ�����Ϣ": 	return msg_String;
		case "ͼ����Ϣ":	return msg_Icon;
		case "�����Ϣ":	return msg_Component;
		case "��ͨ������Ϣ":return msg_Object;
		default:
			return new Object[]{msg_String,msg_Icon,msg_Object,msg_Component};
		}
	}
	//�����û�ѡ�񷵻���Ϣ����(����ͼ������ͼ��)
	private int getDialogType(){
		switch(messageTypePanel.getSelection()){
		case "ERROR_MESSAGE": 		return JOptionPane.ERROR_MESSAGE;
		case "INFORMATION_MESSAGE": return JOptionPane.INFORMATION_MESSAGE;
		case "WARNING_MESSAGE": 	return JOptionPane.WARNING_MESSAGE;
		case "QUESTION_MESSAGE": 	return JOptionPane.QUESTION_MESSAGE;
		default : 					return JOptionPane.PLAIN_MESSAGE;
		}
	}
	private Object[] getOptions(){
		String src="./src/icon/";
		switch(optionsPanel.getSelection()){
		case "�ַ���ѡ��":
			return new String[]{"��","��","��","��"};
		case "ͼ��ѡ��":
			return new Icon[]{
				new ImageIcon(src+"new.png"),
				new ImageIcon(src+"ok.png"),
				new ImageIcon(src+"edit.png"),
				new ImageIcon(src+"open.png")
			};
		default :
			return new Object[]{
				LocalDate.now(),LocalTime.now(),LocalDateTime.now()
			};
		}
	}
	
	//Ϊ����ť�����¼�������
	private class ShowAction implements ActionListener{
		public void actionPerformed(ActionEvent evt){
			switch(evt.getActionCommand()){
			case "ȷ�϶Ի���":
				JOptionPane.showConfirmDialog(jf, getMessage());
				break;
			case "����Ի���":
				if(inputPanel.getSelection().equals("�����ı���")){
					JOptionPane.showInputDialog(jf,getMessage(),"����Ի���",getDialogType());
				}else{
					JOptionPane.showInputDialog(jf,getMessage(),"����Ի���",getDialogType(),null,
						new String[]{"��һ��,����Եǳ","����ʱ�ܻ�������","��ʱ���ƶ��ĳ���"},"��������");
				}
				break;
			case "��Ϣ�Ի���":
				JOptionPane.showMessageDialog(jf, getMessage(),"��Ϣ�Ի���",getDialogType());
				break;
			case "ѡ��Ի���":
				JOptionPane.showOptionDialog(jf, getMessage(), "ѡ��Ի���", getOptionType(), getDialogType(), null, getOptions(), "initialValue");
			}
		}
	}
	
	
	@SuppressWarnings("serial")
	//����һ��JPanel�����չ��,����Ķ����������������е�
	//JRadioButton�ؼ�,��Panel��չ�����ָ��һ���ַ�����ΪTitledBorder
	class ButtonPanel extends JPanel{
		//����һ������,���ڷ����û�ѡ���ѡ��
		private ButtonGroup group;
		public ButtonPanel(String title,String[] options){
			setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),title));
			setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
			group = new ButtonGroup();
			for(int i=0;options!=null && i<options.length;++i){
				JRadioButton b = new JRadioButton(options[i]);
				b.setActionCommand(options[i]);
				add(b);
				group.add(b);
				b.setSelected(i==0);
			}
		}
		public String getSelection(){
			return group.getSelection().getActionCommand();
		}
	}

	public static void main(String[] args){
		new JOptionPaneTest().init();
	}
}
