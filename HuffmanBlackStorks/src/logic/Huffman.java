package logic;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Huffman {
	
	final static int ASCII_AMOUNT = 256;

	public static String compress(String s) {
		 		
		int[] freq = getAmount(s);
		Node root = generateTree(freq);
		Map<Character, String> compressCodes = giveBits(root);
		String compressedData = generateCompressedData(s, compressCodes);	
	//	hexData += " | ";
	//	String hexData = toHexData(compressedData);
		
		String savedTree = saveCodes(compressCodes);
	//	String wholeData = hexData + savedTree;
		String wholeData = compressedData + savedTree;
		// - for saves
		// DONE compressedData = toHex(compressedData);
		//compressedData = compressedData + ".!.";
		// saveTree(compressedData, root);
		
		// - tests
	//	System.out.println(wholeData);
	//	String originalData = decompress(compressedData, root);
	//	System.out.println(originalData);
		
		return wholeData;
	}
	
	private static int[] getAmount(String s) {
		
		 int[] freq = new int[ASCII_AMOUNT];
		 for (char c : s.toCharArray()) {
			 freq[c]++;
		 }
		 return freq;
	}
	
	private static Node generateTree(int[] freq) {
		
		PriorityQueue<Node> queue = new PriorityQueue<>();
	//	Node root = new Node('\u0000', 0, null, null);
		
		for(char i = 0; i < ASCII_AMOUNT; i++) {
			if(freq[i] > 0) {
				queue.add(new Node(i, freq[i], null, null));
			}
		}
		
		if(queue.size() == 1) {
			queue.add(new Node('\0', 1, null, null));
		}
		
		while(queue.size() > 1) {
			Node left = queue.poll();
			Node right = queue.poll();
			Node parent = new Node('\0', left.getFreq() + right.getFreq(), left, right);
			queue.add(parent);
		}
		
		return queue.poll();
	}
	
	private static Map<Character, String> giveBits(Node root){
		Map<Character, String> map = new HashMap<>();
		
		giveBitsRec(root, "", map);
		
		return map;
	}

	private static void giveBitsRec(Node node, String s, Map<Character, String> map) {
		
		if (!node.isLeaf()) {
			giveBitsRec(node.getLeft(), s + '0', map);
			giveBitsRec(node.getRight(), s + '1', map);
		} else {
			map.put(node.getChar(), s);
		}
		
	}
	
	private static String generateCompressedData(String data, Map<Character, String> map) {
		StringBuilder builder = new StringBuilder();
		for(char character : data.toCharArray()) {
			builder.append(map.get(character));
		}
		return builder.toString();
	}
	
	private static String toHexData(String compressedData) {
		String hexData = "";
		/*for (int i = 0; i < compressedData.length(); i = i + 8) {
			if (compressedData.length() - i < 8) {
				String tempBin = compressedData.substring(i);
				int decimal = Integer.parseInt(tempBin, 2);
				if (Integer.toHexString(decimal).toUpperCase().length() == 1) {
					hexData = hexData + "0" + Integer.toHexString(decimal).toUpperCase();
				} else {
				hexData = hexData + Integer.toHexString(decimal).toUpperCase();
				}
				
				break;
			}
			String tempBin = compressedData.substring(i, i + 8);
			int decimal = Integer.parseInt(tempBin, 2);
			if (Integer.toHexString(decimal).toUpperCase().length() == 1) {
				hexData = hexData + "0" + Integer.toHexString(decimal).toUpperCase() + " ";
			} else {
			hexData = hexData + Integer.toHexString(decimal).toUpperCase() + " ";
			}*/
			
		for (int i = 0; i < compressedData.length(); i = i + 4) {
			if (compressedData.length() - i < 4) {
				String tempBin = compressedData.substring(i);
				int decimal = Integer.parseInt(tempBin, 2);
				if (decimal < 8) {
					hexData = hexData + "0" + Integer.toHexString(decimal).toUpperCase();
				} else {
					hexData = hexData + Integer.toHexString(decimal).toUpperCase();
				}
					/*else if (decimal < 4) {
					
				} else if (decimal < 2) {
					
				} else if */
				/*if (Integer.toHexString(decimal).toUpperCase().length() == 1) {
					hexData = hexData + "0" + Integer.toHexString(decimal).toUpperCase();
				} else {
				hexData = hexData + Integer.toHexString(decimal).toUpperCase();
				}*/
				
				break;
			}
			String tempBin = compressedData.substring(i, i + 4);
			int decimal = Integer.parseInt(tempBin, 2);
			/*if (Integer.toHexString(decimal).toUpperCase().length() == 1) {
				hexData = hexData + "0" + Integer.toHexString(decimal).toUpperCase() + " ";
			} else {*/
			hexData = hexData + Integer.toHexString(decimal).toUpperCase()/* + " "*/;
			//}
			
		//	hexData = hexData + Integer.toHexString(decimal).toUpperCase() + " ";
		//	if (hexData.length() == 1) {
		//		hexData = "0" + hexData;
		//	}
		}
		hexData = hexData + "|";
		return hexData;
	}
	
	private static String saveCodes(Map<Character, String> map) {
		String mapAsString = map.keySet().stream()
			      .map(key -> key + "=" + map.get(key))
			      .collect(Collectors.joining(",.", "{", "}"));
			    return mapAsString;
	}
	
	/*private static String hexToBin(String content) {
		int stop = content.indexOf('|');
		content = content.substring(0, stop);
		String binary;
		for (int i = 0; i < content.length(); i++) {
			BigInteger integer = new BigInteger(content.valueOf(charAt(i)), 16);
			String temp += new BigInteger(content.charAt(i), 16).toString(2);
	//		String temp = content.charAt(i).;
		}
		return binary;
	}*/
	
	private static Map<Character, String> returnMap(String s) {
		Map<Character, String> map = new HashMap<>();
		s = s.substring(s.indexOf("{"));
		s = s.substring(1, s.length() - 1);
		String[] parts = s.split(",.");
		/*for (String p : parts) {
			System.out.println(p);
		}*/ 
	//	System.out.println(parts.length);
		
		for (String p : parts) {
			map.put(p.charAt(0), p.substring(2));
			//System.out.println("added");
		}
	//	System.out.println(saveCodes(map));
	//	System.out.println(map.size());
		return map;
	}
	
	public static String decompress(String compressed) {
		String original = "";
		Map<Character, String> map = returnMap(compressed);
		int stop = compressed.indexOf('{');
		compressed = compressed.substring(0, stop);
		String current = "";
		for(int i = 0; i < compressed.length(); i++) {
			current = current + compressed.charAt(i);
			if (getKey(map, current) != null) {				
				original += getKey(map, current);
				current = "";
			}
		}
		return original;
	}
	
	/*public static String decompress(String compressed, Node root) {
		StringBuilder builder = new StringBuilder();
		Node current = root;
		int i = 0;
		while(i < compressed.length()) {
			while(!current.isLeaf()) {
				char bit = compressed.charAt(i);
				if(bit == '0') {
					current = current.getLeft();
				} else if(bit == '1') {
					current = current.getRight();
				}
				i++;
			}
			builder.append(current.getChar());
			current = root;
		}
		
		return builder.toString();
	}*/
	
	public static <K, V> K getKey(Map<K, V> map, V value) {
		for (Map.Entry<K, V> entry : map.entrySet()) {
			if (value.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}
}
