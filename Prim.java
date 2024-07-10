
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
 import java.util.Iterator;

public class Prim {

  public static void updateFrontier(Vertex v, //
                                    Map<Vertex, Integer> settled, //
                                    PriorityQueue<Edge> frontier) {

  


  Iterator<Edge> edgeIter = v.iterator();
  for(int i = 0; i < v.numOfEdges();  i++){
    Edge e = edgeIter.next();
      if (!settled.containsKey(e.dest()) && !settled.containsKey(e.src())) {
      continue;
    }
    
    frontier.add(e);
    }
  }

  public static Set<Edge> runPrim(UG g, Vertex s) {
    Map<Vertex, Integer> settled = new HashMap<>();
    Set<Edge> mst = new HashSet<>();
    PriorityQueue<Edge> frontier = new PriorityQueue<>();

    settled.put(s, 0);
    updateFrontier(s, settled, frontier);
    while (!frontier.isEmpty()) {
      Edge nextEdge = frontier.poll();
      Vertex destV = nextEdge.dest();
      Vertex srcVertex = nextEdge.src();
      Vertex v = destV;
      if (settled.containsKey(destV)) {
        v = srcVertex;
        if(settled.containsKey(srcVertex)) {
          continue;
        }
      }
      settled.put(v, 0);
      mst.add(nextEdge);
      updateFrontier(v, settled, frontier);
  }
  return mst;
  }

  public static int setSum(Set<Edge> set) {
    int sum = 0;
    for (Edge e : set)
      sum += e.wght();
    return sum ;
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
