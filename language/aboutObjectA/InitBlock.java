package aboutObjectA;

public class InitBlock {
	static int count;
	int eye,ear,nose;
	String name;
	int height;
	
	//ִ��˳��:3
	public InitBlock(String name){
		this.name = name;
		System.out.println("InitBlock���޲ι�����!");
	}
	public InitBlock(String name,int height){
		this.name = name;
		this.height = height;
	}
	
	//��̬��ʼ���죬���౻jvm���ص�ϵͳʱִ��(ִֻ��һ��),���ж�����ڴ����е�˳��ִ��
	//ִ��˳��:1
	static{
		count=0;
		System.out.println("InitBlock��һ����̬��ʼ����!");
	}
	//���涨����һ����ͨ��ʼ����,��ÿ��ʵ����һ������ʱ�ڹ��캯��֮ǰ��ִ��,���ж��,���ڴ����е�˳��ִ��
	//ʹ�ó�ʼ�����ԭ�򣬸����캯������ͬ�Ĵ�����ȡ�����ŵ���ʼ�����У�����ߴ��븴����
	//ִ��˳��:2
	{
		eye=ear=2;
		nose=1;
		System.out.println("InitBlock�����ͨ��ʼ����!");
	}
	public String toString(){
		return "[eye="+eye+", ear="+ ear + ", nose="+nose+", name=\""+name+"\", height="+height+"]";
	}
	
	public static void main(String[] args){
		System.out.println(new InitBlock("Tom"));
		System.out.println(new InitBlock("Bob",168));
	}
}
