package searchengine.dictionary;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class MyHashDictionary<K extends Comparable<K>, V> implements
		DictionaryInterface<K, V> {

	int arraySize = 30;
	ListNode<K, V> first = new ListNode<K, V>();
	int size = 0;

	ListDictionary<K, V>[] ht = (ListDictionary<K, V>[]) new ListDictionary[arraySize];

	public MyHashDictionary() {
		for (int i = 0; i < arraySize; i++) {
			ht[i] = new ListDictionary<K, V>();
		}
		first.key = (K) "bobby";
	}

	@Override
	public K[] getKeys() {

		size = 0;

		for (int i = 0; i < arraySize; i++) {
			size = size + ht[i].size;
			// System.out.println(ht[i].size);
		}

		K[] arr = (K[]) Array.newInstance(first.key.getClass(), size);

		int i = 0;
		int k = 0;
		while (i < arraySize) {
			if (ht[i].size != 0) {
				@SuppressWarnings("unchecked")
				K[] arr1 = (K[]) Array.newInstance(first.key.getClass(),
						ht[i].size);

				arr1 = ht[i].getKeys();
				for (int j = 0; j < ht[i].size; j++) {
					arr[k] = arr1[j];
					k++;
				}
			}
			i++;
		}

		return arr;
	}

	@Override
	public V getValue(K str) {
		String k = (String) str;
		int index = stringHashFunction(k);
		index = Math.abs(index);
		return ht[index].getValue(str);

	}

	@Override
	public void insert(K key, V value) {
		String k = (String) key;
		int index = stringHashFunction(k);
		index = Math.abs(index);
		ht[index].insert(key, value);

	}

	@Override
	public void remove(K key) {
		String k = (String) key;
		int index = stringHashFunction(k);
		index = Math.abs(index);
		ht[index].remove(key);

	}

	public int stringHashFunction(String wordToHash) {
		int hashKeyValue = 0;
		for (int i = 0; i < wordToHash.length(); i++) {
			int charCode = wordToHash.charAt(i) - 96;
			int hKVTemp = hashKeyValue;
			hashKeyValue = (hashKeyValue * 27 + charCode) % arraySize;
		}
		// System.out.println();
		return hashKeyValue;
	}
}
