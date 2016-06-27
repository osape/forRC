package test.gui;

import javax.swing.JTextArea;

import test.net.InputStreamClientThread;
import test.net.OutputStreamClientThread;

/**
 * スレッド管理版のプログラム
 * 整数のデータを送信する
 * 整数のデータを受信する、受信するデータはすべてのクライアントから送信されたデータを受信する
 *
 * @author osamu
 *
 */
public class GUIClient {
	/**
	 * メッセージ出力サーバーポート
	 */
	private final static int OUTPUT_SERVER_PORT = 65333;

	/**
	 * メッセージ入力サーバーポート
	 */
	private final static int INPUT_SERVER_PORT = 65334;

	/**
	 * サーバホスト名
	 */
	private final static String SERVER_ADDRESS = "localhost";

	public static void main(String[] args) {
		// フレームの作成
		ThreadFrame tf = new ThreadFrame();

		// 受信テキストエリア
		JTextArea recTa = tf.getReceiveMessage();

		// 送信テキストエリア
		JTextArea senTa = tf.getSendMessage();

		// サーバから受信するスレッドを起動
		// サーバから受信するデータを表示するテキストエリアのオブジェクトが必要
		InputStreamClientThread isct = new InputStreamClientThread(INPUT_SERVER_PORT, SERVER_ADDRESS, recTa);
		isct.start();

		// サーバに送信するスレッドを起動
		// 送信するデータを表示するテキストエリアのオブジェクトが必要
		OutputStreamClientThread osct = new OutputStreamClientThread(OUTPUT_SERVER_PORT, SERVER_ADDRESS, recTa);

	}

}
