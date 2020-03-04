
public class Huffman {

	public static void main(String[] args) {
			// zapyskaet gui

	}

	static void compress(String s) {
		 
		int[] freq = getAmount(s);
		Node root = generateTree(freq);
		
	}
	
	static int[] getAmount(String s) {
		final int ASCII_AMOUNT = 256;
		 int[] freq = new int[ASCII_AMOUNT];
		 for (char c : s.toCharArray()) {
			 freq[c]++;
		 }
		 return freq;
	}
	
	static Node generateTree(int[] freq) {
		
		Node root = new Node('\u0000', 0, null, null);
		return root;
	}
}
