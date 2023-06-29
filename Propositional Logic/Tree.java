
/*
 * this class were implemented for evaluating propositional logic as a underlying data structure
 */
public class Tree<T>{

    public Node root;
    
    public class Node{

        public Node left;
        public Node right;
        public T data;

        public Node(T data,Node left,Node right){

            this.data = data;
            this.left = left;
            this.right = right;

        }

    }

}