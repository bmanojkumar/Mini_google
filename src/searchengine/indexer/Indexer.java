/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Modified by Mahender on 12-10-2009
 */

package searchengine.indexer;

import java.awt.List;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import searchengine.dictionary.AVLDictionary;
import searchengine.dictionary.BSTDictionary;
import searchengine.dictionary.DictionaryInterface;
import searchengine.dictionary.HashDictionary;
import searchengine.dictionary.ListDictionary;
import searchengine.dictionary.MyHashDictionary;
import searchengine.dictionary.ObjectIterator;
import searchengine.element.PageElementInterface;
import searchengine.element.PageWord;

/**
 * Web-indexing objects. This class implements the Indexer interface using a
 * list-based index structure.
 * 
 * A Hash Map based implementation of Indexing
 */
public class Indexer implements IndexerInterface {
	/**
	 * The constructor for ListWebIndex.
	 */

	// Index Structure
	DictionaryInterface index;
	int i = 0;
	boolean noresults = false;
	boolean firsttime = false;
	boolean more = false;
	String ll = "";
	String repeat = "";
	boolean repeat1 = false;

	// This is for calculating the term frequency
	HashMap<String, Integer> wordFrequency = new HashMap<String, Integer>();
	HashMap<String, String> restore = new HashMap<String, String>();
	// HashMap<String,Integer> rank = new HashMap<String,Integer>();
	HashMap<String, String> rank = new HashMap<String, String>();
	SetImplementation<String> sett = new SetImplementation<String>();
	SetImplementation<String> settemp = new SetImplementation<String>();

	public Indexer(String mode) {
		// hash - Dictionary Structure based on a Hashtable or HashMap from the
		// Java collections
		// list - Dictionary Structure based on Linked List
		// myhash - Dictionary Structure based on a Hashtable implemented by the
		// students
		// bst - Dictionary Structure based on a Binary Search Tree implemented
		// by the students
		// avl - Dictionary Structure based on AVL Tree implemented by the
		// students

		// HERE WE SELECT THE NECESSARY INDEX

		if (mode.equals("hash"))
			index = new HashDictionary();
		else if (mode.equals("list")) {
			// System.out.println("calling list dict");
			index = new ListDictionary();
		} else if (mode.equals("myhash"))
			index = new MyHashDictionary<String, String>();
		else if (mode.equals("bst"))
			index = new BSTDictionary();
		else if (mode.equals("avl"))
			index = new AVLDictionary();
	}

	/**
	 * Add the given web page to the index.
	 * 
	 * @param arr
	 *            The web page to add to the index
	 * @param keywords
	 *            The keywords that are in the web page
	 * @param links
	 *            The hyperlinks that are in the web page
	 */

	// HERE WE ADD THE GIVE PAGE TO DICTIONARY
	public void addPage(String url, ObjectIterator<?> keywords) {
		// //////////////////////////////////////////////////////////////////
		// Write your Code here as part of Integrating and Running Mini Google
		// assignment
		//
		// /////////////////////////////////////////////////////////////////

		// if(keywords == null) System.out.println("nulll.... ");

		// System.out.println(keywords.size());

		wordFrequency.clear();

		System.out.println(++i + " :" + url);

		// System.out.println(keywords.toString());

		while (keywords.hasNext() == true) {
			String k = (String) keywords.next();
			// System.out.println(k);
			// System.out.println(wordFrequency.containsKey(k));
			if (wordFrequency.containsKey(k)) {
				// System.out.println("updating...");
				wordFrequency.put(k, (Integer) wordFrequency.get(k) + 1);
			} else {
				// System.out.println("new...");
				wordFrequency.put(k, 1);
			}

		}

		// System.out.println(wordFrequency.size());

		for (String s : wordFrequency.keySet()) {
			// System.out.println("hi");
			// System.out.println("word :"+ s +" = " + wordFrequency.get(s));
			// index.insert(url+" "+wordFrequency.get(s), s);
			String[] keys = null;
			if (firsttime == false) {
				// keys = (String[]) index.getKeys();
				firsttime = true;
				index.insert(s, url + "$" + wordFrequency.get(s));
			} else {
				// if(keys == null) System.out.println("ewfemw");

				keys = (String[]) index.getKeys();
				if (!Arrays.asList(keys).contains(s)) {
					index.insert(s, url + "$" + wordFrequency.get(s));
				} else {
					index.insert(s, index.getValue(s) + "," + url + "$"
							+ wordFrequency.get(s));
				}
			}
		}

		String[] keys = (String[]) index.getKeys();

		// System.out.println("length :" + keys.length);
		int i = 0;
		/*
		 * while(i<keys.length) { System.out.print(i+1); System.out.println(") "
		 * +keys[i] +", "+index.getValue(keys[i])); i++; }
		 */
	}

