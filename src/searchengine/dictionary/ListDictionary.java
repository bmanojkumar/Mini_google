package searchengine.dictionary;

import java.lang.reflect.Array;
import java.util.ArrayList;

//import ListNode;

class ListNode<K extends Comparable<K>, V> {
	K key;
	V value;
	ListNode<K, V> next;
	ListNode<K, V> prev;
}

public class ListDictionary<K extends Comparable<K>, V> implements
		DictionaryInterface<K, V> {

	ListNode<K, V> first;
	ListNode<K, V> current;
	int size = 0;

	@Override
	public K[] getKeys() {
		// TODO Auto-generated method stub

		// ListNode temp=first;

		/*
		 * if(size == 0) { System.out.println("null..."); return null; }
		 */
		ListNode<K, V> temp = first;

		// K[] arr = (K[]) Array.newInstance(first.key.getClass(), size);

		K[] array;
		array = (K[]) Array.newInstance(first.key.getClass(), size);

		int i = 0;
		while (temp != null) {
			// System.out.println("inside..keys");
			array[i] = (K) temp.key;
			i++;
			temp = temp.next;
		}

		// System.out.println(array.toString());

		return array;
	}

	@Override
	public V getValue(K str) {
		// TODO Auto-generated method stub

		ListNode<K, V> temp = first;

		try {
			while (true) {
				if (temp.key == str) {
					return temp.value;
				}

				temp = temp.next;
			}
		} catch (NullPointerException e) {

		}

		return null;
	}

	@Override
	public void insert(K key, V value) {
		// TODO Auto-generated method stub
		// System.out.println("inside..insert");
		boolean duplicate = false;

		ListNode<K, V> temp1 = first;

		if (temp1 != null) {
			try {
				while (true) {
					if (temp1.key.equals(key)) {
						// System.out.println("FOUND!!");
						temp1.value = value;
						duplicate = true;
					}

					temp1 = temp1.next;
				}
			} catch (NullPointerException e) {

			}
		}

		if (duplicate == false) {
			if (size == 0) {
				first = new ListNode<K, V>();
				first.key = key;
				first.value = value;
				current = first;
				++size;
			} else {
				ListNode<K, V> temp = new ListNode<K, V>();
				temp.key = key;
				temp.value = value;

				current.next = temp;
				current = current.next;
				++size;

			}
			// return true;
		}

	}

	@Override
	public void remove(K key) {
		// TODO Auto-generated method stub

		// System.out.println("INSIDE REMOVE....");
		ListNode<K, V> temp = first;

		if (size == 1) {
			if (first.key.equals(key)) {
				first = null;
				size--;
			}
		}

		if (size > 1) {
			if (first.key.equals(key)) {
				first = first.next;
				size--;
			} else {
				try {
					while (true) {
						if (temp.next.key.equals(key)) {
							System.out.println("FOUND...");
							if (temp.next.next == null)
								temp.next = null;
							else
								temp.next = temp.next.next;
							size--;
						}

						temp = temp.next;
					}
				} catch (NullPointerException e) {

				}
			}
		}

	}

}
