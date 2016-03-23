package com.sqrfactor.sms.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.sqrfactor.sms.SMS;

/**
 * @author Angad Gill
 *
 */
public class TextLocalSmsImpl extends SMS{
	
	public boolean send(String contactNumber,String messageText) {
		try {
			// Construct data
			String apiKey = "apiKey=" + "XC8WfJy455Q-As94X3d5fpI3n82SCEbfRMm522bpFa";
			String message = "&message=" + messageText;
			String sender = "&sender=" + "SQRFAC";
			String numbers = "&numbers=" + "917263957201";
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("http://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			
			//return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return false;
		}
		return true;
	}


}
