package test.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * クライアントからのリクエストを受け付ける
 * クライアントからデータを受信するための子スレッドを開始する
 *
 * @author Osamu Takahashi
 *
 */
public class InputServerThread extends Thread {
	/**
	 * サーバーポート
	 */
	private final int SERVER_PORT;

	/**
	 * データトランスファーオブジェクト
	 */
	private DataTransfer dt;

	/**
	 * サーバーポートを設定する
	 * @param sERVER_PORT サーバーポート
	 */
	public InputServerThread(int sERVER_PORT) {
		super();
		SERVER_PORT = sERVER_PORT;
	}

	/**
	 *
	 * @param sERVER_PORT サーバーポート
	 * @param dt データトランスファーオブジェクト
	 */
	public InputServerThread(int sERVER_PORT,DataTransfer dt) {
		super();
		SERVER_PORT = sERVER_PORT;
		this.dt = dt;
	}

	/**
	 * スレッドを開始
	 * 子送信サーバスレッドを開始する。
	 */
	@Override
	public void run() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(SERVER_PORT);
			while(true) {
				socket = serverSocket.accept();
				InputServerChildThread isc = new InputServerChildThread(socket, dt);
				isc.start();
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(socket != null) {
				try {
					socket.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}

			if(serverSocket != null) {
				try {
					serverSocket.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
