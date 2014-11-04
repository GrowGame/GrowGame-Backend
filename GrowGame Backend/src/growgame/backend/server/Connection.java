package growgame.backend.server;

import java.net.Socket;

/**
 * Represents an active connection with a game client to the server
 * @author Alex
 *
 */
public class Connection implements Runnable {

	
	private Socket socket;

	/**
	 * Created a connection with a game client
	 * @param socket which is used to communicate
	 */
	public Connection(Socket socket){
		this.socket = socket;
	}
	
	/**
	 * 
	 * @return the socket representing the connection
	 */
	public Socket getSocket(){
		return socket;
	}

	@Override
	/**
	 * Waits for requests, as long as the connection is active
	 */
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * returns if the connection represented by the Socket socket is still active or not
	 * @return true if the socket is active, false if not
	 */
	public boolean isActive(){
		return false;
	}
	
	
	
	
}
