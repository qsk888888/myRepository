/**
 * 
 */
package com.thread.test.conn011;

/**
 * @author Mr.Wu
 *
 */
public class DubbleSingleton {

	private static DubbleSingleton dubbleSingleton;
	
	private DubbleSingleton(){}
	
	public static DubbleSingleton getDubbleSingleton(){
		
		if(dubbleSingleton==null){
			synchronized (DubbleSingleton.class) {
				if(dubbleSingleton==null){
					dubbleSingleton=new DubbleSingleton();
				}
				
			}
		}
		
		
		return dubbleSingleton;
	}
	
	public static void main(String[] args) {
		
		
		Thread t1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.err.println(DubbleSingleton.getDubbleSingleton().hashCode());
			}
		});
		Thread t2=new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.err.println(DubbleSingleton.getDubbleSingleton().hashCode());
			}
		});
		Thread t3=new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.err.println(DubbleSingleton.getDubbleSingleton().hashCode());
			}
		});
		
		t1.start();
		t2.start();
		t3.start();
	}
	
	
	
	
}
