package test.net.server;

import test.net.DataTransfer;

/**
 * サーバスレッドを開始する
 * @author Osamu Takahashi
 *
 */
public class SimpleServer {
	/**
	 * クライアントからメッセージを入力するサーバーポート
	 */
	private static final int INPUT_SERVER_PORT = 65333;

	/**
	 * クライアントにメッセージを出力するサーバーポート
	 */
	private static final int OUTPUT_SERVER_PORT = 65334;

	public static void main(String[] args) {
		DataTransfer dt = new DataTransfer();
		Thread threadIn = new InputServerThread(INPUT_SERVER_PORT,dt);
		threadIn.start();

		Thread threadOut = new OutputServerThread(OUTPUT_SERVER_PORT,dt);
		threadOut.start();
	}

}