	/**
	 * Produce a printable representation of the index.
	 * 
	 * @return a String representation of the index structure
	 */

	/**
	 * Retrieve all of the web pages that contain the given keyword.
	 * 
	 * @param keyword
	 *            The keyword to search on
	 * @return An iterator of the web pages that match.
	 */

	// HERE WE RETRIEVE THE KEYWORD URLS FROM THE FILE
	public ObjectIterator<?> retrievePages(String keyword, String operator) {
		// //////////////////////////////////////////////////////////////////
		// Write your Code here as part of Integrating and Running Mini Google
		// assignment
		//
		// /////////////////////////////////////////////////////////////////

		HashMap<String, String> temp = new HashMap<String, String>();

		String j = null;
		String[] p = null;

		String s = keyword;

		// j = restore.get(s); //we'll get urls here

		String[] kk = (String[]) index.getKeys();

		for (int i = 0; i < kk.length; i++) {
			if (kk[i].equals(keyword)) {
				System.out.println("found...");
				j = (String) index.getValue(kk[i]);
			}
		}

		System.out.println(s);
		// j = (String) index.getValue(s);
		System.out.println(j);

		if (j != null)
			p = j.split(",");

		if (rank.isEmpty() && j != null) {
			// System.out.println("first time...");
			for (String h : p) {
				// System.out.println(h);
				String[] l = h.split("\\$");
				// System.out.println(l[0]);
				// System.out.println(l[1]);

				String[] temp1 = l[0].split("/");
				// System.out.println("length :" + temp1.length);
				int t = (Integer.parseInt(l[1]) / temp1.length) * 100;
				l[1] = Integer.toString(t);
				// System.out.println("rank" + l[0] + "  " + l[1]);

				rank.put(l[0], l[1]);

			}

			rank = sortByValue(rank);

			for (String k : rank.keySet()) {
				sett.add(k);
			}

			/*
			 * System.out.println("1"); System.out.println(sett.toString());
			 */
			// System.out.println(rank.toString());
		} else if (j != null) {
			more = true;
			// System.out.println("another time");
			for (String h : p) {
				String[] l = h.split("\\$");

				String[] temp1 = l[0].split("/");
				int t = (Integer.parseInt(l[1]) / temp1.length) * 100;
				l[1] = Integer.toString(t);
				temp.put(l[0], l[1]);

			}

			// System.out.println(temp.toString());

			temp = sortByValue(temp);

			for (String k : temp.keySet()) {
				settemp.add(k);
			}
			// System.out.println("2");
			// System.out.println("sett" + sett.toString());
			if (operator.equalsIgnoreCase("and"))
				sett = (SetImplementation<String>) sett.intersection(settemp);
			else if (operator.equalsIgnoreCase("or"))
				sett = (SetImplementation<String>) sett.union(settemp);
			else if (operator.equalsIgnoreCase("not"))
				sett = (SetImplementation<String>) sett.difference(settemp);
			// System.out.println(sett.toString());
			rank.clear();

			// rank = temp;
			rank.putAll(temp);
			// System.out.println(rank.toString());

			temp.clear();
		} else if (j == null && operator.equalsIgnoreCase("and")) {
			System.out.println(" into null....");
			sett.clear();
			settemp.clear();
			sett.fclear();
			settemp.fclear();
			// System.out.println("j is null..");
			/*
			 * settemp.clear(); sett = (SetImplementation<String>)
			 * sett.difference(settemp);
			 */
		}

		return new ObjectIterator<PageElementInterface>(
				new Vector<PageElementInterface>());
	}

