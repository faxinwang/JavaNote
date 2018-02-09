package myjava.lang;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/* java.lang包下提供了5个基本Annotation:
 * @Override:限定重写父类方法
 * @DeprecatedL:用于标记某个程序元素(类，方法等)已过时
 * @SuppressWarnings:用来抑制编译器警告
 * @SaveVarargs:用来“抑制”堆污染警告
 * @FunctionalInterface:java 8为lambda表达式新增的用来指定函数值接口的annotation
 */

/* java.lang.annotation包下提供了6个Meta Annotation,其中有五个annotation都用来修饰其他的annotation定义
 * @Retention:用于指定被修饰的Annotation可以保留多长时间,它有一个value成员变量，可取如下三个值
 * 		RetentionPolicy.CLASS:保留到class文件,jvm不可获取，默认值 
 * 		XXX.RUNTIME:保留到class文件,jvm可以获取 
 * 		XXX.SOURCE:保留到源文件,编译时将丢失
 * 		eg: @Retention(RetentionPolicy.SOURCE)//指定该annotation只保留到源文件，编译时将丢失
 * 			public @interface Testable{}
 * @Target:指定被修饰的Annotation能修饰那些程序员单元,它也有一个成员变量，可取如下值:
 * 		ElementType.ANNOTATION_TYPE:
 * 		XXX.CONSTRUCTOR:
 * 		XXX.FIELD:
 * 		XXX.LOCAL_VARIABLE:
 * 		XXX.METHOD:
 * 		XXX.PACKAGE:
 *  	XXX.PARAMETER:
 *  	XXX.TYPE:
 * 		eg:	@Target(ElementType.FIELD)//指定该annotation只能修饰成员变量
 * 			public @interface Testable{}
 * @Documented:指定被修饰的annotation将被javadoc工具提取到文档中
 * @Inherited:指定被该annotation修饰的annotation将具有继承性---如果某个类使用了@Xxx注解(定义该注解时使用了@Inherited修饰)，
 * 				则其子类将自动被@Xxx修饰.
 */

/* java.lang.reflect包下提供了AnnotatedElement接口,该接口有如下几个实现类用来表示程序元素:
 * Class:类定义
 * Constructor:构造器定义
 * Field:类的成员变量定义
 * Method:类的方法定义
 * Packeage:类的包定义
 * 该接口中有下面几个方法用于访问annotation信息
 * <A extends Annotation> A getAnnotation(Class<A> annotationClass):
 * <A extends Annotation> A getDeclaredAnnotation(Class<A> annotationClass):
 * <A extends Annotation> A[] getAnnotationsByType(Class<A> annotationClass):
 * <A extends Annotation> A[] getDeclaredAnnotationsByType(Class<A> annotationClass):
 * Annotation[]	getAnnotations():返回该程序元素上存在的所有注解
 * Annotation[] getDeclaredAnnotations():返回直接修饰该程序元素上的所有注解
 * boolean isAnnotationPresent(Class<? extends Annotation> annotationClass):判断程序元素上是否存在指定类型的注解
 */

//定义一个标记注解，不包含任何成员变量，即不可传入元数据
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Testable{}


class Test {
	//使用@Testable标记该方法是可测试的
		@Testable
		public static void f1(){}
		public static void f2(){}
		
		//使用@Testable标记该方法是可测试的
		@Testable
		public static void f3(){
			throw new IllegalArgumentException("参数出错了!");
		}
		public static void f4(){}
		
		//使用@Testable标记该方法是可测试的
		@Testable
		public static void f5(){}
		public static void f6(){}
		
		//使用@Testable标记该方法是可测试的
		@Testable
		public static void f7(){
			throw new RuntimeException("程序业务出现异常!");
		}
		public static void f8(){}
}



class ProcessorTest{
	public static void process(String clazz)throws ClassNotFoundException{
		int passed = 0;
		int failed = 0;
		//遍历clazz里对应的类里的所有方法
		for(Method f : Class.forName(clazz).getMethods()){
			//如果该方法使用了@Testable修饰
			if(f.isAnnotationPresent(Testable.class)){
				try{
					//调用f方法
					System.out.println("调用:"+f.getName()+"()...");
					f.invoke(null);
					//测试通过
					++passed;
				}catch(Exception e){
					System.out.println("方法" + f+ "运行失败,异常:" + e.getCause());
					//测试未通过
					++failed;
				}
			}
		}
		//统计测试结果
		System.out.println("共运行了" + (passed + failed) + "个方法,其中:");
		System.out.println("失败了" + failed+"个");
		System.out.println("通过了" + passed+"个");
	}
}

public class Annotations {

	public static void main(String[] args)throws Exception{
		ProcessorTest.process("myjava.lang.Test");
	}
}
