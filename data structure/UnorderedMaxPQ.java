/*
* Note that the array index starts from 1 (0 is not used)
* This is to guarantee that the following important relationship is always true:
* child1 = parent*2; child2 = parent*2+1; parent = child/2
* */
class UnorderedMaxPQ<Key extends Comparable<Key>>{

    Key[] keys;
    int size;

    public UnorderedMaxPQ(int capacity){
        //use casting to create a generic keys array
        this.keys = (Key[]) new Comparable[capacity+1];
        this.size = 0;
    }

    /*
    * construct a max heap from an unordered array in linear time
    * this is more efficient than inserting all the array items one by one by using insert()
    * */
    public UnorderedMaxPQ(Key[] array){
        this.keys = (Key[]) new Comparable[array.length+1];

        //copy the array
        for (int i=0; i<array.length; i++){
            this.keys[i+1] = array[i];
        }
        this.size = array.length;

        //sink to build the max heap, starting from the parent node of the last node
        for (int i=this.size/2; i>=1; i--){
            this.sink(i);
        }
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    /*
    * swim up: exchange the node with its parent node if the node is greater than its parent node
    * */
    private void swim(int node){
        //we need node/2 > 0 here to stop the loop and prevent the unused keys[0] from being accessed
        while (node/2 > 0){
            //break if the node is smaller than its parent node
            if (this.keys[node].compareTo(this.keys[node/2])<=0) break;
            //otherwise exchange the node with its parent
            this.exch(node, node/2, this.keys);
            node = node/2;
        }
    }

    /*
     * sink down: exchange the node with its LARGER child node if the node is smaller than ANY of its child node
     * */
    private void sink(int node){
        while (node*2 <= this.size){
            int largerChild = node * 2;
            if (node*2+1 <= this.size && this.keys[node*2+1].compareTo(this.keys[largerChild])>0) {
                largerChild = node*2+1;
            }
            //break if the node is greater than both of its child node
            if (this.keys[node].compareTo(this.keys[largerChild]) > 0) break;
            //otherwise exchange node with its LARGER child
            this.exch(node, largerChild, this.keys);
            node = largerChild;
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
        this.swim(this.size);
    }

    public Key delMax(){
        if (this.size == 0) throw new IllegalStateException("Cannot delete from an empty PQ");
        // delete the head node by replacing it with the last node
        Key max = this.keys[1];
        this.exch(1, this.size, this.keys);
        //set the last node to null to prevent loitering: when there is no reference to key, it can be garbage collected
        this.keys[size--] = null;
        //sink: rsstore the order of the binary heap
        this.sink(1);
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
