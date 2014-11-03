package growgame.backend.server;

public class CentralUnit {

	private Connector connector;
	
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
	
	
}
