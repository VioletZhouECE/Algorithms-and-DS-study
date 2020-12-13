class UnorderedMaxPQ<Key extends Comparable<Key>>{

    Key[] keys;
    int size;

    public UnorderedMaxPQ(int capacity){
        //use casting to create a generic keys array
        this.keys = (Key[]) new Comparable[capacity+1];
        this.size = 0;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    /*
    * swim up: exchange the node with its parent node if the node is greater than its parent node
    * */
    private void swim(){
        int node = this.size;
        int parent = node/2;

        //we need parent > 0 here to stop the loop and prevent the unused keys[0] from being accessed
        while (parent>0 && this.keys[node].compareTo(this.keys[parent])>0){
            this.exch(node, parent, this.keys);
            node = parent;
            parent = node/2;
        }
    }

    /*
     * sink down: exchange the node with its LARGER child node if the node is smaller than ANY of its child node
     * */
    private void sink(){
        int node = 1;
        int lchild = node * 2;
        int rchild = node * 2 + 1;

        while (lchild <= this.size){
            int largerChild = lchild;
            if (rchild <= this.size && this.keys[rchild].compareTo(this.keys[lchild])>0) {
                largerChild = rchild;
            }
            //return if the node is greater than both of its child node
            if (this.keys[node].compareTo(this.keys[largerChild]) > 0) return;
            //otherwise exchange node with its LARGER child
            this.exch(node, largerChild, this.keys);
            node = largerChild;
            lchild = node * 2;
            rchild = node * 2 + 1;
        }
    }

    private void exch(int x, int y, Key[] array) {
        Key x_val = array[x];
        array[x] = array[y];
        array[y] = x_val;
    }

    public void insert(Key x){
        if (x == null) throw new IllegalArgumentException("Cannot add null");
        if (this.size == keys.length-1) throw new IllegalStateException("cannot add to the PQ. Capacity exceeded");
        //add the key to the end of the PQ
        keys[++size] = x;
        //swim: restore the order of the binary heap
        this.swim();
    }

    public Key delMax(){
        if (this.size == 0) throw new IllegalStateException("Cannot delete from an empty PQ");
        // delete the head node by replacing it with the last node
        Key max = this.keys[1];
        this.exch(1, this.size, this.keys);
        //set the last node to null to prevent loitering
        this.keys[size--] = null;
        //sink: rsstore the order of the binary heap
        this.sink();
        return max;
    }

    public static void main(String[] args){
        //test code goes here
        UnorderedMaxPQ<Integer> pq = new UnorderedMaxPQ<>(5);
        pq.insert(1);
        pq.insert(3);
        pq.insert(2);
        System.out.println(pq.delMax());
        System.out.println(pq.delMax());
    }
}
