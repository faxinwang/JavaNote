package lambda;


@FunctionalInterface //����ʽ�ӿڵı�ע
interface Conveter{
	Integer convert(String number);
}

public class RefClassFunc {
	
	public static void main(String[] args){
		//ʹ��lambda���ʽ����Converter����
		Conveter conveter = num -> Integer.valueOf(num);
		Integer val1 = conveter.convert("666");
		System.out.println(val1);
		
		//�������ô���Lambda���ʽ :�����෽��
		//����ʽ�ӿ��б�ʵ�ֵķ�����ȫ�������������෽����Ϊ����
		Conveter conveter2 = Integer::valueOf;
		Integer val2 = conveter2.convert("999");
		System.out.println(val2);
	}
}
