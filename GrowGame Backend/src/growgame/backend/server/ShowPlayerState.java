package growgame.backend.server;

import java.util.HashSet;
import java.util.Set;

public class ShowPlayerState implements ConsoleState {

	public static ConsoleState state;
	private Console console;
	private HashSet<String> commands;

	public ShowPlayerState(Console c){
		console = c;
		commands = new HashSet<String>();
		commands.add("showCommands");
	}
	
	@Override
	public void handle() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Set<String> getCommands() {
		// TODO Auto-generated method stub
		return commands;
	}

}
