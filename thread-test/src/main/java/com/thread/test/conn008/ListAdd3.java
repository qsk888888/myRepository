/**
 * 
 */
package com.thread.test.conn008;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 *阿里巴巴面试题
 *	在上一个类中存在什么问题？
 *	notify()不释放锁,必须等到t1线程执行完毕之后,t2才能收到通知,也就是不存在不实时的问题
 *	
 *	假如在100万条数据中查询需要的数据时,t1、t2为两个程序,如果按照上边的代码来写肯定存在很大的问题
 *	如果说t1在查询到第五条数据时,就查询到了想要的数据,应该即使将数据传递给t2程序.肯定不能等到100万条
 *	数据查询完后,才传递给t2
 *
 */
public class ListAdd3 {
	List<String> list=new ArrayList<String>();
	public void add(){
		list.add("bw");
	}
	public int size(){
		return list.size();
	}

	public static void main(String[] args) {
		
		//final Object lock=new Object();
		final CountDownLatch countDownLatch=new CountDownLatch(1);
		final ListAdd3 list2 = new ListAdd3();
		Thread t1=new Thread(new Runnable() {
			@Override
			public void run() {
				//synchronized (lock) {
					for (int i = 0; i < 10; i++) {
						list2.add();
						System.out.println("新添加一个元素....");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if(list2.size()==5){
							//lock.notify();
							countDownLatch.countDown();
							System.out.println("t1发出通知.....");
						}
					}
				}
			//}
		},"t1");
		Thread t2=new Thread(new Runnable() {
			@Override
			public void run() {
				//synchronized (lock) {
					if(list2.size()!=5){
						try {
							//lock.wait();
							countDownLatch.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("已经收到通知....");
					throw new RuntimeException();
				}
				
			//}
		},"t2");
		
		t2.start();
		t1.start();
		
		
		
		
	}
	
	
	
}
