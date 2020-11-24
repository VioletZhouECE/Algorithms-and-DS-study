class QuickSort {
    /*
    * Notes:
    * 1. Use of pre-increment makes sure that the final lo and hi take on the values that break the while condition
    * 2. We need to consider the special cases where i or j traverse all the way through the array
    * 3. The break condition is if (hi <= lo). Not if (hi < lo)
    * */
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

    /* Easier to implement but results in more exchanges
    * Notes:
    * 1. we need to check lo == hi (the last item needs to be examined), hence the while condition: while(lo<=hi)
    * 2. In this approach we "focus on" moving one pointer - i, so it's easier to think through :)
    * */
    public static int partition_easy(int start, int end, int[] array){
        if (start==end) return start;

        // choose the first item as the pivot point
        int pivot = array[start];
        int lo = start;
        int hi = end;

        while(lo<=hi){
            if (array[lo] <= pivot) lo++;
            else exch(lo, hi--, array);
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
        int[] test_array2 = {2,2,2,2,1,1};
        //partition(0, test_array1.length-1, test_array1);
        quicksort(0, test_array2.length-1, test_array2);
        for (int num : test_array2){
            System.out.println(num);
        }
    }
}
