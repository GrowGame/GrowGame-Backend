package growgame.backend.server;

import java.util.Set;

public interface ConsoleState {
	
	public void handle();
	public Set<String> getCommands();
}
