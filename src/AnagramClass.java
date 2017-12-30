/*
 * Stores the words in a partition-class of anagrams as a linked list.
 */

public class AnagramClass {
	
	private Node<String> head;
	private String line = "";//ex: "abc, acb, bca\n"
	
	public class Node<T> {
		private T content;
		private Node<T> link;

		public Node(T c){
			content = c;
			link = null;
		}

		public T getContent(){
			return this.content;
		}

		public void setContent(T c){
			content = c;
		}
		
		public Node<T> getLink(){
			return link;
		}

		public void setLink(Node<T> N){
			link = N;
		}
	}

	public AnagramClass(String word){
		head = new Node<String>(word);
		line = word + "\n";
	}
	
	public void addWord(String word){
		//This doesn't handle repeats
		Node<String> newWord = new Node<String>(word);
		newWord.setLink(head);
		head = newWord;
		line = word + ", " + line;
	}
	
	public String toString(){
		return line;
	}
}