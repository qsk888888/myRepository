/**
 * 
 */
package com.thread.test.sync007;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Mr.Wu
 *
 *volatile具备可见性,但是不具备原子性(同步synchronized)
 *这里可以使用原子类AtomicInteger
 *
 */
public class VolatileNoAtomic extends Thread {

	private static AtomicInteger count=new AtomicInteger();
	//private volatile static Integer count=0;
	private  static void addCount(){
		for (int i = 0; i < 1000; i++) {
			count.incrementAndGet();//==count++
			//count++;
		}
		System.out.println(count);
	}
	
	
	@Override
	public void run() {
		addCount();
	}
	
	
	public static void main(String[] args) {
		
		VolatileNoAtomic[] arr=new VolatileNoAtomic[10];
		
		for (int i = 0; i < 10; i++) {
			arr[i]=new VolatileNoAtomic();
		}
		for (int i = 0; i < 10; i++) {
			arr[i].start();
		}
		
		
	}
	
	
}
