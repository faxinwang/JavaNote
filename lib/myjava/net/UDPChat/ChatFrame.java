package myjava.net.UDPChat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.time.LocalTime;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import myjava.net.chat.MyProtocal;

@SuppressWarnings("serial")
public class ChatFrame extends JFrame{
	private UserInfo user;
	private JTextArea jta=new JTextArea(16,48);
	private JTextField jtf = new JTextField(40);
	private JButton sendBn = new JButton("发送");
	private LanTalk lanTalk= new LanTalk();
	private ComUtil comUtil =null;;
	
	public ChatFrame(UserInfo user)throws Exception{
		super("与" + user.getName() +"聊天中");
		this.user = user;
		comUtil = new ComUtil(lanTalk);
		init_layout();
		add_listener();
	}
	
	void init_layout(){
		jta.setEditable(false);
		this.add(new JScrollPane(jta));
		JPanel pl = new JPanel();
		pl.add(jtf);
		pl.add(sendBn);
		this.add(pl,BorderLayout.SOUTH);
		this.setVisible(false);
		this.pack();
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	void add_listener(){
		Action sendAction = new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent evt) {
				String content = jtf.getText();
				//将文本框中的信息显示在文本域中
				addString(content +"\t" + LocalTime.now() +"\n");
				//将消息发送出去
				if(user.getName().equals("所有人")){
					comUtil.broadcast(MyProtocal.ONLINE+content+MyProtocal.ONLINE);
				}else{
					comUtil.sendSingle(content, user.getAddress());
				}
				//清空文本框
				jtf.setText("");
			}
		};
		//将"send"关键字和  Enter 键关联
		jtf.getInputMap().put(KeyStroke.getKeyStroke('\n'), "send");
		//将"send"关键字和 sendAction 关联
		jtf.getActionMap().put("send", sendAction);
		
		sendBn.addActionListener(sendAction);
	}
	
	public void addString(String msg){
		jta.append(msg);
	}

}
