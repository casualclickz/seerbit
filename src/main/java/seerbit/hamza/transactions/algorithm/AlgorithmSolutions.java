package seerbit.hamza.transactions.algorithm;

import java.util.TreeMap;

/**
* AlgorithmSolutions implements solution to the algorithm challenge
* 
* @author Hamza Salihu
* 
*/
public class AlgorithmSolutions {
	
	public static void main(String[] args) {
		
		int[] arr = subArrayWithMaxXOR(new int[] {1,2,3,4});
		for(int i = 0; i < arr.length; i++) System.out.print(arr[i]+" ");
	}
	
	/**
	 * <p>This method resolves sub array with max xor from tree map</p>
	 * @param arr incoming array
	 * @return sub array with max xor
	 * @since 0.0.1-SNAPSHOT
	 */
	public static int[] subArrayWithMaxXOR(int[] arr) {
		
		if(arr.length == 1) return arr;
		
		TreeMap<Integer, int[]> map = new TreeMap<>();
		
		for(int i = 0; i < arr.length; i++) {
			
			populateMaxXORTree(arr[i], i, arr, map);
		}
		
		return map.lastEntry().getValue();
	}
	
	/**
	 * <p>This method builds xor to array tree map</p>
	 * @param currentI current element of array
	 * @param currentIndex current index of array
	 * @param arr array reference
	 * @param map tree map reference
	 * @since 0.0.1-SNAPSHOT
	 */
	private static void populateMaxXORTree(int currentI, int currentIndex, int[] arr, TreeMap<Integer, int[]> map) {
		
		for(int j = currentIndex; j < arr.length; j++) {
			
			int xor = currentI ^ arr[j];
			map.put(xor, new int[] {currentI, arr[j]});
		}
	}
}

