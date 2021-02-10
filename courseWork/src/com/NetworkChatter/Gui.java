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
		setSize(290,330);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textName = new JTextField();
		textName.setBounds(70, 35, 154, 20);
		contentPane.add(textName);
		textName.setColumns(10);
		
		JLabel lblNIPAddress = new JLabel("IP Address:");
		lblNIPAddress.setBounds(114, 85, 66, 14);
		contentPane.add(lblNIPAddress);
		
		textAddress = new JTextField();
		textAddress.setBounds(70, 100, 154, 20);
		contentPane.add(textAddress);
		textAddress.setColumns(10);
		
		lblName = new JLabel("Name:");
		lblName.setBounds(124, 21, 46, 14);
		contentPane.add(lblName);
		
		textPort = new JTextField();
		textPort.setColumns(10);
		textPort.setBounds(70, 162, 154, 20);
		contentPane.add(textPort);
		
		lblPort = new JLabel("Port:");
		lblPort.setBounds(114, 146, 66, 14);
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
		btnNewButton.setBounds(99, 239, 89, 23);
		contentPane.add(btnNewButton);
	}
	
	
	private void login(String name, String address, int port) {
		dispose();
		new Client(name, address, port);
		
	}


}
