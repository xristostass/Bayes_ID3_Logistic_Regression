//Marianthi Mindrinou 3150110
//Michalis Roussos 3150148
//Christos Tasiopoulos 3150170

import java.util.*;

public class Properties {
	//this array contains the name of the properties that are 
	ArrayList<String> prop=new ArrayList<String>();
	//default constructor
	public Properties(){
		
	}
	//constractor that extracts the properties from a string
	public Properties(String prop){
		StringTokenizer tok=new StringTokenizer(prop," ");
		this.prop.add(tok.nextToken());
	}
	// this method returns the number of properties
	public int numofprop(){
		return this.prop.size();
	}
	//a getter that returns a specific property
	public String getprop(int i){
		return this.prop.get(i);
	}
	//this method adds properties to the arraylist
	public void add(String ex){
		prop.add(ex);
	}
}
