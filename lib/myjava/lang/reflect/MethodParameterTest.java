package myjava.lang.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/* 编译该程序时,需要使用-parameter选项,这样在编译时就会保留函数的形参.
 * 从而可以通过反射获取形参名和参数类型
 */

class Test{
	Map<String,Integer> score;
	public void replace(String str, List<String> list){}
}

public class MethodParameterTest {
	static void testMethodParameter()throws Exception{
		//获取Test类
		Class<Test> clazz=Test.class;
		//获取Test类的带String和List的replace方法
		Method replace = clazz.getMethod("replace", String.class,List.class);
		//获取指定方法的参数个数
		System.out.println("replace方法参数数个:" + replace.getParameterCount());
		//获取repalce的所有参数
		Parameter[] parameter = replace.getParameters();
		int index = 1;
		for(Parameter p:parameter){
			if(p.isNamePresent()){
				System.out.println("---第"+ index++ +"个参数");
				System.out.println("参数名:" + p.getName());
				System.out.println("形参类型:" + p.getType());
				System.out.println("泛型参数:" + p.getParameterizedType());
			}
		}
	}
	
	static void testGeneric()throws Exception{
		Class<Test> clazz = Test.class;
		Field f = clazz.getDeclaredField("score");
		//直接使用getType()取出类型只对普通类型的成员变量有效
		//下面输出将看到java.util.Map
		System.out.println("score的类型是:" + f.getType());
		//获取成员变量f的泛型类型
		Type gtype= f.getGenericType();
		//如果gtype是类型是ParameterizedType对象
		if(gtype instanceof ParameterizedType){
			ParameterizedType ptype = (ParameterizedType)gtype;
			//获取原始类型
			Type rtype = ptype.getRawType();
			System.out.println("socre原始类型是:" + rtype);
			//获取泛型类型的泛型参数
			Type[] args = ptype.getActualTypeArguments();
			System.out.println("泛型信息:");
			for(int i=0;i<args.length;++i){
				System.out.println("第" + i+"泛型:" + args[i]);
			}
		}else{
			System.out.println("获取泛型类型出错!");
		}
	}
	
	public static void main(String[] args)throws Exception{
	//	testMethodParameter();
		testGeneric();
	}
}
