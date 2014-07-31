/**  
 *  
 * This program is part of an implementation for the Mini-Google project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Created by Mahender K on 04-10-2009
 */

package searchengine.dictionary;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * This Class implements a HashDictionary implementing the interface
 * DictionaryInterface,which maps key's to values. Any non-<code>null</code>
 * object can be used as a key or as a value.
 * <p>
 * 
 * To successfully store and retrieve objects from HashDictionary, the String
 * Object used as keys and the Object as value's for the key is stored.
 * 
 * An instance of <code>HashDictionary</code> has no parameters, use's the the
 * default load factor (.75) offers a good tradeoff between time and space
 * costs. Higher values decrease the space overhead but increase the time cost
 * to look up an entry (which is reflected in most <tt>Hashtable</tt>
 * operations, including <tt>get</tt> and <tt>put</tt>).
 * <p>
 * 
 * 
 * This example creates a HashDictionary of words. It uses the names of the word
 * as keys:
 * <p>
 * <blockquote>
 * 
 * <pre>
 * HashDictionary words = new HashDictionary();
 * words.insert(&quot;one&quot;, new Integer(1));
 * words.insert(&quot;two&quot;, new Integer(2));
 * words.insert(&quot;three&quot;, new Integer(3));
 * </pre>
 * 
 * </blockquote>
 * <p>
 * To retrieve a number, use the following code:
 * <p>
 * <blockquote>
 * 
 * <pre>
 * Integer n = (Integer) words.getValue(&quot;two&quot;);
 * if (n != null) {
 * 	System.out.println(&quot;two = &quot; + n);
 * }
 * </pre>
 * 
 * </blockquote>
 * <p>
 */

public class HashDictionary<K extends Comparable<K>, V> implements
		DictionaryInterface<K, V> {

	// The ht as the Hashtable for this HashDictionary
	private Hashtable<K, V> ht;

	/**
	 * Constructs a new, empty hashtable with a default initial capacity (11)
	 * and load factor, which is <tt>0.75</tt>.
	 */
	public HashDictionary() {
		ht = new Hashtable<K, V>();
	}

	/**
	 * getKeys functions returns all the Keys present in the HashDictionary as a
	 * String Array.
	 * 
	 * @return all the Keys in a String Array
	 */
	public K[] getKeys() {
		Enumeration<K> k = ht.keys();
		String[] arr = new String[ht.size()];

		for (int i = 0; k.hasMoreElements(); i++) {
			arr[i] = (String) k.nextElement();
		}

		return (K[]) arr;
	}

	/**
	 * getValue returns the value for the key(str) if found in the hashtable
	 * else returns null
	 * 
	 * @param key
	 *            is the key for which value is to be returned
	 * @return the value of the key.
	 */
	public V getValue(K str) {
		// TODO Auto-generated method stub
		return ht.get(str);
	}

	/**
	 * Maps the specified <code>key</code> to the specified <code>value</code>
	 * in this HashDictionary. Neither the key nor the value can be
	 * <code>null</code>.
	 * <p>
	 * 
	 * The value can be retrieved by calling the <code>getValue</code> method
	 * with a key that is equal to the original key.
	 * 
	 * @param key
	 *            the HashDictionary key.
	 * @param value
	 *            the value.
	 * 
	 * @see #getValue(String)
	 */
	public void insert(K key, V value) {
		// TODO Auto-generated method stub
		ht.put(key, value);
	}

	/**
	 * remove function removes the key and value from the HashTable if found in
	 * the hashtable
	 * 
	 * @param key
	 */
	public void remove(K key) {
		// TODO Auto-generated method stub
		ht.remove(key);
	}
}
