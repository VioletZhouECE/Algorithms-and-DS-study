public class RedBlackTree<Key extends Comparable<Key>, Value> {
    //private static final is usually used for declaring a constant
    //mapping is used internally in the RedBlackTree class
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    TreeNode root;
    int size;
    //implement this later
    //int height;

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
    private void rotateLeft(TreeNode h){
        TreeNode x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
    }

    //left-leaning -> right-leaning
    private void rotateRight(TreeNode h){
        TreeNode x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
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
            return new TreeNode(key, value, BLACK);
        }

        int result = node.key.compareTo(key);
        if (result < 0) node.right = insertHelper(key, value, node.right);
        else if (result > 0) node.left = insertHelper(key, value, node.left);
        //else do nothing - avoid inserting duplicate keys

        //adjust the links when backtracking
        if (getColor(node.right) == RED && getColor(node.left) != RED ) rotateLeft(node);
        if (getColor(node.left) == RED && getColor(node.left.left) == RED) rotateRight(node);
        if (getColor(node.left) == RED && getColor(node.right) == RED) flipColor(node);

        return node;
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
        RedBlackTree RBT= new RedBlackTree<Integer, String>();
        RBT.insert(2, "Violet");
        RBT.insert(1, "May");
        RBT.insert(3, "Alexander");
        RBT.print();
    }
}
