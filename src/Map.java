
/*
 * A data structure that is an array called library that stores Tuples.
 * The Tuple contain a key and an Anagram Class and there is a Tuple made for every AnagramClass.
 * There is not necessarily a Tuple in every index of library.
 */

public class Map {
	
	private int size;// size of the library
	private int freeSpace;// amount of free space in the library
	private Tuple[] library;// the library itself
	private int numResizes;// number of times the library has been resized
	APNode<Integer> usedIndexes;//keeps track of indexes that have been used to speed up resizing
	private final int OGSIZE;//stores original size of library
	
	
	//Described in the Map general description.
	private class Tuple {
		
		private String key;
		private AnagramClass ac;
		
		public Tuple(String key, AnagramClass ac){
			this.key = key;
			this.ac = ac;
		}
		
		public String getKey(){
			return key;
		}
	}
	
	
	
	public Map(int size){
		this.size = size;//initial size is the STARTCAP from AnPartitioner
		library = new Tuple[size];
		numResizes = 0;
		freeSpace = size;
		OGSIZE = size;
	}
	
	/*
	 * Resizes the library because there is less than a fourth of size free space left.
	 * It creates an array twice the size of library called B.
	 * It copies all non-null elements (kept track of by usedIndexes) of library to twice their index -1 in B.
	 * It leaves whatever's in index 0 in index 0.
	 * It points the identifier library at this new array B.
	 * 
	 * O(size/2) for the scope of one method call
	 */
	public void resize(int newSize){
		Tuple[] B = new Tuple[newSize];
		APNode<Integer> trav = usedIndexes;
		
		while(trav != null){
			int index = trav.getContent();
			if (index == 0)
				B[index] = library[index];
			else
				B[index*2-1] = library[index];
			trav = trav.getLink();
		}
		
		library = B;
		numResizes++;
	}
	
	/*
	 * Adds a word to the library. Takes the word from AnPartitioner a parameter.
	 * 
	 * O(n/2) + 0(k) + O(k) + O(1)
	 * O(n/2) + 0(k)
	 */
	public void addWord(String word){
		
		//Checks to see if there is a reasonable amount of free space and resizes to twice its size if necessary
		if (freeSpace < size/2){
			freeSpace += size;
			size = size*2;
			resize(size);// 0(n/2)
		}
		
		//gets an alphabetized permutation to use as the key
		String key = wordToKey(word);
		
		//gets a nonzero hash for the key
		int hash = keyToHash(key);
		//System.out.println("key: " + key + "\nhash: " + hash);
		
		//gets an index in library for the words AnagramClass (or (-1)*hash if the key is new)
		int index = getIndex(key, hash);
		
		//adds word if library contains its class
		//and adds a new class if it does not
		/*
		if (index > 0){
			library[index].ac.addWord(word);// O(1)
		}
		else{
			addAn(key, hash, new AnagramClass(word));
		}
		*/
		if (library[index] != null){
			library[index].ac.addWord(word);// O(1)
		}
		else{
			addAn(key, hash, new AnagramClass(word));
		}
	}
	
	/*
	 * Converts the word being processed into a key that will be the
	 * same as the key for any anagram of the word.
	 * 
	 * It does this by alphabetically ordering the characters in the string. ("pots" becomes "opst")
	 * 
	 * O(k)
	 */
	private String wordToKey(String word){
		
		char[] wordChars = word.toCharArray();
		char[] alpha = new char[26];
		String result = "";
		
		for (int i = 0; i < word.length(); i++){// 0(k)
			alpha[wordChars[i] - 97]++;
		}
		
		for (int i = 0; i < alpha.length; i++){//O(26*k)
			if (alpha[i] > 0){
				for (int j = 0; j < alpha[i]; j++){
					result += (char)(i + 97);
				}	
			}	
		}
		return result;
	}
	
	/*
	 * Creates a nonzero hash code from the key.
	 * 
	 * hash = the sum of [ charAt(i) * |20-i| ] for i 0 to n-1
	 * 
	 * Before it's returned it is modded to fit the current size of the array.
	 * It accounts for resizes.
	 * 
	 * 0(k) where k is word length
	 */
	private int keyToHash(String key){
		/*
		int hash = 0;
		
		for (int i = 0; i < key.length(); i++){// O(k)
			int c = key.charAt(i) - 97;
			
			int x = 20 - i;
			if (x < 1) x = x*-1;
			hash += Math.pow(2, x)*c;
		}

		return ((int)(hash % OGSIZE) * (int)Math.pow(2, numResizes)) % size;
		*/
		int hash = (int) key.hashCode() % library.length;
		if (hash < 0) return hash*-1; 
		else return hash;
	}
	
	/*
	 * finds an index using the key and the hash values using linear probing (the least efficient area of this program)
	 * 
	 * It searches until it finds a null opening in the library or until the key's AC is found.
	 * It returns -hash if it finds a null opening hash if nonnull.
	 * 
	 * O(n/2) because there cannot be more than n/2 closed slots in library without it being resized
	 */
	public int getIndex(String key, int hash){
		while(hash == 0 || (library[hash] != null && library[hash].getKey().compareTo(key) != 0)){
			hash = (hash + 1) % library.length;
		}
		if (library[hash] == null)
			return (hash);
		else 
			return hash;
	}
		
	/*
	 * Adds a new AC to the library and takes the AC, the index where it belongs, and it's key.
	 * It makes a new triple and stores it in the library.
	 * freeSpace decrements once.
	 * 
	 * O(1)
	 */
	public void addAn(String key, int index, AnagramClass ac){
		if (index < 0)
			index *= -1;
		Tuple t = new Tuple(key, ac);
		library[index] = t;
		freeSpace--;
		
		APNode<Integer> newInd = new APNode<Integer>(index);
		newInd.setLink(usedIndexes);
		usedIndexes = newInd;
	}
	
	/*
	 * Creates a string representation of the library.
	 * String can be output into a file, to the console, etc.
	 * 
	 * O(n)
	 */
	public String acToString(){
		String collection = "";
		for (int i = 0; i < size; i++){
			if (library[i] != null && library[i].ac != null){
				collection += library[i].ac.getLine();
			}
		}
		return collection;
	}
}