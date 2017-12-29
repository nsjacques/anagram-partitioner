//package?


public class Library<K,V> implements MapInterface<K,V>{
	
	int size;
	int capacity;
	Entry[] lib;

	final int INITAL_CAP = 256;///These should be generic
	final double THRESHOLD = .7;

	private class Entry()<K,V>{
		K key;
		V value;

		private Entry(K key, V value){
			this.key = key;
			this.value = value;
		}

		public K getKey(){
			return key;
		}
		public V getValue(){
			return value;
		}
		private String toString(){
			return "not yet implemented";
		}
	}

	public Library(){

	}

	//public void clear(){}

	public boolean containsKey(Object key){
		return lib[getIndex(key)] != null;
	}

	/*
	Either gets index for key's AC or return first empty one
	*/
	private int getIndex(Object key){
		int hash = hash(key);
		while (lib[hash] != null && lib[hash].getKey().equals(key)){
			hash++;
		}
		return hash;
	}

	private int hash(Object key){
		int hash = key.hashCode();
		return hash < 0 ? -1*hash : hash;
	}

	//public boolean containsValue(Object value);
	//public boolean equals(Object o);

	public boolean isEmpty(){
		return numClasses == 0;
	}

	public V put(String key, AnagramClass value){
		if (containsKey(key)){
			return value;//HMMMMMMM
		}
		if (size == capacity*THRESHOLD){
			resize();
		}

		Entry newEntry = new Entry(key, value);
		lib[getIndex(key)] = newEntry;
		size++;
		return value;
	}	

	public V get(String key){
		int index = getIndex(key);
		if (lib[index] == null){
			System.out.println("\nNot in lib\n")
			return null; //OVER HEREEEEE
		}
		else
			return lib[index].getValue();
	}

	//public V remove(Object key);

	public int size(){
		return capacity;
	}

	public Iterator<K> iterator(){
		return null;
	}


}