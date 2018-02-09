package myjava.net.chat;

public interface MyProtocal {
	//定义协议字符串的长度
	int PROTOCAL_LEN= 2;
	//下面是一些协议字符串,服务器和客户端交换信息都应该这前后添加这种特殊字符串
	String MSG_ROUND="☞";
	String USER_ROUND="☜";
	String LOGIN_SUCCESS="✔";
	String NAME_REP="㏂";
	String PRIVATE_ROUND="☆";
	String SPLIT_SIGN="※";
	String ONLINE="○";
}
