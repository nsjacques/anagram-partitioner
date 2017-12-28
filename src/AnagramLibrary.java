/*

*/

public class AnagramLibrary implements MapInterface<String,AnagraamClass>{
	
	int numClasses;
	int numWords;
	int capacity;
	Entry[] lib;

	final int INITAL_CAP = 256;
	final double THRESHOLD = .7;

	public AnagramLibrary(){

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
		while (lib[hash] != null && lib[hash].key.equals(key)){
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
		//Check capacity
		//resize if necessary
		Entry newEntry = new Entry<String,AnagraamClass>(key, value);
		lib[getIndex(key)]
	}	

	public V get(String key){
		int index = getIndex(key);
		if (lib[index] == null){
			System.out.println("\nNot in lib\n")
			return null; //OVER HEREEEEE
		}
		else
			return lib[index].VALUE;//OVER HEREEEE
	}

	//public V remove(Object key);

	public int size(){
		return capacity;
	}

	public Iterator<K> iterator(){

	}


}