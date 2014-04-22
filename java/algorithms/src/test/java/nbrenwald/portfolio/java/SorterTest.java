package nbrenwald.portfolio.java;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SorterTest {
	
	
	private static final int SIZE = 100;
	private static final int[] EMPTY_ARRAY = new int[0];
	private static final int[] NULL_ARRAY = null;
	
	private int[] unsortedArray;
	private int[] sortedArray;
	
	@Before
	public void setupTestArrays(){
		unsortedArray = new int[SIZE];
		sortedArray = new int[SIZE];
		
		for(int i =0; i<SIZE; i++){
			sortedArray[i]=i;
			unsortedArray[i]=SIZE-i;
		}
	}
	
	@Test                   
	public void bubbleSortWithNullArray() {
		Sorter.bubbleSort(NULL_ARRAY);
		assertNull(NULL_ARRAY);
	}
	
	@Test
	public void bubbleSortWithEmptyArray() {
		Sorter.bubbleSort(EMPTY_ARRAY);
		assertEquals(EMPTY_ARRAY.length, 0);
	}

	@Test
	public void bubbleSortWithSortedArray() {
		Sorter.bubbleSort(sortedArray);
		assertTrue(isSorted(sortedArray));
	}
	
	@Test
	public void bubbleSortWithUnsortedArray() {
		Sorter.bubbleSort(unsortedArray);
		assertTrue(isSorted(unsortedArray));
	}
	
	@Test
	public void insertionSortWithUnsortedArray() {
		Sorter.insertionSort(unsortedArray);
		assertTrue(isSorted(unsortedArray));
	}
	
	private static boolean isSorted(int[] inArray){
		for(int i =0; i<inArray.length-1;i++){
			if(inArray[i+1] < inArray[i])return false;
		}
		return true;
	}

}
