package com.NetworkChatter;

import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Shahzeb changed this repo mfker");
		//Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		//System.out.println(timestamp);
		String timeStamp = new SimpleDateFormat("HH:mm").format(new Date());
		
		System.out.println(timeStamp);

	}

	
}
