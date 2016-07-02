package test.net.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 個々のクライアントからのデータを読み込む処理を行うスレッドクラス
 *
 * @author osamu
 *
 */
public class InputServerChildThread extends Thread {
	/**
	 * ソケット
	 */
	private Socket socket;

	/**
	 * データトランスファーオブジェクト
	 */
	private DataTransfer dt;

	/**
	 * コンストラクタ
	 * @param socket ソケットオブジェクト
	 * @param dt データランスファーオブジェクト
	 */
	public InputServerChildThread(Socket socket,DataTransfer dt) {
		this.socket = socket;
		this.dt = dt;
	}

	/**
	 * read client message
	 */
	@Override
	public void run() {
		InputStream is = null;
		try {

			byte[] buf = new byte[100];
			is = socket.getInputStream();
			is.read(buf);
			dt.put(buf);
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
