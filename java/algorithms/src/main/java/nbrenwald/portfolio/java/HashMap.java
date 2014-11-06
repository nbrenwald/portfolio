package nbrenwald.portfolio.java;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HashMap {
  
  private List<Integer>[] hashTable;
  private Map<Integer, String> map = new java.util.HashMap<>();
  private int size = 0;
  private double loadFactor = 0.75; // if #elements stored /#number of slots increases above this, we double the table size.
  
  public HashMap(int initialSize){
    
    hashTable =  new List[initialSize];
    for(int i = 0; i < hashTable.length; i++){
      hashTable[i] = new LinkedList<Integer>();
    }
  }

  
  private int hash(int value, int slots){
    return value % slots;
  }
  
  private void doubleHashTable(){
    System.out.println("Doubling hash table");
    List<Integer>[] newHashTable =  new List[hashTable.length*2];
    for(int i = 0; i < newHashTable.length; i++){
      newHashTable[i] = new LinkedList<Integer>();
    }
    for(List<Integer> li : hashTable){
      if(li != null){
        for(Integer i : li){
          int hashCode = hash(i,hashTable.length*2);
          newHashTable[hashCode].add(i);
        }
      }
    }
    
    hashTable = newHashTable;
    
  }

  public boolean containsKey(int key) {
 // returns null if key doesn't exist in the hash map.
    int hashCode = hash(key,hashTable.length);
    for(Integer i : hashTable[hashCode]){
      if(i.equals(key)) return true;
    }
    return false;
  }



 
  public Integer get(int key) {
    // returns null if key doesn't exist in the hash map.
    int hashCode = hash(key,hashTable.length);
    for(Integer i : hashTable[hashCode]){
      if(i.equals(key)) return i;
    }
    return null;
  }

 
  public void put(int key) {
    int hashCode = hash(key,hashTable.length);
    hashTable[hashCode].add(key);
    size ++;
    if(size / hashTable.length >=0.75){
      doubleHashTable();
    }
    
  }

  public void remove(int key) {
    int hashCode = hash(key,hashTable.length);
    hashTable[hashCode].remove(Integer.valueOf(key));
    size --;
  }


  public int size() {
    return size;
  }

}
