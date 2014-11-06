package nbrenwald.portfolio.interviews;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of quest Get Shorty. The problem is modeled as a non-negative weighted graph which
 * can be solved by Dijkstra's single source shortest path algorithm. This version improves the time
 * of my first submission by using a priority queue based on a binary heap data structure.
 * 
 * @author Nicholas Brenwald
 * @version 1.1
 */

public class Graph {

  /**
   * An adjacency list representation of this graph.
   */
  private Map<Integer, List<Edge>> adjacencyList;

  /**
   * The number of vertices in this graph.
   */
  private final int N_VERTEX;

  /**
   * Constructs an unconnected graph with n vertices.
   */
  public Graph(int n) {
    N_VERTEX = n;
    adjacencyList = new HashMap<>();
    for (int i = 0; i < n; i++) {
      List<Edge> li = new ArrayList<>();
      adjacencyList.put(i, li);
    }
  }

  /**
   * Adds a forward and backward edge between from and to.
   * 
   * @param from the start vertex of this edge
   * @param to the end vertex of this edge
   * @param weight the weight associated with using this edge
   */
  public void addUndirectedEdge(int from, int to, double weight) {
    Edge forwardEdge = new Edge(from, to, weight);
    adjacencyList.get(from).add(forwardEdge);

    Edge backwardEdge = new Edge(to, from, weight);
    adjacencyList.get(to).add(backwardEdge);
  }

  /**
   * Finds the shortest path between two vertices in this graph. As this algorithm is based on
   * Dijkstra's single source shortest path algorithm, all edges must be non-negative. Uses a
   * priority queue based on a binary min heap to improve running time from O(|V|^2) to O(|V| log
   * |V|). java.util.PriorityQueue was not used as it does not offer a O(log n) method to update the
   * priority of an element in the queue. This method assumes that our start vertex is 0 and our
   * destination vertex is N_VERTEX-1.
   * 
   * @return the cost of the shortest path from vertex <i>0</i> to vertex <i>N_VERTEX-1</i>
   */
  public double getShortestPath() {

    MinHeapPriorityQueue<Double> pq = new MinHeapPriorityQueue<>(N_VERTEX);
    for (int i = 0; i < N_VERTEX; i++) {
      pq.insert(Double.POSITIVE_INFINITY);
    }
    pq.reducePriority(0, 0.0);

    while (!pq.isEmpty()) {
      int nextVertex = pq.getMin();
      double minDistance = pq.getPriority(nextVertex);
      if (nextVertex == N_VERTEX - 1)
        return minDistance;
      pq.removeMin();

      for (Edge e : adjacencyList.get(nextVertex)) {
        if (pq.contains(e.getTo())) {
          double alternative = minDistance + e.getWeight();
          if (alternative < pq.getPriority(e.getTo())) {
            pq.reducePriority(e.getTo(), alternative);
          }
        }
      }

    }
    return -1; // returns -1 if no path exists
  }

  public static void main(String[] args) {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

      String inputLine;
      Graph g = null; // - variable to hold current graph we are solving
      int n = 0;
      int m = 0;
      int edgesRead = 0;
      // long start = System.currentTimeMillis();
      while ((inputLine = br.readLine()) != null) {
        // assuming that space separates all values
        String[] params = inputLine.split("\\s+");

        if (params.length == 2) {
          // solve previous graph
          if (g != null) {
            double shortestPath = g.getShortestPath();
            shortestPath = Math.pow(Math.E, (-1 * shortestPath));
            System.out.format("%.4f%n", shortestPath);
          }

          // break condition
          if (inputLine.equals("0 0"))
            break;

          // initialize new graph
          n = Integer.valueOf(params[0]);
          m = Integer.valueOf(params[1]);
          g = new Graph(n);
          edgesRead = 0;

        } else if (params.length == 3) {
          edgesRead++;
          if (edgesRead > m)
            throw new IllegalArgumentException("Already loaded " + m + " edges");

          int from = Integer.valueOf(params[0]);
          int to = Integer.valueOf(params[1]);
          double factor = Double.valueOf(params[2]);

          if (from < 0 || from >= n)
            throw new IndexOutOfBoundsException("Vertex " + from + " is not between 0 and "
                + (n - 1));
          if (to < 0 || to >= n)
            throw new IndexOutOfBoundsException("Vertex " + to + " is not between 0 and " + (n - 1));
          if (factor < 0 || factor > 1)
            throw new IllegalArgumentException("Factor " + factor + " is not between 0 and 1");

          /*
           * To model this problem as a non-negative shortest path problem, the logarithm product
           * rule ( log (x*y) = log(x) + log(y) ) is used. As we are dealing with factors f where 0
           * <= f <= 1, log(f) will be 0 when f is 1 and -infinity when f is 0. By multiplying
           * log(f) by -1, we will have a smaller weight associated with corridors with larger
           * factors.
           */
          double weight = -1 * Math.log(factor);
          g.addUndirectedEdge(from, to, weight);
        }
      }
      // long end = System.currentTimeMillis();
      // System.out.println("Total Time = " + (end - start) / 1000.0);

    } catch (IOException io) {
      io.printStackTrace();
    }
  }
}
