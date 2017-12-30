//package?

import java.util.Iterator;


public class Library<K,V> implements MapInterface<K,V>, Iterable<Entry>{//do i need to specify entry since it is in MI?

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

	private class NonNullEntryIterator implements Iterator<Entry>{

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


	final int INITIAL_CAP = 256;///These should be passed in
	final double THRESHOLD = .7;

	int size;
	int capacity;
	Entry<K,V>[] lib;

	public Library(){
		lib = new Entry[INITIAL_CAP];//pass up types
		this.capacity = INITIAL_CAP;
	}

	//public void clear(){}

	public boolean containsKey(Object key){
		return lib[getIndex(key,capacity)] != null;
	}

	/*
	Either gets index for key's AC or return first empty one
	*/
	private int getIndex(Object key, int cap){
		int hash = hash(key, cap);
		System.out.printf("Size: %d Cap: %d\n", size, cap);
		System.out.println("lib[hash]: " + lib[hash] +"hash: "+ hash);
		while (lib[hash] != null && !lib[hash].key.equals(key)){
			hash = (hash+1) % cap;
		}
		if (lib[hash] != null){
			System.out.println("our key " + key +" found key "+ lib[hash].key);
			System.out.printf("Does %s equal %s: %b",key,lib[hash].key,lib[hash].key.equals(key));
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
		if (containsKey(key)){
			return value;//HMMMMMMM
		}
		if (size == capacity*THRESHOLD){
			resize();
		}

		Entry<K,V> newEntry = new Entry<K,V>(key, value);
		lib[getIndex(key, capacity)] = newEntry;
		size++;
		return value;
	}	

	public V get(K key){
		int index = getIndex(key,capacity);
		if (lib[index] == null){
			System.out.println("\nNot in lib\n");
			return null; //OVER HEREEEEE
		}
		else
			return lib[index].value;
	}

	//public V remove(Object key);

	public int size(){
		return capacity;
	}

	public Iterator<Entry> iterator(){
		return new NonNullEntryIterator();
	}

	public void resize(){
		System.out.println("\n\nTRYING TO RESIZE\n\n");
		int oldCap = capacity;
		int newCap = oldCap*2;
		Entry<K,V>[] oldLib = lib;
		Entry<K,V>[] newLib = new Entry[newCap];
		Iterator<K> iter = this.iterator();

		for(Entry e : oldLib){
			//make list of all entries(k,v pairs); THEN add to new lib
			System.out.println("Is this working????");
			newLib[getIndex(e.key, newCap)] = e;
		}

		//iterate through old one and store each Entry in its new hash location for new lib[]
		
		//old lib = new lib
	}

	public String toString(){
		return "trying to print lib";
	}

	//only works for english alphabet
	/*
	private String sort1(String s){
		Char[] cs = s.toLowerCase().toCharArray()
		int[] buckets = new int[26];
		Char[] scs = new Char[cs.length];
		for(char c : cs){
			buckets[c]++;
		}
		int 
		for(int i : buckets){
			bucket[c]*c;
		}

	}
	*/


}