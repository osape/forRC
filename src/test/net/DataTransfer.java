package test.net;

public class DataTransfer {
	/**
	 * データを格納
	 */
	private int[] data;

	synchronized public void put(int[] data) {
		if(data != null) {
			try {
				wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			this.data = data;
			notifyAll();
		}
	}
}
