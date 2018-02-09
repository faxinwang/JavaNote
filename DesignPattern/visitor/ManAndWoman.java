package visitor;


//��������ߣ�����������˺�Ů�˵ĳ��󷽷�
abstract class ActionVisitor{
	public abstract void visitMan(Man man);
	public abstract void visitWoman(Woman woman);
}

//����Ԫ������,�������Action���ʵĳ��󷽷�accept
abstract class Person{
	public abstract void accept(ActionVisitor visitor);
}

//---------------����Ԫ��-----------------//
//���˾���Ԫ��ʵ��accept����ʱ����visitor��visitMan����
class Man extends Person{
	public void accept(ActionVisitor visitor) {
		visitor.visitMan(this);
	}
}
//Ů�˾���Ԫ��ʵ��accept����ʱ����visitor��visitWoman����
class Woman extends Person{
	public void accept(ActionVisitor visitor) {
		visitor.visitWoman(this);
	}
}

// -----------���������--------------//

//�ֱ����ʵ�ַ������˺�Ů�˵ķ���

class Success extends ActionVisitor{
	public void visitMan(Man man) {
		System.out.println("���˳ɹ�ʱ,��������һ��ΰ���Ů��");
	}
	public void visitWoman(Woman woman) {
		System.out.println("Ů�˳ɹ�ʱ,��������һ�����ɹ�������");
	}
}

class Failing extends ActionVisitor{
	public void visitMan(Man man) {
		System.out.println("����ʧ��ʱ,��ͷ�Ⱦ�,˭Ҳ����Ȱ");
	}
	public void visitWoman(Woman woman) {
		System.out.println("Ů��ʧ��ʱ,��������,˭ҲȰ����");
	}
}

class Amativeness extends ActionVisitor{
	public void visitMan(Man man) {
		System.out.println("��������ʱ,���²���ҲҪײ��");
	}
	public void visitWoman(Woman woman) {
		System.out.println("Ů������ʱ,���¶�ҲҪײ����");
	}
}

public class ManAndWoman {

	//�����Ҫ��չ���ܣ� ���ӽ���״̬���������˺�Ů�ˣ���ֻ��Ҫ������Ӧ�ķ�������Ϳ�����
	static class Marriage extends ActionVisitor{
		public void visitMan(Man man) {
			System.out.println("���˽��ʱ,�п���:������Ϸ�ս�ʱ,�С��ޡ�ͽ��ң����");
		}
		public void visitWoman(Woman woman) {
			System.out.println("Ů�˽��ʱ,��οԻ:���鳤��·����, �������ձ�ƽ��");
		}
	}
	
	
	public static void main(String[] args) {
		Man man = new Man();
		Woman woman = new Woman();
		
		Success success = new Success();
		man.accept(success);
		woman.accept(success);
		
		Failing failing = new Failing();
		man.accept(failing);
		woman.accept(failing);
		
		Amativeness amativeness = new Amativeness();
		man.accept(amativeness);
		woman.accept(amativeness);
		
		Marriage marriage = new Marriage();
		man.accept(marriage);
		woman.accept(marriage);
	}
}
