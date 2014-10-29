package growgame.backend.server;

public class Console {
	private ConsoleState state;
	
	
	public void start(){
		state = new PasswordRequestState(this);
	}
	
	public void switchState(ConsoleState state){
		this.state = state;
	}
	
}
