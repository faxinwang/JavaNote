package myjava.util;

import java.util.ListResourceBundle;
/*�����ļ�������Դ�ļ�:
 * 1.���������������baseName_language_country�������������ļ���������
 * 2.�������̳�ListResourceBoundle,����дgetContents()����,
 *   �÷�������Object�������飬�������ÿһ���key-value��
 */

public class Msg_zh_CN extends ListResourceBundle{

	//������Դ,��Դ���ÿһ��Ԫ�ض���key-value��
	private Object myData[][] ={
			{"hello","���!"},
			{"good night","��"}
	};
	@Override
	protected Object[][] getContents() {
		return myData;
	}
	
}
