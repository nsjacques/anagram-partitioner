import java.io.*;
import java.util.Scanner;

/*
 * An object of this class processes all words in the file and creates a Map
 * (from key to AnagramClass) that stores anagram classes.
 */

public class AnPartitioner {
	
	private static final int STARTCAP = 256; // starting capacity of the map, 2^8
	private AnagramLibrary lib; // the library
		
	//O(1)
	public AnPartitioner(){
		lib = new AnagramLibrary();//(STARTCAP);
	}
	
	/*
	 * Processes the words in the file.
	 * It retrieves the word and adds the word to the library.
	 * 
	 * 0(n)*( O(n) + O(k) )
	 */
	public AnagramLibrary partition(String fileName) throws IOException{
		
		Scanner scan = new Scanner(new FileReader(fileName));//bufferedReaders??
				
		while (scan.hasNext()){
			lib.addWord(scan.nextLine());//0(n) + 0(k) + O(n/2) + O(1)
		}
		
		scan.close();

		return lib;
	}

	public String getAnagrams(){
		return lib.toString();
	}

}