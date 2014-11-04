package growgame.backend.server;

/**
 * This interface represents requests which have been sent by users to the server
 * A user needs to fulfill all requirements of a request in order to be able to execute it.
 * These requirements are checked before executing.
 * That means that the execute(Object... param) method does not need to check any requirements, because this has
 * been done before.
 * 
 * Note that requirements are being accessed by many threads simultaneously. Both methods should define a
 * critical section, if reading AND writing is needed by the requirement
 * @author Alex
 *
 */
public interface Request {

	/**
	 * Tests if the user or his inventory/profile is capable of executing this request
	 * @param userID
	 * @return
	 */
	public boolean fulfillsRequirements(long userID);
	
	/**
	 * Executes the request
	 * @param param a list of request-specific parameters, which are needed to execute the request as desired
	 */
	public void execute(Object... param);
	
	
}
