package test.net.server;

/**
 * 各サーバスレッドを開始する
 * メッセージを入力するポートと出力するポートを別々にしている
 *
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

	/**
	 * データトランスファーオブジェクトの作成
	 * 入力サーバオブジェクトの作成
	 * 出力サーバオブジェクトの作成
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		DataTransfer dt = new DataTransfer();
		Thread threadIn = new InputServerThread(INPUT_SERVER_PORT,dt);
		threadIn.start();

		Thread threadOut = new OutputServerThread(OUTPUT_SERVER_PORT,dt);
		threadOut.start();
	}

}
