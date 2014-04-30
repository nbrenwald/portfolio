package nbrenwald.portfolio.java;

import static org.junit.Assert.*;

import org.junit.Test;

public class FinderTest {

  @Test
  public void testFindMaxSubArray() {
    int[] emptyArray = {};
    assertEquals(Finder.findMaxOfMaxSubArray(emptyArray),0);
    
    int[] negativeArray = {-4,-3,-5};
    assertEquals(Finder.findMaxOfMaxSubArray(negativeArray),-3);
    
    int[] positiveArray = {2,3,4};
    assertEquals(Finder.findMaxOfMaxSubArray(positiveArray),9);
    
    int[] mixedArray = {-10,-5,1,-2,100,-50,55,-3};
    assertEquals(Finder.findMaxOfMaxSubArray(mixedArray),105);
  }

}
