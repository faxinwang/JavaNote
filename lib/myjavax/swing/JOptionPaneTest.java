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

/* JOptionPane提供了如下4个方法来创建对话框
 * showMessageDialog/showInternalMessageDialog:
 * 		消息对话框,告知用户某事已发生,用户只能单击"确定",无返回值
 * showConfirmDialog/showInternalMessageDialog:
 * 		确认对话框,想用户确认某个问题,用户可以选择yes,no,cancel等选项.返回用于单击了那个按钮
 * showInputDialog/showInternalMessageDialog:
 * 		输入对话框,提示要求输入某些信息,返回用户输入的字符串
 * showOptionDialog/showInternalOptionDialog:
 * 		自定义选项对话框,允许使用自定义选项,可以取代showConfirmDialog所产生的对话框,只是用起来更复杂,返回一个整数代表用户的选项,
 * 		如果用户选择了第一个选项,则返回0,如果选择了第二个,则返回1...依此类推.
 */

public class JOptionPaneTest {
	private JFrame jf = new JFrame("测试JOptionPane");
	private String src ="./src/icon/";
	//定义6个面板,分别用于定义对话框的几张选项
	private ButtonPanel messagePanel;
	private ButtonPanel messageTypePanel;
	private ButtonPanel msgPanel;
	private ButtonPanel confirmPanel;
	private ButtonPanel optionsPanel;
	private ButtonPanel inputPanel;
	private String msg_String ="消息区内容";
	private Icon msg_Icon = new ImageIcon(src+"img.png");
	private Object msg_Object = new Date();
	private Component msg_Component = new JButton("组件消息");
	private JButton msgBn = new JButton("消息对话框");
	private JButton confirmBn = new JButton("确认对话框");
	private JButton inputBn = new JButton("输入对话框");
	private JButton optionBn = new JButton("选项对话框");
	
	public void init(){
		JPanel top = new JPanel();
		top.setBorder(new TitledBorder(
				new EtchedBorder(),"对话框的通用选项",TitledBorder.CENTER,TitledBorder.TOP));
		top.setLayout(new GridLayout(1,2));
		//消息类型Panel,该Panel中的选型决定对话框中的图标
		messageTypePanel = new ButtonPanel("选择消息的类型",new String[]{
			"ERROR_MESSAGE","INFORMATION_MESSAGE","WARNING_MESSAGE",
			"QUESTION_MESSAGE","PLAIN_MESSAGE"	
		});
		//消息内容类型Panel,该Panel中的选项决定对话框消息区的内容
		messagePanel = new ButtonPanel("选择消息内容的类型",new String[]{
			"字符串消息","图标消息","组件消息","普通对象消息","Object[]消息"
		});
		top.add(messageTypePanel);
		top.add(messagePanel);
		
		JPanel bottom = new JPanel();
		bottom.setBorder(new TitledBorder( new EtchedBorder(),
				"弹出不同的对话框",TitledBorder.CENTER,TitledBorder.TOP));
		bottom.setLayout(new GridLayout(1,4));
		//创建用于弹出消息对话框的Panel
		msgPanel = new ButtonPanel("消息对话框",null);
		msgBn.addActionListener(new ShowAction());
		msgPanel.add(msgBn);
		//创建用于弹出确认对话框的Panel
		confirmPanel = new ButtonPanel("确认对话框",new String[]{
			"DEFAULT_OPTION","YES_NO_OPTION","OK_CANCEL_OPTION","YES_NO_CANCEL_OPTION"
		});
		confirmBn.addActionListener(new ShowAction());
		confirmPanel.add(confirmBn);
		//创建用于弹出输入对话框的Panel
		inputPanel = new ButtonPanel("输入对话框",new String[]{
			"单行文本框","下拉列表文本框"
		});
		inputBn.addActionListener(new ShowAction());
		inputPanel.add(inputBn);
		//创建用于弹出选项对话框的Panel
		optionsPanel = new ButtonPanel("选项对话框",new String[]{
			"字符串选项","图标选项","对象选项"
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
	//根据用户选择返回选型类型
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
	//根据用户选择返回消息
	private Object getMessage(){
		switch(messagePanel.getSelection()){
		case "字符串消息": 	return msg_String;
		case "图标消息":	return msg_Icon;
		case "组件消息":	return msg_Component;
		case "普通对象消息":return msg_Object;
		default:
			return new Object[]{msg_String,msg_Icon,msg_Object,msg_Component};
		}
	}
	//根据用户选择返回消息类型(决定图标区的图标)
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
		case "字符串选项":
			return new String[]{"宝","贝","别","哭"};
		case "图标选项":
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
	
	//为各按钮定义事件监听器
	private class ShowAction implements ActionListener{
		public void actionPerformed(ActionEvent evt){
			switch(evt.getActionCommand()){
			case "确认对话框":
				JOptionPane.showConfirmDialog(jf, getMessage());
				break;
			case "输入对话框":
				if(inputPanel.getSelection().equals("单行文本框")){
					JOptionPane.showInputDialog(jf,getMessage(),"输入对话框",getDialogType());
				}else{
					JOptionPane.showInputDialog(jf,getMessage(),"输入对话框",getDialogType(),null,
						new String[]{"这一程,情深缘浅","下雨时总会想起你","被时光移动的城市"},"恋恋相忘");
				}
				break;
			case "消息对话框":
				JOptionPane.showMessageDialog(jf, getMessage(),"消息对话框",getDialogType());
				break;
			case "选项对话框":
				JOptionPane.showOptionDialog(jf, getMessage(), "选项对话框", getOptionType(), getDialogType(), null, getOptions(), "initialValue");
			}
		}
	}
	
	
	@SuppressWarnings("serial")
	//定义一个JPanel类的扩展类,该类的对象包含多个纵向排列的
	//JRadioButton控件,且Panel扩展类可以指定一个字符串作为TitledBorder
	class ButtonPanel extends JPanel{
		//定义一个方法,用于返回用户选择的选项
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
