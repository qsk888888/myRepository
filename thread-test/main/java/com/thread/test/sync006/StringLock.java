/**
 * 
 */
package com.thread.test.sync006;

/**
 * @author Mr.Wu
 *
 */
public class StringLock {

	public void stringLock() {
		
		synchronized ("字符串常量") {
			while (true) {
				System.out.println(Thread.currentThread().getName()+"...开始....");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"...结束....");
			}
		}
	}
	
	public static void main(String[] args) {
		
		final StringLock lock=new StringLock();
		
		Thread t1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				lock.stringLock();
			}
		},"t1");
		
		Thread t2=new Thread(new Runnable() {
			
			@Override
			public void run() {
				lock.stringLock();
			}
		},"t2");
		
		t1.start();
		t2.start();
		
		
	}
	
	
	
	
}
