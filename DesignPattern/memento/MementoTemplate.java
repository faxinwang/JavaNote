package memento;

/* �ڲ��ƻ���װ�Ե�ǰ���£�����һ��������ڲ�״̬�����ڸö���֮�Ᵽ�����״̬������
 * �Ժ�Ϳ��Խ��ö���ָ���ԭ�ȱ����״̬��
 * Originator(������):���𴴽�һ������¼Memento�����Լ�¼��ǰʱ�������ڲ�
 * ״̬������ʹ�ñ���¼�ָ��ڲ�״̬��Originator������Ҫ����Memento�洢������Щ
 * �ڲ�״̬��
 * Memento(����¼):����洢Originator������ڲ�״̬���ݣ����ɷ���Originator
 * ���������������ʱ���¼Memento������¼�������ӿڣ�Caretakerֻ�ܿ���խ�ӿڣ���ֻ��
 * ������¼���ݸ���������Originator�����ܹ�����һ����ӿڣ����������ʷ��ص���ǰ״̬��
 * ��Ҫ���������ݡ�
 * Caretaker(������):���𱣴�ñ���¼Memento�����ܶԱ���¼�����ݽ��в������顣
 */

//��Ҫ���б��汸�����ݵ���
class Originator{
	private String state;
	public void setState(String state) {
		this.state = state;
	}
	public String getState() {return state;}
	//����һ��Memento���󲢽�Ҫ��������ݴ��롣
	public Memento createMemento() {
		return new Memento(state); //��������
	}
	
	public void setMemento(Memento memento) {
		state = memento.state; //�ָ�����
	}
	
	public void show() {
		System.out.println("state: " + state);
	}
}


//���汸�����ݵ���
class Memento{
	String state;
	public Memento(String state) {
		this.state = state;
	}
	public String getState() {
		return state;
	}
}

//��������
class Caretaker{
	private Memento memento;
	public Memento getMemento() {return memento;}
	public void setMemento(Memento memento) {this.memento = memento;}
}

public class MementoTemplate {
	public static void main(String[] args) {
		Originator originator = new Originator();
		originator.setState("on");
		originator.show();
		
		//��������
		Caretaker caretaker = new Caretaker();
		caretaker.setMemento(originator.createMemento());
		
		//�ı�״̬
		originator.setState("off");
		originator.show();
		
		//�ָ�����
		originator.setMemento(caretaker.getMemento());
		originator.show();
	}
}
