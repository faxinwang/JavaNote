package basic_grammer;


public class Switch {

	public static void switchTest(String season){
		switch(season){
		case "春天":
			System.out.println("春暖花开");
			break;
		case "夏天":
			System.out.println("夏日炎炎");
			break;
		case "秋天":
			System.out.println("秋高气爽");
			break;
		case "冬天":
			System.out.println("冬雪皑皑");
			break;
		default :
			System.out.println("季节错误!");
		}
	}
	
	public static void main(String args[]){
		switchTest("春天");
		switchTest("明天");
	}

}
