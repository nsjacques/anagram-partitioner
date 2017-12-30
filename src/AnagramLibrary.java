/*

*/

public class AnagramLibrary extends Library<String,AnagramClass>{
	
	int numClasses;
	int numWords;

	public AnagramLibrary(int cap){
		super(cap);
	}

	public void addWord(String word){

		String key = sort(word); //OVER HEREEEE. quick vs counting vs something else sort
		
		if (this.containsKey(key)){
			this.get(key).addWord(word);
		}
		else{
			this.put(key, new AnagramClass(word));
			numClasses++;
		}
		numWords++;
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

	//only works for english alphabet
	/*
	private String sort1(String s){
		Char[] cs = s.toLowerCase().toCharArray()
		int[] buckets = new int[26];
		Char[] scs = new Char[cs.length];
		for(char c : cs){
			buckets[c]++;
		}
		int 
		for(int i : buckets){
			bucket[c]*c;
		}

	}
	*/

	public String toString(){
		return String.format("\nFinal Output.\n#words: %d\n#classes: %d", numWords, numClasses);
	}


}