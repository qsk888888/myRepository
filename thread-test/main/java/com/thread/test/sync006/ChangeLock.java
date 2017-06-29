/**
 * 
 */
package com.thread.test.sync006;

/**
 * @author Mr.Wu
 *
 */
public class ChangeLock {

	
	private String lock="lock";
	public void changeLock(){
		
		synchronized (lock) {
			
			try {
				System.out.println(Thread.currentThread().getName()+".....开始");
				lock="lock change";
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName()+".....结束");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) throws Exception {
		
		
		final ChangeLock clock = new ChangeLock();
		
		Thread t1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				clock.changeLock();
				
			}
		});
		
		Thread t2=new Thread(new Runnable() {
			
			@Override
			public void run() {
				clock.changeLock();
			}
		});
		
		t1.start();
		Thread.sleep(100);
		t2.start();
		
		
	}
	
	
	
}
