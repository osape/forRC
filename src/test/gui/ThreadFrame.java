package test.gui;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ThreadFrame extends JFrame {
	private JTextArea receiveMessage;
	private JTextArea sendMessage;

	public ThreadFrame() {
		receiveMessage = new JTextArea();
		JScrollPane jscRec = new JScrollPane(receiveMessage);
		sendMessage = new JTextArea();
		JScrollPane jscSen = new JScrollPane(sendMessage);

		Container container = getContentPane();
		container.add(jscRec);
		container.add(jscSen);

		setVisible(true);
	}
}
