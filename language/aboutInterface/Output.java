package aboutInterface;

/* 1.�ӿ��ﶨ������г�Ա�����Զ���Ϊ public,����:
 *   ��Ա�����Զ���Ϊ public static final,�ӿ��ﲻ���г�ʼ���飬����ֻ���ڶ���ʱ��ʼ��
 *   ��ͨ��Ա�����Զ���Ϊ public abstract
 * 2.һ���ӿڿ���ʹ��extends�ؼ��ּ̳ж���ӿڣ��̳к󽫵õ����ӿڵ����г�Ա
 * 3.һ�������ʹ��implements�ؼ���ʵ�ֶ���ӿڣ�������ֲ�java���̳еĲ���
 * */
public interface Output {
	//�ӿ��ﶨ��ĳ�Ա����ֻ���ǳ���
	int MAX_CACHE_LINE = 50;
	//�ӿ��ﶨ�����ͨ����ֻ����public�ĳ��󷽷�
	void out();
	void getData(String msg);
	
	//�ڽӿ��ﶨ���Ĭ�Ϸ�������Ҫʹ��default�ؼ�������
	default void print(String...msgs){
		for(String msg:msgs){
			System.out.println(msg);
		}
	}
	default void test(){
		System.out.println("Ĭ�ϵ�test()����");
	}
	//�ڽӿ����涨�徲̬��������Ҫʹ��static����
	static String staticTest(){
		return "�ӿ�����෽��";
	}
	
}
