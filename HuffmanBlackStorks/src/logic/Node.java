package logic;

public class Node implements Comparable<Node>{
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
	
	public boolean isLeaf() {
		return (this.leftNode == null && this.rightNode == null);
	}

	@Override
	public int compareTo(Node node2) {
		int freqComp = Integer.compare(this.freq, node2.freq);
		if (freqComp != 0) {
			return freqComp;
		}
		return Integer.compare(this.character, node2.character);
	}
	
}
