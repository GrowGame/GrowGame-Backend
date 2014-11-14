package growgame.backend.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class CentralUnit {

	private Connector connector;
	private boolean isRunning;

	
	public CentralUnit(){
		connector = new Connector();
	}
	
	public boolean getRunning(){
		return isRunning;
	}
	
	public void setRunning(boolean running){
		isRunning = running;
		connector.setRunning(running);
	}
	
	/**
	 * Starts the game server (Loads previously saved server-file from the given direction)
	 */
	public void startGameServer(){
		//show dialog to select the server file you want to load
		
					//load gamestatus
					//contine game (accept requests and connection attemps)
					//-> start listening
		System.out.println("starting game server");
		setRunning(true);
		Thread t = new Thread(connector);
		t.start();
		
		
		Socket socket = null;
		Socket socket2 = null;
		BufferedWriter out2 = null;
		BufferedReader in2 = null;
		BufferedWriter out = null;
		BufferedReader in = null;
		
		try {
			socket = new Socket("localhost",1337);
			socket2 = new Socket("localhost",1337);
		//	socket.bind(new SocketAddress());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
			out2 = new BufferedWriter(new OutputStreamWriter(socket2.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final BufferedWriter out3=out2;
			//sleep 5 sec
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				out2.write("AUTH~124,huso\n");
				out2.flush();
				//send keepalive
				out.write("Auth~123,unreal\n");
				out.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Runnable r = new Runnable(){

				@Override
				public void run() {
					while(true){
						
						try {
							out3.write("KeepAlive~\n");
							out3.flush();
							try {
								Thread.sleep(5000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} catch (IOException e) {
							// should be solved better
							System.out.println("Seems that the server cut the connection...stop sending packets");
							return;
						}
						
					}
				}
				};
				Thread tt = new Thread(r);
				tt.start();
			
	}
	
	public void pauseGameServer(int mins){
		//Pauses the game server in a mins minutes
		//Send warning to all connected clients
		//...
		//block all incoming transactions etc...
		//save game status
		//disconnect all connected clients (friendly :) )
		setRunning(false);
	}
	
	/**
	 * Parses a string input transmitted by a user and returns the corresponding request
	 * request syntax: bsp "send": 'SEND~param1,param2,param3,Hello, my name is Alexander. I'm testing GrowGame' 
	 * @param req the string transmitted by the user
	 * @return the corresponding request matching the string
	 */
	public static Request createRequest(String input){
		String request = input.substring(0,input.indexOf("~"));

		request = request.toUpperCase();
        System.out.println(request);
		switch(request) {
		case "BUY":{

			return new BuyRequest();
		}
		case "SELL":{
			return new SellRequest();
		}
		case "SEND":{
			return new SendRequest();
		}
		case "KEEPALIVE":{
			return new KeepAliveRequest();
		}
		case "AUTH":{
			return new AuthenticationRequest();
		}
		//Invalid request neg. acknowledgement
		default:{
			return new InvalidRequest(input);
		}
		}
		}
	


	
	
	
}
