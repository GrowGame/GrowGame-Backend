package growgame.backend.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
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
	/**
	 * userID = -1 means that no Authentication occured before, all requests are denied
	 */
	private int userID;
	private GregorianCalendar lastKeepAlive;
	private String username;
	private boolean aborted;
	private BufferedWriter out;

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
	 * @return the username if the user has already commited an authentication request and null if not
	 */
	public String getUsername(){
		return username;
	}
	
	/**
	 * Tries to eliminate the connection. The current read process will be finished, 
	 * but no request executed
	 */
	public void stop(){
		aborted = true;
	}
	
	/**
	 * 
	 * @return
	 */
	public BufferedWriter getOut(){
		if(out==null){
			try {
				out = new BufferedWriter(new OutputStreamWriter(getSocket().getOutputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
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
			while((!socket.isClosed())&&(line=in.readLine()) != null){
				if(aborted){//connection shall be interrupted
					socket.close();
					return;
				}
			// handle communication , try to parse requests
			// execute requests and send positive or negative acknowledgement
				Request req = CentralUnit.createRequest(line);
				Object[] args = req.parseArguments(line);
				System.out.println("REQ: "+req.getClass().getName());
				if(req.fulfillsRequirements(userID,args)){
					//AutenticationRequest and KeepAlive is the only request which needs this following treatment
					//instead of execute()
					
					if(req instanceof AuthenticationRequest){
						userID = Integer.parseInt((String)args[0]);
						username = ((AuthenticationRequest)req).getUsername(userID);
						keepAlive();
					}
					else if(req instanceof KeepAliveRequest){
						keepAlive();
					}
					
					req.execute();
					//Send positive Acknowledgement
					getOut().write(req.getPositiveAck()+"\n");
					getOut().flush();
					System.out.println("Request acknowledged");
				}
				else{
					getOut().write(req.getErrorMsg()+"\n");
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
	 * refreshes the lastKeepAlive of of this connection
	 */
	public void keepAlive(){
		lastKeepAlive = new GregorianCalendar();
	}

	/**
	 * @return the userID of this connection or -1 if the user has not identified himself yet
	 */
	public long getUserID() {
		return userID;
	}
	
	public void setUserID(int id){
		this.userID = id;
	}
	
	public String toString(){
		return getSocket().getInetAddress()+"   "+getUserID()+"   "+getUsername()+"   lastKeepAlive(sec): "+(getInactiveTime().getTimeInMillis()/1000.0);
	}
	
	
	
}
