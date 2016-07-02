package src.test.net.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * キーボードから入力したメッセージをサーバに送信
 *
 *
 * @author Osamu Takahashi
 *
 */
public class OutputStreamClient {
	/**
	 * サーバーポート
	 */
	private final int SERVER_PORT;

	/**
	 * サーバホスト名
	 */
	private final String SERVER_ADDRESS;

	/**
	 *
	 * @param serverPort サーバポート
	 * @param serverAddress サーバアドレス
	 */
	public OutputStreamClient(int serverPort,String serverAddress) {
		SERVER_PORT = serverPort;
		SERVER_ADDRESS = serverAddress;
	}


	public void output(String message) {
		Socket socket = null;
		OutputStream os = null;

		try {
			socket = new Socket(SERVER_ADDRESS,SERVER_PORT);
			os = socket.getOutputStream();
			message = socket.getInetAddress().getHostName() + ":" + message;
			byte[] buf = message.getBytes("UTF-8");
			os.write(buf);
			os.flush();
			os.close();
		} catch(IOException e) {
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
		}
	}

}
