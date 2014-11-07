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

	private HashSet<Connection> connections;
	private ServerSocket ssocket;
	private boolean serverRunning;
	private ReentrantLock lock;
	
	public Connector(){
		try {
			ssocket = new ServerSocket(1337);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connections = new HashSet<Connection>();
		lock = new ReentrantLock();
	}
	
	public void setRunning(boolean running){
		this.serverRunning = running;
	}
	
	public boolean getRunning(){
		return serverRunning;
	}
	
	public void addConnection(Connection con){
		System.out.println("Connection added: "+con.getSocket().getLocalAddress());
		connections.add(con);
	}
	


	
	public void awaitConnections(){
		System.out.println("start listening");
		while(serverRunning){
			try {
				Socket socket = ssocket.accept();
				Connection c = new Connection(socket);
				Thread t = new Thread(c);
				t.start();
				this.addConnection(c);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("stop listening");
	}
	
	
}
