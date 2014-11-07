package growgame.backend.server;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class accepts connection requests from sockets
 * @author Alex
 *
 */
public class Connector {

	private ServerSocket ssocket;
	private boolean serverRunning;
	
	public Connector(){
		try {
			ssocket = new ServerSocket(1337);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setRunning(boolean running){
		this.serverRunning = running;
	}
	
	public boolean getRunning(){
		return serverRunning;
	}
	
	public void awaitConnections(){
		System.out.println("start listening");
		while(serverRunning){
			try {
				Socket socket = ssocket.accept();
				Connection c = new Connection(socket);
				Thread t = new Thread(c);
				t.start();
				ActiveConnections.getInstance().addConnection(c);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("stop listening");
	}
	
	
}
