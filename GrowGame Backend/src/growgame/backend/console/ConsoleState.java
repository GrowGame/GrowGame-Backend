package growgame.backend.console;

import java.util.Set;

public interface ConsoleState {
	
	public void handle();
	public Set<String> getCommands();
}
