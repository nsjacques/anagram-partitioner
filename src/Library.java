//package?


public class Library<K,V> implements MapInterface<K,V>{
	
	int size;
	int capacity;
	Entry[] lib;

	final int INITAL_CAP = 256;///These should be generic
	final double THRESHOLD = .7;

	private class Entry{
		K key;
		V value;

		public Entry(K key, V value){//permissions?
			this.key = key;
			this.value = value;
		}

		public K getKey(){//do i even need getters? why not direct
			return key;
		}
		public V getValue(){
			return value;
		}
		public String toString(){
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
		return size == 0;
	}

	public V put(K key, V value){
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

	public V get(K key){
		int index = getIndex(key);
		if (lib[index] == null){
			System.out.println("\nNot in lib\n");
			return null; //OVER HEREEEEE
		}
		else
			return lib[index].getValue();
	}

	//public V remove(Object key);

	public int size(){
		return capacity;
	}

	//public Iterator<K> iterator(){
	//	return null;
	//}

	public void resize(){

	}


}