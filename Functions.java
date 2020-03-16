//Marianthi Mindrinou 3150110
//Michalis Roussos 3150148
//Christos Tasiopoulos 3150170

import java.util.*;
public class Functions {

	//this method calculates the probability that an example's specific attribute in the field property equals to the value c 
	public static float probability1 (String c,Examples ex,int property){
		int sum=0;
		for(int i=0;i<ex.numofex();i++){
			if(ex.getex(i).getat(property).equals(c)){
				sum++;
			}
		}
		return sum/ex.numofex();
	}
	
	//this method calculates the probability that an example's specific attribute in the field property1 equals to the value c provided that this example's attribute in the field property2 equals to x
	public static float probability2(String c,Examples ex,int property1,String x,int property2){
		int sumofC=0;
		int sumofCandX=0;
		for(int i=0;i<ex.numofex();i++){
			if(ex.getex(i).getat(property1).equals(c)){
				sumofC++;
			}
			if(ex.getex(i).getat(property1).equals(c) && ex.getex(i).getat(property2).equals(x)){
				sumofCandX++;
			}
		}
		return sumofCandX/sumofC;
	}
	
	//this method calculates the entropy of the specific property
	public static float entropy1(String c,Examples ex,int property){
		float sum=0;
		float prob=0;
		for(int i=0; i==Integer.parseInt(c) ;i++){
			prob=probability1(c,ex,property);
			sum+=prob*Math.log(prob)/Math.log(2);
		}
	
		return -sum;
	
	}
	
	//this method calculates the entropy of the specific property1 provided that the field property 2 equals to x
	public static float entropy2(String c,Examples ex,int property1,String x,int property2){
		float sum=0;
		float prob=0;
		for(int i=0; i==Integer.parseInt(c) ;i++){
			prob=probability2(c,ex,property1,x,property2);
			sum+=prob*Math.log(prob)/Math.log(2);
		}
	
		return -sum;
	
	}
	
	//this method calculates the information gain of a specific property
	public static float IG(String c,Examples ex,int property,int property2){
		float result=entropy1(c,ex,property);
		float sum=0;
		for(int x=0; x<2;x++){
			sum+=probability1((x+" ").trim(),ex,property2)*entropy2(c,ex,property,(x+" ").trim(),property2);
		}
		result-=sum;
		return result;
	}
	
	//this method chooses the property with the highest IG
	public static int choosebest(Examples ex,Properties prop){
		ArrayList<Double> infogain=new ArrayList<Double>();
		double variable;
		for(int i=0; i<ex.getex(0).numofatts(); i++){
			variable=0;
			for(int j=0; j<ex.numofex(); j++){
				variable += IG(ex.getex(j).getat(ex.getex(0).numofatts()) ,ex,ex.getex(0).numofatts(),i);
				
			}
			infogain.add(variable);
		}
		double maxfr=infogain.get(0);/////////////
		int position=0;/////////////////////////
		for(int i=1; i<ex.getex(0).numofatts(); i++){
			if(infogain.get(i)>maxfr){
				maxfr=infogain.get(i);
				position=i;
			}
		}
		return position;
	}
	

	
}
