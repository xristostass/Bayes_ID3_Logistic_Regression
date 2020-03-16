//Marianthi Mindrinou 3150110
//Michalis Roussos 3150148
//Christos Tasiopoulos 3150170

import java.util.*;
import java.lang.*;

import static java.lang.Math.log;

public class Bayes {
	//here we calculate the sorting function h
	public static int h_method(Example e,Examples exs) {
		double productc0=1;//for the P(C=0) part
		double productc1=1;//for the P(C=1) part
		ArrayList<Integer> c0_attr0=new ArrayList<Integer>();
		ArrayList<Integer> c0_attr1=new ArrayList<Integer>();
		ArrayList<Integer> c1_attr0=new ArrayList<Integer>();
		ArrayList<Integer> c1_attr1=new ArrayList<Integer>();
		
		for(int i=0 ; i<exs.size() ; i++){//for each example
			
			if(exs.getex(i).getat(exs.getex(i).numofatts()).equals("0")) {//if C==0
				
				for(int j=0;j<exs.getex(i).numofatts();j++) {//for each attribute of a specific example
					c0_attr0.add(0);
					c0_attr1.add(0);
					if(exs.getex(i).getat(j).equals("0")) {//attribute_j=0
						c0_attr0.set(j, c0_attr0.get(j)+1);
					}else {
						c0_attr1.set(j, c0_attr1.get(j)+1);
					}
				}
			}
			else {//if C==1
				
				for(int j=0;j<exs.getex(i).numofatts();j++) {//for each attribute of a specific example
					c1_attr0.add(0);
					c1_attr1.add(0);
					if(exs.getex(i).getat(j).equals("0")) {//attribute_j=1
						c1_attr0.set(j, c1_attr0.get(j)+1);
					}else {
						c1_attr1.set(j, c1_attr1.get(j)+1);
					}
				}
			}
		
		}
		productc0=log(productc0)+log(c0_attr0.size()+c0_attr1.size()) - log(exs.size());
		productc1=log(productc1)+log(c1_attr0.size()+c1_attr1.size())-log(exs.size());
		for(int i=0;i<e.numofatts();i++) {
			if (e.getat(i).equals("1")) {
				productc0=log(productc0)+log(c0_attr1.get(i));
				productc1=log(productc1)+log(c1_attr1.get(i));
			}else {
				productc0=log(productc0)+log(c0_attr0.get(i));
				productc1=log(productc1)+log(c1_attr0.get(i));
			}
		}

		int true_type=0;
		if(productc1>productc0) {

		    return 1;
		}else {

			return 0;
		}
		
	}
}
