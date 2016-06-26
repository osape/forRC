package test.net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * クライアントから整数を定期的に出力
 *
 * @author Osamu Takahashi
 *
 */
public class OutputStreamClientThread extends Thread {
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
	public OutputStreamClientThread(int serverPort,String serverAddress) {
		SERVER_PORT = serverPort;
		SERVER_ADDRESS = serverAddress;
	}

	/**
	 * スレッドを開始した時に実行
	 */
	@Override
	public void run() {
		/**
		 * サーバソケット
		 */
		Socket socket = null;

		OutputStream os = null;
		byte[] buf = { 1,2,3,4,5,6,7,8,9,10 };
		try {
			for(int i = 0;i < 100;i++) {
				socket = new Socket(SERVER_ADDRESS,SERVER_PORT);
				os = socket.getOutputStream();
				os.write(i);
				os.flush();
				os.close();
				sleep(1000);
			}
		} catch(IOException e) {
			e.printStackTrace();
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
		}
	}

}
