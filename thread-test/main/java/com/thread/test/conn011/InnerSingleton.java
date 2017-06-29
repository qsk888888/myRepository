/**
 * 
 */
package com.thread.test.conn011;

/**
 * @author Mr.Wu
 *
 */
public class InnerSingleton {

	//静态内部类
	private static class Singletion{
		private static Singletion singletion=new Singletion();
	}
	
	public static Singletion getInstance(){
		return Singletion.singletion;
	}
	
}
