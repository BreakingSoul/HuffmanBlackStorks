
public class Node {
	private char character;
	private int freq;
	private Node leftNode;
	private Node rightNode;
	
	public Node(char c, int f, Node l, Node r) {
		character = c;
		freq = f;
		leftNode = l;
		rightNode = r;
	}
	
	public char getChar() {
		return character;
	}	
	public int getFreq() {
		return freq;
	}
	public Node getLeft() {
		return leftNode;
	}
	public Node getRight() {
		return rightNode;
	}
	
	public boolean isLeaf(Node n) {
		return (n.leftNode == null && n.rightNode == null);
	}
	
}
