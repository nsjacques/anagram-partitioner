/*

*/

public class AnagramLibrary extends Library<String,AnagramClass>{
	
	int numClasses;
	int numWords;

	public AnagramLibrary(){
		super();
	}

	public void addWord(String word){
		//check capacity
		if (size > capacity*THRESHOLD)
			resize();
		String anagramKey = sort(word); //OVER HEREEEE. quick vs counting vs something else sort
		System.out.println("word: " + word);
		if (this.containsKey(anagramKey))
			this.get(anagramKey).addWord(word);
		else
			this.put(anagramKey, new AnagramClass(word));
	}

	private String sort(String word){
		char[] chars = word.toCharArray();
		int[] histogram = new int[26];
        for (int i = 0; i < chars.length; i++) {
            histogram[chars[i] - 'a'] += 1;
        }
        int total = 0;
        int oldCount;
        for (int i = 0; i < 26; i++) {
            oldCount = histogram[i];
            histogram[i] = total;
            total += oldCount;
        }
        char[] output = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            output[histogram[chars[i] - 'a']] = chars[i];
            histogram[chars[i] - 'a'] += 1;
        }
        return new String(output);
	}


}