public class HashTableLinearProbing<Key, Value> {
    int size;
    int capacity;
    Key[] keys;
    Value[] values;

    public HashTableLinearProbing(int input_size){
        this.size = 0;
        this.capacity = input_size;
        this.keys = (Key[]) new Object[input_size];
        this.values = (Value[]) new Object[input_size];
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
            };
            i = (i+1)%this.capacity;
        }
    }

    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff)%this.capacity;
    }

    public static void main(String[] args){
        HashTableLinearProbing<String, String> hashTable = new HashTableLinearProbing<>(5);
        hashTable.put("Xinyi", "19");
        hashTable.put("Evelyn", "20");
        hashTable.put("Julia", "27");
        System.out.println(hashTable.search("David"));
        System.out.println(hashTable.search("Xinyi"));
        System.out.println(hashTable.search("Evelyn"));
        hashTable.delete("Xinyi");
        System.out.println(hashTable.search("Xinyi"));
    }
}
