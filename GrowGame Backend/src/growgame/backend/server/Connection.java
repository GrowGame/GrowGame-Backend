package growgame.backend.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Represents an active connection with a game client to the server
 * @author Alex
 *
 */
public class Connection implements Runnable {

	
	private Socket socket;
	private long userID;

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
	
	/**
	 * 
	 * @return
	 */
	public BufferedWriter getOut(){
		BufferedWriter out = null;
		try {
			 out = new BufferedWriter(new OutputStreamWriter(getSocket().getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;
	}

	@Override
	/**
	 * Waits for requests, as long as the connection is active
	 */
	public void run() {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} 
		catch (IOException e) {
			System.out.println("Exception one start up");
			e.printStackTrace();
		}
		String line = null;
		try {
			System.out.println("try reading");
			while((line=in.readLine()) != null){
				System.out.println("reading!!");
			// handle communication , try to parse requests
			// execute requests and send positive or negative acknowledgement
				Request req = CentralUnit.createRequest(line);
				System.out.println(req.getClass());
				Object[] args = req.parseArguments(line);
				System.out.println(args[0]+"   "+args[1]);
				if(req.fulfillsRequirements(userID,args)){
					req.execute();
					System.out.println("execute :D");
				}
				
			}
		} 
		catch (IOException e) {
			System.out.println("Exception while reading");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * TODO implementation with keep alive
	 * returns if the connection represented by the Socket socket is still active or not
	 * @return true if the socket is active, false if not
	 */
	public boolean isActive(){

		return false;
	}
	
	
	
	
}
