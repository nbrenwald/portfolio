package nbrenwald.portfolio.java;
public class Heap<T extends Comparable<T>>{
private int capacity=16;
private T[] heap;
private int end = 0;

public Heap(){
  heap = (T[]) new Comparable[capacity];
}

public void resize(int newCapacity){
}

public void insert(T t){
 heap[end]=t;
 siftUp(end);
 end++;
 if(!checkHeap(0)) throw new IllegalStateException("extract min broke heap property");
}

public boolean contains(T t){
 return false;
}

public T extractMin(){
 if(end==0)return null;
 T value = heap[0];
 heap[0]=heap[end-1];
 end--;
 siftDown(0);
 if(!checkHeap(0)) throw new IllegalStateException("extract min broke heap property");
 return value;
}

private boolean checkHeap(int index){
int left = (index*2)+1;
int right = (index*2)+2;
if(left >=end && right >=end)return true;
if(left < end && heap[left].compareTo(heap[index]) < 0)return false;
if(right < end && heap[right].compareTo(heap[index]) < 0)return false;
return checkHeap(left) & checkHeap(right);
}

public T getMin(){
  return (end==0)?null:heap[0];
}

private void siftDown(int index){
//called after extractMin
int indexOfSmallest = index;
int left = (index*2)+1;
int right = (index*2)+2;
if(left<end && heap[left].compareTo(heap[index]) < 0) indexOfSmallest = left;
if(right<end && heap[right].compareTo( heap[indexOfSmallest] )< 0)indexOfSmallest = right;
 if(indexOfSmallest != index){
  T tmp = heap[index];
  heap[index] = heap[indexOfSmallest];
  heap[indexOfSmallest] = tmp;
  siftDown(indexOfSmallest);
 }
}

private void siftUp(int index){
int parent = (index-1)/2;
 if(parent>=0 && heap[parent].compareTo( heap[index]) >0){
  T tmp = heap[parent];
  heap[parent] = heap[index];
  heap[index] = tmp;
  siftUp(parent);
 }
}

@Override
public String toString(){
 String result = "Size = "+end+"\n";
 for(int i = 0 ; i < end ; i ++){
  result+= heap[i] +" : ";
 }
 return result;
}

public static void main(String[] args){
 Heap<Integer> h = new Heap<>();
 System.out.println(h);
 h.insert(5);
 System.out.println(h);
 h.insert(4);
 System.out.println(h);
 h.insert(3);
 System.out.println(h);
 h.insert(2);
 System.out.println(h);
 h.insert(1);
 System.out.println(h);
 System.out.println(h.extractMin());
 System.out.println(h);
System.out.println(h.extractMin());
 System.out.println(h);
System.out.println(h.extractMin());
 System.out.println(h);
System.out.println(h.extractMin());
 System.out.println(h);
System.out.println(h.extractMin());
 System.out.println(h);
System.out.println(h.extractMin());
 System.out.println(h);
}

}