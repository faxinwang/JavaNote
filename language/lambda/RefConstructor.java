package lambda;

import javax.swing.JFrame;

@FunctionalInterface
interface RefConst{
	JFrame win(String title);
}

public class RefConstructor {
	
	public static void main(String[] args){
		//使用lambda表达式创建FefConstructer对象
		RefConst rc = title -> new JFrame(title);
		JFrame jf = rc.win("my window!");
		jf.setVisible(true);
		
		//构造器引用代替lambda表达式
		//函数式接口中的全部参数传给该构造器作为参数
		@SuppressWarnings("unused")
		RefConst rc2 = JFrame::new;
		JFrame jf2 = rc.win("我的窗口!");
		jf2.setVisible(true);
	}
}
