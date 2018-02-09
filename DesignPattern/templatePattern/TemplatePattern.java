package templatePattern;

/*
 * ʹ��ģ��ģʽ��һЩ�򵥹���: 
 * 1.���������ֻ������Ҫʹ�õ�ĳЩ�������Ѳ���ʵ�ֵĲ��ֳ���ɳ��󷽷�,��������ȥʵ��
 * 2.�����п��ܰ�����Ҫ��������ϵ�з����ķ���,��Щ�����õķ����ȿ����ɸ���ʵ�֣�Ҳ������
 *   ������ʵ��,�������ṩ�ķ���ֻ�Ƕ�����һ��ͨ���㷨,��ʵ��Ҳ������ȫ������ʵ��,��
 *   ��������������ĸ���
 * */

abstract class  SpeedMeter{
	private double turnRate;
	public SpeedMeter(){}
	//�ѷ��س��ְ뾶�ķ�������Ϊ���󷽷�,�������Լ�ȥʵ��
	public abstract double getRadius();
	public void setTureRate(double rate){
		turnRate = rate;
	}
	//��������ٶȵ�ͨ���㷨
	public double getSpeed(){
		//�ٶ� = �뾶 * 2 * PI * ת��
		return java.lang.Math.PI * 2 * turnRate * getRadius();
	}
}

public class TemplatePattern extends  SpeedMeter{
	//ʵ�ָ���ĳ��󷽷�
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
