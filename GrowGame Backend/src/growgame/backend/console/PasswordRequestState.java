package growgame.backend.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PasswordRequestState implements ConsoleState {
	
	public static ConsoleState state;
	private Console console;
	private HashSet<String> commands;

	public PasswordRequestState(Console c){
		console = c;
		commands = new HashSet<String>();
		commands.add("showCommands");
	}
	
	@Override
	public void handle() {
		 char[] passwd = null;	 
		BufferedReader in = console.getIn();
		String input = "";
		boolean validPW= false;
		System.out.println("Password required:");
		//continue reading until correct command was typed or user typed exit
		while(!validPW){
			input = "";
				 if ((passwd = System.console().readPassword("[%s]", "Password:")) != null) {
					 input = new String(passwd);
				     java.util.Arrays.fill(passwd, ' ');
				 }
//			try {
//				input = in.readLine();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			if(input.equals("wiesenfeld666"))
				validPW = true;
		}
		console.switchState(ConfigureState.state);
	}

	@Override
	public Set<String> getCommands() {
		// TODO Auto-generated method stub
		return commands;
	}

}
