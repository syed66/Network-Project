package com.NetworkChatter.server;

import java.net.InetAddress;

public class ClientServer {
	
	//storing details of client
	public String name;
	public InetAddress address;
	public int port;
	//unique id of user
	private final int ID;
	public int attempt = 0;
	
	
	public ClientServer(String name,InetAddress address,int port, final int ID) {
		this.name=name;
		this.ID=ID;
		this.address=address;
		this.port=port;
	}
	
	public int getID() {
		return ID;
	}
}
