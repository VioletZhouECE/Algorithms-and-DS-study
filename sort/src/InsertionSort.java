public class InsertionSort {
    public static void insertion_sort(int[] array){
        for (int i=1; i<array.length; i++){
            int key = array[i];
            int j = i-1;
            while (j>=0 && array[j] > key){
                //move array[j] to the right
                array[j+1] = array[j];
                j--;
            }
            //place key in the correct position
            array[j+1] = key;
        }
    }

    public static void main(String[] args){
        int[] test_array1 = new int[] {2,3,4,1,7,0};
        int[] test_array2 = new int[] {2,2,2,2,1,1};
        int[] test_array3 = new int[] {};
        int[] test_array4 = new int[] {1};
        insertion_sort(test_array1);
        for (Integer item: test_array1){
            System.out.println(item);
        }
    }
}
