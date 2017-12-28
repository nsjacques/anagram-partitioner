/*
 * Nodes used for linked lists that form AnagramClasses, etc.
 */

public class APNode<T> {
	
	private T content;
	private APNode<T> link;
	
	//O(1)
	public APNode(T c){
		content = c;
		link = null;
	}
	
	//O(1)
	public T getContent(){
		return this.content;
	}
	
	//O(1)
	public void setContent(T c){
		content = c;
	}
	
	//O(1)
	public APNode<T> getLink(){
		return link;
	}
	
	//O(1)
	public void setLink(APNode<T> N){
		link = N;
	}
}