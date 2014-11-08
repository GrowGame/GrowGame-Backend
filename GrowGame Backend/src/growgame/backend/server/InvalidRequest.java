package growgame.backend.server;

public class InvalidRequest implements Request {

	String msg = "UnknownRequest~";

	/**
	 * Creates a new invalid request object. The constructor needs the request string, which was
	 *  used to parse a valid request
	 * @param request
	 */
	public InvalidRequest(String request){
		msg+=request;
	}
	
	@Override
	public boolean fulfillsRequirements(long userID, Object[] args) {
		// returns always false because the request is not valid
		return false;
	}

	@Override
	public void execute() throws RequestArgumentsException {
		// do nothing, this method will never be executed		
	}

	@Override
	public Object[] parseArguments(String reqString)
			throws RequestArgumentsException {
		// TODO Auto-generated method stub
		return new Object[0];
	}

	@Override
	public String getErrorMsg() {
		// TODO Auto-generated method stub
		return "The desired request could not be recognized!";
	}

	@Override
	public String getPositiveAck() {
		// TODO Auto-generated method stub
		return null;
	}

}
