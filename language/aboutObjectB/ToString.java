package aboutObjectB;

public class ToString {
	int a;
	double b;
	String str;
	ToString(int a,double b,String str){
		this.a = a ;
		this.b = b ;
		this.str = str ;
	}
	public String toString(){
		return "ToString[a = "+a+",b = "+ b+",str = "+str+"]";
	}
	
	public static void main(String[] args){
		System.out.println(new ToString(1,3.3,"Beautiful!"));
	}
}
