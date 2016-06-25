package test.net;

public class DataTransfer {
	/**
	 * データを格納
	 */
	private byte[] data;

	synchronized public void put(byte[] data) {
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

	synchronized public byte[] get() {
		if(data == null) {
			try {
				wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		byte[] result = data;
		data = null;
		notifyAll();
		return result;
	}
}
