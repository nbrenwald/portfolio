package nbrenwald.portfolio.java;

public class Sorter {

  public static void bubbleSort(int[] inArray) {
    // iterate over list, let large elements float to the right.
    if (inArray != null) {
      if (inArray.length > 1) {
        // Only run algorithm if the array is not null, not empty, and has more than 1 element
        for (int i = inArray.length - 1; i > 0; i--) {
          for (int j = 0; j < i; j++) {
            if (inArray[j] > inArray[j + 1]) {
              int tmp = inArray[j];
              inArray[j] = inArray[j + 1];
              inArray[j + 1] = tmp;
            }
          }
        }
      }
    }
  }

  public static void insertionSort(int[] inArray) {
    // select each element in turn and insert into a sorted left hand array.

    if (inArray != null) {
      if (inArray.length > 1) {

        for (int i = 1; i < inArray.length; i++) {
          int tmp = inArray[i]; // tmp holds the element we are trying to insert into the sorted
                                // lefthand array.
          int j = i - 1;// j points to the last element of the left hand sorted array
          while (j >= 0 && tmp < inArray[j]) {
            inArray[j + 1] = inArray[j];// move right element j
            j--;
          }
          inArray[j + 1] = tmp;
        }
      }
    }
  }

  public static void selectionSort(int[] inArray) {
    if (inArray != null && inArray.length > 1) {
      for (int i = 0; i < inArray.length; i++) {// i points to start of unsorted list
        int min = i;
        for (int j = i; j < inArray.length; j++) {
          if (inArray[j] < inArray[min]) {
            min = j;
          }
        }
        int tmp = inArray[i];
        inArray[i] = inArray[min];
        inArray[min] = tmp;
      }
    }
  }

  public static void mergeSort(int[] inArray) {
    // Divide and conquer.
    // As usual, make sure list is not null, non empty and longer than 1.
    if (inArray != null && inArray.length > 1) {
      // Top Down. Split the list in the middle. Call Merge Sort on the left. Call MergeSort on the
      // right.
      // and merge the results together.
      int middle = inArray.length / 2;
      int[] lhs = new int[middle];
      int[] rhs = new int[inArray.length - middle]; // check i'm not off by one.
      for (int i = 0; i < middle; i++) {
        lhs[i] = inArray[i];
      }
      for (int i = 0; i < inArray.length - middle; i++) {
        rhs[i] = inArray[middle + i];
      }

      mergeSort(lhs);
      mergeSort(rhs);

      // now merge back contents of lhs and rhs into inArray;
      int i = 0, j = 0;
      for (int m = 0; m < inArray.length; m++) {
        if (i == lhs.length) {
          inArray[m] = rhs[j];
          j++;
        } else if (j == rhs.length) {
          inArray[m] = lhs[i];
          i++;
        } else if (lhs[i] <= rhs[j]) {
          inArray[m] = lhs[i];
          i++;
        } else {
          inArray[m] = rhs[j];
          j++;
        }
      }
    }
  }

  public static void quickSort(int[] arrayToSort, int[] tempArray, int start, int length) {
    // Inefficient quicksort algo using O(n) extra space for the tempArray.
    // start is the starting position of our array to sort.
    // length gives us how many elements are contained.
    System.out.print("Calling quickSort current Array = ");
    printArray(arrayToSort, 0, arrayToSort.length);
    System.out.print("Section To Sort = ");
    printArray(arrayToSort, start, length);
    System.out.println("Start = " + start);
    System.out.println("Length = " + length);

    if (arrayToSort != null && length > 1) { // if array is empty, or 1 element, then its sorted.


      // Pick pivot
      int pivotIndex = (length / 2) + start;
      int pivotValue = arrayToSort[pivotIndex];
      System.out.println("Pivot = " + pivotValue);

      // Now the partition, working with a pointer working from start called lessPointer and a
      // pointer
      // starting from the beginning called morePointer
      int lessPointer = start;
      int morePointer = start + length - 1;
      System.out.println("Less Pointer = " + lessPointer);
      System.out.println("More Pointer = " + morePointer);
      for (int i = start; i <= start + length - 1; i++) {
        if (arrayToSort[i] < pivotValue) {
          tempArray[lessPointer] = arrayToSort[i];
          lessPointer++;// because we are incrementing, we may have to subtract 1 later to move back
                        // to the end
        } else if (arrayToSort[i] > pivotValue) {
          tempArray[morePointer] = arrayToSort[i];
          morePointer--;// because we are decrementing, we may have to subtract 1 later to move back
                        // to the end
        }
      }
      System.out.print("Partitioned Array = ");
      printArray(tempArray, start, length);

      // Lets copy items less than pivot into the correct place
      for (int i = start; i < lessPointer; i++) {
        arrayToSort[i] = tempArray[i];
      }
      // lets copy in the pivot
      arrayToSort[lessPointer] = pivotValue;

      // lets copy in values greater than pivot
      for (int i = morePointer + 1; i < start + length; i++) {
        arrayToSort[i] = tempArray[i];
      }


      System.out.print("Original Array after copy = ");
      printArray(arrayToSort, start, length);
      System.out.println("Less Pointer = " + lessPointer);
      System.out.println("More Pointer = " + morePointer);
      quickSort(arrayToSort, tempArray, start, lessPointer - start);
      quickSort(arrayToSort, tempArray, morePointer + 1, (start + length) - (morePointer + 1));


    }

  }

  private static void printArray(int[] inArray, int start, int length) {
    for (int i = start; i < start + length; i++) {
      System.out.print(inArray[i] + " , ");
    }
    System.out.println();
  }


}
