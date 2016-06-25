package test.net.server;

public class DataTransfer {
	/**
	 * データを格納
	 */
	private byte[] data;

	synchronized public void put(byte[] data) {
		if(this.data != null) {
			try {
				System.out.println("putをブロック");
				wait();
				System.out.println("putのロックを解除");
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.data = data;
		notifyAll();
		System.out.println("putのnotifyAll");
	}

	synchronized public byte[] get() {
		if(data == null) {
			try {
				System.out.println("getをブロック");
				wait();
				System.out.println("getのロックを解除");
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		byte[] result = data;
		data = null;
		notifyAll();
		System.out.println("getのnotifyAll");
		return result;
	}
}
