package myjava.net.UDPChat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class LoginFrame {
	private JFrame jf ;
	private JTextField username = new JTextField(25);
	private JTextField usericon = new JTextField(25);
	private JButton login = new JButton("登录");
	private LanTalk lanTalk;
	
	public LoginFrame(LanTalk talk,String title)throws Exception{
		this.lanTalk = talk;
		jf = new JFrame(title);
		username.setText("用户名");
		usericon.setText("头像名");
		jf.add(username,BorderLayout.NORTH);
		jf.add(usericon,BorderLayout.CENTER);
		jf.add(login,BorderLayout.SOUTH);
		jf.pack();
		jf.setVisible(true);
		jf.setResizable(false);
		add_listener();
	}
	
	private void add_listener(){
		login.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = username.getText().trim();
				String icon = usericon.getText().trim();
				InetSocketAddress address=new InetSocketAddress("localhost",ComUtil.BROADCAST_PORT);
				lanTalk.addUser(new UserInfo(name,icon,address,0));
				lanTalk.setVisible(true);
				jf.dispose();
			}
		});
	}
}
