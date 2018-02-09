package myjava.net.UDPChat;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.text.DateFormat;
import java.time.LocalDate;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;


@SuppressWarnings("serial")
public class LanTalk extends JFrame{
	private DefaultListModel<UserInfo> listModel= new DefaultListModel<>();
	//����һ��JList����
	private JList<UserInfo> friendsList = new JList<>(listModel);
	//����һ�����ڸ�ʽ�����ڵĸ�ʽ��
	private DateFormat formatter = DateFormat.getDateTimeInstance();
	
	public LanTalk(){
		super("����������");
		//���ø�JListʹ��ImageCellRenderer��Ϊ��Ԫ�������
		friendsList.setCellRenderer(new ImageCellRenderer());
		listModel.addElement(new UserInfo("all","������",null,-2000));
		friendsList.addMouseListener(new ChangeMusicListener());
		add(new JScrollPane(friendsList));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(2,2,160,600);
	}
	//���ݵ�ַ����ѯ�û�
	public UserInfo getUserBySocketAddress(SocketAddress address){
		for(int i=1;i<getUserNum();++i){
			UserInfo user = getUser(i);
			if(user.getAddress()!=null &&
					user.getAddress().equals(address)){
				return user;
			}
		}
		return null;
	}
	/****�����ĸ������Ƕ�ListModel�İ�װ********/
	//���û��б�������û�
	public void addUser(UserInfo user){
		listModel.addElement(user);
	}
	//���û��б���ɾ���û�
	public void removeUser(int pos){
		listModel.removeElement(pos);
		
	}
	//��ȡ�����촰�ڵ��û�����
	public int getUserNum(){
		return listModel.size();
	}
	//��ȡָ��λ�õ��û�
	public UserInfo getUser(int pos){
		return listModel.get(pos);
	}
	
	//ʵ��JList�ϵ����˫���¼�������
	class ChangeMusicListener extends MouseAdapter{
		public void mouseClicked(MouseEvent evt){
			if(evt.getClickCount() >= 2){
				//ȡ�����˫����ѡ����б���
				UserInfo user = (UserInfo)friendsList.getSelectedValue();
				if(user.getChatFrame() == null){
					//Ϊ���û�����һ����̸����,���ø��û����øô���
					try{
						user.setChatFrame(new ChatFrame(user));
					}catch(Exception e){
						System.out.println("�����Ի�����ʧ��!");
						//�˳��÷���,����������������NullPointerException
						return;
					}
					
				}
				//������û��Ĵ���û����ʾ,���ø��û��Ĵ�����ʾ����
				if(!user.getChatFrame().isShowing()){
					user.getChatFrame().setVisible(true);
				}
			}
		}
	}
	/* �������籨,�÷���������������Ϣ�õ�������
	 * ������Ϣ��ʾ������Ի�����
	 * @param pakcet ��Ҫ��������ݱ�
	 * @param single ����Ϣ�Ƿ�Ϊ˽����Ϣ
	 */
	public void processMsg(DatagramPacket packet,boolean single){
		//��ȡ�������ݱ���SocketAddress
		InetSocketAddress srcAddress = (InetSocketAddress)packet.getSocketAddress();
		//�����˽����Ϣ,���packet��ȡ����DatagramSocket�ĵ�ַ
		//���˿ںż�һ���Ƕ�Ӧ��MulticastSocket�ĵ�ַ
		if(single){
			srcAddress = new InetSocketAddress(srcAddress.getHostName(),srcAddress.getPort()-1);
			UserInfo srcUser = getUserBySocketAddress(srcAddress);
			if(srcUser!=null){
				//ȷ����Ϣ��Ҫ��ʾ���Ǹ��û���Ӧ�Ĵ���
				UserInfo alertUser = single?srcUser:getUser(0);
				//������û���Ӧ�Ĵ���Ϊ��,����ʾ�ô���
				if(alertUser.getChatFrame() == null){
					try{
						alertUser.setChatFrame(new ChatFrame(alertUser));
					}catch(Exception e){
						System.out.println("�����Ի�����ʧ��!");
						//�˳��÷���,����������������NullPointerException
						return;
					}
				}
				//������ӵ���ʾ��Ϣ
				String tipMsg = single?"����˵" : "�Դ��˵";
				try{
					//��ʾ��ʾ��Ϣ
					alertUser.getChatFrame().addString(srcUser.getName() +
							tipMsg + "........................(" +
							formatter.format( LocalDate.now() ) + ")\n" +
							new String(packet.getData(),0,packet.getLength(),ComUtil.CHARSET) + 
							"\n" );
				}catch(Exception e){e.printStackTrace();}
				if(!alertUser.getChatFrame().isShowing()){
					alertUser.getChatFrame().setVisible(true);
				}
			}
		}
	}
	
	//���ڸı�JList�б���۵���
	class ImageCellRenderer extends JPanel implements ListCellRenderer<UserInfo>{
		private ImageIcon icon;
		private String name;
		//������Ƶ�Ԫ��ʱ�ı���ɫ
		private Color background;
		//������Ƶ�Ԫ��ʱ��ǰ��ɫ
		private Color foreground;
		
		@Override
		public Component getListCellRendererComponent(JList<? extends UserInfo> list, 
				UserInfo userInfo, int index,
				boolean isSelected, boolean cellHasFocus) {
			//����ͼ��
			icon = new ImageIcon("./src/icon/" + userInfo.getIcon()+".jpg");
			name = userInfo.getName();
			//���ñ���ɫ,ǰ��ɫ
			background = isSelected?list.getSelectionBackground():list.getBackground();
			foreground = isSelected?list.getSelectionForeground():list.getForeground();
			//���ظ�JPanel������Ϊ��Ԫ�������
			return this;
		}
		
		//��дpaintComponent����,�ı�JPanel�����
		@Override
		public void paintComponent(Graphics g){
			int imageWidth = icon.getImage().getWidth(null);
			int imageHeight= icon.getImage().getHeight(null);
			g.setColor(background);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(foreground);
			//���ƺ���ͼ��
			g.drawImage(icon.getImage(), getWidth()/2 - imageWidth/2, 10, null);
			g.setFont(new Font("SansSerif",Font.BOLD,18));
			//���ƺ����û���
			g.drawString(name, getWidth()/2 - name.length()*10, imageHeight+30);
		}
		
		//ͨ���÷��������ø�ImageCellRenderer����Ѵ�С
		@Override
		public Dimension getPreferredSize(){
			return new Dimension(60,80);
		}
	}
	
	
	public static void main(String[] args)throws Exception{
		LanTalk lanTalk = new LanTalk();
		new LoginFrame(lanTalk,"�������û���,ͷ����¼!");
	}
}
