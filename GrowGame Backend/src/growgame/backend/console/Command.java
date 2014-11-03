package growgame.backend.console;

/**
 * Not yet used
 * @author Alex
 *
 */
public abstract class Command {

	private String name;
	private String desc;




	public Command(String name){
		this.name = name;
	}
		
	
	
	public String toString(){
		return getName()+" - "+getDesc();
	}


	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc){
		this.desc = desc;
	}


	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	/**
	 * Encapsulates arguments from the console input and returns them
	 * @return the arguments provided by the user
	 */
	public abstract String[] parseArguments();
	
	/**
	 * Gets a description of the arguments
	 * @return a description of each argument or null if this command has no arguments
	 */
	public abstract String getArgumentDesc();
	
	/**
	 * Executes this command
	 */
	public abstract void execute();
	
	
}
