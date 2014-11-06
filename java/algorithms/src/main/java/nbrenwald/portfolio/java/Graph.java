package nbrenwald.portfolio.java;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph<V extends Comparable<V>>{
private Map<V, List<Edge<V>>> adjacencyList;

public Graph(){
 adjacencyList = new HashMap<>();
}

public void addEdge(V start, V end, int weight){
 List<Edge<V>> li;
 Edge<V> e = new Edge<>(start, end, weight);
 if(!adjacencyList.containsKey(start)){
  li = new ArrayList<>();
  li.add(e);
  adjacencyList.put(start,li);
 }
 else{
  adjacencyList.get(start).add(e);
 }
 if(!adjacencyList.containsKey(end)){
   li = new ArrayList<>();
   adjacencyList.put(end,li);
  }
}

public String toStringBFS(V source){
 //BFS, use a linked list.
 String result = "";
 Deque<V> q = new LinkedList<>();
 Set<V> visited = new HashSet<>();
 q.addLast(source);
 while(!q.isEmpty()){
  V node = q.removeFirst();
  result+=node+"\n";
  visited.add(node);
  for(Edge<V> e : adjacencyList.get(node)){
   if(!visited.contains(e.end))q.addLast(e.end);
  }
 }
 return result;
}

public String toStringDFS(V source){
//use stack, or recursion.
String result = "";
Deque<V> s = new LinkedList<>();
Set<V> visited = new HashSet<>();
s.push(source);
while(!s.isEmpty()){
 V next = s.pop();
 visited.add(next);
 result+=next+"\n";
 for(Edge<V> e : adjacencyList.get(next)){
   if(!visited.contains(e.end))s.push(e.end);
  }
}
return result;
}

@Override
public String toString(){
  String result = "";
  for(V key : adjacencyList.keySet() ){
    result +="Vertex ="+key+" has edges: \n" ;
    for(Edge<V> e: adjacencyList.get(key)){
      result+="\tStart "+e.start+" End "+e.end+ " Weight "+e.weight;
    }
    result += "\n\n";
  }
  return result;
}

private static class Edge<V>{
V start;
V end;
int weight;

 Edge(V s, V e, int w){
  start=s;
  end=e;
  weight=w;
 }
}

public int bellmanFord(V source, V destination){
  // need to do v times e passes
  // step 1, initialise distance list
  Map<V, Integer> dist = new HashMap<>();
  for(V v : adjacencyList.keySet()){
    dist.put(v, Integer.MAX_VALUE);
  }
  dist.put(source, 0);
  
  //now we loop over all edges v times to see what is happening
  for(int i = 0; i < adjacencyList.keySet().size(); i++){
  for(V v : adjacencyList.keySet()){
    for(Edge<V> e : adjacencyList.get(v)){
      // is weight u,v + path u less than path v
      if (dist.get(v)!= Integer.MAX_VALUE && dist.get(v) + e.weight < dist.get(e.end)){
        //relax
        dist.put(e.end, dist.get(v) + e.weight);
      }
    }
  }
  }
  
  //Now we done one last check for negative cost cycle
  for(V v : adjacencyList.keySet()){
    for(Edge<V> e : adjacencyList.get(v)){
      // is weight u,v + path u less than path v
      if (dist.get(v)!= Integer.MAX_VALUE && dist.get(v) + e.weight < dist.get(e.end)){
        //relax
        throw new IllegalStateException("Graph contains negative cost cycle");
      }
    }
  }
  return dist.get(destination);
  
  
}

public int[][] floydWarshall(){
  Set<V> vertices = adjacencyList.keySet();
  int size = vertices.size();
  Map<V,Integer> vertexIndex = new HashMap<>();
  int index = 0;
  for(V v : adjacencyList.keySet()){
    vertexIndex.put(v,index);
    index++;
  }
  
  int[][] dist = new int[size][size];
  
  // Initialise
  for(int i=0; i < dist.length; i++){
    for(int j=0; j < dist[i].length; j++){
      dist[i][j] = Integer.MAX_VALUE;
    }
  }
  
  for(V v : vertexIndex.keySet()){
    int i = vertexIndex.get(v);
    for(Edge<V> e : adjacencyList.get(v)){
      int j = vertexIndex.get(e.end);
      int weight = e.weight;
      dist[i][j] = weight;
    }
  }
  
  for(int k = 0; k < size; k++){
    for(int i = 0; i < size; i ++){
      for(int j=0; j < size; j++){
        if(dist[i][k]!=Integer.MAX_VALUE && dist[k][j]!=Integer.MAX_VALUE && dist[i][j] >  dist[i][k] + dist[k][j])
           {
          dist[i][j] = dist[i][k] + dist[k][j];
        }
      }
    }
  }
  
  
  
  return dist;
}

//running time O(E+V2) -> O(V2)
public int dijkstra(V source, V end){
Map<V, Integer> dist = new HashMap<>();
for(V v : adjacencyList.keySet()){
dist.put(v, Integer.MAX_VALUE);
}
dist.put(source, 0);

//The while loop will execute O(V) times
while(!dist.isEmpty()){
//find vertex with minimum distance
V min = null;
int minWeight = Integer.MAX_VALUE;
//The for loop will execute O(V)times
for(V v : dist.keySet()){
  if(dist.get(v) < minWeight){
   minWeight = dist.get(v);
   min = v;
  }
}

dist.remove(min);

if(min.compareTo(end) ==0){
 return minWeight;
}

// Every Edge will be covered
for(Edge<V> e : adjacencyList.get(min)){
 if(dist.containsKey(e.end) && (dist.get(e.end) > (minWeight + e.weight))){
  dist.put(e.end, minWeight + e.weight);
 }
}

}

return Integer.MAX_VALUE; // if destination cannot be reached by source 

}

public static void main(String[] args){
 Graph<Character> g = new Graph<>();
 g.addEdge('a','b', 5);
 g.addEdge('b', 'a', 5);
 g.addEdge('b','c', 10);
 g.addEdge('c','b', 50);
 g.addEdge('a','c', 50);
 g.addEdge('a','d', 100);
 g.addEdge('d','a', 1000);
 System.out.println(g.toString());
 for(int[] a : g.floydWarshall()){
   System.out.println(Arrays.toString(a));
 }
 System.out.println(g.bellmanFord('c', 'd'));
 System.out.println(g.dijkstra('c', 'd'));
}
}