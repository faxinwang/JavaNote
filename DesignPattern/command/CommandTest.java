package command;

interface Command{
	//�ӿ��ﶨ��process�������ڷ�װ��������Ϊ��
	void process(int[] target);
}

class ArrayProcessor{
	//porcess()�����������"������Ϊ"��cmdȷ��
	public void process(int[] target, Command cmd){
		cmd.process(target);
	}
}

/* ����PrintCommand��PrintCommand����ʵ������ԣ�ʵ��������Ĳ��־���process(int[] target)������
 * �÷����ķ�������Ǵ���ArrayProcessor�����process()�����ġ�������Ϊ��,ͨ�����ַ�ʽ�Ϳ���ʵ��process()
 * �����͡�������Ϊ���ķ���.
 * */
class PrintCommand implements Command{
	//��ӡ��������Ԫ��
	public void process(int[] a){
		for(int tmp : a)
		System.out.println("�������Ŀ�������Ԫ��:"+ tmp);
	}
}

class AddCommand implements Command{
	//������Ԫ�غ�
	public void process(int[] a){
		int sum = 0;
		for(int tmp : a){
			sum += tmp;
		}
		System.out.println("����Ԫ�ص��ܺ�Ϊ:" + sum);
	}
}

public class CommandTest {
	public static void main(String[] args){
		int[] target = {3,-4,5,6,7};
		ArrayProcessor ap = new ArrayProcessor();
		//��һ�δ������飬������Ϊȡ����PrintCommand
		ap.process(target, new PrintCommand());
		//�ڶ��δ������飬������Ϊȡ����AddCommand
		ap.process(target, new AddCommand());
	}
}
