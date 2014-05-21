package nbrenwald.portfolio.java;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HashMapTest {
  
  private HashMap myHashMap;

  @Before
  public void setUp() throws Exception {
    myHashMap = new HashMap(100);
    myHashMap.put(1000000);
    myHashMap.put(1000005);
    
  }

  @Test
  public void testContainsKey() {
    assertTrue(myHashMap.containsKey(1000000));
    assertFalse(myHashMap.containsKey(2000000));
  }

  @Test
  public void testGet() {
    assertEquals(myHashMap.get(1000000), Integer.valueOf(1000000));
    assertNull(myHashMap.get(3000000));
  }

  @Test
  public void testPut() {
    
    assertFalse(myHashMap.containsKey(3000000));
    myHashMap.put(3000000);
    assertTrue(myHashMap.containsKey(3000000));
  }

  @Test
  public void testRemove() {
    assertTrue(myHashMap.containsKey(1000000));
    myHashMap.remove(1000000);
    assertFalse(myHashMap.containsKey(1000000));
  }
  
  @Test
  public void testDoubleHashTable() {
    myHashMap = new HashMap(10);
    for(int i = 0 ; i < 100 ; i ++){
      myHashMap.put(i);
    }
    assertEquals(myHashMap.size(),100);
  }

}
