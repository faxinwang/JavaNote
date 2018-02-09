package builder;
import java.util.ArrayList;
import java.util.List;

/* ������ģʽ(Builder)��Ҫ���ڴ���һЩ���ӵĶ�����Щ�����ڲ�������Ľ���˳��ͨ��
 * ���ȶ��ģ��������ڲ��Ĺ���ͨ�������Ÿ��ӵı仯�������ߵĺô�����ʹ�ù����������ʾ��
 * ����룬���ڽ����������˸ò�Ʒ�������װ�ģ���������Ҫ�ı�һ����Ʒ���ڲ���ʾ��ֻ��Ҫ
 * �ٶ���һ������Ľ����߾Ϳ����ˡ����Խ�����ģʽ���ڵ��������Ӷ�����㷨Ӧ�ö������
 * �������ɲ����Լ����ǵ�װ�䷽ʽʱ���õ�ģʽ��
 */

/* Product(��Ʒ��)
 * 
 * Builder(���󴴽�����,Ϊ����һ��Product����ĸ�����ָ������Ľӿ�)
 *	  |------>ConcreteBuilderA(ʵ����Builder�ĳ��󷽷�) 		
 * 	  |------>ConcreteBuilderB(ʵ����Builder�ĳ��󷽷�)
 * 
 * Director(ָ�����࣬��װ��Ʒ�Ĵ�������)
 */

/* ������ģʽ��ģ�巽��ģʽ��Щ����,��֮ͬ����:
 * 1.ģ�巽��ģʽ�ǽ�ģ�巽�������˳�������,ģ�巽���ķ������ɳ�����ĳ��������ɶ����߼���
 * 	 ��ͬ������ʵ�ֿ����Բ�ͬ�ķ�ʽ(��ͬ�Ĳ���)��ɶ����߼���ʵ�֡���������ģʽ�Ķ����߼��Ƿ�����
 * 	������Director���С�
 * 2.ģ�巽��ģʽ��û��Product�࣬Ҳû��Director�࣬
 * 3.ģ�巽���������������ϸ�����в�ͬʵ�ֵ������ʱ��������������ǱȽϹ̶��ģ���������
 *   ģʽ���ʺ��������ṹ���ӵĶ��󣬲�����Щ����ĸ������������в�ͬ���ױ仯��ʵ�֡���Щʵ��
 *   �ǽ��������ConcreteBuilder��ȥ��ɵģ���ͬ��Builder��ͨ���в�ͬ�Ĳ���ʵ�֡�
 */

//��Ʒ
class Product{
	List<String> parts = new ArrayList<>();
	//��Ӳ�Ʒ����
	public void add(String part) {parts.add(part);}
	//�о����еĲ�Ʒ����
	public void show() {
		System.out.println("��Ʒ����:");
		for (String part : parts) {
			System.out.println(part);
		}
	}
}

//�����Ʒ�����ߣ����崴����Ʒ��Ҫʵ�ֵĹ��ܻ����
abstract class Builder{
	public abstract void BuildPartA();
	public abstract void BuildPartB();
	public abstract Product getResult();
}

//----��������������࣬�����в�ͬ��ʵ��
class ConcreteBuilder1 extends Builder{
	private  Product product = new Product();
	@Override
	public void BuildPartA() {
		product.add("����A");
	}
	@Override
	public void BuildPartB() {
		product.add("����B");
	}
	@Override
	public Product getResult() {
		return product;
	}
}

class ConcreteBuilder2 extends Builder{
	private Product product = new Product();
	@Override
	public void BuildPartA() {
		product.add("����X");
	}
	@Override
	public void BuildPartB() {
		product.add("����Y");
	}
	@Override
	public Product getResult() {
		return product;
	}
}

//ָ�����࣬��װ��Ʒ�Ĵ�������
class Director{
	public Product Construct(Builder builder) {
		builder.BuildPartA();
		builder.BuildPartB();
		return builder.getResult();
	}
}

public class BuilderTemplate {
	public static void main(String[] args) {
		Director director = new Director();
		Builder b1 = new ConcreteBuilder1();
		Builder b2 = new ConcreteBuilder2();
		
		Product p1 = director.Construct(b1);
		p1.show();
		System.out.println();
		Product p2 = director.Construct(b2);
		p2.show();
	}
}
