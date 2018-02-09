package myjava.net.NIOChat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
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

public class NClient {
	//定义检测SocketChannel的Selector对象
	private Selector selector = null;
	static final int PORT = 30000;
	private final String IP="172.25.1.125";
	//定义处理编码和解码的字符集
	private Charset charset = Charset.forName("UTF-8");
	//客户端SocketChannel
	private SocketChannel sc = null;
	
	/********UI组件***************/
	JFrame mainWin = new JFrame("群聊窗口");
	JTextArea jta = new JTextArea(16,48);
	JTextField jtf = new JTextField(40);
	JButton sendBn = new JButton("发送");
	//初始化主界面
	private void  init_ui(){
		mainWin.setLayout(new BorderLayout());
		jta.setEditable(false);
		mainWin.add(new JScrollPane(jta),BorderLayout.CENTER);
		JPanel jp = new JPanel();
		jp.add(jtf);
		jp.add(sendBn);
		//发送消息的Action,Action是ActionListener的子接口
		@SuppressWarnings("serial")
		Action sendAction = new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent event) {
				String content = jtf.getText();
				try{
					//将content内容写入channel中
					sc.write(ByteBuffer.wrap(content.trim().getBytes("UTF-8")));
				}catch(Exception e){
					e.printStackTrace();
				}
				//将文本框的内容追加到jta
				jta.append(jtf.getText() + "\t" + LocalTime.now() +"\n");
				//清空输入框
				jtf.setText("");
				
			}
		};
		sendBn.addActionListener(sendAction);
		//将"Ctrl+Enter"键和"send"关联
		jtf.getInputMap().put(
			KeyStroke.getKeyStroke('\n',java.awt.event.InputEvent.CTRL_MASK),"send");
		//将"send"和sendAction关联
		jtf.getActionMap().put("send", sendAction);
		mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWin.add(jp, BorderLayout.SOUTH);
		mainWin.pack();
		mainWin.setResizable(false);
		mainWin.setVisible(true);
	}
	/***************************/
	
	public void init()throws IOException{
		init_ui();
		selector = Selector.open();
		InetSocketAddress isa = new InetSocketAddress(IP,PORT);
		//调用open静态方法创建连接到指定主机的SocketChannel
		sc = SocketChannel.open(isa);
		//设置该sc以非阻塞方式工作
		sc.configureBlocking(false);
		//将SocketChannel对象注册到Selelctor对象上
		sc.register(selector, SelectionKey.OP_READ);
		//启动读取服务器端数据的线程
		new ClientThread().start();
/*		
		//创建键盘输入流
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			//将键盘输入的内容输出到SocketChannel中
			sc.write(charset.encode(line));
		}
		scanner.close();
*/
	}

	//定义读取服务器端数据的线程
	private class ClientThread extends Thread{
		public void run(){
			try{
				while(selector.select()>0){
					//遍历每个有可用的IO操作的Channel对应的SelectionKey
					for(SelectionKey sk:selector.selectedKeys()){
						//删除正在处理的SelectionKey
						selector.selectedKeys().remove(sk);
						//如果该sk对应的Channel中有可读的数据
						if(sk.isReadable()){
							//使用NIO读取Channel中的数据
							SocketChannel sc = (SocketChannel)sk.channel();
							ByteBuffer buff = ByteBuffer.allocate(1024);
							String content ="";
							while(sc.read(buff) > 0){
							//	sc.read(buff);
								buff.flip();
								content += charset.decode(buff);
							}
							//打印输出读取到的内容
							System.out.println("聊天信息:" + content);
							//将读取到的内容显示到文本域
							jta.append(content+"\t"+LocalTime.now()+"\n");
							//为下一次读取数据做准备
							sk.interestOps(SelectionKey.OP_READ);
						}
					}
				}
			}catch(IOException ioe){
				System.out.println(ioe);
				ioe.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args)throws IOException{
		new NClient().init();
	}
}
