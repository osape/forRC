package test.gui;

import test.net.client.InputStreamClientThread;

public class SimpleChatClient {
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
	private final static String SERVER_ADDRESS = "192.168.31.7";

	public static void main(String[] args) {
		ChatFrame cf = new ChatFrame("チャットクライアント");
		cf.getInputArea().addActionListener(new MessageSend(OUTPUT_SERVER_PORT, SERVER_ADDRESS));
		InputStreamClientThread isct = new InputStreamClientThread(INPUT_SERVER_PORT, SERVER_ADDRESS,cf.getMessageArea());
		isct.start();

	}

}
