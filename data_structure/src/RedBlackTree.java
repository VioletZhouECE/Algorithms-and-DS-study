public class RedBlackTree<Key extends Comparable<Key>, Value> {
    //private static final is usually used for declaring a constant
    //mapping is used internally in the RedBlackTree class
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    TreeNode root;
    int size;

    private class TreeNode <Key extends Comparable<Key>, Value>{
        Key key;
        Value value;
        boolean color;
        TreeNode left;
        TreeNode right;

        public TreeNode(Key key, Value value, boolean color){
            this.key = key;
            this.value = value;
            this.color = color;
            this.left = null;
            this.right = null;
        }
    }

    public RedBlackTree(){
        this.root = null;
        this.size = 0;
    }

    //to "encapsulate" the color of null node
    public boolean getColor(TreeNode node){
        //null node is BLACK because we cannot have a dangling (incomplete) 2-node (RED)
        if (node == null) return BLACK;
        return node.color;
    }

    //right-leaning -> left-leaning
    private TreeNode rotateLeft(TreeNode h){
        TreeNode x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    //left-leaning -> right-leaning
    private TreeNode rotateRight(TreeNode h){
        TreeNode x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    //split a 3-node
    private void flipColor(TreeNode h){
        h.left.color = BLACK;
        h.right.color = BLACK;
        h.color = RED;
    }

    //insert a key-value pair into the red black tree recursively
    public void insert(Key key, Value value){
        this.root = this.insertHelper(key, value, root);
    }

    private TreeNode insertHelper(Key key, Value value, TreeNode node){
        if (node == null){
            this.size++;
            return new TreeNode(key, value, RED);
        }

        int result = node.key.compareTo(key);
        if (result < 0) node.right = insertHelper(key, value, node.right);
        else if (result > 0) node.left = insertHelper(key, value, node.left);
        //else do nothing - avoid inserting duplicate keys

        //adjust the links when backtracking
        if (getColor(node.right) == RED && getColor(node.left) != RED ) node = rotateLeft(node);
        if (getColor(node.left) == RED && getColor(node.left.left) == RED) node = rotateRight(node);
        if (getColor(node.left) == RED && getColor(node.right) == RED) flipColor(node);

        return node;
    }

    public int getHeight(){
        return getHeightofNode(this.root);
    }

    //height = the maximum number of black links from the current node to the leaf
    private int getHeightofNode(TreeNode node){
        if (node == null) return 0;
        if (getColor(node) == RED) {
            return Math.max(getHeightofNode(node.left), getHeightofNode(node.right));
        } else {
            return Math.max(getHeightofNode(node.left), getHeightofNode(node.right))+1;
        }
    }

    //do an inorder traversal of the tree
    public void print(){
        inorderTraversal(this.root);
    }

    private void inorderTraversal(TreeNode node){
        if (node==null) return;
        inorderTraversal(node.left);
        System.out.println("key: " + node.key + ", value: " + node.value);
        inorderTraversal(node.right);
    }

    public static void main(String[] args){
        //keys in descending order
        RedBlackTree RBT1= new RedBlackTree<Integer, String>();
        RBT1.insert(7, "Violet");
        RBT1.insert(6, "May");
        RBT1.insert(5, "Alexander");
        RBT1.insert(4, "Anya");
        RBT1.insert(3, "Maven");
        RBT1.insert(2, "Susan");
        RBT1.insert(1, "Tom");
        RBT1.print();
        System.out.println(RBT1.getHeight()); //height = 2

        //duplicate keys
        RedBlackTree RBT2= new RedBlackTree<Integer, String>();
        RBT2.insert(7, "Violet");
        RBT2.insert(7, "May");
        RBT2.insert(7, "Alexander");
        RBT2.insert(6, "Anya");
        RBT2.insert(6, "Maven");
        RBT2.insert(6, "Susan");
        RBT2.insert(1, "Tom");
        RBT2.print();
        System.out.println(RBT2.getHeight()); //height = 1
    }
}
