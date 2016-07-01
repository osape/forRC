package test.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		int msgNo = 1;
		try (
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				Socket socket = new Socket(SERVER_ADDRESS,SERVER_PORT);
			)
		{
			while(true) {
				System.out.print("メッセージ入力 : ");
				String input = br.readLine();
				String message = socket.getInetAddress().getHostName() + " : " + msgNo + " : " + input;
				if(message.equals("\\q")) {
					break;
				}
				byte[] buf = message.getBytes("UTF-8");

				try (OutputStream os = socket.getOutputStream()) {
					os.write(buf);
					os.flush();
				} catch(IOException e) {
					e.printStackTrace();
				}
				try {
					sleep(1000);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}