package searchengine.dictionary;

import java.lang.reflect.Array;

class BNode<K, V> {
	K key;
	V value;
	BNode lChild;
	BNode rChild;

	public BNode() {
		key = null;
		value = null;
		lChild = null;
		rChild = null;
	}

	public BNode(K key1, V value1) {
		key = key1;
		value = value1;
		lChild = null;
		rChild = null;
	}

}

public class BSTDictionary<K extends Comparable<K>, V> implements
		DictionaryInterface<K, V> {

	BNode root;
	private int count;
	public K[] keys;
	K k1;
	int traversal = 0;
	BNode parent;

	public BSTDictionary() {
		root = null;
		count = 0;
	}

	@Override
	public K[] getKeys() {
		// TODO Auto-generated method stub
		keys = (K[]) Array.newInstance(k1.getClass(), count);
		BNode temp = root;
		traversal = 0;
		preOrder(temp);
		return keys;
	}

	@Override
	public V getValue(K str) {
		// TODO Auto-generated method stub
		BNode head = root;
		while (head != null) {
			int cmp = ((Comparable) str).compareTo(head.key);
			if (cmp == 0) {
				return (V) head.value;
			} else if (cmp > 0) {
				head = head.rChild;
			} else
				head = head.lChild;
		}
		return null;
	}

	@Override
	public void insert(K key, V value) {
		// TODO Auto-generated method stub
		k1 = key;
		BNode temp = new BNode(key, value);
		BNode head;
		if (root == null) {
			root = temp;
			count++;
		}

		else {

			head = root;
			while (true) {
				parent = head;
				int comp = ((Comparable) key).compareTo(head.key);
				if (comp > 0) {
					head = head.rChild;
					if (head == null) {
						parent.rChild = temp;
						count++;

						return;
					}
				} else if (comp < 0) {
					head = head.lChild;
					if (head == null) {
						parent.lChild = temp;
						count++;

						return;
					}
				} else {
					/*
					 * int fl=1; String s=head.value.toString(); String[]
					 * s1=s.split(", "); for(int i=0;i<s1.length;i++) {
					 * if(s1[i].equals(value)) fl=0; } if(fl==1) {
					 */
					head.value = value;
					// head.value=head.value+","+value;

					// }
					return;
				}
			}
		}

	}

	@Override
	public void remove(K key) {
		// TODO Auto-generated method stub
		BNode temp = root;
		BNode traverse = root;
		BNode traverse1 = root;
		int flag = 0;
		BNode temp2, temp1;
		while (temp != null) {
			int com = ((Comparable) key).compareTo(temp.key);
			if (com == 0) {
				if (temp == root && temp.rChild == null && temp.lChild == null) {
					root = null;
					System.out.println("Successfully removed leafnode");
					count--;

					break;
				}

				if (temp.rChild == null && temp.lChild == null) {

					int com1 = ((Comparable) traverse.key).compareTo(temp.key);
					if (com1 > 0) {
						traverse.lChild = null;

					} else {
						traverse.rChild = null;
					}
					System.out.println("Successfully removed leafnode");
					count--;

					break;
				} else if (temp.rChild == null || temp.lChild == null) {

					if (temp == root) {
						if (temp.lChild == null)
							root = temp.rChild;
						else
							root = temp.lChild;
						count--;

						break;
					} else if (temp.rChild == null) {
						int com1 = ((Comparable) traverse.key)
								.compareTo(temp.key);
						if (com1 > 0)
							traverse.lChild = temp.lChild;
						else
							traverse.rChild = temp.lChild;
						System.out
								.println("Successfully removed left node child");
						count--;

						break;
					} else {
						System.out.println("got it" + traverse.key + "      "
								+ temp.key);
						int com1 = ((Comparable) traverse.key)
								.compareTo(temp.key);
						if (com1 > 0)
							traverse.lChild = temp.rChild;
						else
							traverse.rChild = temp.rChild;
						System.out
								.println("Successfully removed right node child");
						count--;

						break;
					}
				} else {
					BNode trav1;

					traverse = temp;
					trav1 = traverse;
					temp2 = temp.lChild;
					temp = temp.rChild;

					boolean x = true;
					while (temp.lChild != null) {
						x = false;
						traverse = temp;
						temp = temp.lChild;
					}
					if (!x) {

						trav1.key = temp.key;
						trav1.value = temp.value;
						trav1.lChild = temp2;
						traverse.lChild = null;
					}

					else {
						trav1.key = temp.key;
						trav1.value = temp.value;
						trav1.rChild = temp.rChild;
						// trav1.lChild=temp2;

					}

					System.out
							.println("Successfully removed node with 2 children");
					count--;

					break;
				}
			} else if (com > 0) {
				traverse = temp;
				temp = temp.rChild;
			} else {
				traverse = temp;
				temp = temp.lChild;
			}
		}

	}

	public void preOrder(BNode root) {

		if (root != null) {
			keys[traversal] = (K) root.key;
			traversal++;
			preOrder(root.lChild);
			preOrder(root.rChild);
		}

	}

}
