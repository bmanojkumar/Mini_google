package searchengine.search;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

import javax.swing.JApplet;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/*
 <applet code="GUI" width="300" height="50">
 </applet>
 */

import java.io.*;
import java.net.*;

class client {
	static String results = "";

	public static void main(String args) throws UnknownHostException,
			IOException {
		// System.out.println(args);
		// GUI k = new GUI();
		// k.init();

		boolean firsttime = true;
		Socket socket = new Socket("localhost", 4001);
		PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		BufferedReader reader2 = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));

		// String readerinput = reader.readLine();

		// System.out.println(args);
		writer.println(args);
		String s1 = null;
		String response = "";

		int k;

		/*
		 * while((k = reader2.read()) != -1) { if(k == -1)
		 * System.out.println("----1");; System.out.print((char)k); response =
		 * response+(char)k;
		 * 
		 * }
		 */

		SearchGUI.textArea.setText("No results");

		while ((response = reader2.readLine()) != null) {
			System.out.println("hhey...");
			// String query = "a.save hash and start valign";
			// String response = reader2.readLine();
			if (firsttime == true) {
				System.out.println(response);
				SearchGUI.textArea.setText(response);
				SearchGUI.textArea.append("\n");

				System.out.println("hi...!!");
				results = results.concat(response).concat("\n");
				firsttime = false;
			} else {
				System.out.println(response);
				SearchGUI.textArea.append(response);
				SearchGUI.textArea.append("\n");

				System.out.println("hi...!!");
				results = results.concat(response).concat("\n");
			}

		}

		System.out.println("outside while...!!!");

		// System.out.println("\n\tssfsdfs"+response);
		// SearchGUI.settext(results);
	}

	/*
	 * public static void main(String args[]) { try { Socket socket = new
	 * Socket("localhost",5554);
	 * 
	 * PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
	 * writer.println("manoj");
	 * 
	 * BufferedReader reader = new BufferedReader(new
	 * InputStreamReader(socket.getInputStream()));
	 * System.out.println("Reply from server : " + reader.readLine());
	 * 
	 * } catch(Exception e) {
	 * 
	 * } }
	 */
}
/*
 * class GUI extends JApplet {
 *//**
	 * 
	 */
/*
 * private static final long serialVersionUID = 1L; JTextField jtf;
 * 
 * public void init() { try { SwingUtilities.invokeAndWait( new Runnable() {
 * 
 * @Override public void run() { // TODO Auto-generated method stub makeGUI(); }
 * 
 * }); } catch(Exception e) {
 * 
 * } }
 * 
 * public void makeGUI() { setLayout( new FlowLayout());
 * 
 * jtf = new JTextField(20); add(jtf);
 * 
 * jtf.addActionListener(new ActionListener() {
 * 
 * @Override public void actionPerformed(ActionEvent arg0) { // TODO
 * Auto-generated method stub showStatus(jtf.getText()); } });
 * 
 * }
 */

