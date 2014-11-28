package testFlanaga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class arrayAlgorithms {

	//worst case big-O n, acerage case big-O n
	public int removeDuplicates(int[] A) {  
		if(A.length == 0)  
			return 0; 
		if(A.length == 1)  
			return 1;  

		int index = 0;  
		for(int i = 1; i < A.length; ++i) {  
			if(A[i] != A[index]) {  
				++index;  
				A[index] = A[i];  
			}  
		}
		return index + 1;
	}

	public int searchInRotatedSortedArray(int[] A, int target) {
		int left = 0;  
		int right = A.length - 1;  

		while (left <= right) {  
			int mid = (left + right) / 2;  

			if (A[mid] == target) {  
				return mid;  
			}  

			if (A[mid] >= A[left]) {   
				if (A[mid] > target && A[left] <= target) {  
					right = mid - 1;  
				} else {  
					left = mid + 1;  
				}  
			} else {  
				if (A[mid] < target && A[right] >= target) {  
					left = mid + 1;  
				} else {  
					right = mid - 1;  
				}  
			}  
		}  

		return -1;  
	}

	//worst case big-O log(m+n)
	public double findMedianSortedArrays(int A[], int B[]) {  
		// IMPORTANT: Please reset any member data you declared, as  
		// the same Solution instance will be reused for each test case.  
		int m = A.length;  
		int n = B.length;  
		int total = m + n;  
		if ((total & 0x01) != 0) {  
			return find_kth(A, m, B, n, total / 2 + 1);  
		} else {  
			return (find_kth(A, m, B, n, total / 2) + find_kth(A, m, B, n,  
					total / 2 + 1)) / 2.0;  
		}  
	}  

	public double find_kth(int A[], int m, int B[], int n, int k) {  
		if (m > n) {  
			return find_kth(B, n, A, m, k);  
		}  
		if (m == 0) {  
			return B[k - 1];  
		}  
		if (k == 1) {  
			return Math.min(A[0], B[0]);  
		}  

		int pa = Math.min(k / 2, m);  
		int pb = k - pa;  
		if (A[pa - 1] < B[pb - 1]) {  
			return find_kth(Arrays.copyOfRange(A, pa, A.length), m - pa, B, n,  
					k - pa);  
		} else if (A[pa - 1] > B[pb - 1]) {  
			return find_kth(A, m, Arrays.copyOfRange(B, pb, B.length), n - pb,  
					k - pb);  
		} else {  
			return A[pa - 1];  
		}  
	}

	public ArrayList<ArrayList<Integer>> threeSum(int[] num) {  
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();  

		Arrays.sort(num);  

		for (int i = 0; i < num.length - 2; ++i) {  
			if (i > 0 && num[i] == num[i - 1]) {  
				continue;  
			}  

			int j = i + 1;  
			int k = num.length - 1;  

			while (j < k) {  
				if (num[i] + num[j] + num[k] > 0) {  
					--k;  
				} else if (num[i] + num[j] + num[k] < 0) {  
					++j;  
				} else {  
					ArrayList<Integer> tmp = new ArrayList<Integer>();  
					tmp.add(num[i]);  
					tmp.add(num[j]);  
					tmp.add(num[k]);  

					result.add(tmp);  

					--k;  
					++j;  

					while (j < k && num[j] == num[j - 1]) {  
						++j;  
					}  
					while (j < k && num[k] == num[k + 1]) {  
						--k;  
					}  
				}  
			}  
		}  

		return result;  
	}  

	public int[] plusOne(int[] digits) {  
		int carry = 1;  
		int i = digits.length - 1;  

		for (; i >= 0; --i) {  
			digits[i] += carry;  

			if (digits[i] >= 10) {  
				digits[i] -= 10;  
				carry = 1;  
			} else {  
				carry = 0;  
				break;  
			}  
		}  

		if (i < 0) {  
			int[] result = new int[digits.length + 1];  
			result[0] = 1;  

			System.arraycopy(digits, 0, result, 1, digits.length);  

			return result;  
		} else {  
			return digits;  
		}  
	}


	public int candy(int[] ratings) {
		if (ratings==null||ratings.length==0){
			return 0;
		}

		int[] candies=new int[ratings.length];
		// every child should has at least one candy
		for (int i=0; i<candies.length; i++){
			candies[i]=1;
		}
		// if child i has rating higher than i-1, which should 1 bigger than its left neighbour
		for (int i=1; i<ratings.length; i++){
			if (ratings[i]>ratings[i-1]){
				candies[i]=candies[i-1]+1;
			}
		}
		// if child i has rating higher than its right neighbour, but the candies array did not 
		// represented this situation correctly, then correct it.

		for (int i=ratings.length-2; i>=0; i--){
			if (ratings[i]>ratings[i+1] && candies[i]<=candies[i+1]){
				candies[i]=candies[i+1]+1;
			}
		}

		int total=0;
		// calculate the total candies needed
		for (int i=0; i<candies.length; i++){
			total+=candies[i];
		}

		return total;
	}


	public int longestConsecutive(int[] num) {
		// Start typing your Java solution below
		// DO NOT write main() function
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < num.length; i++){
			int n = num[i];
			if(map.containsKey(n)) continue;
			if(map.containsKey(n - 1) || map.containsKey(n + 1)){
				int value = 1;
				int beforeOffset = 0;
				if(map.containsKey(n - 1)){
					beforeOffset = map.get(n - 1);
					value += map.get(n - 1);
					map.put(n - beforeOffset, value);
					map.put(n, value);
				}
				if(map.containsKey(n + 1)){
					value += map.get(n + 1);
					map.put(n + map.get(n + 1), value);
					map.put(n, value);
				}
				if(map.containsKey(n - 1)) map.put(n - beforeOffset, value);
			}
			else map.put(n, 1);
		}
		int max = 0;
		for(Integer len: map.values()){
			if(len > max) max = len;
		}
		return max;
	}

}
