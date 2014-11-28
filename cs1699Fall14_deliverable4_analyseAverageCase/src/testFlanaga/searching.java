package testFlanaga;

import java.util.Arrays;

public class searching {

	public boolean linearSearch(int[] input,int target){
		for(int i=0; i<input.length; i++){
			if(input[i]==target)
				return true;
		}
		return false;
	}
	
	public int binarySearch(int[] input,int target){
		return Arrays.binarySearch(input, target);
	}
}
