package myjava.net.AIOChat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class AClient {
	final static int PORT = 30000;
	final static String IP ="172.25.1.125";
	//与服务器端通信的异步Channel
	AsynchronousSocketChannel clientChannel;
	JFrame mainWin = new JFrame("群聊窗口");
	JTextArea  jta = new JTextArea(16,48);
	JTextField jtf = new JTextField(40);
	JButton sendBn = new JButton("发送");
	public void init(){
		mainWin.setLayout(new BorderLayout());
		jta.setEditable(false);
		mainWin.add(new JScrollPane(jta),BorderLayout.CENTER);
		JPanel jp = new JPanel();
		jp.add(jtf);
		jp.add(sendBn);
		
		//发送消息的Action,Action是ActionListener的接口
		@SuppressWarnings("serial")
		Action sendAction  = new AbstractAction(){
			public void actionPerformed(ActionEvent event){
				String content = jtf.getText();
				try{
					//将content内容写入channel中
					clientChannel.write(ByteBuffer.wrap(content.trim().getBytes("UTF-8"))).get();
				}catch(Exception e){
					e.printStackTrace();
				}
				jta.append(content +"\t" + LocalTime.now() + "\n");
				//清空文本框
				jtf.setText("");
			}
		};
		sendBn.addActionListener(sendAction);
		//将"Enter"建和"send"关联
		jtf.getInputMap().put(KeyStroke.getKeyStroke('\n'), "send");
		//将"send"和sendAction关联
		jtf.getActionMap().put("send", sendAction);
		mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWin.add(jp, BorderLayout.SOUTH);
		mainWin.setResizable(false);
		mainWin.pack();
		mainWin.setVisible(true);
	}
	
	public void connect()throws Exception{
		//定义一个ByteBuffer准备读取数据
		final ByteBuffer buff = ByteBuffer.allocate(1024);
		//创建一个线程池
		ExecutorService pool = Executors.newFixedThreadPool(30);
		//以指定线程池来创建AsynchronousChannelGroup
		AsynchronousChannelGroup channelGroup = 
			AsynchronousChannelGroup.withThreadPool(pool);
		//以channelGroup作为组管理器来创建AsynchronousSocketChannel
		clientChannel = AsynchronousSocketChannel.open(channelGroup);
		//让clientChannel连接到指定的IP地址,指定端口
		clientChannel.connect(new InetSocketAddress(IP,PORT)).get();
		jta.append("---与服务器连接成功---\t" + LocalTime.now() +"\n");
		buff.clear();
		clientChannel.read(buff,null,new CompletionHandler<Integer,Object>(){
			@Override
			public void completed(Integer result, Object attachment) {
				buff.flip();
				//将buff中的内容转换为字符串
				String content = StandardCharsets.UTF_8.decode(buff).toString();
				//显示从服务器端读取的数据
				jta.append("xxx:" + content +"\t" +LocalTime.now() +"\n");
				buff.clear();
				clientChannel.read(buff, null, this);
			}

			@Override
			public void failed(Throwable exc, Object attachment) {
				System.out.println("读取数据失败!"+exc);
			}
		});
	}
	
	public static void main(String[] args)throws Exception{
		AClient client = new AClient();
		client.init();
		client.connect();
	}
}
