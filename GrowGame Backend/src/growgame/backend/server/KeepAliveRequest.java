package growgame.backend.server;

public class KeepAliveRequest implements Request {

	private String errmsg;

	@Override
	public boolean fulfillsRequirements(long userID, Object[] args) {
		// keep alive is okay if user has authenticated
		if(userID!=-1)
			return true;
		errmsg = "Authentication required before keepAlive";
		return false;
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
		return errmsg;
	}

	@Override
	public String getPositiveAck() {
		// TODO Auto-generated method stub
		return null;
	}

}
