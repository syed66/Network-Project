package com.NetworkChatter.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable{
	//saving client details in list as multiple clientServer classes
	private List<ClientServer> clients=new ArrayList<ClientServer>();
	
	
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
			return;
		}
		run=new Thread(this,"Server");
		run.start();
	}

	public void run() {
		running =true;
		System.out.println("Server started on port "+ port);
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
					//storing we receive in bytes
					byte[] data =new byte[1024];
					DatagramPacket packet= new DatagramPacket(data,data.length);
					try {
						socket.receive(packet);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//process packet
					process(packet);
					clients.add(new ClientServer("shahzeb",packet.getAddress(),packet.getPort(),50));
					System.out.println(clients.get(0).address.toString() + ":" + clients.get(0).port);
			
				}
			}
		};
		//starting thread
		receive.start();
	}
	
	//processing data
	private void process(DatagramPacket packet) {
		//data received
		String string=new String(packet.getData());
		//if user makes a connection request to be added to members list
		if (string.startsWith("/c/")) {
			clients.add(new ClientServer(string.substring(3,string.length()),packet.getAddress(),packet.getPort(),50));
			System.out.println(string.substring(3,string.length()));
		}else {
			System.out.println(string);
		}
	}
	
	
	
	
	
	
	
	
}
