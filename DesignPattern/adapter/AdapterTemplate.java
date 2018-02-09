package adapter;

/* ������ģʽ(Adapter):
 * ��һ����Ľӿ�ת��Ϊ�ͻ�ϣ��������һ���ӿڡ�Adapterģʽʹ��ԭ�����ڽӿڲ�����
 * ������һ��������Щ�����һ������ ����������У���ʱϵͳ�����ݺ���Ϊ����ȷ�� ��
 * �ӿڲ���ʱ��Ӧ�ÿ���ʹ����������Ŀ����ʹ���Ʒ�Χ֮���һ��ԭ�ж�����ĳ���ӿ�ƥ�䣬
 * ������ģʽ��ҪӦ����ϣ������һЩ�ִ���࣬���ǽӿ� ���븴�û�����һ�µ������
 * 
 * ��Ҫע�����,���������Ԥ���ӿڲ�ͬ�����⣬��ƥ������Ͳ��ᷢ��������С�Ľӿڲ�
 * ͳһʱ����ʱ�ع������ⲻ��������ֻ�������޷��ı�ԭ����ƺʹ����������ſ������䡣
 * �º���Ʋ������п��ƣ����п��Ʋ�����ǰ���ơ�
 */

//���ǿͻ������ڴ��Ľӿڣ�Ŀ������Ǿ���ĺ������࣬Ҳ�����ǽӿڡ�
class Target{
	//�����C++���룬��Ҫ���÷�������Ϊvirtual��
	public void Request() {
		System.out.println("��ͨ����");
	}
}

//��Ҫ�������
class Adaptee{
	public void SpecificRequest() {
		System.out.println("��������");
	}
}


//ͨ�����ڲ���װһ��Adaptee���󣬰�Դ�ӿ�ת��ΪĿ��ӿڡ�
class Adapter extends Target{
	private Adaptee adaptee = new Adaptee();
	@Override
	public void Request() {
		adaptee.SpecificRequest();
	}
}

public class AdapterTemplate {
	public static void main(String[] args) {
		Target target = new Adapter();
		target.Request();
	}
}
