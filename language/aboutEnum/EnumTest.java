package aboutEnum;

import java.util.Arrays;

/*1.ö�������ʵ��һ�������ӿڣ�ʹ��enum�����ö���඼Ĭ�ϼ̳���java.lang.Enum�࣬
 *  ������Object�࣬���ö���಻����ʾ�̳��������ࡣ
 *2.ʹ��enum����ǳ����ö��ֵʱ���Զ�ʹ��final���Σ����ö���಻���������ࡣ
 *3.ö����Ĺ�����ֻ����private���Σ����ʡ���˻��Զ���Ϊprivate
 *4.ö���������ʵ��������ö����ĵ�һ����ʾ�г����������ö������Զ�������ٲ���ʵ����
 *	�г���Щö��ֵʱ��ϵͳ���Զ����public static final����.
 * */

enum SeasonEnum{
	SPRING,SUMMER,FALL,WINTER;//public static final����
}

public class EnumTest {
	
	public static void judge(SeasonEnum s){
		//switch�����ı��ʽ������ö��ֵ
		switch(s){
		case SPRING:
			System.out.println("��ů����,����̤��!");
			break;
		case SUMMER:
			System.out.println("��������,�ʺ���Ӿ!");
			break;
		case FALL:
			System.out.println("�����ˬ,������ʱ");
			break;
		case WINTER:
			System.out.println("��ѩ����,Χ¯��ѩ!");
			break;
		}
	}
	
	
	public static void main(String[] args){
		//ö��������һ��Ĭ�Ϸ��������ظ�ö�����������ʵ��
		for(SeasonEnum s : SeasonEnum.values()){
			System.out.println(s);
		}
		judge(SeasonEnum.SPRING);
		System.out.println(SeasonEnum.FALL);			//1
		System.out.println(SeasonEnum.FALL.name());		//2  ��������䶼����ӡ��ö��ֵ������"FALL"
		System.out.println(SeasonEnum.FALL.toString());	//3
		System.out.println(Arrays.toString(SeasonEnum.values()));
	}
}
