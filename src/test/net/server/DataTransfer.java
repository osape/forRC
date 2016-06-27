package test.net.server;

import java.util.ArrayList;

public class DataTransfer {
	/**
	 * データを格納
	 */
	private byte[] data;

	/**
	 * データ受信オブジェクト
	 */
	private DataReceive receive;

	/**
	 * データ送信オブジェクト
	 */
	private DataSend send;

	/**
	 * 送受信オブジェクトの初期化
	 */
	public DataTransfer() {
		receive = new DataReceive();
		send = new DataSend();
		receive.setSend(send);
		send.setReceive(receive);
	}

	/**
	 *
	 * @param data
	 */

	public void put(byte[] data) {
		receive.receive(data);
	}

	public byte[] get(int clients,ArrayList<Thread> childThreads) {
		return send.get(clients,childThreads);
	}
}
