package growgame.backend.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ActiveConnections {

	private ArrayList<Connection> list;
	private static ActiveConnections instance = null;
	private ReentrantLock lock;
	private static int Timeout = 7;
	private boolean serverRunning;
	
	private ActiveConnections(){
		lock = new ReentrantLock();
		list = new ArrayList<Connection>();
		serverRunning = true;
		Runnable r = new Runnable(){		
			@Override
			/**
			 * Remove all connections who have been inactive since Timeout seconds
			 */
			public void run() {
				// TODO Auto-generated method stub 
				while(serverRunning){					
					for(Connection c : getConnections()){
						if(c.getInactiveTime()>Timeout){
							removeConnection(c);
						}
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			};
			Thread t = new Thread(r);
			t.start();
	}
	
	public void setRunning(boolean running){
		this.serverRunning = running;
	}
	
	public static ActiveConnections getInstance(){
		if(instance == null){
			instance = new ActiveConnections();			
		}
			return instance;
		
	}
	
	public void addConnection(Connection con){
		lock.lock();
		list.add(con);
		lock.unlock();
	}
	
	public void removeConnection(Connection con){
		lock.lock();
		list.remove(con);
		lock.unlock();
	}
	
	/**
	 * Returns the first Connection with ID = userID (two connections with the same IDs should not
	 * be possible anyway) or null if no such connection exists
	 * @param userID
	 * @return
	 */
	public Connection getConnection(long userID){
		for(Connection c : getConnections()){
			if(c.getUserID()==userID){
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Gives a view only perspective of all active Connections
	 * @return unmodifiable list of all active connections
	 */
	public List<Connection> getConnections(){
		//is mutual exclusion needed here?
		lock.lock();
		List<Connection> result = Collections.unmodifiableList(list);
		lock.unlock();
		return result;
	}
	
	
	
}
