package aboutObjectA;

import static java.lang.System.*;
import basic_grammer.Switch;

	/************************************************************
	 *			*  private	*  default	* protected *  public	*
	 * **********************************************************
	 *  ͬһ������	*	  Y		*	 Y		*	  Y		*	  Y		*
	 *  ͬһ������	*			*	 Y		*	  Y		*	  Y		*
	 *   ������	*			*			*	  Y		*	  Y		*
	 *  ȫ�ַ�Χ��	*			*			*			*	  Y		*
	 ************************************************************ */

/*	Ϊjava����Ӱ�������javaԴ�ļ���ͨ��package���ָ��,����Ŀ¼��û��ָ����.
 * 	Java�İ�������Ҫ��������ı�֤:
 * 		1.Դ�ļ���ͨ��package���ָ������
 * 		2.��Ӧ��class�ļ�������ڶ�Ӧ��·����
 * 
 * */
public class AccessQualifier {
	
	public static void main(String[] args){
		//����Switch�ľ�̬����
		Switch.switchTest("����");
		
		This ths = new This(1,"test_qualifier");
		out.println(ths.a);		//a  -public
		out.println(ths.str);	//str-default
		out.println(ths.d);		//d  -protected
	//	out.println(ths.p); 	//p  -private
		
		ths.grow().grow().grow().grow();
		out.println(ths);
	}
}
