public class HashTableLinearProbing<Key, Value> {
    int size;
    int capacity;
    Key[] keys;
    Value[] values;

    public HashTableLinearProbing(int inputSize){
        this.size = 0;
        this.capacity = inputSize;
        this.keys = (Key[]) new Object[inputSize];
        this.values = (Value[]) new Object[inputSize];
    }

    public void put(Key key, Value value){
        if (key==null || value==null) throw new IllegalArgumentException("key and value cannot be null");
        if (this.size==this.capacity) throw new IllegalStateException("The hash table is full");

        int i = hash(key);
        while (this.keys[i]!=null){
            i = (i+1)%this.capacity;
        }
        this.keys[i] = key;
        this.values[i] = value;
        this.size++;

        //double the arrays if they are more than half full
        if (this.size>this.capacity/2) resize(2*this.capacity);
    }

    public Value search(Key key){
        if (key==null) throw new IllegalArgumentException("search key cannot be null");

        int i = hash(key);
        while(this.keys[i]!=null){
            if (this.keys[i].equals(key)) return this.values[i];
            i = (i+1)%this.capacity;
        }
        return null;
    }

    public void delete(Key key){
        if (key==null) throw new IllegalArgumentException("key cannot be null");

        int i = hash(key);
        while(this.keys[i]!=null){
            if (this.keys[i].equals(key)) {
                this.keys[i] = null;
                this.values[i] = null;
                this.size--;

                //halve the arrays if they are less than 1/8 full
                if(this.size<this.capacity/8) resize(this.capacity/2);
            };
            i = (i+1)%this.capacity;
        }
    }

    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff)%this.capacity;
    }

    /*
    * resize keys array and values array to the new Length
    * We want to keep both arrays between 1/8 full and half full for better performance
    * as the runtime grows dramatically once the array is more than half full
    * */
    private void resize(int newLength){
        Key[] newKeys = (Key[]) new Object[newLength];
        Value[] newValues = (Value[]) new Object[newLength];
        Key[] oldKeys = this.keys;
        Value[] oldValues = this.values;

        this.size = 0;
        this.capacity = newLength;
        this.keys = newKeys;
        this.values = newValues;

        //hash all the old keys, values into the new arrays
        for (int i=0; i<oldKeys.length; i++) {
            if (oldKeys[i] != null) {
                this.put(oldKeys[i],oldValues[i]);
            }
        }
    }

    public void print() {
        System.out.println("Size: " + this.size + " Capacity: " + this.capacity);
        for (int i = 0; i < this.keys.length; i++) {
            System.out.print("key: " + this.keys[i] + " ");
            System.out.print("value: " + this.values[i]);
            System.out.println(" ");
        }
        System.out.println("\n");
    }

    public static void main(String[] args){
        HashTableLinearProbing<String, String> hashTable = new HashTableLinearProbing<>(8);
        hashTable.put("Xinyi", "19");
        hashTable.print();
        hashTable.put("Eve", "20");
        hashTable.print();
        hashTable.put("Julia", "27");
        hashTable.print();
        hashTable.put("Zhou", "19");
        hashTable.print();
        hashTable.put("Liam", "20");
        hashTable.print();
        hashTable.put("Tang", "27");
        hashTable.print();
        System.out.println(hashTable.search("David"));
        System.out.println(hashTable.search("Xinyi"));
        System.out.println(hashTable.search("Evelyn"));
        hashTable.delete("Xinyi");
        hashTable.print();
        hashTable.delete("Eve");
        hashTable.print();
        hashTable.delete("Julia");
        hashTable.print();
        hashTable.delete("Zhou");
        hashTable.print();
        hashTable.delete("Liam");
        hashTable.print();
        hashTable.delete("Tang");
        hashTable.print();
        System.out.println(hashTable.search("Xinyi"));
    }
}
