package searchengine.search;

import java.io.*;
import java.net.*;

class server {

	public static final int PORT = 4001;

	public static void main(String args[]) throws IOException {
		new server().runserver();
	}

	public void runserver() throws IOException {
		ServerSocket ser = new ServerSocket(PORT);
		if (ser.isBound())
			System.out.println("Server is up...");
		while (true) {
			Socket socket = ser.accept();
			new serverthread(socket).start();
		}

		// ser.close();
	}

	/*
	 * public static void main(String args[]) { try { ServerSocket server = new
	 * ServerSocket(5554); Socket socket = server.accept();
	 * 
	 * System.out.println("server is up ....");
	 * 
	 * BufferedReader reader = new BufferedReader(new
	 * InputStreamReader(socket.getInputStream()));
	 * 
	 * String message = reader.readLine();
	 * 
	 * System.out.println("message from server : " + message);
	 * 
	 * PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
	 * writer.println("echo : " + message); } catch(Exception e) {
	 * System.out.println(e); }
	 * 
	 * }
	 */
}