/**
 * 
 */
package com.thread.test.conn009;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
//模拟队列
public class MyQueue {

	
	//1、需要一个承装元素的集合
	
	private  LinkedList<Object> list=new LinkedList<Object>();
	
	//2、需要一个计数器
	private AtomicInteger count=new AtomicInteger(0);
	
	//3、需要制定上限和下限
	private final  int minSize=0;
	
	private int maxSize;//在创建队列时，指定长度
	//4、构造方法
	public MyQueue(int size){
		maxSize=size;
	}
	//5、初始化一个对象锁
	private final Object lock=new Object();
	
	public void put(Object obj){
		synchronized (lock) {
			while (count.get()==this.maxSize) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//将元素添加到集合中
			list.add(obj);
			//计数器计数
			count.incrementAndGet();
			//唤醒可能在等待的另外一个线程
			lock.notify();
			//打印
			System.out.println("新加入的元素为:"+obj);
		}
	}
	
	public Object take() {
		Object ret=null;
		synchronized (lock) {
			while (count.get()==this.minSize) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//移除元素操作
			ret=list.removeFirst();
			//计数器递减
			count.decrementAndGet();
			//唤醒另一个线程
			lock.notify();
		}
		return ret;
	}
	
	public int getSize(){
		return list.size();
	}
	
	public static void main(String[] args) {
		
		final MyQueue mq=new MyQueue(5);
		
		mq.put("a");
		mq.put("b");
		mq.put("c");
		mq.put("d");
		mq.put("e");
		System.out.println("当前容器的长度："+mq.getSize());
		
		Thread t1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				mq.put("g");
				mq.put("h");
				
			}
		},"t1");
		t1.start();
		
		Thread t2=new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				Object o1 = mq.take();
				System.out.println("移除的元素为:"+o1);
				Object o2 = mq.take();
				System.out.println("移除的元素为:"+o2);
			}
		},"t2");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.start();
	}
}
