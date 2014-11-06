package nbrenwald.portfolio.interviews;
/**
 * A weighted graph edge.
 * 
 * @author Nicholas Brenwald
 * @version 1.1
 */
public final class Edge {

  /**
   * The start vertex edge.
   */
  private final int from;

  /**
   * The end vertex edge.
   */
  private final int to;

  /**
   * The weight of this edge.
   */
  private final double weight;

  /**
   * Constructs an edge between f and t with weight w.
   * 
   * @param f the start vertex of this edge
   * @param t the end vertex of this edge
   * @param w the weight associated with using this edge
   */
  public Edge(int f, int t, double w) {
    from = f;
    to = t;
    weight = w;
  }

  /**
   * @return the start vertex of this edge
   */
  public int getFrom() {
    return from;
  }

  /**
   * @return the end vertex of this edge
   */
  public int getTo() {
    return to;
  }

  /**
   * @return the weight of using this edge
   */
  public double getWeight() {
    return weight;
  }
}
