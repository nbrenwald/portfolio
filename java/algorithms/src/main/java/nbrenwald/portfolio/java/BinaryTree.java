package nbrenwald.portfolio.java;
import java.util.Deque;
import java.util.LinkedList;

public class BinaryTree<T extends Comparable<T>>{
 public Node<T> root;
 
 public BinaryTree(){}
 
 public BinaryTree(Node<T> node){
   root = node;
 }
 
 public void insert(T t){ // running time O(n)
  Node<T> newNode = new Node<>(t);
  if(root == null){
   root = newNode;
  }
  else{
   Node<T> current = root;
   while(current.left != null){
    current = current.left;
   }
   current.left = newNode;
  }
 }

 public void insertBST(T t){ // running time, O(n)
 Node<T> newNode = new Node<>(t);
  if(root == null){
   root = newNode;
  }
  else{
   insertBST(root, t);
  }
 }

 public int getHeight(){
  return getHeight(root);
 }
 
 private int getHeight(Node<T> node){
 //furthest leaf from this node
  if(node == null || (node.left == null && node.right == null)){
   return 0;
  }
  else{
   return 1 + Math.max(getHeight(node.left), getHeight(node.right));
  }
 }
 
 public int getDepth(T value){
  return getDepth(root, value);
 }

 private int getDepth(Node<T> node, T value){
  //distance from root of this node
  if(node == null)return -1;
  else if(node.value == value){
   return 0;
  }
  else if(getDepth(node.left, value)>=0){
   return 1 + getDepth(node.left, value);
  }
  else if(getDepth(node.right, value)>=0) {
    return 1 + getDepth(node.right, value);
  }
  else return -1;
 }

 public boolean isComplete(){
  return isComplete(root);
 }

 private boolean isComplete(Node<T> node){
  if(node == null){
   return true;
  }
  if(node.left == null && node.right == null){
    return true;
   }
  if(node.left == null || node.right == null){
   return false;
  }
  return isComplete(node.left) && isComplete(node.right);
 }

 public void printInOrder(){
  printInOrder(root);
 }
 
 private void printInOrder(Node<T> node){
  if(node != null){
   printInOrder(node.left);
   System.out.println(node.value);
   printInOrder(node.right);
  }
 }

 public void printPreOrder(){
  printPreOrder(root);
 }

 private void printPreOrder(Node<T> node){
  if(node != null){
   System.out.println(node.value);
   printPreOrder(node.left);
   printPreOrder(node.right);
  }
 }

 public void printPostOrder(){
  printPostOrder(root);
 }

 private void printPostOrder(Node<T> node){
  if(node != null) {
   printPostOrder(node.left);
   printPostOrder(node.right);
   System.out.println(node.value);
  }
 }

 public void printBFS(){
  printBFS(root);
 }

 private void printBFS(Node<T> node){
  if(node != null){
   Deque<Node<T>> queue = new LinkedList<>();
   queue.addLast(node);
   while(!queue.isEmpty()){
    Node<T> next = queue.removeFirst();
    System.out.println(next.value);
    if(next.left != null){
     queue.addLast(next.left);
    }
    if(next.right != null){
     queue.addLast(next.right);
    }
   }
  }
 }

 public boolean containsBST(T value){
  return containsBST(root, value);
 }

 public boolean containsBST(Node<T> node, T value){
  if(node == null) return false;
  if(node.value == value) return true;
  if(node.value.compareTo(value) >= 0) return containsBST(node.left, value);
  else return containsBST(node.right, value); // get to throw away half if balanced, O(lg n)
 }

 public boolean contains(T value){
  return contains(root, value);
 }
 
 private boolean contains(Node<T> node, T value){
  if(node == null){
   return false;
  }
  if(node.value == value){
   return true;
  }
  return contains(node.left, value) || contains(node.right, value);
 } 


 public boolean isBalanced(){
  return isBalanced(root);
 }
  
 private boolean isBalanced(Node<T> node){
  if(node == null){
   return true;
  }
  if(Math.abs(getHeight(node.left) - getHeight(node.right)) > 1) {
   return false;
  }
  return isBalanced(node.left) && isBalanced(node.right);
 }
 
 private void insertBST(Node<T> node, T value){ //running time O(n)
  if(value.compareTo(node.value) < 0){
    if(node.left == null){
     Node<T> newNode = new Node<>(value);
     node.left = newNode;
    }
    else{
     insertBST(node.left, value);
    }
  }
  else{
    if(node.right==null){
     Node<T> newNode = new Node<>(value);
     node.right = newNode;
    }
    else{
     insertBST(node.right, value);
    }
  }
 }

 public boolean isBinarySearchTree(){
  return isBinarySearchTree(root);
 }

 private boolean isBinarySearchTree(Node<T> node){
  if(node == null) return true;
  if(node.left != null && node.left.value.compareTo(node.value) >=0) return false;
  if(node.right != null && node.right.value.compareTo(node.value) < 0) return false;
  return isBinarySearchTree(node.left) && isBinarySearchTree(node.right);
 }

