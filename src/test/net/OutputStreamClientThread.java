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
		try (
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			)
		{
			for(int i = 0;i < 100;i++) {
				System.out.print("メッセージ入力 : ");
				String input = br.readLine();
				try (
						Socket socket = new Socket(SERVER_ADDRESS,SERVER_PORT);
						OutputStream os = socket.getOutputStream();
					)
				{
					String message = socket.getInetAddress().getHostName() + " : " + i + " : " + input;
					byte[] buf = message.getBytes("UTF-8");
					os.write(buf);
					os.flush();
					os.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
				sleep(1000);
			}
		} catch(IOException e) {
			e.printStackTrace();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

}
