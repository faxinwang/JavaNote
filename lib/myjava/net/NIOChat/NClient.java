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
	//������SocketChannel��Selector����
	private Selector selector = null;
	static final int PORT = 30000;
	private final String IP="172.25.1.125";
	//���崦�����ͽ�����ַ���
	private Charset charset = Charset.forName("UTF-8");
	//�ͻ���SocketChannel
	private SocketChannel sc = null;
	
	/********UI���***************/
	JFrame mainWin = new JFrame("Ⱥ�Ĵ���");
	JTextArea jta = new JTextArea(16,48);
	JTextField jtf = new JTextField(40);
	JButton sendBn = new JButton("����");
	//��ʼ��������
	private void  init_ui(){
		mainWin.setLayout(new BorderLayout());
		jta.setEditable(false);
		mainWin.add(new JScrollPane(jta),BorderLayout.CENTER);
		JPanel jp = new JPanel();
		jp.add(jtf);
		jp.add(sendBn);
		//������Ϣ��Action,Action��ActionListener���ӽӿ�
		@SuppressWarnings("serial")
		Action sendAction = new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent event) {
				String content = jtf.getText();
				try{
					//��content����д��channel��
					sc.write(ByteBuffer.wrap(content.trim().getBytes("UTF-8")));
				}catch(Exception e){
					e.printStackTrace();
				}
				//���ı��������׷�ӵ�jta
				jta.append(jtf.getText() + "\t" + LocalTime.now() +"\n");
				//��������
				jtf.setText("");
				
			}
		};
		sendBn.addActionListener(sendAction);
		//��"Ctrl+Enter"����"send"����
		jtf.getInputMap().put(
			KeyStroke.getKeyStroke('\n',java.awt.event.InputEvent.CTRL_MASK),"send");
		//��"send"��sendAction����
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
		//����open��̬�����������ӵ�ָ��������SocketChannel
		sc = SocketChannel.open(isa);
		//���ø�sc�Է�������ʽ����
		sc.configureBlocking(false);
		//��SocketChannel����ע�ᵽSelelctor������
		sc.register(selector, SelectionKey.OP_READ);
		//������ȡ�����������ݵ��߳�
		new ClientThread().start();
/*		
		//��������������
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			//��������������������SocketChannel��
			sc.write(charset.encode(line));
		}
		scanner.close();
*/
	}

	//�����ȡ�����������ݵ��߳�
	private class ClientThread extends Thread{
		public void run(){
			try{
				while(selector.select()>0){
					//����ÿ���п��õ�IO������Channel��Ӧ��SelectionKey
					for(SelectionKey sk:selector.selectedKeys()){
						//ɾ�����ڴ����SelectionKey
						selector.selectedKeys().remove(sk);
						//�����sk��Ӧ��Channel���пɶ�������
						if(sk.isReadable()){
							//ʹ��NIO��ȡChannel�е�����
							SocketChannel sc = (SocketChannel)sk.channel();
							ByteBuffer buff = ByteBuffer.allocate(1024);
							String content ="";
							while(sc.read(buff) > 0){
							//	sc.read(buff);
								buff.flip();
								content += charset.decode(buff);
							}
							//��ӡ�����ȡ��������
							System.out.println("������Ϣ:" + content);
							//����ȡ����������ʾ���ı���
							jta.append(content+"\t"+LocalTime.now()+"\n");
							//Ϊ��һ�ζ�ȡ������׼��
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
