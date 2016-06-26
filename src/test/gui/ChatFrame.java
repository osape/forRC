package test.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.text.DefaultCaret;

public class ChatFrame extends JFrame {
	private JTextArea messageArea;
	private JTextField inputArea;
	private JButton sendButton;
	private JScrollPane scrollPane;
	public ChatFrame(String title) throws HeadlessException {
		super(title);
		messageArea = new JTextArea(10,50);
		messageArea.setBorder(new EtchedBorder());
		DefaultCaret caret = (DefaultCaret)messageArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		scrollPane = new JScrollPane(messageArea);
		scrollPane.setPreferredSize(new Dimension(50, 10));
		inputArea = new JTextField();
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		container.add("Center",scrollPane);
		container.add("South",inputArea);
		setBounds(100, 100, 700, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	/**
	 * @return messageArea
	 */
	public JTextArea getMessageArea() {
		return messageArea;
	}
	/**
	 * @param messageArea セットする messageArea
	 */
	public void setMessageArea(JTextArea messageArea) {
		this.messageArea = messageArea;
	}
	/**
	 * @return inputArea
	 */
	public JTextField getInputArea() {
		return inputArea;
	}
	/**
	 * @param inputArea セットする inputArea
	 */
	public void setInputArea(JTextField inputArea) {
		this.inputArea = inputArea;
	}
	/**
	 * @return scrollPane
	 */
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	/**
	 * @param scrollPane セットする scrollPane
	 */
	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

}
