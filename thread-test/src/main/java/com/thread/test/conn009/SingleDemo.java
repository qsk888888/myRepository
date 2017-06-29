/**
 * 
 */
package com.thread.test.conn009;

/**
 * @author Mr.Wu
 *
 */
public class SingleDemo {

	
	//1、构造方法私有化
	private SingleDemo (){
	}
	//2、提供一个静态的全局成员变量
	private static SingleDemo singleDemo;
	//3、提供一个公共的静态的访问方法
	
	public static SingleDemo getSingleDemo(){
		if(singleDemo==null){
			synchronized (SingleDemo.class) {
				if(singleDemo==null){
					singleDemo=new SingleDemo();
				}
			}
		}
		return singleDemo;
		
	}
	
	
	
}
