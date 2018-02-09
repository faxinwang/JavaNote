package myjava.lang.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* Proxy提供了如下两个方法来创建动态代理类和动态代理对象
 * static Class<?> getProxyClass(ClassLoader loader,Class<?> ...interfaces):
 * 		创建一个动态代理类所对应的Class对象,该代理类将实现interfaces所指定的多个接口.第一个ClassLoader
 * 参数指定生成动态代理类的类加载器.
 * static Object newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler hd):
 * 		直接创建一个动态代理类的对象,该代理对象的实现类实现了interfaces指定的系列接口,执行代理对象的每个方法时都会被替换成执行
 * InvocationHandler对象的invoke方法
 */


//定义Dog接口
interface Dog{
	//接口里的方法全部都是public的
	void info();
	void run();
}

//定义一个Dog接口的实现类
class GunDog implements Dog{
	public void info(){
		System.out.println("我是一只猎狗");
	}
	public void run(){
		System.out.println("我奔跑迅速");
	}
}

//定义一个工具类,里面包含了工具方法
class  DogUtil{
	//第一个拦截器方法
	public void fun1(){
		System.out.println("-------模拟第一个通用方法-------");
	}
	//第二个拦截器方法
	public void fun2(){
		System.out.println("-------模拟第二个通用方法-------");
	}
}

class MyInvocationHandler implements InvocationHandler{
	//需要被代理的对象
	private Object target;
	public void setTarget(Object obj){
		target=obj;
	}
	
	@Override //指定动态代理对象的所有方法时,都会被替换成执行如下的方法
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		DogUtil du = new DogUtil();
		//执行第一个通用方法
		du.fun1();
		//以target作为主调来执行method方法
		//这就是回调了target对象的原有方法
		Object result = method.invoke(target, args);
		//执行第二个通用方法
		du.fun2();
		
		return result;
	}
}

//提供一个MyProxyFactory类,该对象转为指定的target生成动态代理实例
class MyProxyFactory{
	@SuppressWarnings("unchecked")
	public static <T> T getProxy(T target){
		//创建一个MyInvocationHandler对象
		MyInvocationHandler handler = new MyInvocationHandler();
		//为MyInvocationHandler设置target对象
		handler.setTarget(target);
		//创建并返回一个动态代理
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), handler);
	}
}

public class ProxyAndAOP {

	public static void main(String[] args){
		//创建一个原始的GunDog对象作为target
		Dog target = new GunDog();
		//以指定的target来创建动态代理对象
		Dog dog = MyProxyFactory.getProxy(target);
		//下面方法实际上会替换成调用MyInvocationHandler的invoke()方法
		dog.info();
		dog.run();
	}
	/* 采用动态代理可以非常灵活地实现解耦
	 * 用Proxy生成动态代理在AOP(Aspect Orient Programming面向切片编程)中被称为AOP代理,
	 * AOP代理可代替目标对象,AOP代理包含目标对象的全部方法
	 */
}
