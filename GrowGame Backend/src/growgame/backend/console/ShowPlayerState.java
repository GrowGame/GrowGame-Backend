package growgame.backend.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ShowPlayerState implements ConsoleState {

	public static ConsoleState state;
	private Console console;
	private HashSet<String> commands;

	public ShowPlayerState(Console c){
		console = c;
		commands = new HashSet<String>();
		commands.add("show");
		commands.add("showInventory");
		commands.add("ban");
		commands.add("showGrows");
		commands.add("sendMSG");
		commands.add("exit");
	}
	
	@Override
	public void handle() {
		System.out.println("Show Player Mode");
		BufferedReader in = console.getIn();
		String input = "";
		boolean validCommand= false;
		while(console.getState() instanceof ShowPlayerState){		
			//continue reading until correct command was typed or user typed exit
			while(!validCommand){
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
			switch(words[0].toLowerCase()){
			case("show") : {
				for(String cmd : commands)
					System.out.println(cmd);
				break;
			}

			case("showinventory") : {
				System.out.println("");
				break;
			}

			case("ban") : {
				System.out.println("");
				break;
			}

			case("showgrows") : {
				System.out.println("");
				break;
			}

			case("sendmsg") : {
				System.out.println("");
				break;
			}
			
			case("exit") : {
				console.switchState(ConfigureState.state);
				break;
			}
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
