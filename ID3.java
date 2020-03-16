//Marianthi Mindrinou 3150110
//Michalis Roussos 3150148
//Christos Tasiopoulos 3150170

import java.util.ArrayList;
public class ID3 {
	//main method where we just call the recursive ID3 method with a default category.
	public static TreeNode ID3method(Examples ex,Properties prop){
		TreeNode result=Id3recursive(ex,prop,"default");
		return result;
	}
	public static TreeNode Id3recursive(Examples ex,Properties prop,String s){
		TreeNode result;
		//check in order to create the decision tree 
		if(ex.numofex()==0){
			result=new TreeNode(s,null,null,null,null);
			return result;
		}else{
			boolean category=true;
			//checking if the examples belong in the same category
			for(int i=0; i<ex.getex(0).numofatts(); i++){
				category=true;
				for(int j=0; j<ex.numofex(); j++){
					if(ex.getex(j).getat(i).equals("0")){
						category=false;
						break;
					}
				}
				if(category && prop.numofprop()>i ){
					result=new TreeNode(prop.getprop(i),null,null,null,null);
					return result;
				}
				
			}
			//here we are checking whether the properties list is empty or not
			int counter=0;
			for(int j=0; j<ex.numofex(); j++){
				if(ex.getex(j).getat(ex.getex(0).numofatts()).equals("1")){
					counter++;
				}
			}
			if (prop.numofprop()==0){
				if(counter>(ex.numofex()/2)-1 ){
					result=new TreeNode("Give",null,null,null,null);
				}else{
					result=new TreeNode("Don't Give",null,null,null,null);
				}	
				return result;
			}
			//here we are checking for the best property and setting the subtrees
			int bestprop=Functions.choosebest(ex,prop);
			result=new TreeNode(prop.getprop(bestprop),null,null,null,null);
			Properties prop1=new Properties();
			Examples ex1=new Examples();
			Examples ex0=new Examples();
			for (int i=0;i<prop.numofprop();i++){
				if(i!=bestprop){
					prop1.add(prop.getprop(i));
				}				
			}
			for (int i=0;i<ex.numofex();i++){
				ex.getex(i).getArray().remove(bestprop);
				if(ex.getex(i).getat(bestprop).equals(1)){
					ex1.add(ex.getex(i));
				}else{
					ex0.add(ex.getex(i));
				}
			}
			//setting the subtrees
			result.setLeftNode(Id3recursive(ex1,prop1,result.name));		
			result.setRightNode(Id3recursive(ex0,prop1,result.name));
		}
		return result;
	}
}