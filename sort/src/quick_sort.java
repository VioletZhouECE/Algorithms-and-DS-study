class QuickSort {
    public static int partition(int start, int end, int[] array){
        if (start==end) return start;

        // choose the first item as the pivot point
        int pivot = array[start];
        int lo = start;
        int hi = end+1;

        while(true){
            while(array[++lo] < pivot) {
                if (lo == end) break;
            }

            while(array[--hi] > pivot) {
                if (hi == start) break;
            }

            if (hi <= lo) break;
            exch(lo, hi, array);
        }

        //place the pivot in the correct position of the array by exchanging it with hi
        exch(start, hi, array);

        return hi;
    }

    private static void exch(int x, int y, int[] array){
        int x_val = array[x];
        array[x] = array[y];
        array[y] = x_val;
    }

    /* quick sort an array in place */
    public static void quicksort(int start, int end, int[] array){
        if (start>=end) return;
        int pivot = partition(start, end, array);
        quicksort(start, pivot-1, array);
        quicksort(pivot+1, end, array);
    }

    public static void main(String[] args){
        int[] test_array1 = {2,3,4,0,7,2};
        //partition(0, test_array1.length-1, test_array1);
        quicksort(0, test_array1.length-1, test_array1);
        for (int num : test_array1){
            System.out.println(num);
        }
    }
}
