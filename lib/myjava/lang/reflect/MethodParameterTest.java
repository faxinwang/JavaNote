package myjava.lang.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/* ����ó���ʱ,��Ҫʹ��-parameterѡ��,�����ڱ���ʱ�ͻᱣ���������β�.
 * �Ӷ�����ͨ�������ȡ�β����Ͳ�������
 */

class Test{
	Map<String,Integer> score;
	public void replace(String str, List<String> list){}
}

public class MethodParameterTest {
	static void testMethodParameter()throws Exception{
		//��ȡTest��
		Class<Test> clazz=Test.class;
		//��ȡTest��Ĵ�String��List��replace����
		Method replace = clazz.getMethod("replace", String.class,List.class);
		//��ȡָ�������Ĳ�������
		System.out.println("replace������������:" + replace.getParameterCount());
		//��ȡrepalce�����в���
		Parameter[] parameter = replace.getParameters();
		int index = 1;
		for(Parameter p:parameter){
			if(p.isNamePresent()){
				System.out.println("---��"+ index++ +"������");
				System.out.println("������:" + p.getName());
				System.out.println("�β�����:" + p.getType());
				System.out.println("���Ͳ���:" + p.getParameterizedType());
			}
		}
	}
	
	static void testGeneric()throws Exception{
		Class<Test> clazz = Test.class;
		Field f = clazz.getDeclaredField("score");
		//ֱ��ʹ��getType()ȡ������ֻ����ͨ���͵ĳ�Ա������Ч
		//�������������java.util.Map
		System.out.println("score��������:" + f.getType());
		//��ȡ��Ա����f�ķ�������
		Type gtype= f.getGenericType();
		//���gtype��������ParameterizedType����
		if(gtype instanceof ParameterizedType){
			ParameterizedType ptype = (ParameterizedType)gtype;
			//��ȡԭʼ����
			Type rtype = ptype.getRawType();
			System.out.println("socreԭʼ������:" + rtype);
			//��ȡ�������͵ķ��Ͳ���
			Type[] args = ptype.getActualTypeArguments();
			System.out.println("������Ϣ:");
			for(int i=0;i<args.length;++i){
				System.out.println("��" + i+"����:" + args[i]);
			}
		}else{
			System.out.println("��ȡ�������ͳ���!");
		}
	}
	
	public static void main(String[] args)throws Exception{
	//	testMethodParameter();
		testGeneric();
	}
}
