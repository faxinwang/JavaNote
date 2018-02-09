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
	//���������ͨ�ŵ��첽Channel
	AsynchronousSocketChannel clientChannel;
	JFrame mainWin = new JFrame("Ⱥ�Ĵ���");
	JTextArea  jta = new JTextArea(16,48);
	JTextField jtf = new JTextField(40);
	JButton sendBn = new JButton("����");
	public void init(){
		mainWin.setLayout(new BorderLayout());
		jta.setEditable(false);
		mainWin.add(new JScrollPane(jta),BorderLayout.CENTER);
		JPanel jp = new JPanel();
		jp.add(jtf);
		jp.add(sendBn);
		
		//������Ϣ��Action,Action��ActionListener�Ľӿ�
		@SuppressWarnings("serial")
		Action sendAction  = new AbstractAction(){
			public void actionPerformed(ActionEvent event){
				String content = jtf.getText();
				try{
					//��content����д��channel��
					clientChannel.write(ByteBuffer.wrap(content.trim().getBytes("UTF-8"))).get();
				}catch(Exception e){
					e.printStackTrace();
				}
				jta.append(content +"\t" + LocalTime.now() + "\n");
				//����ı���
				jtf.setText("");
			}
		};
		sendBn.addActionListener(sendAction);
		//��"Enter"����"send"����
		jtf.getInputMap().put(KeyStroke.getKeyStroke('\n'), "send");
		//��"send"��sendAction����
		jtf.getActionMap().put("send", sendAction);
		mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWin.add(jp, BorderLayout.SOUTH);
		mainWin.setResizable(false);
		mainWin.pack();
		mainWin.setVisible(true);
	}
	
	public void connect()throws Exception{
		//����һ��ByteBuffer׼����ȡ����
		final ByteBuffer buff = ByteBuffer.allocate(1024);
		//����һ���̳߳�
		ExecutorService pool = Executors.newFixedThreadPool(30);
		//��ָ���̳߳�������AsynchronousChannelGroup
		AsynchronousChannelGroup channelGroup = 
			AsynchronousChannelGroup.withThreadPool(pool);
		//��channelGroup��Ϊ�������������AsynchronousSocketChannel
		clientChannel = AsynchronousSocketChannel.open(channelGroup);
		//��clientChannel���ӵ�ָ����IP��ַ,ָ���˿�
		clientChannel.connect(new InetSocketAddress(IP,PORT)).get();
		jta.append("---����������ӳɹ�---\t" + LocalTime.now() +"\n");
		buff.clear();
		clientChannel.read(buff,null,new CompletionHandler<Integer,Object>(){
			@Override
			public void completed(Integer result, Object attachment) {
				buff.flip();
				//��buff�е�����ת��Ϊ�ַ���
				String content = StandardCharsets.UTF_8.decode(buff).toString();
				//��ʾ�ӷ������˶�ȡ������
				jta.append("xxx:" + content +"\t" +LocalTime.now() +"\n");
				buff.clear();
				clientChannel.read(buff, null, this);
			}

			@Override
			public void failed(Throwable exc, Object attachment) {
				System.out.println("��ȡ����ʧ��!"+exc);
			}
		});
	}
	
	public static void main(String[] args)throws Exception{
		AClient client = new AClient();
		client.init();
		client.connect();
	}
}
