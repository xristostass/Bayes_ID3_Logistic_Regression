//Marianthi Mindrinou 3150110
//Michalis Roussos 3150148
//Christos Tasiopoulos 3150170

import java.util.*;
public class Example {
	//list to store the attributes 
	ArrayList<String> attrb=new ArrayList<String>();//short for attributes
	//adding attributes to the list
	public Example(String ex){
		StringTokenizer tok=new StringTokenizer(ex," ");
		while(tok.hasMoreTokens()){
			this.attrb.add(tok.nextToken());
		}
	}
	//getting the list's size
	public int numofatts(){
		return this.attrb.size()-1;
	}
	//getting an attribute from the list
	public String getat(int i){
		return this.attrb.get(i);
	}
	//setting attributes to the list
	public void setat(int i,String atr){
		attrb.set(i, atr);
	}
	//returning the list
	public ArrayList<String> getArray(){
		return attrb;
	}
}
