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

  public static void quickSortSpaceInefficient(int[] inArray, int[] tempArray, int start, int length) {
    // Inefficient quicksort algo using O(n) extra space for the tempArray.
    // start is the starting position of our array to sort.
    // length gives us how many elements are contained.

    if (inArray != null && length > 1) { // if array is empty, or 1 element, then its sorted.


      // Pick pivot
      int pivotIndex = (length / 2) + start;
      int pivotValue = inArray[pivotIndex];

      // Now the partition, working with a pointer working from start called lessPointer and a
      // pointer
      // starting from the beginning called morePointer
      int lessPointer = start;
      int morePointer = start + length - 1;
      for (int i = start; i <= start + length - 1; i++) {
        if (inArray[i] < pivotValue) {
          tempArray[lessPointer] = inArray[i];
          lessPointer++;// because we are incrementing, we may have to subtract 1 later to move back
                        // to the end
        } else if (inArray[i] > pivotValue) {
          tempArray[morePointer] = inArray[i];
          morePointer--;// because we are decrementing, we may have to subtract 1 later to move back
                        // to the end
        }
      }
      // Lets copy items less than pivot into the correct place
      for (int i = start; i < lessPointer; i++) {
        inArray[i] = tempArray[i];
      }
      // lets copy in the pivot
      inArray[lessPointer] = pivotValue;

      // lets copy in values greater than pivot
      for (int i = morePointer + 1; i < start + length; i++) {
        inArray[i] = tempArray[i];
      }

      quickSortSpaceInefficient(inArray, tempArray, start, lessPointer - start);
      quickSortSpaceInefficient(inArray, tempArray, morePointer + 1, (start + length)
          - (morePointer + 1));
    }

  }

  public static void quickSort(int[] inArray, int startIndex, int endIndex) {
    // If length is less than 2 then there is nothing to do as the array is either empty or sorted.
    if (inArray != null && startIndex < endIndex) {
      int pivotIndex = partition(inArray, startIndex, endIndex);
      quickSort(inArray, startIndex, pivotIndex - 1);
      quickSort(inArray, pivotIndex + 1, endIndex);
    }
  }

  public static int partition(int[] inArray, int startIndex, int endIndex) {
    int pivotValue = inArray[startIndex];
    int i = startIndex;
    int j = endIndex;

    while (i < j) {
      while (inArray[i] <= pivotValue && i < endIndex) {
        i++;
      }
      while (inArray[j] > pivotValue && j > startIndex) {
        j--;
      }
      if (i < j) {
        int tmp = inArray[i];
        inArray[i] = inArray[j];
        inArray[j] = tmp;
      } else {
        // If we arrive here, we are either at the very start of the list as the smallest element
        // was there,
        // or we are at the end of the list was descending order,
        // or we maybe complete and its simply time to put the pivot into the correct place.
        inArray[startIndex] = inArray[j];
        inArray[j] = pivotValue;
        return j;
      }
    }
    return -1;
  }

  public static void heapify(int[] inArray) {
    // Starting with the right most parent node. Call siftDown on all higher nodes.
    // This is order O(n)
    int endPointer = inArray.length - 1;
    for (int i = ((endPointer - 1) / 2); i >= 0; i--) {
      siftDown(inArray, i, endPointer);

    }
  }

  public static void siftDown(int[] inArray, int node, int end) {
    // Check the left child and right child, swap the current node with the the largest child
    // which is greater than the current node.
    int leftChild = (2 * node) + 1;
    int rightChild = (2 * node) + 2;
    int largest = node;
    if (leftChild <= end && inArray[leftChild] > inArray[node]) {
      largest = leftChild;
    }

    if (rightChild <= end && inArray[rightChild] > inArray[largest]) {
      largest = rightChild;
    }

    if (largest != node) {
      int tmp = inArray[node];
      inArray[node] = inArray[largest];
      inArray[largest] = tmp;
      siftDown(inArray, largest, end);
    }
  }

  public static void heapSort(int[] inArray) {
    if (inArray != null && inArray.length > 1) {
      // First build a heap.
      heapify(inArray);

      for (int i = inArray.length - 1; i > 0; i--) {
        // Swap
        int tmp = inArray[0];
        inArray[0] = inArray[i];
        inArray[i] = tmp;
        // Sift Down new member
        siftDown(inArray, 0, i - 1);
      }
    }
  }



  public static void radixSort(int[] inArray) {

    if (inArray != null && inArray.length > 1) {
      // Step 1. Iterate through the array one time to find the max element. Cost O(n).
      int max = inArray[0];
      for (int i : inArray) {
        if (i > max) {
          max = i;
        }
      }

      int[] tempArray = new int[inArray.length];

      // We want to sort on least significant digit, then next and so on
      for (int i = 0; max >= Math.pow(10, i); i++) {
        int[] buckets = new int[10];

        // First we need to go through the array and update a counter based on the digit we are
        // working on
        for (int j = 0; j < inArray.length; j++) {
          // Move to correct place in radixArray

          buckets[((inArray[j] / (int) Math.pow(10, i)) % 10)]++;
        }

        // next calculate a running sum
        for (int j = 1; j < buckets.length; j++) {
          buckets[j] += buckets[j - 1];
        }

        // Now we can work backwards through the original array inArray.
        // For each element, we see where it would have been placed, we can then see how many
        // elements appear before it,
        // so we know its position in the output array tempArray.
        for (int j = inArray.length - 1; j >= 0; j--) {
          int tmpIndex = (inArray[j] / (int) Math.pow(10, i)) % 10;
          int howManySmaller = buckets[tmpIndex];
          buckets[tmpIndex] -= 1;
          tempArray[howManySmaller - 1] = inArray[j];
        }

        // Finally we copy back tempArray into the inArray, i.e. we have finished sorting on digit i
        for (int j = 0; j < inArray.length; j++) {
          inArray[j] = tempArray[j];
        }
      }
    }

  }

  public static void countingSort(int[] inArray) {
    // Works on inArray of n elements where all elements in the range {0,1,2...k}.
    // Could be suitable if we are sorting something like human age.
    if (inArray != null && inArray.length > 1) {
      // Step 1. Iterate through the array one time to find the max element. Cost O(n).
      int max = inArray[0];
      for (int i : inArray) {
        if (i > max) {
          max = i;
        }
      }

      // Now make a working array of length max. We use max + 1 to account for indexes starting at
      // position 0.
      int[] tempArray = new int[max + 1];

      // Initialize working array to 0. Cost O(max).
      for (int i = 0; i < tempArray.length; i++) {
        tempArray[i] = 0;
      }

      // Now iterate through inArray, counting occurrences cost O(n)
      for (int i : inArray) {
        tempArray[i]++;
      }

      // Now iterate through tempArray and write contents back into inArray. Cost O(max)
      int m = 0;
      for (int i = 0; i < tempArray.length; i++) {
        if (tempArray[i] != 0) {
          for (int j = 0; j < tempArray[i]; j++) {
            inArray[m] = i;
            m++;
          }
        }
      }

    }
  }

  // helper function for debugging
  public static void printArray(int[] inArray) {
    for (int i : inArray) {
      System.out.print(i + ", ");
    }
    System.out.println();

  }
}
