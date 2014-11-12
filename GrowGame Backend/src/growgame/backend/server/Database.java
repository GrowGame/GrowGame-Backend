package growgame.backend.server;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.locks.ReentrantLock;

import com.mysql.jdbc.Statement;

public class Database {

	private static Database instance;
	private static final String host = "localhost";
	private static java.sql.Connection con;
	private static ReentrantLock lock = new ReentrantLock();
	
	private Database(){
		
	}
	
	/**
	 * Database Singleton, returns an instance of Database
	 * @return an instance of Database
	 */
	public static Database getInstance(){
		if(instance == null){
			instance = new Database();
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			try {

				con = DriverManager.getConnection("jdbc:mysql://"+host+"/database","user","password");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
		return instance;
	}
	
	/**
	 * Reconnects to the MySQL-Server
	 */
	public void reconnect(){
		lock.lock();
		try {
			con = DriverManager.getConnection("jdbc:mysql://"+host+"/database","user","password");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock.unlock();
	}
	
	/**
	 * Expects a mysql query string, which is used to get data from the currently connected mysql server
	 * @param a read query string
	 * @return the result set given by the server as respond to the query
	 */
	public ResultSet sendReadQuery(String query){
		java.sql.Statement st;
		ResultSet rs = null;
		lock.lock();
		try {
			st = con.createStatement();
			rs = st.executeQuery("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock.unlock();
		return rs;
		
	}
	
	/**
	 * Expects a mysql update string(INSERT, UPDATE, DELETE ), which is used to get data from the currently
	 *  connected mysql server
	 * @param a update query string
	 */
	public void sendUpdateQuery(String query){
		java.sql.Statement st;
		lock.lock();
		try {
			st = con.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock.unlock();
	}
	

}
