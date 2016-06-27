package test.net.server;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * データを送信する
 * @author "Osamu Takahashi"
 *
 */
public class DataSend {
	/**
	 * データ
	 */
	private byte[] data;

	/**
	 * 受信オブジェクト
	 */
	private DataReceive receive;

	/**
	 * データを格納
	 */
	private ArrayList<byte[]> dataArray;

	/**
	 * getメソッドの呼び出し回数
	 * クライアント数分の実行をカウントする
	 */
	private int count = 0;

	/**
	 * 子スレッド
	 */
	private HashSet<Thread> childThreads;

	/**
	 * スレッドオブジェクトの一致数
	 */
	private int sameCount;

	public DataSend() {
		dataArray = new ArrayList<byte[]>();
		childThreads = new HashSet<Thread>();
	}

	public DataSend(DataReceive receive) {
		this.receive = receive;
	}

	/**
	 * データがnullであるかどうかを調べる為に利用する
	 * @return データ
	 */
	public byte[] getData() {
		return data;
	}

	synchronized public byte[] get(int clients,ArrayList<Thread> childThreads) {
		/**
		 * データを受信するためのスレッドがすべて揃うまで待つように変更する必要がある
		 *
		 */

		this.childThreads.add(Thread.currentThread());

		for(Thread th : this.childThreads) {
			for(Thread thAll : childThreads) {
				if(th == thAll) {
					sameCount++;
				}
			}
		}

		int childThreadsSize = childThreads.size();
		if(childThreadsSize <= sameCount) {
			sameCount = 0;
			this.childThreads = new HashSet<Thread>();
			notifyAll();
		} else {
			try {
				wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}

		count++;
		if(data == null) {
			while(data == null) {
				try {
					wait();
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		byte[] result = data;
		if(clients <= count) {
			data = null;
			synchronized (receive) {
				receive.notify();
			}
			count = 0;
		}
		return result;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public void setReceive(DataReceive receive) {
		this.receive = receive;
	}
}
