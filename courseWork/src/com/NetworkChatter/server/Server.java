package com.NetworkChatter.server;

import java.net.DatagramSocket;
import java.net.SocketException;

public class Server implements Runnable{
	private DatagramSocket socket;
	private int port;
	//creating thread for running server
	private Thread run;
	//creating thread for managing clients
	private Thread manage;
	//creating thread for sending data
	private Thread send;
	//creating thread for receiving data
	private Thread receive;
	//boolean to check wether server is running
	private boolean running=false;
	public Server(int port) {
		this.port =port;
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		run=new Thread(this,"Server");
	}

	public void run() {
		running =true;
		manageClients();
		receive();
	}
	
	//function to manage client 
	private void manageClients() {
		
		manage= new Thread("manage") {
			public void run() {
				while (running) {
					//managing clients
					
				}
			}
		};
		//starting thread
		manage.start();
	}
	

	
	//function to receive data
	private void receive() {
		receive= new Thread("Receive") {
			public void run() {
				while (running) {
					//receiving data
					
				}
			}
		};
		//starting thread
		receive.start();
	}
	
	
	
	
	
	
	
	
	
}
