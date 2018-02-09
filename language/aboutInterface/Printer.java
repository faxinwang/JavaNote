package aboutInterface;

import java.util.Date;

interface Product{
	Date getProductTime();
}
//��Printerʵ��output��Product�ӿ�
public class Printer implements Output,Product{
	private String[] printData = new String[MAX_CACHE_LINE];
	private int dataNum = 0;//���Լ�¼��ǰ��Ҫ��ӡ����ҵ��
	public void out(){
		while( dataNum > 0){
			System.out.println("��ӡ����ӡ:" + printData[0]);
			System.arraycopy(printData, 1, printData, 0, --dataNum);
		}
	}
	
	public void getData(String msg){
		if(dataNum >= MAX_CACHE_LINE){
			System.out.println("����������������ʧ��!");
		}else{
			printData[dataNum++] = msg;
		}
	}
	
	public Date getProductTime(){
		return new Date();
	}
	
	public static void main(String[] args){
		//����һ��Printer���󣬵���Outputʹ��
		Output o = new Printer();
		o.getData("������Java EE��ҵӦ��ʵս");
		o.getData("���Java����");
		o.out();
		o.getData("���Android����");
		o.getData("���Ajax����");
		o.out();
		//����Output�ӿ��е�Ĭ��(��ͨ)����
		o.print("�����","��˽�","�׹Ǿ�");
		o.test();
		
		//����һ��Printer���󣬵���Product��
		Product p = new Printer();
		System.out.println(p.getProductTime());
		//�����������͵ı���������ֱ�Ӹ�ֵ��Object���͵ı���
		@SuppressWarnings("unused")
		Object obj = p;
	}
	
}
