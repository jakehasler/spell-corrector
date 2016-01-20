package spell;

import spell.ITrie.INode;

public class Node implements ITrie.INode {

	private int count;
	private Node[] nodes;

	
	Node() {
		count = 0;
		nodes = new Node[26];
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
	}
	
	public int getCharIndex(char single) {
		int charPosition = single - 'a';
		return charPosition;
	}
	
	public char getCharByIndex(int index) {
		char character = (char)(index + 'a');
		return character;
	}

	@Override
	public int getValue() {
		return count;
	}
	
} // End of Class declaration
