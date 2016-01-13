package spell;

public class Node {

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
	
	// string, character position
	// newposition, string 
	// if not at end of string, keep going, whether exists or not
	
	public void buildTree(String str, int pos, Node curr) {
	
		Node currentNode = curr;
		
		// If not at end of string
		if(pos < str.length()) {
			
			System.out.println(str.charAt(pos));
			System.out.println(pos);
			
			int charPosition = str.charAt(pos) - 'a';
			
			if(currentNode.getArray()[charPosition] != null) {
				// A Node Actually exists here.
				Node newCurr = currentNode.getArray()[charPosition];
				
				// Recurse here -> On Next string index
				System.out.println("Recursing");
				pos++;
				buildTree(str, pos, newCurr);

			}
			else {
				// Insert Letter into a new node and a new array
				Node newNode = new Node();
				currentNode.addToArray(newNode, charPosition);
				
				// Recurse here -> On Next string index
				System.out.println("Recursing");
				pos++;
				buildTree(str, pos, newNode);
			}
	    }
		else {
			// Position is at the end of the string
			currentNode.upCount();
			return;
		}
		
	}
} // End of Class declaration
