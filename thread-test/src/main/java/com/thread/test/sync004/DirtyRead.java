/**
 * 
 */
package com.thread.test.sync004;

/**
 * 业务整体需要使用完整的synchronized，保持业务的原子性
 *
 */
public class DirtyRead {

	private String username="bjsxt";
	
	private String passwrod="123";
	
	public synchronized void setValue(String username,String password){
		
		this.username=username;
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.passwrod=password;
		
		System.out.println("setValue的最终结果,username"+username+"\tpassword="+password);
		
		
	}
	
	public  void getValue(){
		System.out.println("getValue方法得到的：username="+username+"\tpassword="+this.passwrod);
	}
	public static void main(String[] args) throws Exception {
		
		final DirtyRead dr=new DirtyRead();
		Thread t1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				dr.setValue("bw", "456");
				
			}
			
		});
		t1.start();
		Thread.sleep(1000);
		
		dr.getValue();
	}
	
	
	
	
}
