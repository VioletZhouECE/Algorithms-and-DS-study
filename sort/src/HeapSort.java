
public class HeapSort {
    public static void heapsort(Integer[] array){
        //build the max heap
        UnorderedMaxPQ<Integer> maxheap = new UnorderedMaxPQ<>(array.length);
        maxheap.UnorderedMaxPQFromArray(array);
        //delMax and restore the heap order
        for (int i=array.length-1; i>=0; i--){
            array[i] = maxheap.delMax();
        }
    }

    public static void main(String[] args){
        Integer[] test_array1 = new Integer[] {2,3,4,0,7,2};
        Integer[] test_array2 = new Integer[] {2,2,2,2,1,1};
        Integer[] test_array3 = new Integer[] {};
        Integer[] test_array4 = new Integer[] {1};
        heapsort(test_array4);
        for (Integer item: test_array4){
            System.out.println(item);
        }
    }
}