	/**
	 * Retrieve all of the web pages that contain any of the given keywords.
	 * 
	 * @param keywords
	 *            The keywords to search on
	 * @return An iterator of the web pages that match.
	 * 
	 *         Calculating the Intersection of the pages here itself
	 **/

	HashMap<String, String> sortByValue(HashMap<String, String> map) {

		LinkedList<String> list = new LinkedList(map.entrySet());
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) cleanComparison((((Map.Entry) (o1))
						.getValue()).toString()))
						.compareTo(cleanComparison((((Map.Entry) (o2))
								.getValue()).toString()));
			}
		});
		HashMap<String, String> result = new LinkedHashMap<String, String>();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			result.put((String) entry.getKey(), (String) entry.getValue());
		}
		return result;
	}

	String cleanComparison(String s) {
		String result = s.toLowerCase();
		String[] or = { "á", "é", "í", "ó", "ú", "ä", "ë", "ï", "ö", "ü", "à",
				"è", "ì", "ò", "ù" };
		String[] res = { "a", "e", "i", "o", "u", "a", "e", "i", "o", "u", "a",
				"e", "i", "o", "u" };
		for (int i = 0; i < or.length; i++) {
			result = result.replace(or[i], res[i]);
		}
		return result;
	}

	public ObjectIterator<?> retrievePages(ObjectIterator<?> keywords) {
		return null;
	}

	public ObjectIterator<?> retrievePages(ObjectIterator<?> keywords,
			String operator) {
		// //////////////////////////////////////////////////////////////////
		// Write your Code here as part of Integrating and Running Mini Google
		// assignment
		//
		// /////////////////////////////////////////////////////////////////

		boolean firsttime = true;

		System.out.println(keywords.size());
		while (keywords.hasNext()) {
			// System.out.println();

			if (firsttime) {
				ll = keywords.next().toString();
				retrievePages(ll, operator);
				firsttime = false;

			} else {
				operator = keywords.next().toString();

				System.out.println("operator :" + operator);

				if (operator.equalsIgnoreCase("and")
						|| operator.equalsIgnoreCase("or")
						|| operator.equalsIgnoreCase("not")) {

					if (keywords.hasNext()) {
						repeat = ll;

						ll = keywords.next().toString();

						if (repeat.equals(ll)) {
							System.out.println("repeated!!" + repeat);
							repeat1 = true;
						} else {
							System.out.println("not repeated!!" + repeat);
							repeat1 = false;
						}

						System.out.println("string :" + ll);

						if (operator.equalsIgnoreCase("and")
								|| operator.equalsIgnoreCase("or")
								|| operator.equalsIgnoreCase("not")) {
							System.out.println("inside...kkk");
							if (!repeat1) {
								System.out.println("repeat call..");
								retrievePages(ll, operator);
							}
						}

					}
				} else {

					retrievePages(operator, "and");
				}

			}
		}

		// System.out.println(rank.toString());

		rank = sortByValue(rank);

		// System.out.println(rank.toString());
		if (noresults == false) {
			/*
			 * int i = 0; for(String k:rank.keySet()) { i++;
			 * System.out.println(i + ":  " + k + " --->" + rank.get(k) +
			 * "  times"); }
			 */

		}

		// System.out.println("set..values" + sett.size());

		Iterator<String> l;

		if (more) {
			l = sett.finaliterator();
		} else {
			l = sett.iterator();
		}

		Vector<String> vec = new Vector<String>();

		while (l.hasNext()) {
			vec.add(l.next());
			// System.out.println(l.next());
		}

		// Vector<String> vec = new Vector<String>();

		/*
		 * System.out.println(); System.out.println();
		 */

		// SORTING HERE....DONT WORRY............

		// Map<String, String> map = new TreeMap<String, String>(rank);

		/*
		 * ArrayList<Integer> kp = new ArrayList<Integer>();
		 * 
		 * for(Integer t:rank.keySet()) { i++; //System.out.println(i + ". " + t
		 * + " --> " + rank.get(t)); kp.add(t); }
		 * 
		 * 
		 * 
		 * 
		 * Collections.sort(kp);
		 * 
		 * int[] tr = new int[kp.size()];
		 * 
		 * for(int i=0;i<kp.size();i++) { tr[i] = kp.get(i); }
		 * 
		 * 
		 * 
		 * for(int i = tr.length-1;i>=0;i--) { //System.out.println(i);
		 * System.out.println(rank.get((tr[i]))+ "-->" + tr[i] + " times"); }
		 */
		/*
		 * 
		 * 
		 * ArrayList<String> sortedKeys=new ArrayList<String>(rank.keySet());
		 * Collections.sort(sortedKeys);
		 * 
		 * String[] a = (String[]) sortedKeys.toArray();
		 * 
		 * for(int i=0;i<a.length;i++) { System.out.println(i + ". " + i+1 +
		 * " --> " + rank.get(a[i])); }
		 */

		/*
		 * int i = 0; System.out.println(map.size()); for(String
		 * t:rank.keySet()) { i++; System.out.println(i + ". " + t + " --> " +
		 * rank.get(t)); }
		 */

		return new ObjectIterator<String>(vec);
	}

	/**
	 * Save the index to a file.
	 * 
	 * @param stream
	 *            The stream to write the index
	 */
	public void save(FileOutputStream stream) throws IOException {
		// //////////////////////////////////////////////////////////////////
		// Write your Code here as part of Integrating and Running Mini Google
		// assignment
		//
		// /////////////////////////////////////////////////////////////////
		String[] keys = (String[]) index.getKeys();

		// System.out.println("length :" + keys.length);
		int i = 0;
		while (i < keys.length) {
			if (keys[i] != null) {
				String str = keys[i] + " " + index.getValue(keys[i]) + "\n";
				byte[] bytes = str.getBytes();
				stream.write(bytes);
				i++;
			}
		}

		stream.close();
	}

	/**
	 * Restore the index from a file.
	 * 
	 * @param stream
	 *            The stream to read the index
	 */
	public void restore(FileInputStream stream) throws IOException {
		// //////////////////////////////////////////////////////////////////
		// Write your Code here as part of Integrating and Running Mini Google
		// assignment
		//
		// /////////////////////////////////////////////////////////////////

		byte[] b = new byte[stream.available()];

		stream.read(b);

		String s = "";
		ArrayList<String> arr = new ArrayList<String>();

		for (int i = 0; i < b.length; i++) {

			// System.out.print((char)b[i]);
			if ((char) b[i] != '\n') {
				s = s + (char) b[i];
			} else {
				arr.add(s);
				s = "";
			}

		}

		for (String k : arr) {
			// System.out.println(k);
			String[] split = k.split(" ");
			/*
			 * if(split.length > 1) restore.put(split[0], split[1]);
			 */
			// System.out.println(split[0]);
			if (split.length > 1)
				index.insert(split[0], split[1]);
		}

		// for(String t:restore.keySet())
		// {
		// System.out.println("word :"+ t +" = " + restore.get(t));
		// }

		/*
		 * String str = b.toString(); System.out.println(str);
		 * 
		 * String arr[] = str.split("\n");
		 * 
		 * for(String k:arr) { System.out.println(k); }
		 */

	}

	/*
	 * Remove Page method not implemented right now
	 * 
	 * @see searchengine.indexer#removePage(java.net.URL)
	 */
	public void removePage(URL url) {
	}

	@Override
	public ObjectIterator<?> retrievePages(PageWord keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addPage(URL url, ObjectIterator<?> keywords) {
		// TODO Auto-generated method stub

	}

	public String toString() {
		// //////////////////////////////////////////////////////////////////
		// Write your Code here as part of Integrating and Running Mini Google
		// assignment
		//
		// /////////////////////////////////////////////////////////////////
		return "You dont need to implement it\n";
	}

	public ObjectIterator<?> retrievePages(String keyword) {
		return null;
	}

};
