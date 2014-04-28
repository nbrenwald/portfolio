package nbrenwald.portfolio.java;

public class Heap {
  // Parent  = i-1/2
  // left child = i*2+1
  // right child = i*2+2
  
  private int[] array;
  private int endPointer;
  
  private Heap(){
  }
  
  
  private Heap(int[] inArray, int i) {
    array = inArray;
    endPointer = i;
    
    
  }


  public static Heap emptyHeap(){
    return new Heap();
  }
  
  private static void heapify(int[] inArray, int endPointer){
    System.out.println(endPointer);
    print(inArray);
    // start with right most subtree. 
    for(int i = (endPointer-1/2); i>=0; i--){
      // call siftdown
      int currentNode = i;
      int leftChild = 2*i+1;
      int rightChild = 2*i+1;
      while(leftChild <= endPointer){
        if(inArray[currentNode]<inArray[leftChild]){
          // swap current with left, then set current to be the left child
          int tmp = inArray[leftChild];
          inArray[leftChild]=inArray[currentNode];
          inArray[currentNode]=tmp;
          currentNode = leftChild;
          leftChild = 2*currentNode+1;
          rightChild = 2*currentNode+2;
        }
        else if (rightChild <=endPointer && inArray[currentNode]<inArray[rightChild]){
       // swap current with left, then set current to be the left child
          int tmp = inArray[rightChild];
          inArray[rightChild]=inArray[currentNode];
          inArray[currentNode]=tmp;
          currentNode = leftChild;
          leftChild = 2*currentNode+1;
          rightChild = 2*currentNode+2;
        }
        else {
          break;
        }
      }
    }
  }

  
  
  public static Heap fromArray(int[] inArray){
    // Static Factory to return a heap
    // Lets clone the array given to us, allowing twice the length for growth
    int[] newArray = new int[2*inArray.length];
    int newEndPointer = inArray.length-1;
    
    for(int i = 0 ; i<inArray.length; i++){
      newArray[i] = inArray[i];
    }
    Heap.heapify(newArray,newEndPointer);
    
    Heap newHeap = new Heap(newArray,newEndPointer);
    return newHeap;
    
  }
  
  
  public int max(){
    return array[0];
  }
  
  public boolean checkRI(){
    // Check the representation invariant.
    // Start with the leaves, check that all parents are large, then check parents etc.
    int parentIndex;
    for(int i = endPointer; i>0; i--){
      parentIndex=(endPointer-1)/2;
      if(array[parentIndex] < array[i]) return false;
    }
    return true;
    
  }
  
  private static void print(int[] inArray){
    for(int i : inArray){
      System.out.print(i+", ");
    }
    System.out.println();
  }

}
