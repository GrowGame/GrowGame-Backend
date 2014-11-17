package growgame.backend.server;

public class KeepAliveRequest implements Request {

	private String posAck;
	private String errorMsg;

	@Override
	public boolean fulfillsRequirements(long userID, Object[] args) {
		// keep alive is okay if user has authenticated
		if(userID==-1){
			errorMsg = "SEND~false,user not authenticated";
			return false;
		}
		posAck = "";
		return true;
	}

	@Override
	public void execute() throws RequestArgumentsException {
		//nothing to do
	}

	@Override
	public Object[] parseArguments(String reqString)
			throws RequestArgumentsException {
		// keep alive has no args
		return new Object[0];
	}

	@Override
	public String getErrorMsg() {
		//null cause no error can occur
		return errorMsg;
	}

	@Override
	public String getPositiveAck() {
		// TODO Auto-generated method stub
		return null;
	}

}
