package growgame.backend.console;

import growgame.backend.server.CentralUnit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class ConfigureState implements ConsoleState {

	public static ConsoleState state;	
	private Console console;
	private HashSet<String> commands;
	private CentralUnit cu = null;

	public ConfigureState(Console c){
		console = c;
		commands = new HashSet<String>();
		commands.add("show");	
		commands.add("startGameServer");
		commands.add("stopGameServer");
		commands.add("showLog");
		commands.add("setMOTD");
		commands.add("showPlayers");
		commands.add("showconnections");
		commands.add("player");
		commands.add("exit");
	}
	
	@Override
	public void handle() {
		System.out.println("Configure Mode");
		// TODO Auto-generated method stub
		BufferedReader in = console.getIn();
		String input = "";
		boolean validCommand= false;
		boolean exit = false;
		while(console.getState() instanceof ConfigureState){
			//continue reading until correct command was typed or user typed exit
			while(!validCommand && !exit){
				try {
					input = in.readLine().toLowerCase();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				for(String cmd : commands){
					if(input.startsWith(cmd.toLowerCase())){
						validCommand = true;
						break;
					}
				}
				if(!validCommand){
					System.out.println("Command "+input+" could not be recognized! Use 'show' to see the commands list");
				}
			}


			String[] words = input.split(" ");
			//Identify and handle command
			String s = words[0].toLowerCase();
			switch(s){

			case "show" : 
				for(String cmd : commands)
					System.out.println(cmd);
				break;


			case "startgameserver" : 
				cu = new CentralUnit();
				cu.startGameServer();
				break;


			case "stopgameserver" : 
				if(cu==null || !cu.getRunning()){
					System.out.println("Illegal command! Server is not running!");
				}
				else{
				cu.pauseGameServer(0);}
				break;


			case "showlog" : 
				System.out.println("Log...");
				break;

			case "setmotd" : 
				System.out.println("MOTD set");
				break;

			case "showplayers" : 
				System.out.println("PlayerID | Playername | Online ");
				break;
			
			case "showconnections" :
				System.out.println("   IP   |UserID| Online ");
				break;

			case "player" : 
				console.switchState(ShowPlayerState.state);
				break;

			case "exit" : 
				System.out.println("Exit Console...");
			//	console.switchState(PasswordRequestState.state);
			//  testing purposes	
				exit = true;
				console.switchState(null);
				break;

			}
			validCommand = false;
		}


	}


	@Override
	public Set<String> getCommands() {
		// TODO Auto-generated method stub
		
		return commands;
	}

}
