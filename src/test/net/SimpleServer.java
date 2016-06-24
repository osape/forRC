package test.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
	private static final int SERVER_PORT = 65333;
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		InputStream is = null;
		try {
			serverSocket = new ServerSocket(SERVER_PORT);
			socket = serverSocket.accept();
			is = socket.getInputStream();
			byte[] buf = new byte[100];
			is.read(buf);
			System.out.println("クライアントからのデータ : " + buf[0]);
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
