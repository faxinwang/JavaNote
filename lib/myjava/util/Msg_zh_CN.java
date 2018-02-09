package myjava.util;

import java.util.ListResourceBundle;
/*用类文件代替资源文件:
 * 1.该类的类名必须以baseName_language_country命名，与属性文件命名相似
 * 2.该类必须继承ListResourceBoundle,并重写getContents()方法,
 *   该方法返回Object对象数组，该数组的每一项都是key-value对
 */

public class Msg_zh_CN extends ListResourceBundle{

	//定义资源,资源里的每一个元素都是key-value对
	private Object myData[][] ={
			{"hello","你好!"},
			{"good night","晚安"}
	};
	@Override
	protected Object[][] getContents() {
		return myData;
	}
	
}
