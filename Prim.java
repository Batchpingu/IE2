
/**
 * =================================================================================
 * This is an implementation of Prim's algorithm to find a MST for an undirected
 * graph of a single connected component. Two command line arguments are
 * expected: the text file that contains the graph and the starting vertex. The
 * output is the sum of the attributes (i.e. costs) of the set of edges selected
 * for the MST.
 * 
 * ---------------------------------------------------------------------------------
 * MAKE SURE ASSERTIONS ARE ENABLED! IF UNSURE, TRY "assert 1 == 2;" TO CHECK.
 * ---------------------------------------------------------------------------------
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Prim {

  public static void updateFrontier(Vertex v, //
                                    Map<Vertex, Integer> settled, //
                                    PriorityQueue<Edge> frontier) {
    for(int i = 0; i < v.numOfEdges(); i++){
      frontier.add(v.iterator().next());
    }
    settled.put(v, 0);
  }

  public static Set<Edge> runPrim(UG g, Vertex s) {
    Map<Vertex, Integer> settled = new HashMap<>();
    Set<Edge> mst = new HashSet<>();
    PriorityQueue<Edge> frontier = new PriorityQueue<>();

    settled.put(s, 0);
    updateFrontier(s, settled, frontier);

    // ===================
    // YOUR WORK GOES HERE
    // ===================

    while (!frontier.isEmpty()) {
      Edge nextEdge = frontier.poll();
      Vertex nextVertex = nextEdge.dest();
    }
                
    return mst;
  }

  public static int setSum(Set<Edge> set) {
    int sum = 0;
    for (Edge e : set)
      sum += e.wght();
    return sum / 2;
  }

  public static void main(String[] args) throws Exception {
    String fileName = args[0];
    String startVertexId = args[1];

    UG g = new UG(fileName);
    Integer sId = Integer.parseInt(startVertexId);
    Vertex s = g.getVertex(sId);
    if (s != null) {
      Set<Edge> mst = runPrim(g, s);
      if (mst != null) {
        System.out.println(setSum(mst));
      } else {
        System.err.println("Prim's algorithm could not be completed");
      }
    } else {
      System.err.println("Vertex id " + sId + " does not exist in the graph");
      System.exit(1);
    }
  }
}
