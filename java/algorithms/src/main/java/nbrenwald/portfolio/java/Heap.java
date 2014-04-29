package nbrenwald.portfolio.java;

public class Heap {
  // Parent = i-1/2
  // left child = i*2+1
  // right child = i*2+2

  private int[] array;
  private int endPointer;

  private Heap() {}


  private Heap(int[] inArray, int i) {
    array = inArray;
    endPointer = i;
  }


  public static Heap emptyHeap() {
    return new Heap();
  }

  private static void siftDown(int[] inArray, int node, int end) {
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

  private static void heapify(int[] inArray, int endPointer) {
    // Starting with the right most parent node. Call siftDown on all higher nodes.
    // This is order O(n)
    for (int i = ((endPointer - 1) / 2); i >= 0; i--) {
      siftDown(inArray, i, endPointer);

    }
  }



  public static Heap fromArray(int[] inArray) {
    // Static Factory to return a heap
    // Lets clone the array given to us, allowing twice the length for growth
    int[] newArray = new int[2 * inArray.length];
    int newEndPointer = inArray.length - 1;

    for (int i = 0; i < inArray.length; i++) {
      newArray[i] = inArray[i];
    }
    Heap.heapify(newArray, newEndPointer);
    Heap newHeap = new Heap(newArray, newEndPointer);
    return newHeap;

  }


  public int max() {
    return array[0];
  }
  
  public void sort(){
    // The max is already at position 0. 
    // We can swap with this with the end point, then decrement the end point and call siftDown.
    int tmp = array[endPointer];
    array[endPointer] = array[0];
    array[0]=tmp;
    endPointer--;
    siftDown(array,0,endPointer);
    
  }

  public boolean checkRI() {
    // Check the representation invariant.
    // Start with the leaves, check that all parents are large, then check parents etc.
    int parentIndex;
    for (int i = endPointer; i > 0; i--) {
      parentIndex = (i - 1) / 2;
      if (array[parentIndex] < array[i]) {
        System.out.println("Parent Index = " + parentIndex + " index = " + i);
        return false;
      }
    }
    return true;

  }

  private static void print(int[] inArray) {
    for (int i : inArray) {
      System.out.print(i + ", ");
    }
    System.out.println();
  }

}
