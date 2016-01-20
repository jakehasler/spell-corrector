package spell;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import spell.ITrie.INode;

public class Trie implements ITrie {
	
	public Node root = new Node();
	
	public Set bigSet = new HashSet<String>();
	public Set finalSet = new HashSet<String>();
	public TreeMap<String, Integer> wordMap = new TreeMap<String, Integer>();
	
	public int wordCount = 0;
	public int nodeCount = 1;
	
	int runningFreq = 0;
	String bestWord = "";
	
	public void add(String word) {
		buildTree(word, 0, this.root);
	}
	
	public Node find(String word) {
		Node fresh = new Node();
		fresh = this.exists(word, 0, root);
		return fresh;
	}
	
	
	public void runFunctions(String str, Set<String> theSet) {
		deletion(str, theSet);
		transposition(str, theSet);
		alteration(str, theSet);
		insertion(str, theSet);
		checkIfWord(theSet);
	}
	
	
	public void checkIfWord(Set<String> theSet) {
		runningFreq = 0;
		bestWord = "";
		for(String str : theSet) {
			Node test = this.find(str);
			//String currStr = str.toString();
			// System.out.println("Str: " + str.toString());
			// System.out.println("BestWord: " + bestWord);
			if(test != null) {
				if(test.getCount() > runningFreq) {
					runningFreq = test.getCount();
					bestWord = str;
				}
				else if(test.getCount() == runningFreq) {
					// compare both strings - str and bestWord
					int result = str.compareTo(bestWord);
					if(result < 0) {
						// First Word OverWrites
						bestWord = str;
					}
					else if(result > 0) {
						// Best Word Stays
					}
					else {
						// Strings are exactly Equal
						// No need to replace anything
					}
				}
			}
		}
	}
	
	
	public void deletion(String str, Set<String> theSet) {
		if(str.length() > 1) {
			for(int i = 0; i < str.length(); i++) {
				StringBuilder sb = new StringBuilder(str);
				sb.deleteCharAt(i);
				theSet.add(sb.toString());
			}
		}
	}
	
	
	public void transposition(String str, Set<String> theSet) {
		if(str.length() > 1) {
			for(int i = 0; i < str.length() - 1; i++) {
				StringBuilder sb = new StringBuilder(str);
				char first = str.charAt(i);
				char second = str.charAt(i+1);
				sb.setCharAt(i, second);
				sb.setCharAt(i+1, first);
				theSet.add(sb.toString());
			}
		}
	}
	
	
	public void alteration(String str, Set<String> theSet) {
		for(int i = 0; i < str.length(); i++) {
			StringBuilder sb = new StringBuilder(str);
			for(int j = 97; j < 97 + 26; j++) {
				sb.setCharAt(i, (char)j);
				theSet.add(sb.toString());
			}
		}
	}
	
	
	public void insertion(String str, Set<String> theSet) {
		for(int i = 0; i < str.length() + 1; i++) {
			for(int j = 97; j < 97 + 26; j++) {
				StringBuilder sb = new StringBuilder(str);
				sb.insert(i, (char)j);
				theSet.add(sb.toString());
			}
		}
	}
	
	
	public int getWordCount() {
		return wordMap.size();
	}
	
	public int getNodeCount() {
		
		return nodeCount;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<Entry<String, Integer>> it = wordMap.entrySet().iterator();
		
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			sb.append((String)pair.getKey() + "\n");
		}
		
		return sb.toString();
	}
	
	@Override
	public int hashCode() {
		int MULTIPLIER = 7;
		int hashCode = this.getNodeCount() * this.getWordCount() * MULTIPLIER;
		if(hashCode < 0) hashCode = hashCode + hashCode*2;
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
					return this.wordMap.equals(that.wordMap);
//					Iterator itThis = this.wordMap.entrySet().iterator();
//					Iterator itThat = that.wordMap.entrySet().iterator();
//					while(itThis.hasNext()) {
//						Map.Entry pairThis = (Map.Entry)itThis.next();
//						while(itThat.hasNext()) {
//							Map.Entry pairThat = (Map.Entry)itThat.next();
//							if(pairThis.)
//						}
//					}
					
					
				}
			}
		}
		return false;
	}
	
	public void buildTree(String str, int pos, Node curr) {
		
		Node currentNode = curr;
		
		// If not at end of string
		if(pos < str.length()) {
			
			int charPosition = currentNode.getCharIndex(str.charAt(pos));
			
			if(currentNode.getArray()[charPosition] != null) {
				// A Node Actually exists here.
				Node newCurr = currentNode.getArray()[charPosition];
				
				// Recurse here -> On Next string index
				pos++;
				buildTree(str, pos, newCurr);

			}
			else {
				// Insert Letter into a new node and a new array
				Node newNode = new Node();
				nodeCount++;
				currentNode.addToArray(newNode, charPosition);
				
				// Recurse here -> On Next string index
				pos++;
				buildTree(str, pos, newNode);
			}
	    }
		else {
			// Position is at the end of the string
			currentNode.upCount();
			wordMap.put(str, currentNode.getCount());
			// Incrementing the total word count from the dictionary.
			
			if(currentNode.getCount() > 0) {
				//System.out.println("F: " + currentNode.getCount() + " \'" + str + "\'");
			}
			
			return;
		}
		
	}
	
	public Node exists(String str, int pos, Node currNode) {
		Node[] theNodes = currNode.getArray();
		Node finalNode = null;
		int position;
		
		if(pos < str.length()) {
			position = currNode.getCharIndex(str.charAt(pos));
			if(theNodes[position] != null) {
				if(pos == str.length() - 1) {
					finalNode = theNodes[position];
					if(finalNode.getCount() > 0) return finalNode;
					else return null;
				}
				Node newNode = theNodes[position];
				pos++;
				return exists(str, pos, newNode);
			}
			else {
				return null;
			}
		}
		else {
			if(finalNode.getCount() > 0) return finalNode;
			else return null;
		}
	}
}
