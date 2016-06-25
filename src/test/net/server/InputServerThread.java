package test.net.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import test.net.DataTransfer;

/**
 * 10回クライアントからのリクエストを受け付ける
 * 受信したデータをクライアントに送信する
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
	 */
	@Override
	public void run() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		InputStream is = null;
		OutputStream os = null;
		try {
			serverSocket = new ServerSocket(SERVER_PORT);

			for(int i = 0;i < 10;i++) {
				socket = serverSocket.accept();
				is = socket.getInputStream();
				byte[] buf = new byte[100];
				is.read(buf);
				dt.put(buf);
				System.out.println("クライアントからのデータ : " + buf[0]);
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(is != null) {
				try {
					is.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}

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
		}
	}

}
