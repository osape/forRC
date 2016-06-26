package test.net.server;

/**
 * データを受信するオブジェクト
 *
 * @author "Osamu Takahashi"
 *
 */
public class DataReceive {
	/**
	 * データを格納
	 */
	private byte[] data;

	/**
	 * 送信オブジェクト
	 */
	private DataSend send;

	/**
	 * メッセージ番号
	 */
	private long messageNo = 0;

	/**
	 *
	 * @param send 送信オブジェクト
	 */
	public DataReceive(DataSend send) {
		this.send = send;
	}

	public DataReceive() {

	}

	synchronized public void receive(byte[] data) {
		messageNo++;
		this.data = send.getData();
		if(this.data != null) {
			try {
				//System.out.println(this.data[0] + " : receiveをブロック");
				wait();
				//System.out.println("data=null : receiveのロックを解除");
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		send.setData(data);
		synchronized (send) {
			send.notifyAll();
		}
		//System.out.println(data[0] + " : receiveのnotify");
	}

	public void setSend(DataSend send) {
		this.send = send;
	}
}
