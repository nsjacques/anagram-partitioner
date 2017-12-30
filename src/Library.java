//package?

import java.util.Iterator;


public class Library<K,V> implements MapInterface<K,V>{//, Iterable<Entry>{//do i need to specify entry since it is in MI?

	private static class Entry<K,V>{
		public K key;
		public V value;

		private Entry(K key, V value){//permissions?
			this.key = key;
			this.value = value;
		}

		public String toString(){
			return String.format(" key: %s, value: %s",key,"no class rn ");
		}
	}

	/*private class NonNullEntryIterator implements Iterator<Entry>{

		int curr = 0;
		int seen = 0;
		int total = size;

		public NonNullEntryIterator(){
			curr = 0;
			total = size;
		}

		public boolean hasNext(){
			return seen != total;
		}
		public Entry next(){
			seen++;
			return findNext();
		}
		private Entry findNext(){
			int i = curr+1;
			while (lib[i] == null){
				i++;
			}
			return lib[i];
		}
		public void remove(K key){

		}
	}
	*/


	final int INITIAL_CAP;///These should be passed in
	final double THRESHOLD = .7;

	int size;
	int capacity;
	Entry<K,V>[] lib;

	public Library(int cap){
		INITIAL_CAP = cap;
		lib = new Entry[INITIAL_CAP];//pass up types
		this.capacity = INITIAL_CAP;
	}

	public void clear(){
		//this = new Library(INITIAL_CAP); //is this possible?
		capacity = INITIAL_CAP;
		size = 0;
		lib = new Entry[capacity];
	}

	public boolean containsKey(Object key){
		return lib[getIndex(key,capacity, lib)] != null;
	}

	/*
	Either gets index for key's AC or return first empty one
	*/
	private int getIndex(Object key, int cap, Entry<K,V>[] lib){
		int hash = hash(key, cap);
		while (lib[hash] != null && !lib[hash].key.equals(key)){
			hash = (hash+1) % cap;
		}
		return hash;
	}

	private int hash(Object key, int cap){
		int hash = key.hashCode() % cap;
		return hash < 0 ? -1*hash : hash;
	}

	//public boolean containsValue(Object value);
	//public boolean equals(Object o);

	public boolean isEmpty(){
		return size == 0;
	}

	public V put(K key, V value){

		if (size > capacity*THRESHOLD){
			resize();
		}

		Entry<K,V> newEntry = new Entry<K,V>(key, value);
		lib[getIndex(key, capacity, lib)] = newEntry;
		size++;
		return value;
	}	

	public V get(K key){
		int index = getIndex(key,capacity, lib);
		return lib[index].value;
	}

	//public V remove(Object key);

	public int size(){
		return capacity;
	}

	/*
	public Iterator<Entry> iterator(){
		return new NonNullEntryIterator();
	}
	*/

	public void resize(){
		int oldCap = capacity;
		int newCap = oldCap*2;
		Entry<K,V>[] newLib = new Entry[newCap];

		for(Entry<K,V> e : lib){
			if (e != null)
				newLib[getIndex(e.key, newCap, newLib)] = e;
		}
		
		lib = newLib;
		capacity = newCap;
	}

	public String toString(){
		return "trying to print lib";
	}

}