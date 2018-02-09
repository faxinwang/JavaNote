package lambda;



public class RefSpecialObjFunc {
	public static void main(String[] args){
		//使用lambda表达式创建Conveter对象
		Conveter cvt1 = tag -> "android".indexOf(tag);
		Integer idx1 = cvt1.convert( "roid");
		System.out.println(idx1);
		
		//方法引用替代lambda表达式:引用特定对象的实例方法
		//函数式接口中被实现方法的全部参数传给该方法作为参数
		Conveter cvt2 = "android"::indexOf;//下标从0开始计算
		Integer idx2 = cvt2.convert("id");
		System.out.println(idx2);
	}
}
