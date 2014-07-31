/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Modified by Mahender on 12-10-2009
 */
package searchengine.spider;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import searchengine.dictionary.ObjectIterator;
import searchengine.element.PageElementInterface;
import searchengine.element.PageHref;
import searchengine.element.PageIframe;
import searchengine.element.PageImg;
import searchengine.element.PageNum;
import searchengine.element.PageWord;
import searchengine.indexer.Indexer;
import searchengine.parser.PageLexer;
import searchengine.parser.WebReader;

/**
 * Web-crawling objects. Instances of this class will crawl a given web site in
 * breadth-first order.
 */
public class BreadthFirstSpider implements SpiderInterface {

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

	public BreadthFirstSpider(URL u, Indexer i) {
		this.u = u;
		this.i = i;

	}

	/**
	 * Crawl the web, up to a certain number of web pages.
	 * 
	 * @param limit
	 *            The maximum number of pages to crawl.
	 */
	public Indexer crawl(int limit) {

		int count = 1;
		Queue<String> q = new LinkedList<String>();
		q.add(u.toString());

		// System.out.println();
		while (!q.isEmpty()) {
			WebReader wb = new WebReader();
			String[] arr = { q.remove() };
			WebReader.main(arr);
			PageLexer<PageElementInterface> ele = WebReader.elts;
			Vector<String> words = new Vector<String>();
			// Vector<String> links = new Vector<String>();

			while (ele.hasNext()) {
				PageElementInterface elt = (PageElementInterface) ele.next();
				if (elt instanceof PageWord) {
					words.add(elt.toString());
					// System.out.println("word: "+elt);
				} else if (elt instanceof PageHref && count < limit) {
					if (!q.contains(elt.toString())) {
						q.add(elt.toString());
					}
					count++;
					// System.out.println("Limit is:" + count);
					// links.add(elt.toString());
					// System.out.println("link: "+elt);
				}

			}

			i.addPage(arr[0], new ObjectIterator<String>(words));

		}

		// //////////////////////////////////////////////////////////////////
		// Write your Code here as part of Breadth First Spider assignment
		//
		// /////////////////////////////////////////////////////////////////

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
