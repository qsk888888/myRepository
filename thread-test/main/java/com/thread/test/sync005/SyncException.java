/**
 * 
 */
package com.thread.test.sync005;

/**
 * @author Mr.Wu
 *
 */
public class SyncException {

	public int i=0;
	
	public synchronized void operation(){
		
		while(true){
			try {
				
				i++;
				Thread.sleep(200);
				System.out.println(Thread.currentThread().getName()+",i="+i);
				if(i==10)
					Integer.parseInt("a");
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("log   info   i="+i);
				//continue;
				//throw new RuntimeException();
			}
		}
	}
	
	public static void main(String[] args) {
		
		
		final SyncException sync = new SyncException();
		Thread t1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				sync.operation();
				
			}
		});
		t1.start();
		
	}
	
	
}
