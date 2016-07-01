package test.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import javax.swing.JTextArea;

/**
 * サーバからデータを読み込む
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
	 * 出力先
	 */
	JTextArea output;

	/**
	 *
	 * @param sERVER_PORT
	 * @param sERVER_ADDRESS
	 */

	public InputStreamClientThread(int sERVER_PORT, String sERVER_ADDRESS,JTextArea output) {
		super();
		SERVER_PORT = sERVER_PORT;
		SERVER_ADDRESS = sERVER_ADDRESS;
		this.output = output;
	}

	/**
	 * スレッドを開始
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
				output.append(message + "\n");
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
