//Marianthi Mindrinou 3150110
//Michalis Roussos 3150148
//Christos Tasiopoulos 3150170

public class TreeNode {
	String name;
	TreeNode leftNode;
	TreeNode rightNode;
	Examples data;
	Properties data2;

	// constructor
	TreeNode(String name,Examples data,Properties data2) {
		this(name,data,data2, null, null);
	}

	// constructor
	TreeNode(String name,Examples data,Properties data2, TreeNode leftNode, TreeNode rightNode) {
		this.name=name;
		this.data = data;
		this.data2=data2;
		this.leftNode = leftNode;
		this.rightNode = rightNode;

	}

	// return method to return the node's data
	String gatName(){
		return name;
	}
	Examples getObject() {
		return data;
	}
	
	Properties getObject2() {
		return data2;
	}

	// getters and setters
	TreeNode getLeftNode() {
		return leftNode;
	}

	TreeNode getRightNode() {
		return rightNode;
	}

	void setLeftNode(TreeNode leftNode) {
		this.leftNode = leftNode;
	}

	void setRightNode(TreeNode rightNode) {
		this.rightNode = rightNode;
	}

	void setData(Examples data) {
		this.data = data;
	}
	void setData2(Properties data) {
		this.data2 = data;
	}
	
	void setName(String name){
		this.name=name;
	}
	//method for printing whether we are at the left or the right child of a subtree.
	void print1(){
		System.out.println(this.name);
		if(this.leftNode!=null){
			System.out.println("left child");
			this.leftNode.print1();
		}
		if(this.rightNode!=null){
			System.out.println("right child");
			this.rightNode.print1();
		}
	}
	
}
