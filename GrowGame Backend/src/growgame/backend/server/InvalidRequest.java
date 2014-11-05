package growgame.backend.server;

public class InvalidRequest implements Request {

	@Override
	public boolean fulfillsRequirements(long userID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void execute(Object... param) {
		// TODO Auto-generated method stub

	}

}
