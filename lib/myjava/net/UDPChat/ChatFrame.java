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
	private JButton sendBn = new JButton("����");
	private LanTalk lanTalk= new LanTalk();
	private ComUtil comUtil =null;;
	
	public ChatFrame(UserInfo user)throws Exception{
		super("��" + user.getName() +"������");
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
				//���ı����е���Ϣ��ʾ���ı�����
				addString(content +"\t" + LocalTime.now() +"\n");
				//����Ϣ���ͳ�ȥ
				if(user.getName().equals("������")){
					comUtil.broadcast(MyProtocal.ONLINE+content+MyProtocal.ONLINE);
				}else{
					comUtil.sendSingle(content, user.getAddress());
				}
				//����ı���
				jtf.setText("");
			}
		};
		//��"send"�ؼ��ֺ�  Enter ������
		jtf.getInputMap().put(KeyStroke.getKeyStroke('\n'), "send");
		//��"send"�ؼ��ֺ� sendAction ����
		jtf.getActionMap().put("send", sendAction);
		
		sendBn.addActionListener(sendAction);
	}
	
	public void addString(String msg){
		jta.append(msg);
	}

}
