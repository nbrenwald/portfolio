package nbrenwald.portfolio.interviews;
/**
 * A priority queue implemented using a binary min heap. The item with the lowest priority is in the
 * front of the queue.
 * 
 * @author Nicholas Brenwald
 * @version 1.1
 */
public class MinHeapPriorityQueue<E extends Comparable<E>> {
  /**
   * Stores the values used for determining an item's position in the queue.
   */
  private final E[] priorities;

  /**
   * A min binary heap where pq[0] is the item with the lowest value in priorities.
   */
  private final int[] pq;

  /**
   * An index giving an items position in the queue.
   */
  private final int[] pqIndex;

  private final int MAX_SIZE;

  /**
   * The current number of elements in the queue.
   */
  private int size;

  /**
   * Creates a new empty queue which can store up to <i>capacity</i> items.
   * 
   * @param capacity the max number of items to be stored in the queue
   * @throws IllegalArgumentException if capacity < 1
   */
  @SuppressWarnings("unchecked")
  public MinHeapPriorityQueue(int capacity) {
    if (capacity < 1)
      throw new IllegalArgumentException("A priority queue needs to store at least 1 item");
    MAX_SIZE = capacity;
    size = 0;
    priorities = (E[]) new Comparable[MAX_SIZE];
    pq = new int[MAX_SIZE];
    pqIndex = new int[MAX_SIZE];
  }

  /**
   * Reduce the priority of an item, updating the heap upwards. Running time is O(log n).
   * 
   * @param i the item to update
   * @param priority the new priority
   * @throws IndexOutOfBoundsException if i is not within the bounds of the queue
   * @throws IllegalArgumentException if item i is not currently in the queue
   * @throws IllegalArgumentException if the new priority is is not strictly smaller
   */
  public void reducePriority(int i, E priority) {
    if (i < 0 || i >= MAX_SIZE)
      throw new IndexOutOfBoundsException();
    if (pqIndex[i] == -1)
      throw new IllegalArgumentException("Item not in the priority queue");
    if (priorities[i].compareTo(priority) <= 0)
      throw new IllegalArgumentException("Priority needs to be reduced");
    priorities[i] = priority;
    heapifyUp(i);
  }

  /**
   * Is item i in the queue. Running time is O(1).
   * 
   * @return true if i is in the queue
   */
  public boolean contains(int i) {
    if (i < 0 || i >= MAX_SIZE)
      throw new IndexOutOfBoundsException();
    return pqIndex[i] != -1;
  }

  /**
   * Checks if the queue contains any items. Running time is O(1).
   * 
   * @return true if the queue is empty
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Insert a new item in the queue. The item is added as a new leaf to the binary heap and then
   * bubbles up. Running time is O(log n).
   * 
   * @param priority the priority of the item
   * @return the index of the inserted item
   * @throws IllegalStateException if the queue is full
   */
  public int insert(E priority) {
    if (size == MAX_SIZE)
      throw new IllegalStateException("Queue is full");
    int i = size;
    size++;
    priorities[i] = priority;
    pq[i] = i;
    pqIndex[i] = i;
    heapifyUp(i);
    return i;
  }

  /**
   * Get, but not remove the index at the front of this queue. Running time is O(1).
   * 
   * @return the index of the item at the front of the queue
   * @throws IllegalStateException if the queue is empty
   */
  public int getMin() {
    if (size == 0)
      throw new IllegalStateException("Priority queue is empty");
    return pq[0];
  }

  /**
   * Get the priority of the item identified by index i. Running time is O(1).
   * 
   * @param the index of the item
   * @return the priority of item i
   * @throws IndexOutOfBoundsException if i is not within the queue bounds
   * @throws IllegalArgumentException if i is not currently in the queue
   */
  public E getPriority(int i) {
    if (i < 0 || i >= MAX_SIZE)
      throw new IndexOutOfBoundsException();
    if (pqIndex[i] == -1)
      throw new IllegalArgumentException("Item not in the priority queue");
    return priorities[i];
  }

  /**
   * Removes the item at the front of the queue. Then copies the right most leaf to the front of the
   * queue. As this new item may not be the minimum, the heap property is then restored downwards.
   * Running time is O(log n).
   * 
   * @throws IllegalStateException if the queue is empty
   */
  public void removeMin() {
    if (size == 0)
      throw new IllegalStateException("Priority queue is empty");
    int nextVertex = pq[0];
    pqIndex[nextVertex] = -1;
    pq[0] = pq[size - 1]; // move the right most leaf to the top of the heap
    pqIndex[pq[0]] = 0;
    size--;
    heapifyDown(0);
  }

  /**
   * This helper method restores the heap property of a heap stored in pq. pq[i] may break the heap
   * property as it may not be less than its left and right children who are valid heaps. Running
   * time is O(log n).
   * 
   * @param i index of the element in the heap which may not satisfy the heap property
   */
  private void heapifyDown(int i) {
    int left = (i << 1) + 1; // left = 2i +1
    int right = (i << 1) + 2;// right = 2i +2
    int smallest = i;

    if (left < size && priorities[pq[left]].compareTo(priorities[pq[smallest]]) < 0) {
      smallest = left;
    }
    if (right < size && priorities[pq[right]].compareTo(priorities[pq[smallest]]) < 0) {
      smallest = right;
    }
    if (smallest != i) {
      // swap
      swap(i, smallest);

      heapifyDown(smallest);
    }
  }

  /**
   * This helper method restores the heap property of a heap stored in pq. pq[i] may break the heap
   * property as its priority has been lowered. It may now be smaller than its parent, so it may
   * bubble up the heap. Running time isO(log n).
   * 
   * @param i index of the element in the heap which may not satisfy the heap property
   */
  private void heapifyUp(int i) {
    int childIndex = pqIndex[i];
    if (childIndex > 0) {
      int parentIndex = (childIndex - 1) >> 1; // parent = (i-1)/2
      E childDistance = priorities[i];
      E parentDistance = priorities[pq[parentIndex]];

      if (childDistance.compareTo(parentDistance) < 0) {
        swap(parentIndex, childIndex);
        heapifyUp(pq[parentIndex]);
      }
    }
  }

  /**
   * This helper method swaps two elements in pq referenced by i and j and updates their indexes in
   * pqIndex.
   */
  private void swap(int i, int j) {
    // swap
    int tmp = pq[j];
    pq[j] = pq[i];
    pq[i] = tmp;

    // update indexes
    pqIndex[pq[j]] = j;
    pqIndex[pq[i]] = i;
  }

}
