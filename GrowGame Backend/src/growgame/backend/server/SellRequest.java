package growgame.backend.server;

public class SellRequest implements Request {

	private String errorMsg;

	@Override
	public boolean fulfillsRequirements(long userID, Object[] args) {
		// TODO Auto-generated method stub
		if(userID==-1){
			errorMsg = "SEND~false,user not authenticated";
			return false;
		}
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

	@Override
	public String getPositiveAck() {
		// TODO Auto-generated method stub
		return null;
	}



}
