package test.net.client;

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
	private final static int OUTPUT_SERVER_PORT = 65333;

	/**
	 * サーバーポート
	 */
	private final static int INPUT_SERVER_PORT = 65334;

	/**
	 * サーバホスト名
	 */
	private final static String SERVER_ADDRESS = "osamu-vaio";

	public static void main(String[] args) {
		Thread threadOutput = new OutputStreamClientThread(OUTPUT_SERVER_PORT, SERVER_ADDRESS);
		threadOutput.start();

		Thread threadInput = new InputStreamClientThread(INPUT_SERVER_PORT,SERVER_ADDRESS);
		threadInput.start();
	}

}
