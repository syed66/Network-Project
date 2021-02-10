package com.NetworkChatter;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Gui extends JFrame {

	private JPanel contentPane;
	private JTextField textName;
	private JTextField textAddress;
	private JLabel lblName;
	private JTextField textPort;
	private JLabel lblPort;
	private JTextField textServerPort;
	private JTextField textServerIP;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Gui() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(290,400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textName = new JTextField();
		textName.setBounds(84, 44, 154, 20);
		contentPane.add(textName);
		textName.setColumns(10);
		
		JLabel lblNIPAddress = new JLabel("IP Address to listen to:");
		lblNIPAddress.setBounds(104, 79, 113, 14);
		contentPane.add(lblNIPAddress);
		
		textAddress = new JTextField();
		textAddress.setBounds(84, 108, 154, 20);
		contentPane.add(textAddress);
		textAddress.setColumns(10);
		
		lblName = new JLabel("Name(ID):");
		lblName.setBounds(134, 15, 54, 14);
		contentPane.add(lblName);
		
		textPort = new JTextField();
		textPort.setColumns(10);
		textPort.setBounds(84, 172, 154, 20);
		contentPane.add(textPort);
		//updated
		lblPort = new JLabel("Port to listen to:");
		lblPort.setBounds(121, 143, 80, 14);
		contentPane.add(lblPort);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				String name = textName.getText();
				String address = textAddress.getText();
				int port = Integer.parseInt(textPort.getText());
				login(name, address, port);
			}

			
		});
		btnNewButton.setBounds(116, 335, 89, 23);
		contentPane.add(btnNewButton);
		
		textServerPort = new JTextField();
		textServerPort.setBounds(84, 236, 154, 20);
		contentPane.add(textServerPort);
		textServerPort.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("port of server:");
		lblNewLabel.setBounds(121, 207, 80, 14);
		contentPane.add(lblNewLabel);
		
		textServerIP = new JTextField();
		textServerIP.setBounds(84, 300, 154, 20);
		contentPane.add(textServerIP);
		textServerIP.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Address of server:");
		lblNewLabel_1.setBounds(110, 271, 101, 14);
		contentPane.add(lblNewLabel_1);
	}
	
	
	private void login(String name, String address, int port) {
		dispose();
		new Client(name, address, port);
		
	}
}