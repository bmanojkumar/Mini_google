package searchengine.dictionary;

import java.lang.reflect.Array;

class AVLNode<K extends Comparable<K>, V> {
	K key;
	V value;
	public int height = 0;
	public AVLNode<K, V> left;
	public AVLNode<K, V> right;

	public void setheight(int height) {
		this.height = height;
	}

	public AVLNode(K key, V value) // node constructor
	{
		this.key = key;
		this.value = value;
		this.left = null;
		this.right = null;
	}

	public AVLNode(K key, V value, AVLNode left, AVLNode right) // node
																// constructor
	{
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}
}

public class AVLDictionary<K extends Comparable<K>, V> implements
		DictionaryInterface<K, V> {

	AVLNode root;
	private int count;
	AVLNode parent;
	public K[] keys;
	K k1;
	int traversal = 0;
	AVLNode head = root;

	@Override
	public K[] getKeys() {
		// TODO Auto-generated method stub

		keys = (K[]) Array.newInstance(k1.getClass(), count);
		AVLNode temp = root;
		traversal = 0;
		preOrder(temp);
		return keys;

	}

	@Override
	public V getValue(K str) {
		// TODO Auto-generated method stub

		AVLNode head = root;
		while (head != null) {
			int cmp = ((Comparable) str).compareTo(head.key);
			if (cmp == 0) {
				return (V) head.value;
			} else if (cmp > 0) {
				head = head.right;
			} else
				head = head.left;
		}
		return null;

	}

	@Override
	public void insert(K key, V value) {
		// TODO Auto-generated method stub
		k1 = key;
		head = root;
		root = insert1(root, key, value);
	}

	public AVLNode insert1(AVLNode head, K key, V value) {
		// AVLNode temp=new AVLNode(key,value);
		if (head == null && root == null) {
			head = new AVLNode(key, value);
			count++;
			root = head;

		} else if (head == null && root != null) {
			head = new AVLNode(key, value);

			count++;

		}

		else {
			// head=root;
			if (key.compareTo((K) head.key) < 0) {

				head.left = insert1(head.left, key, value);
				head = balanceL(head, key);

			} else if (key.compareTo((K) head.key) > 0) {
				head.right = insert1(head.right, key, value);
				head = balanceR(head, key);
			} else {
				/*
				 * int fl=1; String s=head.value.toString(); String[]
				 * s1=s.split(", "); for(int i=0;i<s1.length;i++) {
				 * if(s1[i].equals(value)) fl=0; }
				 */
				// if(fl==1)
				// {
				head.value = value;

				// }
				head.setheight(Math.max(height(head.left), height(head.right)) + 1);
			}

		}
		return head;
	}

	@Override
	public void remove(K key) {
		// TODO Auto-generated method stub

		head = delete(root, key);
		count--;
		root = balance(root);
	}

	public AVLNode delete(AVLNode head, K key) {
		AVLNode trav1, traverse, temp2;
		trav1 = head;

		if (head == null)
			return head;
		if (key.compareTo((K) head.key) < 0) {
			head.left = (delete(head.left, key));
		} else if (key.compareTo((K) head.key) > 0) {
			head.right = (delete(head.right, key));
		} else {
			if (head.left == null && head.right == null) {
				head = null;
				// return head;
			} else if (head.left == null) {
				head = head.right;
				// return head;
			} else if (head.right == null) {
				head = head.left;
				// return head;
			} else {
				traverse = head;
				trav1 = traverse;
				temp2 = head.left;
				head = head.right;

				boolean x = true;
				while (head.left != null) {
					x = false;
					traverse = head;
					head = head.left;
				}
				if (!x) {

					trav1.key = head.key;
					trav1.value = head.value;
					trav1.left = temp2;
					traverse.left = null;
				}

				else {
					trav1.key = head.key;
					trav1.value = head.value;
					trav1.right = head.right;
					// trav1.left=temp2;

				}
				// return trav1;
				head = trav1;
			}
		}

		return head;
	}

	public void preOrder(AVLNode x) {

		if (x != null) {
			keys[traversal] = (K) x.key;

			traversal++;
			preOrder(x.left);
			preOrder(x.right);
		}
	}

	private int height(AVLNode node) {
		// TODO Auto-generated method stub
		if (node == null) {
			return -1;
		}
		if (node.left == null && node.right == null) {
			return 0;
		} else if (node.left == null) {
			return 1 + height(node.right);
		} else if (node.right == null) {
			return 1 + height(node.left);
		} else {
			return 1 + Math.max(height(node.left), height(node.right));
		}
	}

	private AVLNode Rotate2R(AVLNode drroot) {
		// TODO Auto-generated method stub
		// System.out.println("RL rotation");
		drroot.right = Rotate1L(drroot.right);
		return Rotate1R(drroot);
		// return null;
	}

	private AVLNode Rotate1R(AVLNode rroot) {
		// TODO Auto-generated method stub
		// System.out.println("RR rotation");
		AVLNode newnode = rroot.right;
		rroot.right = newnode.left;
		newnode.left = rroot;
		rroot.setheight(Math.max(height(rroot.right), height(rroot.left)) + 1);
		newnode.setheight(Math.max(height(newnode.right), rroot.height) + 1);
		return newnode;
		// return null;
	}

	private AVLNode Rotate2L(AVLNode dlroot) {
		// TODO Auto-generated method stub
		// System.out.println("LR rotation");
		dlroot.left = Rotate1R(dlroot.left);
		return Rotate1L(dlroot);
		// return null;
	}

	private AVLNode Rotate1L(AVLNode lroot) {
		// TODO Auto-generated method stub
		// System.out.println("LL rotation");
		AVLNode newnode = lroot.left;
		lroot.left = newnode.right;
		newnode.right = lroot;
		lroot.setheight(Math.max(height(lroot.left), height(lroot.right)) + 1);
		newnode.setheight(Math.max(height(newnode.left), lroot.height) + 1);

		return newnode;
	}

	public AVLNode balanceL(AVLNode node, K key) {
		if ((height(node.left)) - (height(node.right)) == 2) {

			if (key.compareTo((K) node.left.key) < 0) {
				// System.out.println("..."+node.key);
				node = Rotate1L(node);
				// System.out.println("head.key is "+head.key);
			} else {
				// System.out.println("..."+node.key);
				node = Rotate2L(node);
			}
		}
		return node;
	}

	public AVLNode balanceR(AVLNode node, K key) {
		if ((height(node.left)) - (height(node.right)) == -2) {
			if (key.compareTo((K) node.right.key) < 0) {
				// System.out.println("..."+node.key);
				node = Rotate2R(node);
			} else {
				// System.out.println("..."+node.key);
				node = Rotate1R(node);
			}
		}
		return node;
	}

	public AVLNode balance(AVLNode node) {
		if (node != null) {
			node.left = balance(node.left);

			if ((height(node.left)) - (height(node.right)) == 2) {

				if (height(node.left.left) > height(node.left.right)) {
					// System.out.println("..."+node.key);
					// System.out.println("node.left.key is "+node.left.key+"    node.left.left.key is "+node.left.left.key);
					node = Rotate1L(node);

				} else {
					// System.out.println("..."+node.key);
					node = Rotate2L(node);
				}
			}

			if ((height(node.left)) - (height(node.right)) == -2) {
				if (height(node.right.left) > height(node.right.right)) {
					// System.out.println("..."+node.key);
					node = Rotate2R(node);
				} else {
					// System.out.println("..."+node.key);
					node = Rotate1R(node);
				}
			}
			node.right = balance(node.right);
			return node;
		}
		return null;
	}

}
