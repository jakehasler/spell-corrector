package spell;

import spell.ITrie.INode;

public class Node implements ITrie.INode {

	private int count;
	private Node[] nodes;
	
	public static int wordCount;
	public static int nodeCount = 1;
	
	Node() {
		count = 0;
		nodes = new Node[26];
		
		nodeCount++;
	}
	
	public Node[] getArray() {
		return nodes;
	}
	
	public int getCount() {
		return count;
	}
	
	public void addToArray(Node newNode, int pos) {
		nodes[pos] = newNode;
	}
	
	
	public void upCount() {
		count++;
		wordCount++;
	}
	
	public int getCharIndex(char single) {
		int charPosition = single - 'a';
		return charPosition;
	}
	
	public char getCharByIndex(int index) {
		char character = (char)(index + 'a');
		return character;
	}
	
	public String toString(String word) {
		String dict = "";
		Node theNodes[] = this.getArray();
		
		for(int i = 0; i < theNodes.length; i++) {
			if(theNodes[i] != null) {
				dict += theNodes[i].toString(word + getCharByIndex(i));
			}
		}
		
		return dict;
	}
	
	
	public void buildTree(String str, int pos, Node curr) {
	
		Node currentNode = curr;
		
		// If not at end of string
		if(pos < str.length()) {
			
			int charPosition = getCharIndex(str.charAt(pos));
			
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
				currentNode.addToArray(newNode, charPosition);
				
				// Recurse here -> On Next string index
				pos++;
				buildTree(str, pos, newNode);
			}
	    }
		else {
			// Position is at the end of the string
			currentNode.upCount();
			
			if(currentNode.count > 0) {
				System.out.println("F: " + currentNode.count + " \'" + str + "\'");
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
					return finalNode;
				}
				Node newNode = theNodes[position];
				pos++;
				return exists(str, pos, newNode);
			}
			else {
				return finalNode;
			}
		}
		else {
			return finalNode;
		}
		
		
		
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 0;
	}
	
} // End of Class declaration
