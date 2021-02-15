package com.NetworkChatter.server;

public class ServerMain {
	private int port;
	private Server server;
	public ServerMain(int port) {
		System.out.println(port);
		this.port =port;
		//creating server object
		server=new Server(port);
	}
	public static void main(String[] args) {
		int port;
		//checking for command line argument
		if(args.length != 1) {
			System.out.println("Usage : java -jar  NetworChatServer.jar [port]");
			return;
		}
		//converting port cli argument entered into integer 
		port = Integer.parseInt(args[0]);
		new ServerMain(port);
	}
}
