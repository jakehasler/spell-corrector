package spell;

import java.util.HashSet;
import java.util.Set;

import spell.ITrie.INode;

public class Trie implements ITrie {
	
	public Node root = new Node();
	
	public Set bigSet = new HashSet<String>();
	public Set finalSet = new HashSet<String>();
	
	int runningFreq = 0;
	String bestWord = "";
	
	public void add(String word) {
		
	}
	
	public Node find(String word) {
		Node fresh = new Node();
		fresh = root.exists(word, 0, root);
		return fresh;
	}
	
	
	public void runFunctions(String str, Set theSet) {
		deletion(str, theSet);
		transposition(str, theSet);
		alteration(str, theSet);
		insertion(str, theSet);
		checkIfWord(theSet);
	}
	
	
	public void checkIfWord(Set theSet) {
		runningFreq = 0;
		bestWord = "";
		for(Object str : theSet) {
			Node test = this.find(str.toString());
			if(test != null) {
				if(test.getCount() > runningFreq) {
					runningFreq = test.getCount();
					bestWord = str.toString();
				}
				System.out.println("Current String: " + bestWord);
				System.out.println("Frequency: " + test.getCount());
			}
			
		}
		
	}
	
	
	public void deletion(String str, Set theSet) {
//		System.out.println("Deletion:");
		for(int i = 0; i < str.length(); i++) {
			StringBuilder sb = new StringBuilder(str);
			sb.deleteCharAt(i);
			theSet.add(sb.toString());
		}
	}
	
	
	public void transposition(String str, Set theSet) {
//		System.out.println("Transposition:");
		for(int i = 0; i < str.length() - 1; i++) {
			StringBuilder sb = new StringBuilder(str);
			char first = str.charAt(i);
			char second = str.charAt(i+1);
			sb.setCharAt(i, second);
			sb.setCharAt(i+1, first);
			theSet.add(sb.toString());
		}
	}
	
	
	public void alteration(String str, Set theSet) {
//		System.out.println("Alteration:");
		for(int i = 0; i < str.length(); i++) {
			StringBuilder sb = new StringBuilder(str);
			for(int j = 97; j < 97 + 26; j++) {
				sb.setCharAt(i, (char)j);
				theSet.add(sb.toString());
			}
		}
	}
	
	
	public void insertion(String str, Set theSet) {
//		System.out.println("Insertion:");
		for(int i = 0; i < str.length() + 1; i++) {
			for(int j = 97; j < 97 + 26; j++) {
				StringBuilder sb = new StringBuilder(str);
				sb.insert(i, (char)j);
				theSet.add(sb.toString());
			}
		}
	}
	
	
	@SuppressWarnings("static-access")
	public int getWordCount() {
		
		return root.wordCount;
	}
	
	
	@SuppressWarnings("static-access")
	public int getNodeCount() {
		
		return root.nodeCount;
	}
	
	@Override
	public String toString() {
		
		return root.toString();
	}
	
	@Override
	public int hashCode() {
		int MULTIPLIER = 7;
		int hashCode = this.getNodeCount() * this.getWordCount() * MULTIPLIER;
		return hashCode;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) return false; 
		else if(getClass() != o.getClass()) return false;
		else {
			Trie that = (Trie)o;
			if(this.getNodeCount() == that.getNodeCount()) {
				if(this.getWordCount() == that.getWordCount()) {
					// Check if frequency of each node is the same
					// if(wordFreq == that.wordFreq) {
						// return true;
					// }
					// else return false;
				}
			}
		}
		return true;
	}
}
