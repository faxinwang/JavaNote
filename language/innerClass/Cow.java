package innerClass;

public class Cow {
	private double weight;
	public Cow(){}
	public Cow(double weight){
		this.weight = weight;
	}
	//����һ���Ǿ�̬�ڲ���
	class CowLeg{
		//�Ǿ�̬�ڲ��������ʵ������
		private double length;
		private String color;
		public CowLeg(){}
		public CowLeg(double len,String color){
			this.length = len; 
			this.color = color;
		}
		public double getLength() {
			return length;
		}
		public void setLength(double length) {
			this.length = length;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		//�Ǿ�̬�ڲ����ʵ������
		public void info(){
			System.out.println("��ǰţ����ɫ��:" + color +",�� :" + length);
			//ֱ�ӷ����ⲿ���private��Ա����
			System.out.println("��ţ��������ţ����:" + weight);
			/*����һ�д����У��Ǿ�̬�ڲ���������ⲿ���˽�б�����������Ϊ�ڷǾ�̬�ڲ������������һ����
			 * �������� "�ⲿ�����"������(�����÷Ǿ�̬�ڲ����ʵ������ʱ��������һ���Ǿ�̬�ڲ�����󣬷Ǿ�̬
			 * �ڲ���������Ĵ����ⲿ��ʵ����)
			 * */
		}
	}
	
	public void test(){
		CowLeg cl = new CowLeg(1.12,"�ڰ����");
		cl.info();
	}
	
	public static void main(String[] args){
		Cow cow = new Cow(378.9);
		cow.test();
	}
	
}
