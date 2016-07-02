package test.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import test.net.client.OutputStreamClient;

public class MessageSend implements ActionListener {
	/**
	 * メッセージ出力サーバーポート
	 */
	private final int PORT;

	/**
	 * サーバホスト名
	 */
	private final String ADDRESS;

	public MessageSend(int OUTPUT_SERVER_PORT,String SERVER_ADDRESS) {
		PORT = OUTPUT_SERVER_PORT;
		ADDRESS = SERVER_ADDRESS;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		OutputStreamClient osc = new OutputStreamClient(PORT, ADDRESS);
		JTextField jt = (JTextField)e.getSource();
		osc.output(jt.getText());
		jt.setText("");
	}

}
