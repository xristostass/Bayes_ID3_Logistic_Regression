//Marianthi Mindrinou 3150110
//Michalis Roussos 3150148
//Christos Tasiopoulos 3150170

import java.util.*;

public class Examples {
	ArrayList<Example> data=new ArrayList<Example>();
	//constructor
	public Examples(){
		
	}
	//getting the examples list size 
	public int numofex(){
		return this.data.size();
	}
	//getting an example 
	public Example getex(int i){
		return this.data.get(i);
	}
	//adding an example
	public void add(Example ex){
		data.add(ex);
	}
	//returns the num of examples
	public int size() {
		return data.size();
	}
}
