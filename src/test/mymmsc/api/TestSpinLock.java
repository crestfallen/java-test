/**
 * 
 */
package test.mymmsc.api;

import org.mymmsc.api.atomic.SpinLock;

/**
 * @author wangfeng
 * @date 2016年1月17日 下午7:01:35
 */
public class TestSpinLock implements Runnable {
	static int sum = 1000;
	private SpinLock lock;

	/**
	 * 
	 */
	public TestSpinLock(SpinLock lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		this.lock.lock();
		//this.lock.lock();
		sum++;
		//this.lock.unLock();
		this.lock.unLock();
	}

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws InterruptedException {
		SpinLock lock = new SpinLock();
		for (int i = 0; i < 100; i++) {
			TestSpinLock test = new TestSpinLock(lock);
			Thread t = new Thread(test);
			t.start();
		}

		Thread.currentThread().sleep(1000);
		System.out.println(sum);
	}

}
