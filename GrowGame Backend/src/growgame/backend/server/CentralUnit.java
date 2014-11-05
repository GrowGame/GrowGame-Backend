package growgame.backend.server;

public class CentralUnit {

	private Connector connector;
	private static final InvalidRequest unknownRequest = new InvalidRequest();
	private static final BuyRequest buyRequest = new BuyRequest();
	private static final SellRequest sellRequest = new SellRequest();
	private static final SendRequest sendRequest = new SendRequest();
	
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
	 * @param req the string transmitted by the user
	 * @return the corresponding request matching the string
	 */
	public static Request parseRequest(String input){
		String[] requests = input.split("§");
		if(requests.length!=1){
			//TODO negative acknowledgement: One and only one request is allowed
			
		}
		String req = requests[0].toUpperCase();
		
		switch(req) {
		case "BUY":{
			
			return CentralUnit.buyRequest;
		}
		case "SELL":{
			return CentralUnit.sellRequest;
		}
		case "SEND":{
			return CentralUnit.sendRequest;
		}
		//Invalid request neg. acknowledgement
		default:{
			return CentralUnit.unknownRequest ;
			}
		}
		}
	
	
}
