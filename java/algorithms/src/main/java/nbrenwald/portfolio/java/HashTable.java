package nbrenwald.portfolio.java;
public class HashTable<K,T>{
 private int capacity=1;
 private int size=0;
 private double maxLoad = 0.75;
 private double minLoad = 0.25;
 private Node<K,T>[] table;

 public HashTable(){
  table = (Node<K,T>[]) new Node[capacity];
 }

 public int getSize(){
  return size;
 }

 public T delete(K k){
  T value = null;
  int preHash = k.hashCode();
  int index = preHash % capacity;
  Node<K,T> entry = table[index];
  if(entry != null){
    if(entry.key==k){
      value=entry.value;
      table[index]=entry.next;
      size--;
      return value;
    }
    while(entry.next != null){
      if(entry.next.key==k) {
       value = entry.next.value;
       entry.next = entry.next.next;
       size--;
       return value;
      }
      entry=entry.next;
     }
  }
  if(size/capacity<minLoad && capacity > 1)resize(capacity>>1);
  return value;
 }

 public boolean containsKey(K k){
   int preHash = k.hashCode();
   int index = preHash % capacity;
   Node<K,T> entry = table[index];
   while(entry != null){
    if(entry.key==k) return true;
    entry=entry.next;
   }
  return false;
 }

 public T get(K k){
   int preHash = k.hashCode();
   int index = preHash % capacity;
   Node<K,T> entry = table[index];
   T value = null;
   while(entry != null){
    if(entry.key==k) return entry.value;
    entry=entry.next;
   }
   
   return value;
 }
 
 public void put(K k, T t){
   int preHash = k.hashCode();
   int index = preHash % capacity;
   Node<K,T> entry = table[index];
   Node<K,T> newEntry = new Node<>(k, t);
   if(entry==null) table[index]=newEntry;
   else {
     Node<K,T> prev = null;
     while(entry != null){
       if(entry.key==k){
         entry.value=t;
         return;
       }
       prev=entry;
       entry=entry.next;
     }     
        prev.next=newEntry;    
   }
   size++;
   if(size/capacity>maxLoad)resize(capacity<<1);
 }

 public boolean isEmpty(){
  return (size==0);
 }
 
 public void resize(int newCapacity){
  Node<K,T>[] newTable = (Node<K,T>[]) new Node[newCapacity];
  for(int i = 0; i< table.length; i++){
   Node<K,T> entry = table[i];
   while(entry!= null){
    Node<K,T> newEntry = new Node<>(entry.key, entry.value);
    int newHash = newEntry.key.hashCode() % newCapacity; //get new hashcode
    Node<K,T> newList = newTable[newHash]; //get linked list to insert this node
    if(newList == null) newTable[newHash] = newEntry;
    else{
     while(newList.next !=null){
      newList=newList.next;
     }
     newList.next=newEntry;
    }
    entry=entry.next;
   }
  }
  capacity=newCapacity;
  table = newTable;
 }

 @Override
 public String toString(){
  String result = "Hash Table Capcity = "+capacity+" Size = "+size+"\n";
  for(int i=0; i< table.length; i++){
   Node<K,T> entry = table[i];
   while(entry!=null){
    result+= "Key = "+entry.key+" Value = "+entry.value+"\n";
    entry=entry.next;
   }
  }
  return result;
 }

 public static void main(String[] args){
  HashTable<Integer, String> hashy = new HashTable<>();
  System.out.println(hashy);
  hashy.put(1,"one");
  System.out.println(hashy);
  hashy.put(1,"two");
  System.out.println(hashy);
  hashy.put(17,"seventeen");
  System.out.println(hashy);
  hashy.put(3,"three");
  System.out.println(hashy);
  System.out.println(hashy.get(1));
  System.out.println(hashy.get(2));
  System.out.println(hashy.get(3));
  System.out.println(hashy.get(17));
  System.out.println(hashy.get(4));
  System.out.println(hashy);
  System.out.println(hashy.delete(1));
  System.out.println(hashy);
  System.out.println(hashy.delete(17));
  System.out.println(hashy);
  System.out.println(hashy.delete(1));
  System.out.println(hashy);
 }

 private static class Node<K,T>{
  K key;
  T value;
  Node<K,T> next;
  public Node(K k, T t){
     key=k;
     value=t;
  }
 }

}