/**
 * 
 */
package com.thread.test.conn008;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.Wu
 *
 */
public class ListAdd2 {
	List<String> list=new ArrayList<String>();
	public void add(){
		list.add("bw");
	}
	public int size(){
		return list.size();
	}

	public static void main(String[] args) {
		
		final Object lock=new Object();
		final ListAdd2 list2 = new ListAdd2();
		Thread t1=new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (lock) {
					for (int i = 0; i < 10; i++) {
						list2.add();
						System.out.println("新添加一个元素....");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if(list2.size()==5){
							lock.notify();
							System.out.println("t1发出通知.....");
						}
					}
				}
			}
		},"t1");
		Thread t2=new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (lock) {
					if(list2.size()!=5){
						System.out.println(list2.size());
						try {
							lock.wait();
							System.out.println("----------");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("已经收到通知....");
					throw new RuntimeException();
				}
				
			}
		},"t2");
		
		t2.start();
		t1.start();
		
		
		
		
	}
	
	
	
}
