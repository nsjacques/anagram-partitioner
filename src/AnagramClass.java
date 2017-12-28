/*
 * Stores the words in a partition-class of anagrams as a linked list.
 */

public class AnagramClass {
	
	private APNode<String> head;
	private String line = ""; //String representation of class: "abc, acb, bca\n"
	
	//O(1)
	public AnagramClass(String word){
		head = new APNode<String>(word);
		line = word + "\n";
	}
	
	//O(1)
	public void addWord(String word){
		APNode<String> newWord = new APNode<String>(word);
		newWord.setLink(head);
		head = newWord;
		line = word + ", " + line;
	}
	
	//O(1)
	public String getLine(){
		return line;
	}
}