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
	 * If this is not the case, this method prepares an error-message used by getErrorMsg()
	 * @param userID
	 * @return
	 */
	public boolean fulfillsRequirements(long userID, Object[] args);
	
/** 	
   Executes the request, with the given arguments.
 * @throws RequestArgumentsException if no arguments have been read before, although the request needs one or more.
 */
	public void execute() throws RequestArgumentsException;
	
	
	
	/**
	 * Parses all given arguments from a string representing a request.
	 * request syntax: bsp "send": 'SEND~param1,param2,param3,Hello, my name is Alexander. I'm testing GrowGame'.
	 * All Requests should follow these syntax!!! The third part is optional 
	 * @return an array of arguments which have been parsed from reqString
	 * @throws RequestArgumentsException, if any argument is missing or has a wrong type or value
	 */
	public Object[] parseArguments(String reqString) throws RequestArgumentsException;
	
	/**
	 * Provides an error message to be sent to the user who requested
	 * @return the error message produced by fulfillsRequirements(long userID, Object[] args), or null if 
	 * fulfillsRequirements returned true or if fulfillsRequirements hasn't been executed before
	 */
	public String getErrorMsg();
	
	/**
	 * Returns the positive acknowledgement for a request, which is sent to the client to signalize
	 * that everything is okay and the request has been executed with success
	 * @return the positive acknowledgement for a failure free execution of the request
	 */
	public String getPositiveAck();
	
}
