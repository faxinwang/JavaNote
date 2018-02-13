package facade;


class StockA{
	public void Buy() {
		System.out.println("��ƱA ����1��");
	}
	public void Sell() {
		System.out.println("��ƱA ����1��");
	}
}

class StockB{
	public void Buy() {
		System.out.println("��ƱB ����1��");
	}
	public void Sell() {
		System.out.println("��ƱB ����1��");
	}
}

class StockC{
	public void Buy() {
		System.out.println("��ƱC ����1��");
	}
	public void Sell() {
		System.out.println("��ƱC ����1��");
	}
}

class NationalDebat{
	public void Buy() {
		System.out.println("��ծ ����1��");
	}
	public void Sell() {
		System.out.println("��ծ ����1��");
	}
}

class Realty{
	public void Buy() {
		System.out.println("���ز� ����1��");
	}
	public void Sell() {
		System.out.println("���ز� ����1��");
	}
}

/*
 * Fund���൱�ڹ�Ʊ���Facade�����ڳ�����Ҫѧϰ�ܶ���ص�֪ʶ�����ҹ������Ҫ���ɾ���Ҫ
 * ȥ��ע��ʮ������֧��Ʊ�Ķ�̬��Ȼ��������������(Fund)�Ļ�����������Ҫѧϰ�ܶ�רҵ֪ʶ��
 * ����Ҳ���û�ʱ��ȥ��ע�ɰ���ǧ֮��Ʊ�Ķ�̬����ͼ����˹���ĸ�����
 */
class Fund{
	private StockA stockA;
	private StockB stockB;
	private StockC stockC;
	private NationalDebat nd;
	private Realty realty;
	
	public Fund() {
		stockA = new StockA();
		stockB = new StockB();
		stockC = new StockC();
		nd = new NationalDebat();
		realty = new Realty();
	}
	
	public void Buy() {
		stockA.Buy();
		stockB.Buy();
		stockC.Buy();
		nd.Buy();
		realty.Buy();
	}
	
	public void Sell() {
		stockA.Sell();
		stockB.Sell();
		stockC.Sell();
		nd.Sell();
		realty.Sell();
	}
}


public class StockFacade {
	public static void main(String[] args) {
		Fund fund = new Fund();
		fund.Buy();
//		��ƱA ����1��
//		��ƱB ����1��
//		��ƱC ����1��
//		��ծ ����1��
//		���ز� ����1��
		System.out.println();
		fund.Sell();
//		��ƱA ����1��
//		��ƱB ����1��
//		��ƱC ����1��
//		��ծ ����1��
//		���ز� ����1��
	}
}
