package nbrenwald.portfolio.java;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SorterTest {
  /*
   * In general we will be testing empty arrays, arrays with 1 element, arrays fully sorted asc, arrays fully sorted desc and then some big random lists.
   */
	
	
	private static final int SIZE = 100;
	private static final int[] EMPTY_ARRAY = new int[0];
	private static final int[] NULL_ARRAY = null;
	private static final int[] SORTED_ASCENDING_ARRAY = {1,2,3,4,5,6,7,8,9,10};
	private static final int[] SORTED_DESCENDING_ARRAY = {1,2,3,4,5,6,7,8,9,10};
	
	private static final int[] UNSORTED_ARRAY = new int[SIZE];
	
	@Before
	public void setupTestArrays(){
		
		for(int i =0; i<SIZE; i++){
		  UNSORTED_ARRAY[i]=(int) (Math.random()*10);
		}
		
	}
	
	@Test                   
	public void sortNullArray() {
		Sorter.bubbleSort(NULL_ARRAY);
		Sorter.insertionSort(NULL_ARRAY);
		Sorter.selectionSort(NULL_ARRAY);
		Sorter.mergeSort(NULL_ARRAY);
		Sorter.quickSort(NULL_ARRAY, 0, 0);
		Sorter.countingSort(NULL_ARRAY);
		assertNull(NULL_ARRAY);
	}
	
	@Test                   
    public void sortEmptyArray() {
        Sorter.bubbleSort(EMPTY_ARRAY);
        Sorter.insertionSort(EMPTY_ARRAY);
        Sorter.selectionSort(EMPTY_ARRAY);
        Sorter.mergeSort(EMPTY_ARRAY);
        Sorter.quickSort(EMPTY_ARRAY, 0, 0);
        Sorter.countingSort(EMPTY_ARRAY);
        assertEquals(EMPTY_ARRAY.length, 0);
    }
	
	@Test                   
    public void sortSortedAscendingArray() {
	  
	  int[] tmp= Arrays.copyOf(SORTED_ASCENDING_ARRAY,SORTED_ASCENDING_ARRAY.length);
        Sorter.bubbleSort(tmp);
        assertTrue(Arrays.equals(tmp, SORTED_ASCENDING_ARRAY));

        tmp = Arrays.copyOf(SORTED_ASCENDING_ARRAY,SORTED_ASCENDING_ARRAY.length);
        Sorter.insertionSort(tmp);
        assertTrue(Arrays.equals(tmp, SORTED_ASCENDING_ARRAY));
        
        tmp = Arrays.copyOf(SORTED_ASCENDING_ARRAY,SORTED_ASCENDING_ARRAY.length);
        Sorter.selectionSort(tmp);
        assertTrue(Arrays.equals(tmp, SORTED_ASCENDING_ARRAY));

        tmp = Arrays.copyOf(SORTED_ASCENDING_ARRAY,SORTED_ASCENDING_ARRAY.length);
        Sorter.mergeSort(tmp);
        assertTrue(Arrays.equals(tmp, SORTED_ASCENDING_ARRAY));

        tmp = Arrays.copyOf(SORTED_ASCENDING_ARRAY,SORTED_ASCENDING_ARRAY.length);
        Sorter.quickSort(tmp, 0, 0);
        assertTrue(Arrays.equals(tmp, SORTED_ASCENDING_ARRAY));

        tmp = Arrays.copyOf(SORTED_ASCENDING_ARRAY,SORTED_ASCENDING_ARRAY.length);
        Sorter.countingSort(tmp);
        assertTrue(Arrays.equals(tmp, SORTED_ASCENDING_ARRAY));
    }
	
	@Test                   
    public void sortSortedDescendingArray() {
      int[] tmp;
      tmp = Arrays.copyOf(SORTED_DESCENDING_ARRAY,SORTED_DESCENDING_ARRAY.length);
        Sorter.bubbleSort(tmp);
        assertTrue(Arrays.equals(tmp, SORTED_ASCENDING_ARRAY));

        tmp = Arrays.copyOf(SORTED_DESCENDING_ARRAY,SORTED_DESCENDING_ARRAY.length);
        Sorter.insertionSort(tmp);
        assertTrue(Arrays.equals(tmp, SORTED_ASCENDING_ARRAY));
        
        tmp = Arrays.copyOf(SORTED_DESCENDING_ARRAY,SORTED_DESCENDING_ARRAY.length);
        Sorter.selectionSort(tmp);
        assertTrue(Arrays.equals(tmp, SORTED_ASCENDING_ARRAY));

        tmp = Arrays.copyOf(SORTED_DESCENDING_ARRAY,SORTED_DESCENDING_ARRAY.length);
        Sorter.mergeSort(tmp);
        assertTrue(Arrays.equals(tmp, SORTED_ASCENDING_ARRAY));

        tmp = Arrays.copyOf(SORTED_DESCENDING_ARRAY,SORTED_DESCENDING_ARRAY.length);
        Sorter.quickSort(tmp, 0, 0);
        assertTrue(Arrays.equals(tmp, SORTED_ASCENDING_ARRAY));

        tmp = Arrays.copyOf(SORTED_DESCENDING_ARRAY,SORTED_DESCENDING_ARRAY.length);
        Sorter.countingSort(tmp);
        assertTrue(Arrays.equals(tmp, SORTED_ASCENDING_ARRAY));
    }
	
	
	@Test                   
    public void sortArray() {
	  int[] tmpSorted = Arrays.copyOf(UNSORTED_ARRAY,UNSORTED_ARRAY.length);
      Arrays.sort(tmpSorted);
      //tmpSorted contains a sorted copy of UNSORTED_ARRAY, we will use this for our tests
      
      
      int[] tmp = Arrays.copyOf(UNSORTED_ARRAY,UNSORTED_ARRAY.length);
        Sorter.bubbleSort(tmp);
        assertTrue(Arrays.equals(tmp, tmpSorted));

        tmp = Arrays.copyOf(UNSORTED_ARRAY,UNSORTED_ARRAY.length);
        Sorter.insertionSort(tmp);
        assertTrue(Arrays.equals(tmp, tmpSorted));
        
        tmp = Arrays.copyOf(UNSORTED_ARRAY,UNSORTED_ARRAY.length);
        Sorter.selectionSort(tmp);
        assertTrue(Arrays.equals(tmp, tmpSorted));

        tmp = Arrays.copyOf(UNSORTED_ARRAY,UNSORTED_ARRAY.length);
        Sorter.mergeSort(tmp);
        assertTrue(Arrays.equals(tmp, tmpSorted));

        tmp = Arrays.copyOf(UNSORTED_ARRAY,UNSORTED_ARRAY.length);
        Sorter.quickSort(tmp, 0, UNSORTED_ARRAY.length-1);
        assertTrue(Arrays.equals(tmp, tmpSorted));

        tmp = Arrays.copyOf(UNSORTED_ARRAY,UNSORTED_ARRAY.length);
        Sorter.countingSort(tmp);
        assertTrue(Arrays.equals(tmp, tmpSorted));
    }
	
	
	private static boolean isSorted(int[] inArray){
		for(int i =0; i<inArray.length-1;i++){
			if(inArray[i+1] < inArray[i])return false;
		}
		return true;
	}
	

}
