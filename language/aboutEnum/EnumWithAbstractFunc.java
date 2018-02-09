package aboutEnum;

/*	ö�����ﶨ����󷽷��ǲ���ʹ��abstract�ؼ��ֽ�ö��������Ϊ�����࣬
 *  ��Ϊϵͳ���Զ�Ϊ������abstract�ؼ��֡�ö������Ҫ��ʾ����ö��ֵ��
 *  ��������Ϊ���࣬���Զ���ÿ��ö��ֵʱ����Ϊ���󷽷��ṩʵ�֣����򽫱������
 * 
 * */
enum Operation{
	PLUS{
		public double eval(double x,double y){
			return x + y;
		}
	},
	MINUS{
		public double eval(double x,double y){
			return x - y;
		}
	},
	TIMES{
		public double eval(double x,double y){
			return x * y;
		}
	},
	DIVIDE{
		public double eval(double x,double y){
			return x / y;
		}
	};
	//Ϊö���ඨ��һ�����󷽷�
	//������󷽷��ɲ�ͬ��ö��ֵ�ṩ��ͬ��ʵ��
	public abstract double eval(double x,double y);
}

public class EnumWithAbstractFunc {
	
	public static void main(String[] args){
		System.out.println(Operation.PLUS.eval(3, 4));
		System.out.println(Operation.MINUS.eval(5, 4));
		System.out.println(Operation.TIMES.eval(3, 4));
		System.out.println(Operation.DIVIDE.eval(10, 4));
	}
}
