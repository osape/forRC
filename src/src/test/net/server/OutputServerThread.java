package test.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * 10回クライアントからのリクエストを受け付ける
 * 受信したデータをクライアントに送信する
 * @author Osamu Takahashi
 *
 */
public class OutputServerThread extends Thread {
	/**
	 * サーバーポート
	 */
	private final int SERVER_PORT;

	/**
	 * データトランスファーオブジェクト
	 */
	private DataTransfer dt;

	/**
	 * クライアント数
	 */
	private int clients = 0;

	/**
	 * 子スレッド
	 */
	private ArrayList<Thread> childThreads;

	/**
	 * サーバーポートを設定する
	 * @param sERVER_PORT サーバーポート
	 * @param dt datatransfer object
	 */
	public OutputServerThread(int sERVER_PORT,DataTransfer dt) {
		super();
		SERVER_PORT = sERVER_PORT;
		this.dt = dt;
		childThreads = new ArrayList<Thread>();
	}

	/**
	 * スレッドを開始
	 */
	@Override
	public void run() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(SERVER_PORT);

			while(true) {
				socket = serverSocket.accept();
				clients++;
				OutputServerChildThread osc = new OutputServerChildThread(socket, dt,this,socket.getLocalAddress().getHostName());
				osc.start();
				childThreads.add(osc);
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(socket != null) {
				try {
					socket.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public int getClients() {
		return clients;
	}

	public void setClients(int clients) {
		this.clients = clients;
	}

	/**
	 * @param childThreads セットする childThreads
	 */
	public void setChildThreads(ArrayList<Thread> childThreads) {
		this.childThreads = childThreads;
	}

	/**
	 * @return childThreads
	 */
	public ArrayList<Thread> getChildThreads() {
		System.out.println("getChildThreads");
		HashSet<Thread> deathTh = new HashSet<Thread>();
		synchronized (childThreads) {
			System.out.println("synchronized : " + childThreads.size());
			for(Thread th : childThreads) {
				if(!th.isAlive()) {
					deathTh.add(th);
				}
				State state = th.getState();
				System.out.println(state.name());
			}
			for(Thread th : deathTh) {
				childThreads.remove(th);
			}
		}
		return childThreads;
	}

}
