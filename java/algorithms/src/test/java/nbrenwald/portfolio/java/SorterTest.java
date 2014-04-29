package nbrenwald.portfolio.java;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SorterTest {
  /*
   * In general we will be testing empty arrays, arrays with 1 element, arrays fully sorted asc,
   * arrays fully sorted desc and then some big random lists.
   */


  private static final int SIZE = 100;
  private static final int[] EMPTY_ARRAY = new int[0];
  private static final int[] NULL_ARRAY = null;
  private static final int[] SORTED_ASCENDING_ARRAY = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
  private static final int[] SORTED_DESCENDING_ARRAY = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

  private static final int[] UNSORTED_ARRAY = new int[SIZE];
  private static int[] sortedArray = new int[SIZE];

  @BeforeClass
  public static void setupTestArrays() {

    for (int i = 0; i < SIZE; i++) {
      UNSORTED_ARRAY[i] = (int) (Math.random() * 10);
    }

    sortedArray = Arrays.copyOf(UNSORTED_ARRAY, UNSORTED_ARRAY.length);
    Arrays.sort(sortedArray);

  }

  @Test
  public void sortNullArray() {
    Sorter.bubbleSort(NULL_ARRAY);
    Sorter.insertionSort(NULL_ARRAY);
    Sorter.selectionSort(NULL_ARRAY);
    Sorter.mergeSort(NULL_ARRAY);
    Sorter.heapSort(NULL_ARRAY);
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
    Sorter.heapSort(EMPTY_ARRAY);
    Sorter.quickSort(EMPTY_ARRAY, 0, 0);
    Sorter.countingSort(EMPTY_ARRAY);
    assertEquals(EMPTY_ARRAY.length, 0);
  }

  @Test
  public void sortSortedAscendingArray() {

    int[] tmp = Arrays.copyOf(SORTED_ASCENDING_ARRAY, SORTED_ASCENDING_ARRAY.length);
    Sorter.bubbleSort(tmp);
    assertTrue(Arrays.equals(tmp, SORTED_ASCENDING_ARRAY));

    tmp = Arrays.copyOf(SORTED_ASCENDING_ARRAY, SORTED_ASCENDING_ARRAY.length);
    Sorter.insertionSort(tmp);
    assertTrue(Arrays.equals(tmp, SORTED_ASCENDING_ARRAY));

    tmp = Arrays.copyOf(SORTED_ASCENDING_ARRAY, SORTED_ASCENDING_ARRAY.length);
    Sorter.selectionSort(tmp);
    assertTrue(Arrays.equals(tmp, SORTED_ASCENDING_ARRAY));

    tmp = Arrays.copyOf(SORTED_ASCENDING_ARRAY, SORTED_ASCENDING_ARRAY.length);
    Sorter.mergeSort(tmp);
    assertTrue(Arrays.equals(tmp, SORTED_ASCENDING_ARRAY));
    
    tmp = Arrays.copyOf(SORTED_ASCENDING_ARRAY, SORTED_ASCENDING_ARRAY.length);
    Sorter.heapSort(tmp);
    assertTrue(Arrays.equals(tmp, SORTED_ASCENDING_ARRAY));

    tmp = Arrays.copyOf(SORTED_ASCENDING_ARRAY, SORTED_ASCENDING_ARRAY.length);
    Sorter.quickSort(tmp, 0, 0);
    assertTrue(Arrays.equals(tmp, SORTED_ASCENDING_ARRAY));

    tmp = Arrays.copyOf(SORTED_ASCENDING_ARRAY, SORTED_ASCENDING_ARRAY.length);
    Sorter.countingSort(tmp);
    assertTrue(Arrays.equals(tmp, SORTED_ASCENDING_ARRAY));
  }

  @Test
  public void sortSortedDescendingArray() {
    int[] tmp;
    tmp = Arrays.copyOf(SORTED_DESCENDING_ARRAY, SORTED_DESCENDING_ARRAY.length);
    Sorter.bubbleSort(tmp);
    assertTrue(Arrays.equals(tmp, SORTED_ASCENDING_ARRAY));

    tmp = Arrays.copyOf(SORTED_DESCENDING_ARRAY, SORTED_DESCENDING_ARRAY.length);
    Sorter.insertionSort(tmp);
    assertTrue(Arrays.equals(tmp, SORTED_ASCENDING_ARRAY));

    tmp = Arrays.copyOf(SORTED_DESCENDING_ARRAY, SORTED_DESCENDING_ARRAY.length);
    Sorter.selectionSort(tmp);
    assertTrue(Arrays.equals(tmp, SORTED_ASCENDING_ARRAY));

    tmp = Arrays.copyOf(SORTED_DESCENDING_ARRAY, SORTED_DESCENDING_ARRAY.length);
    Sorter.mergeSort(tmp);
    assertTrue(Arrays.equals(tmp, SORTED_ASCENDING_ARRAY));
    
    tmp = Arrays.copyOf(SORTED_DESCENDING_ARRAY, SORTED_DESCENDING_ARRAY.length);
    Sorter.heapSort(tmp);
    assertTrue(Arrays.equals(tmp, SORTED_ASCENDING_ARRAY));

    tmp = Arrays.copyOf(SORTED_DESCENDING_ARRAY, SORTED_DESCENDING_ARRAY.length);
    Sorter.quickSort(tmp, 0, 0);
    assertTrue(Arrays.equals(tmp, SORTED_ASCENDING_ARRAY));

    tmp = Arrays.copyOf(SORTED_DESCENDING_ARRAY, SORTED_DESCENDING_ARRAY.length);
    Sorter.countingSort(tmp);
    assertTrue(Arrays.equals(tmp, SORTED_ASCENDING_ARRAY));
  }

  @Test
  public void testBubbleSort() {
    int[] tmp = Arrays.copyOf(UNSORTED_ARRAY, UNSORTED_ARRAY.length);
    Sorter.insertionSort(tmp);
    assertTrue(Arrays.equals(tmp, sortedArray));
  }

  @Test
  public void testInsertionSort() {
    int[] tmp = Arrays.copyOf(UNSORTED_ARRAY, UNSORTED_ARRAY.length);
    Sorter.insertionSort(tmp);
    assertTrue(Arrays.equals(tmp, sortedArray));
  }

  @Test
  public void testSelectionSort() {
    int[] tmp = Arrays.copyOf(UNSORTED_ARRAY, UNSORTED_ARRAY.length);
    Sorter.insertionSort(tmp);
    assertTrue(Arrays.equals(tmp, sortedArray));
  }

  @Test
  public void testQuickSort() {
    int[] tmp = Arrays.copyOf(UNSORTED_ARRAY, UNSORTED_ARRAY.length);
    Sorter.quickSort(tmp, 0, UNSORTED_ARRAY.length - 1);
    assertTrue(Arrays.equals(tmp, sortedArray));
  }
  
  @Test
  public void testHeapSort() {
    int[] tmp = Arrays.copyOf(UNSORTED_ARRAY, UNSORTED_ARRAY.length);
    Sorter.heapSort(tmp);
    assertTrue(Arrays.equals(tmp, sortedArray));
  }
  
  @Test
  public void testHeapify() {
    int[] tmp = Arrays.copyOf(UNSORTED_ARRAY, UNSORTED_ARRAY.length);
    Sorter.heapify(tmp);
    assertTrue(isHeap(tmp));
  }

  @Test
  public void testMergeSort() {
    int[] tmp = Arrays.copyOf(UNSORTED_ARRAY, UNSORTED_ARRAY.length);
    Sorter.mergeSort(tmp);
    assertTrue(Arrays.equals(tmp, sortedArray));
  }

  @Test
  public void testCountingSort() {
    int[] tmp = Arrays.copyOf(UNSORTED_ARRAY, UNSORTED_ARRAY.length);
    Sorter.countingSort(tmp);
    assertTrue(Arrays.equals(tmp, sortedArray));
  }
  
  private boolean isHeap(int[] inArray) {
    // Check the representation invariant.
    // Start with the leaves, check that all parents are large, then check parents etc.
    int parentIndex;
    for (int i = inArray.length-1; i > 0; i--) {
      parentIndex = (i - 1) / 2;
      if (inArray[parentIndex] < inArray[i]) {
        System.out.println("Parent Index = " + parentIndex + " index = " + i);
        return false;
      }
    }
    return true;

  }



}
