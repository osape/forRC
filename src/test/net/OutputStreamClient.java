package test.net;

public class OutputStreamClient {
	/**
	 * サーバーポート
	 */
	private final static int SERVER_PORT = 65333;

	/**
	 * サーバホスト名
	 */
	private final static String SERVER_ADDRESS = "localhost";

	public static void main(String[] args) {
		Thread thread = new OutputStreamClientThread(SERVER_PORT, SERVER_ADDRESS);
		thread.start();
	}

}
