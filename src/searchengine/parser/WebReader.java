/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * 
 */

package searchengine.parser;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;

import searchengine.element.PageElementInterface;
import searchengine.url.URLTextReader;

/**
 * A simple test program for Webreader assignment
 * 
 * This class provides a main program for testing Part 1. It opens a web page
 * and creates a PageLexer object for it. It then retrieves and prints out all
 * of the PageElements returned by the PageLexer.
 * 
 * To run this program from the command line, type the following:
 * 
 * % java WebReader <url>
 * 
 * where <url> is the URL of a web page to read.
 * 
 */
public class WebReader {

	public static PageLexer<PageElementInterface> elts;

	public static void main(String[] args) {
		try {

			final String authUser = "iiit-37";
			final String authPassword = "msit123";

			System.setProperty("http.proxyHost", "10.10.10.3");
			System.setProperty("http.proxyPort", "3128");
			System.setProperty("http.proxyUser", authUser);
			System.setProperty("http.proxyPassword", authPassword);

			Authenticator.setDefault(new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(authUser, authPassword
							.toCharArray());
				}
			});

			System.setProperty("http.proxyUser", authUser);
			System.setProperty("http.proxyPassword", authPassword);

			URL u;
			// FileOutputStream saveFile;

			if (args.length >= 1) {
				// if(true){

				u = new URL(args[0]);

				URLTextReader in = new URLTextReader(u);

				// Parse the page into its elements
				elts = new PageLexer<PageElementInterface>(in, u);

				// Print out the tokens
				/*
				 * int count = 0; while (elts.hasNext()) { count++;
				 * PageElementInterface elt = (PageElementInterface)elts.next();
				 * if (elt instanceof PageWord)
				 * System.out.println("word: "+elt); else if (elt instanceof
				 * PageHref) System.out.println("link: "+elt); else if (elt
				 * instanceof PageNum) System.out.println("num: "+elt); else if
				 * (elt instanceof PageImg) System.out.println("img: "+elt);
				 * else if (elt instanceof PageIframe)
				 * System.out.println("Iframe: "+elt); }
				 */
				// System.out.println();
				// System.out.println(count +
				// " total page elements retrieved.");
			} else {
				System.out.println("Usage: WebReader url");
				return;
			}
		} catch (IOException e) {
			System.out.println(e);
			System.out.println("Bad file or URL specification");
		}
	}

}