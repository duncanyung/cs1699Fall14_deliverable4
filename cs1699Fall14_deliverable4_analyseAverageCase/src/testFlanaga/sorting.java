package testFlanaga;
import java.util.ArrayList;

public class sorting {

	//worst case and average case also: big-O n
	public ArrayList<Integer> oNFunctionWithSwap(ArrayList<Integer> input){
		for(int j=0; j<input.size()-1; j++){
			if(input.get(j)>input.get(j+1)){
				//Do a swap
				int temp=input.get(j);
				input.set(j, input.get(j+1));
				input.set(j+1, temp);
			}
		}
		return input;
	}

	//worst case and average case also: big-O n^4
	public ArrayList<Integer> n4Sort(ArrayList<Integer> input){
		for(int x=0; x<input.size(); x++){
			for(int y=0; y<input.size(); y++){
				for(int i=0; i<input.size(); i++){
					for(int j=0; j<input.size()-1; j++){
						if(input.get(j)>input.get(j+1)){
							//Do a swap
							int temp=input.get(j);
							input.set(j, input.get(j+1));
							input.set(j+1, temp);
						}
					}
				}
			}
		}
		return input;
	}

	//worst case and average case also: big-O n^3
	public ArrayList<Integer> n3Sort(ArrayList<Integer> input){
		for(int x=0; x<input.size(); x++){
			for(int i=0; i<input.size(); i++){
				for(int j=0; j<input.size()-1; j++){
					if(input.get(j)>input.get(j+1)){
						//Do a swap
						int temp=input.get(j);
						input.set(j, input.get(j+1));
						input.set(j+1, temp);
					}
				}
			}
		}
		return input;
	}

	//worst case and average case also: big-O n^2
	public ArrayList<Integer> bubbleSort(ArrayList<Integer> input){
		for(int i=0; i<input.size(); i++){
			for(int j=0; j<input.size()-1; j++){
				if(input.get(j)>input.get(j+1)){
					//Do a swap
					int temp=input.get(j);
					input.set(j, input.get(j+1));
					input.set(j+1, temp);
				}
			}
		}

		return input;
	}

	public ArrayList<Integer> merge2SortedList(ArrayList<Integer> l1,ArrayList<Integer> l2){
		int len1=l1.size();
		int len2=l2.size();
		ArrayList<Integer> result=new ArrayList<Integer>();//int[len1+len2];
		int index1=0,index2=0;

		while(index1<len1 && index2<len2){
			if(l1.get(index1)>=l2.get(index2)){
				result.add(l2.get(index2));
				index2++;
			}else{
				result.add(l1.get(index1));
				index1++;
			}
		}

		//put the remain element into the result
		if(index1>=len1){
			//put remain of l2 into result
			for(int j=index2; j<len2; j++)
				result.add(l2.get(j));
		}else{
			//put remain of l1 into result
			for(int j=index1; j<len1; j++)
				result.add(l1.get(j));
		}
		return result;
	}

	//worst case and average case big-O nlogn
	public ArrayList<Integer> mergeSort(ArrayList<Integer> input,int start,int end){
		int mid=(end-start)/2+start;

		if(start==end){
			ArrayList<Integer> l=new ArrayList<Integer>();
			l.add(input.get(start));
			return l;
		}

		ArrayList<Integer> l1=mergeSort(input,start,mid);
		ArrayList<Integer> l2=mergeSort(input,mid+1,end);
		return merge2SortedList(l1,l2);
	}

	//worst case big-O n^2, average case big-O nlogn
	public ArrayList<Integer> quickSort(ArrayList<Integer> input,int start,int end){
		if(start>=end)
			return input;

		int lowIndex=start, upIndex=end-1, pivot=end;

		//separate to two lists
		while(!(lowIndex>upIndex)){
			if(input.get(lowIndex)>input.get(pivot)){
				int temp=input.get(lowIndex);
				input.set(lowIndex,input.get(upIndex));
				input.set(upIndex,temp);
				upIndex--;
			}else
				lowIndex++;
		}

		if(input.get(pivot)<input.get(lowIndex)){
			int temp=input.get(pivot);
			input.set(pivot,input.get(lowIndex));
			input.set(lowIndex,temp);
			pivot=lowIndex;
		}

		//handle 2 lists
		quickSort(input,start,pivot-1);
		quickSort(input,pivot+1,end);
		return input;
	}

	public static void main(String args[]){
		ArrayList<Integer> input=new ArrayList<Integer>();
		input.add(14);
		input.add(5);
		input.add(-1);
		input.add(1);
		input.add(6);
		input.add(5);
		input.add(10);
		input.add(0);
		input.add(8);

		sorting sort=new sorting();
		System.out.println(input.toString());
		//		input=sort.bubbleSort(input);
		//		input=sort.mergeSort(input, 0, input.size()-1);
		input=sort.quickSort(input, 0, input.size()-1);
		System.out.println(input.toString());
	}
}
