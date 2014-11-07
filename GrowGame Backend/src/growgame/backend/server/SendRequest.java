package growgame.backend.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class SendRequest implements Request {

	
	private String errorMsg;
	private Object[] args;
	private Connection sender;
	private Connection[] recipients;
	private String msg;
	public SendRequest() {
	
	}

	@Override
	public boolean fulfillsRequirements(long userID, Object[] args) {
		if(args.length==0){
			errorMsg = "At least one argument (message recipient) is required";
			return false;
		}
		//TODO Check if recipients exist and write recipients in global variable
		msg = (String) args[args.length-1];
		return true;
	}

	@Override
	public void execute() throws RequestArgumentsException {
		// TODO Auto-generated method stub
		System.out.println("Trying to send...");
		for(Connection con : recipients){
				try {
					con.getOut().write(con.getSocket().getInetAddress()+": \""+msg+"\"");
					con.getOut().flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	@Override
	/**
	 * 	
	 * Parses all given arguments from a string representing a request.
	 * request syntax: bsp "send": 'SEND~param1,param2,param3~Hello, my name is Alexander. I'm testing GrowGame'.
	 * All Requests should follow these syntax!!! The third part is optional 
	 * @return an array of arguments which have been parsed from reqString
	 * @throws RequestArgumentsException, if any argument is missing or has a wrong type or value
	 */
	public Object[] parseArguments(String reqString)
			throws RequestArgumentsException {
		String[] splitTilde = reqString.split("~");
		String[] args = splitTilde[1].split(",");
		Object[] result = new Object[args.length];
		for(int i=0;i<args.length-1;i++){
			try{
				result[i] = Long.parseLong(args[i]);
			}
			catch(NumberFormatException e){
				throw new RequestArgumentsException("Argument with index "+i+" isn't of type long");
			}
		}
		result[args.length-1] = args[args.length-1];
		return result;
	}

	@Override
	public String getErrorMsg() {
		// TODO Auto-generated method stub
		return errorMsg;
	}



}
