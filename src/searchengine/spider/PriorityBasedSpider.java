/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Created by Mahender on 12-10-2009
 */
package searchengine.spider;

import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

import searchengine.dictionary.ObjectIterator;
import searchengine.element.PageElementInterface;
import searchengine.element.PageHref;
import searchengine.element.PageWord;
import searchengine.indexer.Indexer;
import searchengine.parser.PageLexer;
import searchengine.parser.WebReader;

/**
 * Web-crawling objects. Instances of this class will crawl a given web site in
 * Priority-first order.
 */
/*-------------------------------------------------- START OF PRIORITY QUEUE---------------------------*/
interface PQueueADT<E extends Comparable<E>> {

	/*
	 * Inserts the element in the priority Queue
	 */
	public void add(E value);

	/*
	 * Deletes the Priority(first element) element from the Queue.
	 */
	public E remove();

	/*
	 * Returns the number of elements of the Queue
	 */
	public int size();

	/*
	 * Returns true if Queue is empty and false otherwise.
	 */
	public boolean isEmpty();

	/*
	 * Returns the Priority(first element) element of the Queue
	 */
	public E front();

}

class MaxPQueue<E extends Comparable<E>> implements PQueueADT<E> {

	E data;
	int count;
	ArrayList<E> maxalist;

	MaxPQueue() {
		maxalist = new ArrayList<E>();
		count = 0;
		maxalist.add(null);
	}

	public void add(E element) {
		maxalist.add(element);
		count++;
		int pos = count;
		while ((pos > 1) && (element.compareTo((E) maxalist.get(pos / 2)) > 0)) {
			maxalist.set(pos, maxalist.get(pos / 2));
			pos = pos / 2;
		}
		maxalist.set(pos, element);
	}

	public E remove() {
		E t1, t2, t3;
		t1 = (E) maxalist.get(1);
		maxalist.set(1, maxalist.get(count));
		maxalist.remove(count);
		count--;
		int k = 1;
		if (!isEmpty()) {
			while ((2 * k) < count) {
				if (((Comparable<E>) maxalist.get(2 * k))
						.compareTo((E) maxalist.get(k)) > 0) {
					t2 = (E) maxalist.get(k);
					t3 = (E) maxalist.get(2 * k);
					maxalist.set(k, t3);
					maxalist.set((2 * k), t2);
				}

				else if ((((Comparable<E>) maxalist.get((2 * k) + 1))
						.compareTo((E) maxalist.get(k)) > 0)) {
					t2 = (E) maxalist.get(k);
					t3 = (E) maxalist.get((2 * k) + 1);
					maxalist.set(k, t3);
					maxalist.set(((2 * k) + 1), t2);
				}

				k = k * 2;
			}
			return t1;
		} else
			return null;
	}

	public int size() {
		return count;
	}

	public void display() {
		System.out.println(maxalist);
	}

	public boolean isEmpty() {
		if (count > 0)
			return false;
		else
			return true;
	}

	public E front() {
		return (E) maxalist.get(1);
	}

}

/*-------------------------------------------------- START OF PRIORITY QUEUE---------------------------*/

public class PriorityBasedSpider implements SpiderInterface {

	/**
	 * Create a new web spider.
	 * 
	 * @param u
	 *            The URL of the web site to crawl.
	 * @param i
	 *            The initial web index object to extend.
	 */

	private Indexer i = null;
	private URL u;
	int counter = 3;
	int count = 0;
	Queue<String> q = new LinkedList<String>();

	public PriorityBasedSpider(URL u, Indexer i) {
		this.u = u;
		this.i = i;

	}

	/**
	 * Crawl the web, up to a certain number of web pages.
	 * 
	 * @param limit
	 *            The maximum number of pages to crawl.
	 */

	public Indexer depth_crawl(String u, int limit, int depth) {

		if (depth == 0) {
			return i;
		}

		// WebReader wb = new WebReader();
		String[] arr = { u.toString() };
		WebReader.main(arr);
		PageLexer<PageElementInterface> ele = WebReader.elts;
		Vector<String> words = new Vector<String>();
		// Vector<String> links = new Vector<String>();

		while (ele.hasNext()) {
			PageElementInterface elt = (PageElementInterface) ele.next();
			if (elt instanceof PageWord) {
				words.add(elt.toString());
				// System.out.println("word: "+elt);
			}
			// }

			// while(ele.hasNext())
			// {
			elt = (PageElementInterface) ele.next();
			if (count == limit) {
				break;
			}
			if (elt instanceof PageHref && count < limit) {

				words.add(elt.toString());
				// System.out.println("Link: "+elt);
				if (!q.contains(elt.toString())) {

					count++;
					if (count == limit) {
						break;
					}
					q.add(elt.toString());
					i = depth_crawl(elt.toString(), limit, depth - 1);
				}
				/*
				 * else { break; }
				 */
				// i.addPage(arr[0], new ObjectIterator<String>(words));
			}
		}
		// }
		/*
		 * else if (elt instanceof PageHref && count < limit) { //
		 * if(!q.contains(elt.toString())) // { q.add(elt.toString()); // }
		 * count++; }
		 */

		i.addPage(arr[0], new ObjectIterator<String>(words));

		return i;
	}

	public Indexer crawl(int limit) {

		// //////////////////////////////////////////////////////////////////
		// Write your Code here as part of Priority Based Spider assignment
		//
		// /////////////////////////////////////////////////////////////////

		int depth = 30;

		/*
		 * int count = 0; //PriorityQueue<String> q = new
		 * PriorityQueue<String>(1000, Collections.reverseOrder());
		 * Stack<String> q = new Stack<String>(); q.push(u.toString());
		 * 
		 * 
		 * //System.out.println(); while(!q.isEmpty()) { WebReader wb = new
		 * WebReader(); String[] arr = {q.pop()}; WebReader.main(arr);
		 * PageLexer<PageElementInterface> ele = WebReader.elts; Vector<String>
		 * words = new Vector<String>(); //Vector<String> links = new
		 * Vector<String>();
		 * 
		 * 
		 * while (ele.hasNext()) { PageElementInterface elt =
		 * (PageElementInterface)ele.next(); if (elt instanceof PageWord) {
		 * words.add(elt.toString()); //System.out.println("word: "+elt); } else
		 * if (elt instanceof PageHref && count < limit) { //
		 * if(!q.contains(elt.toString())) // { q.push(elt.toString()); // }
		 * count++; //System.out.println("Limit is:" + count);
		 * //links.add(elt.toString()); //System.out.println("link: "+elt); }
		 * 
		 * }
		 * 
		 * i.addPage(arr[0], new ObjectIterator<String>(words));
		 * 
		 * }
		 */
		depth_crawl(u.toString(), limit, depth);
		return i;
	}

	/**
	 * Crawl the web, up to the default number of web pages.
	 */
	public Indexer crawl() {
		// This redirection may effect performance, but its OK !!
		System.out.println("Crawling: " + u.toString());
		return crawl(crawlLimitDefault);
	}

	/** The maximum number of pages to crawl. */
	public int crawlLimitDefault = 10;

}
