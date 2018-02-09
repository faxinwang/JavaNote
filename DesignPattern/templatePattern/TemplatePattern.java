package templatePattern;

/*
 * 使用模板模式的一些简单规则: 
 * 1.抽象父类可以只定义需要使用的某些方法，把不能实现的部分抽象成抽象方法,留给子类去实现
 * 2.父类中可能包含需要调用其他系列方法的方法,这些被调用的方法既可以由父类实现，也可以由
 *   其子类实现,父类里提供的方法只是定义了一个通用算法,其实现也许并不完全由自身实现,而
 *   必须依赖其子类的辅助
 * */

abstract class  SpeedMeter{
	private double turnRate;
	public SpeedMeter(){}
	//把返回车轮半径的方法定义为抽象方法,让子类自己去实现
	public abstract double getRadius();
	public void setTureRate(double rate){
		turnRate = rate;
	}
	//定义计算速度的通用算法
	public double getSpeed(){
		//速度 = 半径 * 2 * PI * 转速
		return java.lang.Math.PI * 2 * turnRate * getRadius();
	}
}

public class TemplatePattern extends  SpeedMeter{
	//实现父类的抽象方法
	@Override
	public double getRadius() {
		return 2.5;
	}

	public static void main(String[] args ){
		TemplatePattern tp = new TemplatePattern();
		tp.setTureRate(15);
		System.out.println(tp.getSpeed());
	}

	
}