 public void delete(T value){
  if(root != null){
   if(root.value == value){
    if(root.left  == null && root.right == null){
     root = null;
    }
    else if(root.left == null || root.right == null){
     root = (root.left == null) ? root.right : root.left;
    }
    else{
     //else swap node with a child, call delete again
     T tmp = root.value;
     root.value = root.left.value;
     root.left.value= tmp;
     delete(root, value);
    }
   }
   else{
    delete(root, value);
   }
  }
 }

 public void deleteBST(T value){
   root = deleteBST(root, value);
 }

 private Node<T> deleteBST(Node<T> node, T value){
  if(node != null){
   int cmp = value.compareTo(node.value); 
   if(cmp < 0) node.left=deleteBST(node.left, value);
   else if(cmp > 0) node.right=deleteBST(node.right, value);
   else{
    if(node.left == null) node = node.right;
    else if(node.right == null) node = node.left;
    else{
      Node<T> max = getMaxBST(node.left);
      Node<T> left = deleteMaxBST(node.left);
      max.left=left;
      max.right = node.right;
      node=max;
    }
   }
  } 
  return node;
 }
 
 private  Node<T> getMaxBST(Node<T> node){
  while(node.right != null){
   node=node.right;
  }
  return node;
 }

 private Node<T> deleteMaxBST(Node<T> node){
  if(node != null){
   if(node.right == null) node =null;
   else node.right = deleteMaxBST(node.right);
  }
  return node;

 }

 private void delete(Node<T> parent, T value){
  //assume we dont have parent pointers
  if(parent != null){// nothing to do if subtree is null
   if(parent.left!= null && parent.left.value == value){
    //delete left node;
    if(parent.left.left == null && parent.left.right == null){
     parent.left=null;
    }
    else if(parent.left.left == null || parent.left.right == null){
     parent.left = (parent.left.left == null)? parent.left.right : parent.left.left;
    }
    else{
     T tmp = parent.left.value;
     parent.left.value=parent.left.left.value;
     parent.left.left.value = tmp;
     delete(parent.left, value);
    }
   }
   else if(parent.right != null && parent.right.value == value){
    //delete right node;
    if(parent.right.left == null && parent.right.right == null){
     parent.right=null;
    }
    else if(parent.right.left == null || parent.right.right == null){
     parent.right = (parent.right.left == null)? parent.right.right : parent.right.left;
    }
    else{
     T tmp = parent.right.value;
     parent.right.value=parent.right.left.value;
     parent.right.left.value = tmp;
     delete(parent.right, value);
    }
     
   }
   else{
    delete(parent.left, value);
    delete(parent.right, value);
   }
  }
 }
 
 public static void main(String[] args){
   BinaryTree<Integer> tree = new BinaryTree<>();
   tree.insert(1);
   tree.insert(2);
   tree.insert(3);
   tree.insert(4);
   tree.insert(5);
   tree.printBFS();
   tree.printInOrder();
   tree.printPreOrder();
   tree.printPostOrder();
   
   Node<Integer> n1 = new Node(4);
   Node<Integer> n2 = new Node(2);
   Node<Integer> n3 = new Node(6);
   Node<Integer> n4 = new Node(1);
   Node<Integer> n5 = new Node(3);
   Node<Integer> n6 = new Node(5);
   Node<Integer> n7 = new Node(7);
   
   n1.left=n2;
   n1.right=n3;
   n2.left=n4;
   n2.right=n5;
   n3.left=n6;
   n3.right=n7;
   
   BinaryTree<Integer> t1 = new BinaryTree<>(n1);
   System.out.println("BFS");
   t1.printBFS();
   System.out.println("In order");
   t1.printInOrder();
   System.out.println("Pre order");
   t1.printPreOrder();
   System.out.println("Post order");
   t1.printPostOrder();
   System.out.println("Height");
   System.out.println(t1.getHeight());
   System.out.println("Depth root");
   System.out.println(t1.getDepth(4));
   System.out.println("Depth child");
   System.out.println(t1.getDepth(2));
   System.out.println("Depth leaf");
   System.out.println(t1.getDepth(3));
   System.out.println("Is Balanced");
   System.out.println(t1.isBalanced());
   System.out.println("Is Complete");
   System.out.println(t1.isComplete());
   System.out.println("Contains 4");
   System.out.println(t1.contains(4));
   System.out.println("Contains 100");
   System.out.println(t1.contains(100));
   System.out.println("Contains BST 4");
   System.out.println(t1.containsBST(4));
   System.out.println("Contains BST 100");
   System.out.println(t1.containsBST(100));
   System.out.println("isBinarySearch tree");
   System.out.println(t1.isBinarySearchTree());
   System.out.println("Get max from sub tree 2");
   System.out.println(t1.getMaxBST(n5).value);
   //System.out.println("delete max from sub tree 2");
   //t1.printBFS(t1.deleteMaxBST(n2));
   System.out.println("Delete root - 4");
   t1.deleteBST(4);
   System.out.println("BFS");
   t1.printBFS();
   //System.out.println("isBinarySearch tree");
   //System.out.println(t1.isBinarySearchTree());

 }

 private static class Node<E>{
  E value;
  Node<E> left;
  Node<E> right;
  Node(E e){
   value = e;
  }
 }

}