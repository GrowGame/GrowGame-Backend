package growgame.backend.server;

import java.util.HashSet;
import java.util.Set;

public class ConfigureState implements ConsoleState {

	private Console console;
	private HashSet<String> commands;

	public ConfigureState(Console c){
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
