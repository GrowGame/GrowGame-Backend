package growgame.backend.server;

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

	public ConfigureState(Console c){
		console = c;
		commands = new HashSet<String>();
		commands.add("showCommands");	
		commands.add("startGameServer");
		commands.add("stopGameServer");
		commands.add("showLog");
		commands.add("setMOTD");
		commands.add("showPlayers");
		commands.add("player");
		commands.add("exit");
	}
	
	@Override
	public void handle() {
		// TODO Auto-generated method stub
		BufferedReader in = console.getIn();
		String input = "";
		boolean validCommand= false;
		boolean exit = false;
		
		//continue reading until correct command was typed or user typed exit
		while(!validCommand && !exit){
			if(exit)
				System.exit(0);
			try {
				input = in.readLine().toLowerCase();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for(String cmd : commands){
				if(input.startsWith(cmd)){
					validCommand = true;
					break;
				}
			}
			if(!validCommand){
				System.out.println("Command "+input+" could not be recognized! Use 'showCommands' to see the commands list");
			}

		}
		
		String[] words = input.split(" ");
		//Identify and handle command
		switch(words[0].toLowerCase()){
		
		case("showcommands") : {
			for(String cmd : commands)
				System.out.println(cmd);
		}
		
		case("startgameserver") : {
			
			
		} 
		
		case("stopGameServer") : {
			
		} 
		
		case("showLog") : {
			
		} 
		
		case("setMOTD") : {
			
		} 
		
		case("showPlayers") : {
			
		} 
		
		case("player") : {
			console.switchState(ShowPlayerState.state);
		} 
		
		case("exit") : {
			
		} 		
		
		
		commands.add("startGameServer");
		commands.add("stopGameServer");
		commands.add("showLog");
		commands.add("setMOTD");
		commands.add("showPlayers");
		commands.add("player");
		commands.add("exit");
		
		
		
		}
		
		
	}


	@Override
	public Set<String> getCommands() {
		// TODO Auto-generated method stub
		
		return commands;
	}

}
