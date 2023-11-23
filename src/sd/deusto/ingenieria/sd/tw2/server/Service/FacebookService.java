package sd.deusto.ingenieria.sd.tw2.server.Service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

import sd.deusto.ingenieria.sd.tw2.methods.FacebookMethods;

public class FacebookService extends Thread {
	private DataInputStream in;
	private DataOutputStream out;
	private Socket tcpSocket;
	
	private static String DELIMITER = "#";
	
	public FacebookService(Socket socket) {
		try {
			this.tcpSocket = socket;
			this.in = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			this.start();
		} catch (IOException e) {
			System.err.println("#FacebookService - TCPConnection IO error:"+ e.getMessage());
		}
	}
	
	public void run() {
		try {
			String data = this.in.readUTF();
			System.out.println("  - FacebookService - Received data from '" +  tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "'->'" + data + "'");
			if (data.contains("login")) {
				data = this.Loggin(data);
			}else if (data.contains("register")) {
				data = this.Register(data);
			}
			this.out.writeUTF(data);					
			System.out.println("   - FacebookService - Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data.toUpperCase() + "'");
		} catch (EOFException e) {
			System.err.println("   # FacebookService - TCPConnection EOF error" + e.getMessage());
		} catch (IOException e) {
			System.err.println("   # FacebookService - TCPConnection IO error:" + e.getMessage());
		} finally {
			try {
				tcpSocket.close();
			} catch (IOException e) {
				System.err.println("   # FacebookService - TCPConnection IO error:" + e.getMessage());
			}
		}
	}
	
	public String Loggin(String msg) {
		boolean result = false;
		
		if (msg != null && !msg.trim().isEmpty()) {
			try {
				StringTokenizer tokenizer = new StringTokenizer(msg, DELIMITER);		
				String extra = tokenizer.nextToken();
				String email = tokenizer.nextToken();
				String password = tokenizer.nextToken();
				System.out.println("   - loggin: " + email);
				if (email != null && password != null) {
					result = FacebookMethods.getInstance().loginService(email, password);
					System.out.println("   - Facebook result: " + result);
				}
			} catch (Exception e) {
				System.err.println("   # FacebookService - Translation API error:" + e.getMessage());
				result = false;
				
			}
		}
		return Boolean.toString(result);
	}
	public String Register(String msg) {
		boolean result = false;
		
		if (msg != null && !msg.trim().isEmpty()) {
			try {
				StringTokenizer tokenizer = new StringTokenizer(msg, DELIMITER);		
				String extra = tokenizer.nextToken();
				String email = tokenizer.nextToken();
				String password = tokenizer.nextToken();
				System.out.println("   - Registering: " + email);
		
				if (email != null && password != null) {
					result = FacebookMethods.getInstance().register(email, password);
					System.out.println("   - Facebook result: " + result);
				}
			} catch (Exception e) {
				System.err.println("   # FacebookService - Facebook API error:" + e.getMessage());
				result = false;
			}
		}
		
		return Boolean.toString(result);
	}
}
