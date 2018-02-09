package aboutObjectA;

import static java.lang.System.*;
import basic_grammer.Switch;

	/************************************************************
	 *			*  private	*  default	* protected *  public	*
	 * **********************************************************
	 *  同一个类中	*	  Y		*	 Y		*	  Y		*	  Y		*
	 *  同一个包中	*			*	 Y		*	  Y		*	  Y		*
	 *   子类中	*			*			*	  Y		*	  Y		*
	 *  全局范围内	*			*			*			*	  Y		*
	 ************************************************************ */

/*	为java类添加包必须在java源文件中通过package语句指定,单靠目录是没发指定的.
 * 	Java的包机制需要两个方面的保证:
 * 		1.源文件里通过package语句指定包名
 * 		2.相应的class文件必须放在对应的路径下
 * 
 * */
public class AccessQualifier {
	
	public static void main(String[] args){
		//条用Switch的静态方法
		Switch.switchTest("春天");
		
		This ths = new This(1,"test_qualifier");
		out.println(ths.a);		//a  -public
		out.println(ths.str);	//str-default
		out.println(ths.d);		//d  -protected
	//	out.println(ths.p); 	//p  -private
		
		ths.grow().grow().grow().grow();
		out.println(ths);
	}
}
