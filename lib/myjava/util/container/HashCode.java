package myjava.util.container;

import static java.lang.System.*;
import java.util.HashSet;
import java.util.Iterator;


/* 集合名字中包含hash的容器，在集合中判断两个元素相等是通过equals比较返回true并且其hashCode()的返回值也相等
 * 来判断两个元素是否相等,当hashCode()相等但equals不相等时，两个元素将被放在同一个桶中.
 * 当要把一种数据类型放到hash容器中时，要重写equals方法和hashCode方法，保证当a.equals(b)返回true时，
 * a,b的hashCode()的返回值也相等，其中hashCode()的计算方法如下:
 * 
 * 实例变量类型				计算方式
 * boolean				hashCode = (val?0:1)
 * byte,short,int,char	hashCode = (int)val;
 * long					hashCode = (int)(val ^ (val>>>32))
 * float				hashCode = Float.floatToIntBits(val)
 * double				long lon = Double.doubleToLongBits(val)
 * 						hashCode = (int)(lon ^ (lon>>>32))
 * 引用类型				hashCode = val.hashCode()
 * 为了避免多个实例变量相加产生偶然相等，可以将个实例变量乘以一个质数再相加:
 *  return v1.hashCode() * 19 + (int)v2 * 31;
 */

class R{
	int count;
	String str;
	public R(int count,String str){
		this.count = count;
		this.str = str;
	}
	public String toString(){
		return "\nR[count:" + count+ " "+ str + "]";
	}
	public boolean equals(Object obj){
		if(this==obj) return true;
		if(obj !=null && obj.getClass() == R.class){
			R r = (R)obj;
			return this.count == r.count
					&& str.equals(r.str);
		}
		return false;
	}
	public int hashCode(){
		return count + str.hashCode() * 19;
	}
}

public class HashCode {
	
	public static void main(String[] args) {
		HashSet<R> hset = new HashSet<R>();
		hset.add(new R(5,"str1"));
		hset.add(new R(-1,"str2"));
		hset.add(new R(9,"str3"));
		hset.add(new R(-2,"str4"));
		hset.add(new R(-5,"strstr"));
		hset.add(new R(5,"str2"));
		hset.add(new R(4,"str1"));
		hset.add(new R(-1000,"a"));
		hset.add(new R(1000,"a"));
		hset.add(new R(5,"str1"));//添加与第一个元素相等的元素
		//从输出结果可以看出,元素在hashSet中的顺序大致是按照hashCode由小到大排列的
		out.println(hset);
		
		Iterator<R> it = hset.iterator();
		while(it.hasNext()){
			R r = it.next();
			if(r.count > 0) out.print(r);
		}
	}

}
