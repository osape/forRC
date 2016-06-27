package test.gui;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class ThreadFrame extends JFrame {
	/**
	 * 受信メッセージを表示
	 */
	private JTextArea receiveMessage;
	/**
	 * 送信メッセージを表示
	 */
	private JTextArea sendMessage;

	public ThreadFrame() {
		setBounds(300,300,600,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		receiveMessage = new JTextArea();
		JScrollPane jscRec = new JScrollPane(receiveMessage);
		sendMessage = new JTextArea();
		JScrollPane jscSen = new JScrollPane(sendMessage);

		DefaultCaret caret = (DefaultCaret)receiveMessage.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		caret = (DefaultCaret)sendMessage.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		Container container = getContentPane();
		container.setLayout(new GridLayout(2, 1));
		container.add(jscRec);
		container.add(jscSen);

		setVisible(true);
	}

	public JTextArea getReceiveMessage() {
		return receiveMessage;
	}

	public JTextArea getSendMessage() {
		return sendMessage;
	}
}
