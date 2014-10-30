package growgame.backend.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Console {
	private ConsoleState state;
	private BufferedReader in;
	
	public Console(){
		ConfigureState.state = new ConfigureState(this);
		PasswordRequestState.state = new PasswordRequestState(this);
		ShowPlayerState.state = new ShowPlayerState(this);
	}
	
	public void start(){
		state = new PasswordRequestState(this);
		setIn(new BufferedReader(new InputStreamReader(System.in)));
	}
	
	public void switchState(ConsoleState state){
		this.state = state;
	}

	public BufferedReader getIn() {
		return in;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}
	
	
}
