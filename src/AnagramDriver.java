/*
 * Noah Jacques
 * CS 311
 * Partitions a dictionary of words from the 26-letter English alphabet into anagram classes.
 * It takes as parameters the dictionary filename for input and the anagram filename for output
 *
 * I comment rough worst case time analyses throughout.
 */

/*
 * Creates an AnPartition object for each dictionary and uses the partition method to receive a string to
 * write to the given output file.
 * 
 * Time to process Dict1 with output:
		real	0m1.064s
		user	0m1.549s
		sys		0m0.251s
 * 
 * Time to process Dict2 with output:
		real	0m23.371s
		user	0m38.494s
		sys		0m0.650s
 * 
 * Times found using Terminal's time function.
 * 
 * Run on:
 * MacBook Pro late 2013
 * OS X El Capitan v 10.11.3
 * 2.4 GHz Intel Core i5
 * 8 GB 1600 MHz DDR3
 */

/*

All my todos for this project:



*/

import java.io.*;
import java.util.Scanner;

public class AnagramDriver {

	public static void main(String[] args) throws IOException {
		
		String fileName = args[0];

		AnPartitioner p = new AnPartitioner();// O(1)

		long startTime = System.nanoTime();
		AnagramLibrary lib = p.partition(fileName);// 0(n^2) + O(n*k) worst case
		long endTime = System.nanoTime();
		long duration = (endTime - startTime); 
		System.out.println("total duration in milli: " + duration/1000000);

		System.out.println(p.getAnagrams());

		//PrintWriter out = new PrintWriter(args[1]);
		//out.println(anagrams);
		//out.close();
	}
}