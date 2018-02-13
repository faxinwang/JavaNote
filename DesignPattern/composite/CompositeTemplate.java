package composite;

import java.util.ArrayList;
import java.util.List;

/* 组合模式(Composite):将对象组合成树形结构以表示“部分-整体”的层次结构。
 * 组合模式使得用户对单个对象和组合对象的使用具有一致性。
 */

abstract class Component{
	protected String name;
	public Component(String name) {
		this.name = name;
	}
	//使用add和remove增加和删除组件
	public abstract void add(Component c);
	public abstract void remove(Component c);
	public abstract void display(int depth);
}

//叶结点
class Leaf extends Component{
	public Leaf(String name) {
		super(name);
	}
	public void add(Component c) {
		System.out.println("cannot add to a leaf");
	}
	public void remove(Component c) {
		System.out.println("cannot remove from a leaf");
	}
	public void display(int depth) {
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<depth; ++i) builder.append('-');
		builder.append(name);
		System.out.println(builder.toString());
	}
}

//枝结点
class Composite extends Component{
	private List<Component> list = new ArrayList<>();
	public Composite(String name) {
		super(name);
	}
	public void add(Component c) {
		list.add(c);
	}
	public void remove(Component c) {
		list.remove(c);
	}
	public void display(int depth) {
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<depth; ++i) builder.append('-');
		builder.append(name);
		System.out.println(builder.toString());
		for(Component c : list) {
			c.display(depth + 2);
		}
	}
}

public class CompositeTemplate {
	public static void main(String[] args) {
		Composite root = new Composite("root");
		root.add(new Leaf("Leaf A"));
		root.add(new Leaf("Leaf B"));
		
		Composite comp1 = new Composite("branch X");
		comp1.add(new Leaf("Leaf XA"));
		comp1.add(new Leaf("Leaf XB"));
		root.add(comp1);
		
		Composite comp2 = new Composite("branch XX");
		comp2.add(new Leaf("Leaf XXA"));
		comp2.add(new Leaf("Leaf XXB"));
		comp1.add(comp2);
		
		root.add(new Leaf("Leaf C"));
		
		Leaf leaf = new Leaf("Leaf D");
		root.add(leaf);
		root.display(1);
//		-root
//		---Leaf A
//		---Leaf B
//		---branch X
//		-----Leaf XA
//		-----Leaf XB
//		-----branch XX
//		-------Leaf XXA
//		-------Leaf XXB
//		---Leaf C
//		---Leaf D
		System.out.println("\nremove leaf D");
		root.remove(leaf);
		root.display(1);
//		remove leaf D
//		-root
//		---Leaf A
//		---Leaf B
//		---branch X
//		-----Leaf XA
//		-----Leaf XB
//		-----branch XX
//		-------Leaf XXA
//		-------Leaf XXB
//		---Leaf C

	}
}
