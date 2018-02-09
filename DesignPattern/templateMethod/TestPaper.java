package templateMethod;

/* Paper
 *   |--->TestPaperA
 * 	 |--->TestPaperB
 */

//抽象模板类，包含了一个或多个模板方法,模板方法里面由抽象方法(也可以加上具体方法)组成顶层逻辑
abstract class Paper {
	//具体方法1
	public void TestQuestion1() {
		String question = "杨过得到，后来给了郭靖，炼成了倚天剑、屠龙刀的玄铁可能是:\n"
				+ "a.球磨铸铁\t b.马口铁\t c.高速合金钢\t d.碳纤维素";
		System.out.println(question);
		System.out.println("答案:" + Answer1() + "\n");
	}
	//具体方法2
	public void TestQuestion2() {
		String question = "杨过、程英、陆无双产除了情花，造成: \n"
				+ "a.使这种植物不在害人\t b.使一种珍惜动物灭绝\t c.破坏了那个生物圈的生态平衡\t d.造成该地区沙漠化";
		System.out.println(question);
		System.out.println("答案:" + Answer2()+ "\n");
	}
	//具体方法3
	public void TestQuestion3() {
		String question = "蓝凤凰致使华山师徒、桃谷六仙呕吐不止，如果你是大夫，会给他们开什么药:\n"
				+ "a.阿司匹林\t b.牛黄解毒片\t c.氟哌酸\t d.让他们和大量的生牛奶\t e.以上全不对";
		System.out.println(question);
		System.out.println("答案:" + Answer3()+ "\n");
	}
	
	abstract String Answer1();//抽象方法1
	abstract String Answer2();//抽象方法2
	abstract String Answer3();//抽象方法3
}

//具体类A
class TestPaperA extends Paper{
	//TestPaperA对三个抽象方法的实现
	public String Answer1() {return "b";}
	public String Answer2() {return "a";}
	public String Answer3() {return "c";}
}
//具体类B
class TestPaperB extends Paper{
	//TestPaperB对三个抽象方法的实现
	public String Answer1() {return "c";}
	public String Answer2() {return "b";}
	public String Answer3() {return "a";}
}

public class TestPaper{
	public static void main(String[] args) {
		Paper studentA = new TestPaperA();
		Paper studentB = new TestPaperB();
		//由于各具体类
		System.out.println("StudentA的答卷");
		studentA.TestQuestion1();
		studentA.TestQuestion2();
		studentA.TestQuestion3();
		System.out.println("StudentB的答卷");
		studentB.TestQuestion1();
		studentB.TestQuestion2();
		studentB.TestQuestion3();
	}
}