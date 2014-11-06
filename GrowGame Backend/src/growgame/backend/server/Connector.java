package growgame.backend.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

/**
 * This class accepts connection requests from sockets
 * @author Alex
 *
 */
public class Connector {

	private HashSet<Connection> connections;
	private ServerSocket ssocket;
	private boolean serverRunning;
	
	public Connector(){
		try {
			ssocket = new ServerSocket(1667);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connections = new HashSet<Connection>();
	}
	
	public void setRunning(boolean running){
		this.serverRunning = running;
	}
	
	public boolean getRunning(){
		return serverRunning;
	}
	
	public void awaitConnections(){
		while(serverRunning){
			try {
				Socket socket = ssocket.accept();
				Connection c = new Connection(socket);
				connections.add(c);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
