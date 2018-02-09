package lambda;
import static java.lang.System.*;

@FunctionalInterface
interface RefObjectFunc{
	String subStr(String str,int beg,int end);
}

public class RefObjFunc {
	
	public static void main(String[] args){
		//ʹ��lambda���ʽ����RefObjectFunc����
		RefObjectFunc rof1 = (str,beg,end)->str.substring(beg,end);
		String sub1 = rof1.subStr("android", 3, 6);
		out.println(sub1);
		
		
		//�������ô���lambda���ʽ: ����ĳ�����ʵ������
		//����ʽ�ӿ��еĵ�һ��������Ϊ������,��������в�������������Ϊ����
		RefObjectFunc rof2 = String::substring;
		String sub2 = rof2.subStr("android", 3, 7);
		out.println(sub2);
	}
}
