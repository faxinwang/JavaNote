package aboutObjectA;
import static java.lang.System.*;

class Wrap{
	//a,bû�мӲ������η�����default����Ȩ�ޣ���ֻ���ڰ��౻����
	int a,b;
	Wrap(int a,int b){
		this.a = a;
		this.b = b;
	}
	@Override
	public String toString(){
		return "[" + a+ ","+b+"]";
	}
	
}

public class ArgsTransfer {
	//�ú�����������a,b��ֵ,����������,��Ϊ���ǲ�����������
	public static void swap(int a,int b){
		out.println("before swap:a="+a+", b="+b);
		a^=b;
		b^=a;
		a^=b;
		out.println("after swap:a="+a+", b="+b);
	}
	//�ܳɹ�����w.a ��w.b��ֵ����Ϊw����������
	public static void swap(Wrap w){
		w.a ^= w.b;
		w.b ^= w.a;
		w.a ^= w.b;
	}
	
	static void testVerargs(String str){
		out.println("�ǿɱ������testVerargs() :"+str);
	}
	//���������ɱ�ĺ���,ֻ�������һ������Ϊ�ɱ��������,��ʵ���൱��һ������
	static void testVerargs(String...books){
		int count=0;
		out.println("���������ɱ��testVerargs() :");
		for(String book:books){
			++count;
			out.println(book);
		}
		out.println("��������Ϊ:"+count);
	}
	
	public static void main(String[] args){
		int x = 1,y=2;
		swap(x,y);
		out.println("x=" + x +",y=" + y);
		
		Wrap w = new Wrap(3,4);
		out.println(w);
		swap(w);
		out.println(w);
		
		testVerargs();//���ò��������ɱ�İ汾
		testVerargs("just one arg");//���÷ǲ��������ɱ�İ汾
		testVerargs("arg one","arg two","arg three");//���ò��������ɱ�İ汾
		testVerargs(new String[]{"one","two","three","four"});//���ò��������ɱ�İ汾
	}
}
