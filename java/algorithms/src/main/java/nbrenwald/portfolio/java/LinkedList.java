package nbrenwald.portfolio.java;
public class LinkedList<T extends Comparable<T>>{
  private Node<T> head;
  private Node<T> tail;
  private int size;

  public LinkedList(){
   size=0;
  }
  
  public int getSize(){
   return size;
  }

  public void insert(T n){
  //append end running time is O(n)
  // we could offer a O(1) add to front.
    Node<T> next = new Node<>();
    next.value = n;
    if(head==null) {
        head=next;
    }
    else {
     Node<T> runner = head;
     while(runner.next != null){
      runner = runner.next;
     }
     runner.next = next;
    }
    size ++;
  }

  public int find(T n){
   // returns index of element (could be -1 if doesnt exist, or head =0, head.next = 1 and so on)
   // running time is O(n)
   if(head!=null){
    int index = 0;
    Node<T> runner = head;
    while(runner!= null){
     if(runner.value==n)return index;
     runner = runner.next;
     index++;
    }
   }
   return -1;
  }

  public void delete(T n){
    // would confirm with interviewer, do they want a return bool to say it was deleted, or not found.
    // running time is O(n)
   if(head != null){
    if(head.value==n){
     head=head.next;
     size--;
    }
    else{
      Node<T> runner=head;
     while(runner.next != null){
      if(runner.next.value == n) runner.next = runner.next.next;
      runner=runner.next;
      size--;
     }
    }
   }
  }

  public void push(T n){
   Node<T> next = new Node<>();
   next.value=n;
   next.next=head;
   head=next;
  }
  
  public T pop(){
   if(head==null)throw new IllegalStateException("Cant pop from an empty stack");
   Node<T> top = head;
   head=head.next;
   return top.value;
  }

  public T peek(){
   if(head==null)throw new IllegalStateException("Cant peek from an empty stack");
   return head.value;
  }
  
  public void enqueue(T n){
    Node<T> next = new Node<>();
    next.value=n;
    if(head == null){
     head=next;
     tail=next;
    }
    else{
     tail.next=next;
     tail=next;
    }
   size++;
   }

   public T dequeue(){
    if(head==null)throw new IllegalStateException("Cant dequeue from an empty queue");
    Node<T> front = head;
    head=front.next;
    size--;
    return front.value;
   }
  
  public void printInReverse(){
    printInReverse(head);
  }
  
  public static <T> void printInReverse(Node<T> n){
   if(n!=null){
    printInReverse(n.next);
    System.out.println(n.value);
   }
  }

  @Override
  public String toString(){
   Node<T> runner=head;
   String result;
   result = "List: Size = " + size+ " Elements : ";
   while(runner!=null){
   result += runner.value + " -> ";
   runner = runner.next;
   }
   return result;
  }

  public static void main(String[] args){
//   LinkedList<Integer> li = new LinkedList<>();
//   li.insert(1);
//   System.out.println(li);
//   li.insert(2);
//   System.out.println(li);
//   li.insert(3);
//   System.out.println(li);
//   System.out.println(li.find(1));
//   System.out.println(li.find(2));
//   System.out.println(li.find(3)); 
//   System.out.println(li.find(4));
//   System.out.println(li);
//   li.printInReverse();
//   li.delete(1);
//   System.out.println(li);
//   li.delete(2);
//   System.out.println(li);
//   li.delete(3);
//   System.out.println(li);
   
//   LinkedList<Integer> li = new LinkedList<>();
//   li.push(1);
//   System.out.println(li);
//   li.push(2);
//   System.out.println(li);
//   li.push(3);
//   System.out.println(li);
//   System.out.println(li.find(1));
//   System.out.println(li.find(2));
//   System.out.println(li.find(3)); 
//   System.out.println(li.find(4));
//   System.out.println(li);
//
//   System.out.println(li.pop());
//   System.out.println(li);
//   System.out.println(li.pop());
//   System.out.println(li);
//   System.out.println(li.pop());
//   System.out.println(li);
   
   LinkedList<Integer> li = new LinkedList<>();
   li.enqueue(1);
   li.enqueue(2);
   li.enqueue(3);
   System.out.println(li);
   li.reverseRecursive();
   System.out.println(li);
  }
  
  private void reverseRecursive() {
    if(head != null){
      reverseRecursive(head);
    }  
    Integer.toString(4);
  }

  private void reverseRecursive(Node<T> node) {
    if(node.next == null) head=node;
    else{
      Node<T> tail = node.next;
      reverseRecursive(tail);
      tail.next=node;
      node.next=null;
    }
    
  }

  private static class Node<E>{
    Node<E> next;
    E value;
  }

}
