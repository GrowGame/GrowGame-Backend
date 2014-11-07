package growgame.backend.server;

public class CentralUnit {

	private Connector connector;
	private static final InvalidRequest unknownRequest = new InvalidRequest();
	private static final BuyRequest buyRequest = new BuyRequest();
	private static final SellRequest sellRequest = new SellRequest();
	
	public static void main(String[] args){
		CentralUnit cu = new CentralUnit();
		cu.startGameServer();
	}
	
	public CentralUnit(){
		connector = new Connector();
	}
	
	/**
	 * Starts the game server (Loads previously saved server-file from the given direction)
	 */
	public void startGameServer(){
		//show dialog to select the server file you want to load
		
					//load gamestatus
					//contine game (accept requests and connection attemps)
					//-> start listening!
		connector.setRunning(true);
		connector.awaitConnections();
	}
	
	public void pauseGameServer(int mins){
		//Pauses the game server in a mins minutes
		//Send warning to all connected clients
		//...
		//block all incoming transactions etc...
		//save game status
		//disconnect all connected clients (friendly :) )
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
		//Invalid request neg. acknowledgement
		default:{
			return CentralUnit.unknownRequest ;
		}
		}
		}
	
	
	
}
