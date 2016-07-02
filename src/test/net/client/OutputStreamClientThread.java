package test.net.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * コンソール用のサーバへ文字列を送信するクライアント
 * クライアントからキーボード入力した文字列をサーバに送信
 * コンソール制御用のAPIを利用するように変更する
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
			)
		{
			while(true) {
				Socket socket = new Socket(SERVER_ADDRESS,SERVER_PORT);
				System.out.print("メッセージ入力 : ");
				String input = br.readLine();
				String message = socket.getInetAddress().getHostName() + ":" + input;
				if(message.equals("\\q")) {
					break;
				}
				byte[] buf = message.getBytes("UTF-8");

				OutputStream os = socket.getOutputStream();
				try {
					os.write(buf);
					os.flush();
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
