package aboutObjectA;
//��̬���룬���Ե���һ�����еĵ��������о�̬���������
import static java.lang.System.*;

public class This {
	public int a;
	String str;
	protected double d;
	private float p;
	
	public This(int a,String str){
		out.println("Two arguements constructor called!");
		this.a = a;
		this.str = str;
		p = 0;
	}
	public This(int a,String str,double d){
		//ʹ��this������һ�����캯���Ĵ���
		//����������ڷ����ĵ�һ�У���ֻ���ڹ��캯���в�������ʹ��
		this(a,str);
		this.d = d;
		out.println("Three arguements constructor called!");
	}
	
	//�����κ����η�������default����Ȩ�ޣ���ֻ���ڰ��ڷ���
	This grow(){
		++a;
		return this;//���ض������������Ϳ�������ͬһ������
	}
	
	@Override
	public String toString(){
		return "[" + a+","+ str +","+ d +","+ p + "]" ;
	}
	
	public static void main(String[] args){
		This ths = new This(1,"test_this",3.3);
		out.println(ths);
		ths.grow().grow().grow().grow();
		out.println(ths);
		
	}
	
}
