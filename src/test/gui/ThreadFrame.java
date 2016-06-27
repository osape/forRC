package test.gui;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ThreadFrame extends JFrame {
	/**
	 * 受信メッセージ
	 */
	private JTextArea receiveMessage;

	/**
	 * 受信メッセージ
	 */
	private JTextArea sendMessage;

	public ThreadFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		receiveMessage = new JTextArea();
		JScrollPane jscRec = new JScrollPane(receiveMessage);
		sendMessage = new JTextArea();
		JScrollPane jscSen = new JScrollPane(sendMessage);

		Container container = getContentPane();
		container.setLayout(new GridLayout(2, 1));
		container.add(jscRec);
		container.add(jscSen);

		pack();
		setVisible(true);
	}
}
