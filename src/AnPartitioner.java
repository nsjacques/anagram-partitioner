import java.io.*;
import java.util.Scanner;

/*
 * An object of this class processes all words in the file and creates a Map
 * (from key to AnagramClass) that stores anagram classes.
 */

public class AnPartitioner {
	
	private static final int CAP_INITIAL = 131072; // starting capacity of the map, 2^8
	private AnagramLibrary lib;
		
	public AnPartitioner(){
		lib = new AnagramLibrary(CAP_INITIAL);//(STARTCAP);
	}
	
	/*

	 */
	public AnagramLibrary partition(String fileName) throws IOException{
		
		Scanner scan = new Scanner(new FileReader(fileName));//bufferedReaders??
		while (scan.hasNext()){
			lib.addWord(scan.nextLine());
		}
		
		scan.close();
		return lib;
	}

	public String getAnagrams(){
		return lib.toString();
	}

}