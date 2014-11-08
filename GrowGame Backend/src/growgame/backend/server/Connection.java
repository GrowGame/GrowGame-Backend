package growgame.backend.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Represents an active connection with a game client to the server
 * @author Alex
 *
 */
public class Connection implements Runnable {

	
	private Socket socket;
	private long userID;
	private GregorianCalendar lastKeepAlive;

	/**
	 * Created a connection with a game client
	 * @param socket which is used to communicate
	 */
	public Connection(Socket socket){
		if(socket==null){
			throw new NullPointerException("Given socket was null!");
		}
		this.socket = socket;
		this.userID = -1;
		lastKeepAlive = new GregorianCalendar();
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
			e.printStackTrace();
		}
		String line = null;
		try {
			while((line=in.readLine()) != null){
				System.out.println("reading!!");
			// handle communication , try to parse requests
			// execute requests and send positive or negative acknowledgement
				Request req = CentralUnit.createRequest(line);
				Object[] args = req.parseArguments(line);
				if(req.fulfillsRequirements(userID,args)){
					//AutenticationRequest is the only request which needs this following treatmeant
					//instead of execute()
					if(req instanceof AuthenticationRequest){
						userID = Long.parseLong((String)args[0]);
					}
						
					req.execute();
					//System.out.println("execute :D");
				}
				else{
					getOut().write(req.getErrorMsg());
					getOut().flush();
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

	/**
	 * Calculate inactive time since the last keep alive request
	 * ...Could be calculated faster with differences of System.currentTimeinMillis()...
	 * @return the time difference since the lastKeepAlive request has been sent by the client
	 */
	public GregorianCalendar getInactiveTime() {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date(gc.getTimeInMillis()-lastKeepAlive.getTimeInMillis()));
		return gc;
	}

	/**
	 * @return the userID of this connection or -1 if the user has not identified himself yet
	 */
	public long getUserID() {
		return userID;
	}
	
	public void setUserID(long id){
		this.userID = id;
	}
	
	
	
	
}
