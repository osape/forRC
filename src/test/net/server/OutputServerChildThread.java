package test.net.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 個々のクライアントにデータを書き込む処理を行うスレッドクラス
 *
 * @author osamu
 *
 */
public class OutputServerChildThread extends Thread {
	/**
	 * ソケット
	 */
	private Socket socket;

	/**
	 * データトランスファーオブジェクト
	 */
	private DataTransfer dt;

	/**
	 * サーバオブジェクト
	 */
	private OutputServerThread ost;

	/**
	 * ソケットとデータトランスファーオブジェクトを初期化
	 * @param socket ソケット
	 * @param dt データトランスファーオブジェクト
	 */
	public OutputServerChildThread(Socket socket,DataTransfer dt,OutputServerThread ost,String name) {
		super(name);
		this.socket = socket;
		this.dt = dt;
		this.ost = ost;
	}

	/**
	 * 個々のクライアントにデータを送信する
	 */
	@Override
	public void run() {
		OutputStream os = null;
		int messageNo = dt.getMessagesSize() - 1;
		try {
			os = socket.getOutputStream();
			//System.out.println(os);
			//socket.setSoTimeout(2000);
			byte[] buf;
			while(true) {
				int messageSize = dt.getMessagesSize();
				if(messageNo < 1) {
					while(true) {
						if(dt.getMessagesSize() > 0) {
							messageNo = 0;
							break;
						} else {
							sleep(1000);
						}
					}
				}
				while(true) {
					messageSize = dt.getMessagesSize();
					if(messageSize - 1 >= messageNo) {
						break;
					} else {
						sleep(1000);
					}
				}
				buf = dt.get(messageNo);
				messageNo++;
				os.write(buf);
				os.flush();
				System.out.println("クライアントにデータを送信 : " + buf[0]);
				//sleep(5000);
			}
		} catch(IOException e) {
			e.printStackTrace();
			System.out.println(os);
		} catch(InterruptedException e) {
			e.printStackTrace();
		} finally {
			if(os != null) {
				try {
					os.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}

			if(socket != null) {
				try {
					socket.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}

			int clients = ost.getClients();
			ost.setClients(--clients);
		}
	}
}
