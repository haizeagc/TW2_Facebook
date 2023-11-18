package sd.deusto.ingenieria.sd.tw2.server.main;

import java.io.IOException;
import java.net.ServerSocket;

import sd.deusto.ingenieria.sd.tw2.server.Service.FacebookService;

public class MainServer {
	
	private static int numClients = 0;
	
	public static void main(String[] args) {
		
		if(args.length < 1) {
			System.out.println(" # Usage: FacebookServer [PORT]");
			System.exit(1);
		}
		int serverPort = Integer.parseInt(args[0]);
		try (ServerSocket tcpServerSocket = new ServerSocket(serverPort);){
			System.out.println("- FacebookServer: waiting for connections '" + tcpServerSocket.getInetAddress().getHostAddress() + ":" + tcpServerSocket.getLocalPort() + "'...");
			
			while (true) {
				new FacebookService(tcpServerSocket.accept());
				System.out.println(" - FacebookServer: New client connection accepted. Client number: " +  ++numClients);
			}
		} catch (IOException e) {
			System.err.println(" # FacebookServer: IO error: " + e.getMessage());
		}
	}
	
}
