package myjava.lang;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//����MyTag������ע��
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface MyTags{
	MyTag[] value();
	//����Ϊ��@MyTagsע���value��Ա�����ɽ��ܶ��@MyTagע��,
	//���@MyTagsע�����Ϊ@MyTagע�������
	//��Ҫע�����������פ��ʱ��Ҫ��Ԫ�ص�פ��֮�䳤�����򽫻�������
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(MyTags.class)//������ע�����ͬһ���ظ�ʹ��
@interface MyTag{
	//Ϊ��ע�ⶨ��������Ա����
	String name() default "wfx";
	int age() default 20;
}


//��ͬһ���ظ�ʹ��@MyTagע��
@MyTag(age = 5)//nameʹ����Ĭ��ֵ"wfx"
@MyTag(name="java",age = 8)
public class MyTagTest {

	public static void main(String[] args){
		Class<MyTagTest> clazz = MyTagTest.class;
		//��ȡ����MyTagTest��Ķ��ٸ�@MyTagע��
		MyTag[] tags=clazz.getDeclaredAnnotationsByType(MyTag.class);
		//������Щע��
		for(MyTag tag: tags){
			System.out.println(tag.name() + "--->" + tag.age());
		}
		//ʹ�ô�ͳ��getDeclaredAnnotation()������ȡ����MyTagTest���@MyTagsע��
		MyTags container = clazz.getDeclaredAnnotation(MyTags.class);
		System.out.println(container);
		
		System.out.println(MyTag.class);
		System.out.println(MyTags.class);
	}
}
