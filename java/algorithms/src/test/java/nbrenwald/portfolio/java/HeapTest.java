package nbrenwald.portfolio.java;

import static org.junit.Assert.*;

import org.junit.Test;

public class HeapTest {

  @Test
  public void testFromArray() {
    int[] array = {1,2,3,4,5,6,7,8,9}; 
    Heap h = Heap.fromArray(array);
    assertTrue(h.checkRI());
  }
  

}
