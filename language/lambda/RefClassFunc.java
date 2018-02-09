package lambda;


@FunctionalInterface //函数式接口的标注
interface Conveter{
	Integer convert(String number);
}

public class RefClassFunc {
	
	public static void main(String[] args){
		//使用lambda表达式创建Converter对象
		Conveter conveter = num -> Integer.valueOf(num);
		Integer val1 = conveter.convert("666");
		System.out.println(val1);
		
		//方法引用代替Lambda表达式 :引用类方法
		//函数式接口中被实现的方法的全部参数传给该类方法作为参数
		Conveter conveter2 = Integer::valueOf;
		Integer val2 = conveter2.convert("999");
		System.out.println(val2);
	}
}
