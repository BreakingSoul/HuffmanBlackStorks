package logic;

public class RLE {

	// first goes amount, after that symbol itself
	public static String compress(String s) throws StringIndexOutOfBoundsException, NullPointerException {
		String comp = "";
		char current;
		current = s.charAt(0);
		int counter = 1;
		for (int i = 1; i < s.length(); i++) {
			if (current == s.charAt(i)) {
				counter++;
			} else {
				comp = comp + counter + current + "|";
				current = s.charAt(i);
				counter = 1;
			}
		}
		comp = comp + counter + current;
		return comp;
	}

	public static String decompress(String s) throws StringIndexOutOfBoundsException, NullPointerException {
		String orig = "";
		String[] parts = s.split("\\|");
		for (String part : parts) {
			int amount = Integer.parseInt(part.substring(0, part.length() - 1));
			for (int i = 0; i < amount; i++) {
				orig = orig + part.charAt(part.length() - 1);
			}
		}
		return orig;
	}

	// first goes amount 0, as in white pixel. After that amount of black pixels,
	// then again white and so on.
	public static String compressPicture(String s) throws StringIndexOutOfBoundsException, NullPointerException {
		String comp = "";
		int counter = 0;
		char current = '0';
		for (int i = 0; i < s.length(); i++) {
			if (current == s.charAt(i)) {
				counter++;
			} else {
				comp = comp + counter + "|";
				current = s.charAt(i);
				counter = 1;
			}
		}
		comp = comp + counter;
		return comp;
	}

	public static String decompressPicture(String s) throws StringIndexOutOfBoundsException, NullPointerException {
		String orig = "";
		String[] parts = s.split("\\|");
		boolean isWhite = true;
		for (String part : parts) {
			int amount = Integer.parseInt(part);
			if (isWhite) {
				for (int i = 0; i < amount; i++) {
					orig = orig + "0";
				}
				isWhite = false;
			} else {
				for (int i = 0; i < amount; i++) {
					orig = orig + "1";
				}
				isWhite = true;
			}
		}
		return orig;
	}
}
