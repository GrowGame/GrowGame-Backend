package growgame.backend.server;

public class KeepAliveRequest implements Request {

	@Override
	public boolean fulfillsRequirements(long userID, Object[] args) {
		// keep alive is always okay
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
		return null;
	}

}
