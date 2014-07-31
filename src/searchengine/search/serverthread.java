package searchengine.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;

import searchengine.dictionary.ObjectIterator;

public class serverthread extends Thread {
	Socket socket;

	serverthread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			String message = null;
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
			message = reader.readLine();

			String args[] = message.split(" ");
			ArrayList<String> temp = new ArrayList<String>();

			for (int i = 0; i < args.length; i++) {
				if (!args[i].isEmpty()) {
					temp.add(args[i].toLowerCase());
				}
			}

			String arg[] = new String[temp.size()];
			ArrayList<String> arg1 = new ArrayList<String>();

			Iterator<String> y = temp.iterator();

			for (int i = 0; i < temp.size(); i++) {
				arg[i] = y.next();
			}

			for (int i = 2; i < arg.length; i++) {
				String t = arg[i];
				String temp1 = "";
				for (int j = 0; j < t.length(); j++) {
					int k = t.charAt(j);
					if ((k >= 65 && k <= 90) || (k >= 97 && k <= 122)) {
						temp1 = temp1 + (char) k;
					}

				}

				arg[i] = temp1;
			}

			System.out.println("length of arg :" + arg.length);

			for (int i = 0; i < arg.length; i++) {
				if (!arg[i].isEmpty()) {
					arg1.add(arg[i]);
				}
			}

			String arg2[] = new String[arg1.size()];

			Iterator<String> h = arg1.iterator();

			for (int i = 0; i < arg1.size(); i++) {
				arg2[i] = h.next();
			}

			System.out.println(arg1);
			SearchDriver driver = new SearchDriver();
			driver.main(arg2);
			ObjectIterator<?> i = driver.i;
			String j = "";
			try {
				while (i.hasNext()) {
					String k = (String) i.next();
					System.out.println(k);
					// j = j+k+"\n";
					writer.println(k);
				}

				//
				/*
				 * writer.print(j); writer.print("\n"); writer.print(-1);
				 */

				/*
				 * System.out.println("Incoming client message : " + message);
				 * writer.println("from server" + message);
				 */
			} catch (Exception e) {

			}

			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}