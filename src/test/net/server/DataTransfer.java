package test.net.server;

import java.util.ArrayList;

public class DataTransfer {
	/**
	 *
	 */
	private ArrayList<byte[]> messages;

	/**
	 * 送受信オブジェクトの初期化
	 */
	public DataTransfer() {
		messages = new ArrayList<byte[]>();
	}

	/**
	 *
	 * @param data クライアントからのデータ
	 */
	synchronized public void put(byte[] data) {
		messages.add(data);
	}

	synchronized public byte[] get(int messageNo) {
		return messages.get(messageNo);
	}

	public ArrayList<byte[]> getMessages() {
		return messages;
	}

	public int getMessagesSize() {
		return messages.size();
	}
}
