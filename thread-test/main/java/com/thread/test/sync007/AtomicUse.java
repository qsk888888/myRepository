/**
 * 
 */
package com.thread.test.sync007;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Mr.Wu
 *
 */
public class AtomicUse {

	
	private static AtomicInteger count=new AtomicInteger(0);
	//多个addAndGet方法在一个方法内是非原子性的,需要加synchronized进行修饰,保证多个addAndGet方法的原子性
	public    int multiAdd(){
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		count.addAndGet(1);
		count.addAndGet(2);
		count.addAndGet(3);
		count.addAndGet(4);
		return count.get();//整10
	}
	
	public static void main(String[] args) {
		
		final AtomicUse au=new AtomicUse();
		
		List<Thread> list=new ArrayList<Thread>();
		
		for (int i = 0; i < 100; i++) {
			list.add(new Thread(new Runnable() {
				public void run() {
					System.out.println(au.multiAdd());
				}
			}));
		}
		
		for (Thread thread : list) {
			
			thread.start();
			
		}
		
		
		
	}
	
	
	
	
	
	
	
	
}
