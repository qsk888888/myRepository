/**
 * 
 */
package com.thread.test.sync007;

/**
 * 
 * JDK在执行一个线程时，都会单独分配一个内存空间
 * java1.5以后，每一个线程优化，加了一块独立的内存空间，装主内存中一些引用变量，作为副本,
 * 这样线程在执行的时候,效率会更高一些
 * 
 *
 */
public class RunThread extends Thread {

	private  boolean isRunning=true;
	
	
	private void setRunning(boolean isRunning){
		this.isRunning=isRunning;
	}
	
	public void run() {
		System.out.println("进入run方法......");
		while (isRunning==true) {
		}
		System.out.println("线程终止......");
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		
		RunThread rt = new RunThread();
		rt.start();
		Thread.sleep(3000);
		rt.setRunning(false);
		System.out.println("isRunning的值已经被设置为false");
		/*Thread.sleep(1000);
		System.out.println(rt.isRunning);*/
		
	}
	
	
	
}
