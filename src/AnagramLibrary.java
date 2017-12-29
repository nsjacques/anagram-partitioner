/*
	This should extend a general library
	Library can handle the entry and map stuff
	This extension could then do the "addword" stuff. Ugh
*/

public class AnagramLibrary extends Library<String,AnagramClass>{
	
	int numClasses;
	int numWords;

	public AnagramLibrary(){
		super();
	}

	public void addWord(String word){
		anagramKey = word.sort() //OVER HEREEEE. quick vs counting vs something else sort
		put()

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
		//Check capacity
		//resize if necessary
		//make sure its not already contained
		Entry newEntry = new Entry(key, value);
		lib[getIndex(key)] = newEntry;
		numClasses++;
		numWords++;
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