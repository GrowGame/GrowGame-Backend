package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.SocketAddress;

import junit.framework.Assert;
import growgame.backend.server.CentralUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Tester {
	BufferedWriter out2 = null;
	CentralUnit centralunit;
	@Before
	public void setUp() throws Exception {
		Runnable r = new Runnable(){
				public void run(){
		centralunit = new CentralUnit();
		centralunit.startGameServer();
		}
		};
		Thread t = new Thread(r);
		t.start();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		System.out.println("test start...");
		
		Socket socket = null;
		Socket socket2 = null;
		BufferedReader in = null;
		BufferedWriter out = null;
		BufferedReader in2 = null;
		
		try {
			socket = new Socket("localhost",1337);
			socket2 = new Socket("localhost",1337);
		//	socket.bind(new SocketAddress());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
			out2 = new BufferedWriter(new OutputStreamWriter(socket2.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.write("SEND~123,lol ich faile\n");
			out.flush();
			//sleep 5 sec
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out2.write("AUTH~124,huso\n");
			out2.flush();
			//send keepalive
			out.write("Auth~123,unreal\n");
			out.flush();
			Runnable r = new Runnable(){

				@Override
				public void run() {
					while(true){
						
						try {
							out2.write("KeepAlive~\n");
							out2.flush();
							try {
								Thread.sleep(5000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
				
			};
			Thread t = new Thread(r);
			t.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.in.read(); //damit junit-test nicht endet
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("Not yet implemented");
	}

}
