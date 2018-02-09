package aboutInterface;

import java.util.Date;

interface Product{
	Date getProductTime();
}
//让Printer实现output和Product接口
public class Printer implements Output,Product{
	private String[] printData = new String[MAX_CACHE_LINE];
	private int dataNum = 0;//已以记录当前需要打印的作业数
	public void out(){
		while( dataNum > 0){
			System.out.println("打印机打印:" + printData[0]);
			System.arraycopy(printData, 1, printData, 0, --dataNum);
		}
	}
	
	public void getData(String msg){
		if(dataNum >= MAX_CACHE_LINE){
			System.out.println("输出队列已满，添加失败!");
		}else{
			printData[dataNum++] = msg;
		}
	}
	
	public Date getProductTime(){
		return new Date();
	}
	
	public static void main(String[] args){
		//创建一个Printer对象，当初Output使用
		Output o = new Printer();
		o.getData("轻量级Java EE企业应用实战");
		o.getData("疯狂Java讲义");
		o.out();
		o.getData("疯狂Android讲义");
		o.getData("疯狂Ajax讲义");
		o.out();
		//调用Output接口中的默认(普通)方法
		o.print("孙悟空","猪八戒","白骨精");
		o.test();
		
		//创建一个Printer对象，当成Product用
		Product p = new Printer();
		System.out.println(p.getProductTime());
		//所有引用类型的变量都可以直接赋值给Object类型的变量
		@SuppressWarnings("unused")
		Object obj = p;
	}
	
}
