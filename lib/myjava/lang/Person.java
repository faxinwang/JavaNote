package myjava.lang;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//该注解用于标记类类型程序单元
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
@Documented
@interface Persistent{
	String table();
}
//该注解用于标记成员变量
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
@Documented
@interface Id{
	String column();
	String type();
	String generator();
}

//该注解用于标记成员变量
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
@Documented
@interface Property{
	String column();
	String type();
}


@Persistent(table = "person_inf")
public class Person{
	@Id(column="person_id",type="integer",generator="identity")
	private int id;
	
	@Property(column="Perosn_name",type="String")
	private String name;
	
	@Property(column="Person_age",type="int")
	private int age;
	
	public Person(){}
	public Person(int id,String name,int age){
		this.id = id ;
		this.name = name;
		this.age = age;
	}
}