package src.test.net.client;

import java.awt.Color;
import java.awt.Insets;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * サーバからデータを読み込む
 * @author Osamu Takahashi
 *
 */
public class InputStreamClientThread extends Thread {
	/**
	 * サーバーポート
	 */
	private final int SERVER_PORT;

	/**
	 * サーバホスト名
	 */
	private final String SERVER_ADDRESS;

	/**
	 * 出力先
	 */
	private JTextPane output;

	/**
	 *
	 * @param sERVER_PORT
	 * @param sERVER_ADDRESS
	 */

	public InputStreamClientThread(int sERVER_PORT, String sERVER_ADDRESS,JTextPane output) {
		super();
		SERVER_PORT = sERVER_PORT;
		SERVER_ADDRESS = sERVER_ADDRESS;
		this.output = output;
	}

	/**
	 * スレッドを開始
	 */
	@Override
	public void run() {
		Socket socket = null;
		InputStream is = null;
		try {
			socket = new Socket(SERVER_ADDRESS,SERVER_PORT);
			is = socket.getInputStream();
			boolean colorFlag = true;
			for(int i = 0;i < 1000;i++) {
				SimpleAttributeSet sas = new SimpleAttributeSet();
				StyleConstants.setLineSpacing(sas, 0.0f);
				
				output.setParagraphAttributes(sas, true);
				output.setMargin(new Insets(0, 0, 0, 0));
				Document doc = output.getDocument();

				byte[] buf = new byte[1000];
				is.read(buf);
				String message = new String(buf,"UTF-8");
				System.out.println(message);
				
				try {
					message = message.trim();
					doc.insertString(doc.getLength(), message + "\n", sas);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(is != null) {
				try {
					is.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}

			if(socket != null) {
				try {
					socket.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


}
