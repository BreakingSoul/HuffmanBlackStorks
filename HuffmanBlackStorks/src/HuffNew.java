import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class HuffNew {
	public static void main(String[] args) {
		String text = "ja ebal tvoju mamashu";
		String encoderRez = comp(text);
}
	
	public static String comp(String encodingText) {
		int length = encodingText.length();
		LinkedList<String> list1 = new LinkedList<String>();
		char[] arr = new char[length]; 
		HashMap<Character, Integer> hash_map = new HashMap<Character, Integer>();  
		for (int i=0; i<length; i++ ) {
			arr[i] = encodingText.charAt(i);
			//list1.add(encodingText.charAt(i), encodingText);
			//System.out.println(arr[i]);
		}
		int porog = 1;
		for (int i=0; i<length; i++ ) {
			int iter=0;
			for (int j=0; j<length;j++ ) {
				if (arr[i]==arr[j]) {
					//System.out.println(arr[i]+':'+arr[j]);
					iter++;
				}
				
						
			}
			hash_map.put(arr[i], iter); 
			
		}
		System.out.println(hash_map);
		Map<Character, Integer> unSortedMap = hash_map;
		LinkedHashMap<Character, Integer> reverseSortedMap = new LinkedHashMap<>();

		unSortedMap.entrySet()
		    .stream()
		    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
		    .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
		 
		System.out.println("Reverse Sorted Map   : " + reverseSortedMap);
        
		for (Map.Entry<Character, Integer> entry : reverseSortedMap.entrySet())  
            System.out.println("Key = " + entry.getKey() + 
                             ", Value = " + entry.getValue()); 
		return null;
	}
	
	public static String decomp(String text) {
		return null;
	}
}
