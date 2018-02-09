package myjava.lang;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/* java.lang�����ṩ��5������Annotation:
 * @Override:�޶���д���෽��
 * @DeprecatedL:���ڱ��ĳ������Ԫ��(�࣬������)�ѹ�ʱ
 * @SuppressWarnings:�������Ʊ���������
 * @SaveVarargs:���������ơ�����Ⱦ����
 * @FunctionalInterface:java 8Ϊlambda���ʽ����������ָ������ֵ�ӿڵ�annotation
 */

/* java.lang.annotation�����ṩ��6��Meta Annotation,���������annotation����������������annotation����
 * @Retention:����ָ�������ε�Annotation���Ա����೤ʱ��,����һ��value��Ա��������ȡ��������ֵ
 * 		RetentionPolicy.CLASS:������class�ļ�,jvm���ɻ�ȡ��Ĭ��ֵ 
 * 		XXX.RUNTIME:������class�ļ�,jvm���Ի�ȡ 
 * 		XXX.SOURCE:������Դ�ļ�,����ʱ����ʧ
 * 		eg: @Retention(RetentionPolicy.SOURCE)//ָ����annotationֻ������Դ�ļ�������ʱ����ʧ
 * 			public @interface Testable{}
 * @Target:ָ�������ε�Annotation��������Щ����Ա��Ԫ,��Ҳ��һ����Ա��������ȡ����ֵ:
 * 		ElementType.ANNOTATION_TYPE:
 * 		XXX.CONSTRUCTOR:
 * 		XXX.FIELD:
 * 		XXX.LOCAL_VARIABLE:
 * 		XXX.METHOD:
 * 		XXX.PACKAGE:
 *  	XXX.PARAMETER:
 *  	XXX.TYPE:
 * 		eg:	@Target(ElementType.FIELD)//ָ����annotationֻ�����γ�Ա����
 * 			public @interface Testable{}
 * @Documented:ָ�������ε�annotation����javadoc������ȡ���ĵ���
 * @Inherited:ָ������annotation���ε�annotation�����м̳���---���ĳ����ʹ����@Xxxע��(�����ע��ʱʹ����@Inherited����)��
 * 				�������ཫ�Զ���@Xxx����.
 */

/* java.lang.reflect�����ṩ��AnnotatedElement�ӿ�,�ýӿ������¼���ʵ����������ʾ����Ԫ��:
 * Class:�ඨ��
 * Constructor:����������
 * Field:��ĳ�Ա��������
 * Method:��ķ�������
 * Packeage:��İ�����
 * �ýӿ��������漸���������ڷ���annotation��Ϣ
 * <A extends Annotation> A getAnnotation(Class<A> annotationClass):
 * <A extends Annotation> A getDeclaredAnnotation(Class<A> annotationClass):
 * <A extends Annotation> A[] getAnnotationsByType(Class<A> annotationClass):
 * <A extends Annotation> A[] getDeclaredAnnotationsByType(Class<A> annotationClass):
 * Annotation[]	getAnnotations():���ظó���Ԫ���ϴ��ڵ�����ע��
 * Annotation[] getDeclaredAnnotations():����ֱ�����θó���Ԫ���ϵ�����ע��
 * boolean isAnnotationPresent(Class<? extends Annotation> annotationClass):�жϳ���Ԫ�����Ƿ����ָ�����͵�ע��
 */

//����һ�����ע�⣬�������κγ�Ա�����������ɴ���Ԫ����
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Testable{}


class Test {
	//ʹ��@Testable��Ǹ÷����ǿɲ��Ե�
		@Testable
		public static void f1(){}
		public static void f2(){}
		
		//ʹ��@Testable��Ǹ÷����ǿɲ��Ե�
		@Testable
		public static void f3(){
			throw new IllegalArgumentException("����������!");
		}
		public static void f4(){}
		
		//ʹ��@Testable��Ǹ÷����ǿɲ��Ե�
		@Testable
		public static void f5(){}
		public static void f6(){}
		
		//ʹ��@Testable��Ǹ÷����ǿɲ��Ե�
		@Testable
		public static void f7(){
			throw new RuntimeException("����ҵ������쳣!");
		}
		public static void f8(){}
}



class ProcessorTest{
	public static void process(String clazz)throws ClassNotFoundException{
		int passed = 0;
		int failed = 0;
		//����clazz���Ӧ����������з���
		for(Method f : Class.forName(clazz).getMethods()){
			//����÷���ʹ����@Testable����
			if(f.isAnnotationPresent(Testable.class)){
				try{
					//����f����
					System.out.println("����:"+f.getName()+"()...");
					f.invoke(null);
					//����ͨ��
					++passed;
				}catch(Exception e){
					System.out.println("����" + f+ "����ʧ��,�쳣:" + e.getCause());
					//����δͨ��
					++failed;
				}
			}
		}
		//ͳ�Ʋ��Խ��
		System.out.println("��������" + (passed + failed) + "������,����:");
		System.out.println("ʧ����" + failed+"��");
		System.out.println("ͨ����" + passed+"��");
	}
}

public class Annotations {

	public static void main(String[] args)throws Exception{
		ProcessorTest.process("myjava.lang.Test");
	}
}
