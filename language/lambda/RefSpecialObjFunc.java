package lambda;



public class RefSpecialObjFunc {
	public static void main(String[] args){
		//ʹ��lambda���ʽ����Conveter����
		Conveter cvt1 = tag -> "android".indexOf(tag);
		Integer idx1 = cvt1.convert( "roid");
		System.out.println(idx1);
		
		//�����������lambda���ʽ:�����ض������ʵ������
		//����ʽ�ӿ��б�ʵ�ַ�����ȫ�����������÷�����Ϊ����
		Conveter cvt2 = "android"::indexOf;//�±��0��ʼ����
		Integer idx2 = cvt2.convert("id");
		System.out.println(idx2);
	}
}
