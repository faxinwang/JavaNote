package myjava.net.UDPChat;

import java.net.SocketAddress;

public class UserInfo {
	//定义用户图标
	private String icon;
	//用户名字
	private String name;
	//该用户的MulticastSocket所在的IP地址和端口
	private SocketAddress address;
	//该用户失去联系次数
	private int lost;
	//该用户对应的交谈窗口
	private ChatFrame chatFrame;
	public UserInfo(){}
	public UserInfo(String name,String icon,SocketAddress address,int lost){
		this.icon = icon;
		this.name = name;
		this.address = address;
		this.lost = lost;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SocketAddress getAddress() {
		return address;
	}
	public void setAddress(SocketAddress address) {
		this.address = address;
	}
	public int getLost() {
		return lost;
	}
	public void setLost(int lost) {
		this.lost = lost;
	}

	public ChatFrame getChatFrame() {
		return chatFrame;
	}
	public void setChatFrame(ChatFrame chatFrame) {
		this.chatFrame = chatFrame;
	}
	
	//使用Addrss作为该用户的标识,所以根据address重写
	//hashCode()和equals()方法
	public boolean equals(Object obj){
		if(obj!=null && obj.getClass()==UserInfo.class){
			UserInfo target=(UserInfo)obj;
			if(address!=null){
				return address.equals(target.getAddress());
			}
		}
		return false;
	}
	public int hashCode(){
		return address.hashCode();
	}
}
