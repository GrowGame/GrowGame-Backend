package growgame.backend.server;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationRequest implements Request {

	private String msg = null;
	private String username;
	@Override
	public boolean fulfillsRequirements(long userID, Object[] args) {
		// TODO check if userID exists
		//         no  -> msg="userID/password combination not found"
		//         yes -> check if password is correct
		//					no  -> msg="userID/password combination not found"
		//					yes -> check if user is already online
		//
		//
		System.out.println("Select username FROM growdb.useraccs WHERE useraccs.id="+args[0]+
				" AND pw=\""+args[1]+"\"");
		ResultSet rs = Database.getInstance().sendReadQuery("Select username FROM growdb.useraccs WHERE useraccs.id=\""+args[0]+
				"\" AND pw=\""+args[1]+"\"");
		try {
			if(rs==null){
				msg = "MySql server did not respond.";
				return false;
				}
			if(rs.next()){
				username = rs.getString("username");
				return true;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("false....");
		return false;
	}

	@Override
	public void execute() throws RequestArgumentsException {
		//this special request has no use for this method

	}

	@Override
	/**
	 * Request example: "AUTH~userID,password"
	 */
	public Object[] parseArguments(String reqString)
			throws RequestArgumentsException {
		String[] args = reqString.split("~")[1].split(",");
		if(args.length!=2){
			throw new RequestArgumentsException("Number of arguments must be exactly 2, but was "+args.length);
		}
		return args;
	}

	@Override
	public String getErrorMsg() {
		// TODO Auto-generated method stub
		return msg;
	}

	@Override
	public String getPositiveAck() {
		// TODO Auto-generated method stub
		return "ACK:Auth";
	}

	public String getUsername(int userID) {
		return username;
	}

}
