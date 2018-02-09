package aboutInterface;

/* 1.接口里定义的所有成员都将自动成为 public,其中:
 *   成员变量自动成为 public static final,接口里不能有初始化块，所以只能在定义时初始化
 *   普通成员函数自动成为 public abstract
 * 2.一个接口可以使用extends关键字继承多个接口，继承后将得到父接口的所有成员
 * 3.一个类可以使用implements关键字实现多个接口，这可以弥补java单继承的不足
 * */
public interface Output {
	//接口里定义的成员变量只能是常量
	int MAX_CACHE_LINE = 50;
	//接口里定义的普通方法只能是public的抽象方法
	void out();
	void getData(String msg);
	
	//在接口里定义的默认方法，需要使用default关键字修饰
	default void print(String...msgs){
		for(String msg:msgs){
			System.out.println(msg);
		}
	}
	default void test(){
		System.out.println("默认的test()方法");
	}
	//在接口里面定义静态方法，需要使用static修饰
	static String staticTest(){
		return "接口里的类方法";
	}
	
}
