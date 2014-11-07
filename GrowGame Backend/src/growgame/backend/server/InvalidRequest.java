package growgame.backend.server;

public class InvalidRequest implements Request {



	@Override
	public boolean fulfillsRequirements(long userID, Object[] args) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void execute() throws RequestArgumentsException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] parseArguments(String reqString)
			throws RequestArgumentsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getErrorMsg() {
		// TODO Auto-generated method stub
		return null;
	}

}
