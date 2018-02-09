package myjava.lang.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* Proxy�ṩ����������������������̬������Ͷ�̬�������
 * static Class<?> getProxyClass(ClassLoader loader,Class<?> ...interfaces):
 * 		����һ����̬����������Ӧ��Class����,�ô����ཫʵ��interfaces��ָ���Ķ���ӿ�.��һ��ClassLoader
 * ����ָ�����ɶ�̬��������������.
 * static Object newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler hd):
 * 		ֱ�Ӵ���һ����̬������Ķ���,�ô�������ʵ����ʵ����interfacesָ����ϵ�нӿ�,ִ�д�������ÿ������ʱ���ᱻ�滻��ִ��
 * InvocationHandler�����invoke����
 */


//����Dog�ӿ�
interface Dog{
	//�ӿ���ķ���ȫ������public��
	void info();
	void run();
}

//����һ��Dog�ӿڵ�ʵ����
class GunDog implements Dog{
	public void info(){
		System.out.println("����һֻ�Թ�");
	}
	public void run(){
		System.out.println("�ұ���Ѹ��");
	}
}

//����һ��������,��������˹��߷���
class  DogUtil{
	//��һ������������
	public void fun1(){
		System.out.println("-------ģ���һ��ͨ�÷���-------");
	}
	//�ڶ�������������
	public void fun2(){
		System.out.println("-------ģ��ڶ���ͨ�÷���-------");
	}
}

class MyInvocationHandler implements InvocationHandler{
	//��Ҫ������Ķ���
	private Object target;
	public void setTarget(Object obj){
		target=obj;
	}
	
	@Override //ָ����̬�����������з���ʱ,���ᱻ�滻��ִ�����µķ���
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		DogUtil du = new DogUtil();
		//ִ�е�һ��ͨ�÷���
		du.fun1();
		//��target��Ϊ������ִ��method����
		//����ǻص���target�����ԭ�з���
		Object result = method.invoke(target, args);
		//ִ�еڶ���ͨ�÷���
		du.fun2();
		
		return result;
	}
}

//�ṩһ��MyProxyFactory��,�ö���תΪָ����target���ɶ�̬����ʵ��
class MyProxyFactory{
	@SuppressWarnings("unchecked")
	public static <T> T getProxy(T target){
		//����һ��MyInvocationHandler����
		MyInvocationHandler handler = new MyInvocationHandler();
		//ΪMyInvocationHandler����target����
		handler.setTarget(target);
		//����������һ����̬����
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), handler);
	}
}

public class ProxyAndAOP {

	public static void main(String[] args){
		//����һ��ԭʼ��GunDog������Ϊtarget
		Dog target = new GunDog();
		//��ָ����target��������̬�������
		Dog dog = MyProxyFactory.getProxy(target);
		//���淽��ʵ���ϻ��滻�ɵ���MyInvocationHandler��invoke()����
		dog.info();
		dog.run();
	}
	/* ���ö�̬������Էǳ�����ʵ�ֽ���
	 * ��Proxy���ɶ�̬������AOP(Aspect Orient Programming������Ƭ���)�б���ΪAOP����,
	 * AOP����ɴ���Ŀ�����,AOP�������Ŀ������ȫ������
	 */
}
