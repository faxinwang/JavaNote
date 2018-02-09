package visitor;


//抽象访问者，定义访问男人和女人的抽象方法
abstract class ActionVisitor{
	public abstract void visitMan(Man man);
	public abstract void visitWoman(Woman woman);
}

//抽象元素人类,定义接受Action访问的抽象方法accept
abstract class Person{
	public abstract void accept(ActionVisitor visitor);
}

//---------------具体元素-----------------//
//男人具体元素实现accept方法时调用visitor的visitMan方法
class Man extends Person{
	public void accept(ActionVisitor visitor) {
		visitor.visitMan(this);
	}
}
//女人具体元素实现accept方法时调用visitor的visitWoman方法
class Woman extends Person{
	public void accept(ActionVisitor visitor) {
		visitor.visitWoman(this);
	}
}

// -----------具体访问者--------------//

//分别各自实现访问男人和女人的方法

class Success extends ActionVisitor{
	public void visitMan(Man man) {
		System.out.println("男人成功时,背后多半有一个伟大的女人");
	}
	public void visitWoman(Woman woman) {
		System.out.println("女人成功时,背后大多有一个不成功的男人");
	}
}

class Failing extends ActionVisitor{
	public void visitMan(Man man) {
		System.out.println("男人失败时,闷头喝酒,谁也不用劝");
	}
	public void visitWoman(Woman woman) {
		System.out.println("女人失败时,泪眼汪汪,谁也劝不了");
	}
}

class Amativeness extends ActionVisitor{
	public void visitMan(Man man) {
		System.out.println("男人恋爱时,凡事不懂也要撞懂");
	}
	public void visitWoman(Woman woman) {
		System.out.println("女人恋爱时,凡事懂也要撞不懂");
	}
}

public class ManAndWoman {

	//如果需要扩展功能， 增加结婚的状态来考查男人和女人，则只需要增加相应的访问者类就可以了
	static class Marriage extends ActionVisitor{
		public void visitMan(Man man) {
			System.out.println("男人结婚时,感慨道:恋爱游戏终结时,有‘妻’徒刑遥无期");
		}
		public void visitWoman(Woman woman) {
			System.out.println("女人结婚时,欣慰曰:爱情长跑路漫漫, 婚姻保险保平安");
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
