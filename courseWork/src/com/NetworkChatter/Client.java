package com.NetworkChatter;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import java.awt.GridBagLayout;
import javax.swing.JTextArea;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	private String name;
	private String address;
	private int port;
	private InetAddress ip;

	
	private JTextField txtMessage;
	private JTextArea history;
	
	// intialising client socket
	private DatagramSocket socket;
	//initalising ip adress variable
	
	private Thread send;
	
	public Client(String name, String address, int port) {
		setTitle("Network Client");
		this.name = name;
		this.address = address;
		this.port = port;
		//checking connection
		boolean connect=openConnection(address);
		if (!connect) {
			System.err.println("Connection failed!");
		}
		
		
		createWindow();
		console("Attempting a connection to " + address + ":"+port +" user: "+ name);
		String connection="/c/" + name;
		send(connection.getBytes());

	}
	
	
	//receiving data
	private String receive() {
	
		byte[] data = new byte[1024];
		//creating packet to store data
		DatagramPacket packet = new DatagramPacket(data, data.length);
		//collecting packet of data
		try {
			//receiving data
		socket.receive(packet);
		} catch(IOException e){
			e.printStackTrace();
		}
		//converting bytes to string
		String message = new String(packet.getData());
		return message;
	}
	
	
	//sending data
	private void send(final byte[] data)
	{
		send = new Thread("Send") {
			public void run() {
				//creating packet to send data, with server ip and port as parameters
				DatagramPacket packet = new DatagramPacket(data,data.length,ip,port);
				try {
					socket.send(packet);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		//start thread
		send.start();
	}
	
	private boolean openConnection(String address) {
		try {
			//add port to listen to text in socket MAYBE?
			socket= new DatagramSocket();
			ip= InetAddress.getByName(address);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		}catch (SocketException e) {
			e.printStackTrace();
			return false;

		}
		return true;	
		
	}
	private void createWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(880,550);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{28, 815,30,7};
		gbl_contentPane.rowHeights = new int[]{35, 475,40};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		history = new JTextArea();
		history.setEditable(false);
		
		//putting text area in scrollpane
		JScrollPane scroll = new JScrollPane(history);
		GridBagConstraints scrollConstraints = new GridBagConstraints();
		scrollConstraints.insets = new Insets(0, 0, 5, 5);
		scrollConstraints.fill = GridBagConstraints.BOTH;
		scrollConstraints.gridx = 0;
		scrollConstraints.gridy = 0;
		scrollConstraints.gridwidth =3;
		scrollConstraints.gridheight =2;

		scrollConstraints.insets=new Insets(0,5,0,0);
		contentPane.add(scroll, scrollConstraints);
		
		txtMessage = new JTextField();
		txtMessage.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				//if the enter button is pressed in the text field the message will be sent to the console directly
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					send(txtMessage.getText());
				}
			}
		});
		GridBagConstraints gbc_txtMessage = new GridBagConstraints();
		gbc_txtMessage.insets = new Insets(0, 0, 0, 5);
		gbc_txtMessage.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMessage.gridx = 0;
		gbc_txtMessage.gridy = 2;
		gbc_txtMessage.gridwidth = 2;

		contentPane.add(txtMessage, gbc_txtMessage);
		txtMessage.setColumns(10);
		
		JButton btnNewButton = new JButton("Send");
		//when send button i s clicked the text entered will appear in the console
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//getting text from input field
				String message= txtMessage.getText();
				send(message);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 2;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		setVisible(true);
		txtMessage.requestFocusInWindow();
	}
		
	private void send(String message){
		//time stamp
		String timeStamp = new SimpleDateFormat("HH:mm").format(new Date());

		//checking for empty message
		if(message.equals("")) return;
		message=timeStamp+ "  "+name + ": " + message;
		//displaying message to console
		console(message);
		//returns a byte array of the string message to send to server
		send(message.getBytes());
		//clearing input field after text sent
		txtMessage.setText("");
	}
	
	public void console(String message) {
		history.append(message +"\n");
		//setting caret position so that window scrolls to default position every time message is sent or received
		history.setCaretPosition(history.getDocument().getLength());

	}
}
