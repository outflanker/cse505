import java.util.Stack;
/**
 *  Tree implements the binary search tree property
 * @author Siddharth
 *
 * @param <T>
 */

class Tree<T extends Comparable<T>> {
	public Tree(T v) {
		value = v;
		left = null;
		right = null;
	}

	public void insert(T v) {
		if (value.compareTo(v) == 0)
			return;
		if (value.compareTo(v) > 0)
			if (left == null)
				left = new Tree<T>(v);
			else
				left.insert(v);
		else if (value.compareTo(v) < 0)
			if (right == null)
				right = new Tree<T>(v);
			else
				right.insert(v);
	}

	protected T value;
	protected Tree<T> left;
	protected Tree<T> right;
}
/**
 * Iter class to iterate through the BST
 * @author Siddharth
 *
 * @param <T>
 */
class Iter<T extends Comparable<T>> {
	Stack<T> order=new Stack<>();
	public Iter(Tree<T> tree) {
		inorder(tree);
	}
	private void inorder(Tree<T> tree){
		if(tree.left!=null){
			inorder(tree.left);
		}
		order.push(tree.value);
		if(tree.right!=null){
			inorder(tree.right);
		}
	}
	public T next(){
		return order.pop();
	}
	public boolean done(){
		if(order.isEmpty()){
			return true;
		}
		return false;
	}
}

public class GenericTreeEquality {

     static <T extends Comparable<T>> boolean equals(Tree<T> tree1, Tree<T> tree2) {
    	 Iter<T> iterator1=new Iter<T>(tree1);
    	 Iter<T> iterator2=new Iter<T>(tree2);
	 	 while(!iterator1.done()&&!iterator2.done()){
	 		 if(iterator1.next().compareTo(iterator2.next())!=0){
	 			 return false;
	 		 }
	 	 }
	 	 if(iterator1.done()&&iterator2.done()){
	 		 return true;
	 	 }
    	 return false;
	}
	
	public static void main(String[] args) {
		
		Tree<Integer> tree1 = new Tree<Integer>(50);
		tree1.insert(70);
		tree1.insert(80);	
		tree1.insert(90);
		tree1.insert(100);
		
		Tree<Integer> tree2 = new Tree<Integer>(100);
		tree2.insert(90);
		tree2.insert(80);	
		tree2.insert(70);
		tree2.insert(50);
		
		System.out.println(equals(tree1, tree2));
	}
}