package myjava.lang;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//定义MyTag的容器注解
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface MyTags{
	MyTag[] value();
	//这以为着@MyTags注解的value成员变量可接受多个@MyTag注解,
	//因此@MyTags注解可作为@MyTag注解的容器
	//需要注意的是容器的驻留时间要比元素的驻留之间长，否则将会编译出错
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(MyTags.class)//声明该注解可在同一处重复使用
@interface MyTag{
	//为该注解定义连个成员变量
	String name() default "wfx";
	int age() default 20;
}


//在同一处重复使用@MyTag注解
@MyTag(age = 5)//name使用了默认值"wfx"
@MyTag(name="java",age = 8)
public class MyTagTest {

	public static void main(String[] args){
		Class<MyTagTest> clazz = MyTagTest.class;
		//获取修饰MyTagTest类的多少个@MyTag注解
		MyTag[] tags=clazz.getDeclaredAnnotationsByType(MyTag.class);
		//遍历这些注解
		for(MyTag tag: tags){
			System.out.println(tag.name() + "--->" + tag.age());
		}
		//使用传统的getDeclaredAnnotation()方法获取修饰MyTagTest类的@MyTags注解
		MyTags container = clazz.getDeclaredAnnotation(MyTags.class);
		System.out.println(container);
		
		System.out.println(MyTag.class);
		System.out.println(MyTags.class);
	}
}
