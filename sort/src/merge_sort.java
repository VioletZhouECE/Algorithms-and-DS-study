class MergeSort {
    /* Notes:
     * 1. The use of aux array avoids creating a new array each time in the recursion call
     * 2. We need to check if m and n "overflow" before comparing the magnitude of aux[m] and aux[n]
     */
    private static void merge(int start, int mid, int end, int[] array, int[] aux){
        //copy array to aux array
        for (int i=start; i<=end; i++){
            aux[i] = array[i];
        }

        int m = start;
        int n = mid+1;
        for (int i=start; i<=end; i++){
            if (m>mid) array[i] = aux[n++];
            else if (n>end) array[i] = aux[m++];
            else if (aux[m] > aux[n]) array[i] = aux[n++];
            else array[i] = aux[m++];
        }
    }

    public static void mergesort(int start, int end, int[] array, int[] aux){
        if (start==end) return;
        int mid = start + (end-start)/2;
        mergesort(start, mid, array, aux);
        mergesort(mid+1, end, array, aux);
        merge(start, mid, end, array, aux);
    }

    public static void main(String[] args){
        int[] test_array1 = {2,3,4,0,7,2};
        int[] aux_1 = new int[test_array1.length];
        int[] test_array2 = {2,2,2,2,1,1};
        int[] aux_2 = new int[test_array2.length];
        mergesort(0, test_array2.length-1, test_array2, aux_2);
        for (int num : test_array2){
            System.out.println(num);
        }
    }
}
