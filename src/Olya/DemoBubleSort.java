package Olya;
import java.util.Arrays;

public class DemoBubleSort {
    public static void main (String [] args) {
        int [] array = {9, 5, 4, 16, 7};
        System.out.println(Arrays.toString(array));

        boolean sortArray = false;
        while (!sortArray) {
            sortArray=true;
            for (int i=0; i<array.length-1; i++) {
                if (array [i] > array [i+1]) {
                    sortArray = false;
                    int temp = array [i];
                    array [i] = array [i+1];
                    array [i+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));

    }
}
