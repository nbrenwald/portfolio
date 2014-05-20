package nbrenwald.portfolio.java;

import java.util.LinkedList;
import java.util.List;

public class HashMap {
  
  public HashMap(){
    for(int i = 0; i < slots.length; i++){
      slots[i] = new LinkedList<Integer>();
    }
  }

  private List<Integer>[] slots = new List[100];
  
  private int hash(int value, int slots){
    return value % slots;
  }

  public boolean containsKey(int key) {
 // returns null if key doesn't exist in the hash map.
    int hashCode = hash(key,slots.length);
    for(Integer i : slots[hashCode]){
      if(i.equals(key)) return true;
    }
    return false;
  }



 
  public Integer get(int key) {
    // returns null if key doesn't exist in the hash map.
    int hashCode = hash(key,slots.length);
    for(Integer i : slots[hashCode]){
      if(i.equals(key)) return i;
    }
    return null;
  }

 
  public void put(int key) {
    int hashCode = hash(key,slots.length);
    slots[hashCode].add(key);
    
  }

  public void remove(int key) {
    int hashCode = hash(key,slots.length);
    slots[hashCode].remove(Integer.valueOf(key));
  }

}
