package testFlanaga;
import java.util.ArrayList;


public class Permutation
{
	public static ArrayList<ArrayList<Integer>> permutation(final ArrayList<Integer> list)
	{
		ArrayList<Integer> intakeList = new ArrayList<Integer>(list);
		ArrayList<ArrayList<Integer>> endResult = new ArrayList<ArrayList<Integer>>();

		if (intakeList.size() == 1 && endResult.add(intakeList))
			return endResult; //I just wanted it in one line!

		Integer firstEntry;
		ArrayList<ArrayList<Integer>> permutatedList = new ArrayList<ArrayList<Integer>>();

		firstEntry = intakeList.remove(0);

		permutatedList = permutation(intakeList);

		for (ArrayList<Integer> listItem : permutatedList)
		{
			int itemLen = listItem.size();
			for (int i = 0; i < itemLen+1; i++)
			{
				ArrayList<Integer> tempList = new ArrayList<Integer>();
				tempList.addAll(listItem);
				tempList.add( i, firstEntry);
				endResult.add(tempList);
			}
		}

		return endResult;
	}
}

/*import java.util.ArrayList;


public class Permutation
{
	public static ArrayList<ArrayList<Integer>> permutation(final ArrayList<Integer> list)
	{

		// creates total list that will be passed back 
		ArrayList<ArrayList<Integer>> returner = new ArrayList<ArrayList<Integer>>();

		
		//takes out first entry
		Integer firstEntry;
		
		//makes sublist to be passed
		ArrayList<Integer> tempList; 
	
		//permute the list
		if(list.size()>1){
			
			firstEntry = list.get(0);
			tempList = new ArrayList<Integer>(list.subList(1, list.size()));
			returner = permutation(tempList);
		}
		else{
			
			returner.add(list);
			return returner;
			
		}

		int end = returner.size();
		//for each list
		for(int i = 0; i<end;i++){
			
			for(int j = 0; j<=returner.get(0).size(); j++)
			{
		
				returner.add(new ArrayList<Integer>(returner.get(0)));
				returner.get(returner.size()-1).add(j, firstEntry);				
				
			}
		
			returner.remove(0);
						
		}
		
		return returner;


	}

}*/