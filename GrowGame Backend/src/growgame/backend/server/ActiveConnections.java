package growgame.backend.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ActiveConnections {

	private ArrayList<Connection> list;
	private static ActiveConnections instance = null;
	private ReentrantLock lock;
	private static long Timeout = 7000;
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
					List<Connection> list = getConnections();
					for(Connection c : list){
						if(c.getInactiveTime().getTimeInMillis()>Timeout){
							removeConnection(c);
							System.out.println("Removed Connection: "+c.getUserID()+" due to Timeout");
						//	break;
						}
					}
					//wait some time, no need to check keepalive all the time
					try {
						Thread.sleep(2000);
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
		con.stop();
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
		Connection result = null;
		lock.lock();
		for(Connection c : getConnections()){
			if(c.getUserID()==userID){
				result =  c;
			}
		}
		lock.unlock();
		return result;
	}
	
	/**
	 * Gives a view only perspective of all active Connections
	 * Uses UmmutableList because iterating over Collections.unmodifiableList did not worked as expected...
	 * @return unmodifiable list of all active connections
	 */
	public List<Connection> getConnections(){
		//is mutual exclusion needed here?
		lock.lock();
		List<Connection> result = com.google.common.collect.ImmutableList.copyOf(list);
		lock.unlock();
		return result;
	}
	
	
	
}
