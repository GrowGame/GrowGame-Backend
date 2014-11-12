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
		BufferedReader in = null;
		BufferedWriter out = null;
		try {
			socket = new Socket("localhost",1337);
		//	socket.bind(new SocketAddress());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
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
			//send keepalive
			out.write("Auth~123,unreal\n");
			out.flush();
			System.out.println("Client: "+in.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("Not yet implemented");
	}

}
