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
	//定义一个模拟字符串输入的方法
	public void keyIn(String msg){
		out.getData(msg);
	}
	//定义一个模拟打印大方法
	public void print(){
		out.out();
	}
}

public class OutputFactory {
	
	public Output getOutput(){
	//	return new Printer();
		
	/*  如果打印机有更新，只需要修改这一行代码，二不需要去修改Computer类里的任何代码，
	 *  通过这种方式，即可把所有生成Output对象的逻辑集中在OutputFactory工厂类中管理，
	 *  而所有需要使用Output对象的类只需要与Output接口耦合,而不需要与具体实现类耦合。
	 *  即使系统中有很多类使用了Printer对象，只要OutputFactory类的getOutput()
	 *  返回新的BetterPrinter对象，则他们全都会改为BetterPrinter对象，而所有程序无需修改.
	 * */
		
		return new BetterPrinter();
	}
	
	public static void main(String[] args){
		OutputFactory of = new OutputFactory();
		Computer c = new Computer(of.getOutput());
		c.keyIn("轻量级Java EE企业应用实战");
		c.keyIn("疯狂Java讲义");
		c.print();
//		高速打印机正在打印: 轻量级Java EE企业应用实战
//		高速打印机正在打印: 疯狂Java讲义
	}
}
