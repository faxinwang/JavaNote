package myjava.util.container;

import static java.lang.System.*;
import java.util.HashSet;
import java.util.Iterator;


/* ���������а���hash���������ڼ������ж�����Ԫ�������ͨ��equals�ȽϷ���true������hashCode()�ķ���ֵҲ���
 * ���ж�����Ԫ���Ƿ����,��hashCode()��ȵ�equals�����ʱ������Ԫ�ؽ�������ͬһ��Ͱ��.
 * ��Ҫ��һ���������ͷŵ�hash������ʱ��Ҫ��дequals������hashCode��������֤��a.equals(b)����trueʱ��
 * a,b��hashCode()�ķ���ֵҲ��ȣ�����hashCode()�ļ��㷽������:
 * 
 * ʵ����������				���㷽ʽ
 * boolean				hashCode = (val?0:1)
 * byte,short,int,char	hashCode = (int)val;
 * long					hashCode = (int)(val ^ (val>>>32))
 * float				hashCode = Float.floatToIntBits(val)
 * double				long lon = Double.doubleToLongBits(val)
 * 						hashCode = (int)(lon ^ (lon>>>32))
 * ��������				hashCode = val.hashCode()
 * Ϊ�˱�����ʵ��������Ӳ���żȻ��ȣ����Խ���ʵ����������һ�����������:
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
		hset.add(new R(5,"str1"));//������һ��Ԫ����ȵ�Ԫ��
		//�����������Կ���,Ԫ����hashSet�е�˳������ǰ���hashCode��С�������е�
		out.println(hset);
		
		Iterator<R> it = hset.iterator();
		while(it.hasNext()){
			R r = it.next();
			if(r.count > 0) out.print(r);
		}
	}

}
