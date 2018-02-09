package myjava.util.regex;

import java.util.Arrays;

public class StringRegEx {
	public static void main(String[] args){
		String[] msgs = {
				"Java has regualr expressions in 1.4",
				"regular expression now expressing in java",
				"Java represses oracular expressions"
		};
		for(String  msg:msgs){
			System.out.println(msg.replaceFirst("re\\w*", "hehe"));
			System.out.println(Arrays.toString(msg.split(" ")));
		}
	}
}
