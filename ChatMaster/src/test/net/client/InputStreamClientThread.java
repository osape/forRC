package test.net.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * サーバからデータを読み込むクライアント
 * コンソールにメッセージを出力する
 * @author Osamu Takahashi
 *
 */
public class InputStreamClientThread extends Thread {
	/**
	 * サーバーポート
	 */
	private final int SERVER_PORT;

	/**
	 * サーバホスト名
	 */
	private final String SERVER_ADDRESS;

	/**
	 * サーバポートとサーバアドレスを取得する
	 * @param sERVER_PORT サーバポート
	 * @param sERVER_ADDRESS サーバアドレス
	 */
	public InputStreamClientThread(int sERVER_PORT, String sERVER_ADDRESS) {
		super();
		SERVER_PORT = sERVER_PORT;
		SERVER_ADDRESS = sERVER_ADDRESS;
	}

	/**
	 * サーバからデータを読み込んで表示する。
	 */
	@Override
	public void run() {
		Socket socket = null;
		InputStream is = null;
		try {
			socket = new Socket(SERVER_ADDRESS,SERVER_PORT);
			is = socket.getInputStream();
			for(int i = 0;i < 1000;i++) {
				byte[] buf = new byte[100];
				is.read(buf);
				String message = new String(buf,"UTF-8");
				System.out.println(message);
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
