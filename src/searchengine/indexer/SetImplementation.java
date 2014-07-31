package searchengine.indexer;

import java.util.ArrayList;
import java.util.Iterator;

class SetImplementation<T> implements SetADT<T> {

	public ArrayList<T> myset = new ArrayList<T>();
	public ArrayList<T> temp = new ArrayList<T>();
	public ArrayList<T> finalset = new ArrayList<T>();

	public void clear() {
		this.myset.clear();

	}

	public void fclear() {
		this.finalset.clear();

	}

	@Override
	public void add(T element) {
		// TODO Auto-generated method stub
		if (!contains(element)) {
			myset.add(element);
		}
	}

	@Override
	public T remove(T element) {
		// TODO Auto-generated method stub

		myset.remove(element);
		return null;
	}

	@Override
	public SetImplementation<T> union(SetADT<T> set) {
		// TODO Auto-generated method stub

		/*
		 * System.out.println("---my set-----"); Iterator<T> h =
		 * this.myset.iterator();
		 * 
		 * while(h.hasNext()) { System.out.println(h.next()); }
		 * 
		 * System.out.println("----end myset----");
		 * 
		 * System.out.println("---set-----"); Iterator<T> s =
		 * this.myset.iterator();
		 * 
		 * while(s.hasNext()) { System.out.println(s.next()); }
		 * 
		 * System.out.println("----end set----");
		 */

		finalset.addAll(this.myset);

		Iterator<T> k = set.iterator();

		while (k.hasNext()) {
			T j = k.next();

			if (!finalset.contains(j)) {
				finalset.add(j);
			}
		}

		/*
		 * Iterator<T> m = finalset.iterator();
		 * 
		 * 
		 * while(m.hasNext()) { System.out.println(m.next()); }
		 */
		SetImplementation<T> neww = new SetImplementation<T>();

		neww.finalset.addAll(finalset);

		return neww;
	}

	@Override
	public SetImplementation<T> intersection(SetADT<T> set) {

		SetImplementation<T> neww = new SetImplementation<T>();

		if (set.isEmpty())
			return neww;

		Iterator<T> k = set.iterator();

		/*
		 * System.out.println("---my set-----"); Iterator<T> h =
		 * this.myset.iterator();
		 * 
		 * while(h.hasNext()) { System.out.println(h.next()); }
		 * 
		 * System.out.println("----end myset----");
		 * 
		 * System.out.println("---set-----"); Iterator<T> s =
		 * this.myset.iterator();
		 * 
		 * while(s.hasNext()) { System.out.println(s.next()); }
		 * 
		 * System.out.println("----end set----");
		 */

		while (k.hasNext()) {
			T j = k.next();

			if (this.myset.contains(j)) {
				finalset.add(j);
			}
		}

		// Iterator<T> m = finalset.iterator();
		/*
		 * System.out.println(finalset.size()); while(m.hasNext()) {
		 * System.out.println("aa" + m.next()); }
		 */
		// TODO Auto-generated method stub
		// SetImplementation<T> neww = new SetImplementation<T>();

		neww.finalset.addAll(finalset);

		return neww;
	}

	@Override
	public SetImplementation<T> difference(SetADT<T> set) {
		// TODO Auto-generated method stub

		/*
		 * System.out.println("---my set-----"); Iterator<T> h =
		 * this.myset.iterator();
		 * 
		 * while(h.hasNext()) { System.out.println(h.next()); }
		 * 
		 * System.out.println("----end myset----");
		 * 
		 * System.out.println("---set-----"); Iterator<T> s =
		 * this.myset.iterator();
		 * 
		 * while(s.hasNext()) { System.out.println(s.next()); }
		 * 
		 * System.out.println("----end set----");
		 */

		Iterator<T> k = set.iterator();

		while (k.hasNext()) {
			T j = k.next();

			temp.add(j);
		}

		this.myset.removeAll(temp);
		SetImplementation<T> neww = new SetImplementation<T>();

		neww.finalset.addAll(this.myset);

		return neww;
	}

	@Override
	public boolean contains(T target) {
		// TODO Auto-generated method stub
		return myset.contains(target);
	}

	@Override
	public boolean equals(SetADT<T> set) {
		// TODO Auto-generated method stub

		boolean ntequal = false;

		Iterator<T> i = set.iterator();
		Iterator<T> j = this.myset.iterator();

		while (i.hasNext()) {
			T p = i.next();
			temp.add(p);
		}

		while (j.hasNext()) {
			T p = j.next();

			if (!temp.contains(p)) {
				ntequal = true;
			}
		}

		return ntequal;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.myset.isEmpty();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return myset.size();
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return myset.iterator();
	}

	public Iterator<T> finaliterator() {
		// TODO Auto-generated method stub
		return finalset.iterator();
	}

}