 package simpleFactory;

import aboutInterface.BetterPrinter;
import aboutInterface.Output;
import aboutInterface.Printer;

@SuppressWarnings("unused")
class Computer{
	private Output out;
	public Computer(Output out){
		this.out = out;
	}
	//����һ��ģ���ַ�������ķ���
	public void keyIn(String msg){
		out.getData(msg);
	}
	//����һ��ģ���ӡ�󷽷�
	public void print(){
		out.out();
	}
}

public class OutputFactory {
	
	public Output getOutput(){
	//	return new Printer();
		
	/*  �����ӡ���и��£�ֻ��Ҫ�޸���һ�д��룬������Ҫȥ�޸�Computer������κδ��룬
	 *  ͨ�����ַ�ʽ�����ɰ���������Output������߼�������OutputFactory�������й���
	 *  ��������Ҫʹ��Output�������ֻ��Ҫ��Output�ӿ����,������Ҫ�����ʵ������ϡ�
	 *  ��ʹϵͳ���кܶ���ʹ����Printer����ֻҪOutputFactory���getOutput()
	 *  �����µ�BetterPrinter����������ȫ�����ΪBetterPrinter���󣬶����г��������޸�.
	 * */
		
		return new BetterPrinter();
	}
	
	public static void main(String[] args){
		OutputFactory of = new OutputFactory();
		Computer c = new Computer(of.getOutput());
		c.keyIn("������Java EE��ҵӦ��ʵս");
		c.keyIn("���Java����");
		c.print();
//		���ٴ�ӡ�����ڴ�ӡ: ������Java EE��ҵӦ��ʵս
//		���ٴ�ӡ�����ڴ�ӡ: ���Java����
	}
}
