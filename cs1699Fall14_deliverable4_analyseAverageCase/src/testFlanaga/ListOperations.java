package testFlanaga;
//import java.util.ArrayList;
import java.util.List;


public class ListOperations {

	public boolean check2StringListEqual(List<String> leftResult,List<String> rightResult){
	    if(leftResult.size()!=rightResult.size())
	        return false;
	    
	    for(int i=0; i< leftResult.size(); i++){
	        if(!leftResult.get(i).equals(rightResult.get(i)))
	            return false;
	    }
	    
	    return true;
	}
	
	public int countLeftList(List<String> result){
		int count=0;
		for(int i=0;i<result.size(); i++){
			if(!result.get(i).equals("n"))
				count++;
		}
		return count;
	}
	
	public int countRightList(List<String> result){
		int count=0;
		for(int i=0;i<result.size(); i++){
			if(!result.get(i).equals("n"))
				count++;
		}
		return count;
	}
}
