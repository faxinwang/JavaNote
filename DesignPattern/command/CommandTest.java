package command;

interface Command{
	//接口里定义process方法用于封装“处理行为”
	void process(int[] target);
}

class ArrayProcessor{
	//porcess()方法，其具体"处理行为"由cmd确定
	public void process(int[] target, Command cmd){
		cmd.process(target);
	}
}

/* 对于PrintCommand和PrintCommand两个实现类而言，实际有意义的部分就是process(int[] target)方法，
 * 该方法的方法体就是传入ArrayProcessor类里的process()方法的“处理行为”,通过这种方式就可以实现process()
 * 方法和“处理行为”的分离.
 * */
class PrintCommand implements Command{
	//打印数组所有元素
	public void process(int[] a){
		for(int tmp : a)
		System.out.println("迭代输出目标数组的元素:"+ tmp);
	}
}

class AddCommand implements Command{
	//求数组元素和
	public void process(int[] a){
		int sum = 0;
		for(int tmp : a){
			sum += tmp;
		}
		System.out.println("数组元素的总和为:" + sum);
	}
}

public class CommandTest {
	public static void main(String[] args){
		int[] target = {3,-4,5,6,7};
		ArrayProcessor ap = new ArrayProcessor();
		//第一次处理数组，处理行为取决于PrintCommand
		ap.process(target, new PrintCommand());
		//第二次处理数组，处理行为取决于AddCommand
		ap.process(target, new AddCommand());
	}
}
