
/**
 * =================================================================================
 * This is an implementation of Kruskal's algorithm to find a MST for an
 * undirected graph of a single connected component. Only a single command line
 * argument is expected: the text file that contains the graph. The output is
 * the sum of the attributes (i.e. costs) of the set of edges selected for the
 * MST.
 * 
 * ---------------------------------------------------------------------------------
 * MAKE SURE ASSERTIONS ARE ENABLED! IF UNSURE, TRY "assert 1 == 2;" TO CHECK.
 * ---------------------------------------------------------------------------------
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Kruskal {

  public static Set<Edge> runKruskal(UG g) {
    // We start by sorting the edges
    List<Edge> edges = new ArrayList<>();
    Set<Edge> added = new HashSet<>();
    for (Vertex v : g) {
      for (Edge e : v) {
        if (!added.contains(e)) {
          assert !added.contains(e.peer());
          edges.add(e);
        }
      }
    }
    Collections.sort(edges);

    DS ds = new DS(g.numOfVertexes());
    Set<Edge> mst = new HashSet<>();

    // ===================
    // YOUR WORK GOES HERE
    // ===================
    for(Edge e : edges){
      int u = e.dest().id();
      int v = e.src().id();
      if(ds.findRep(u) != ds.findRep(v)){
        ds.union(u, v);
        mst.add(e);
      }
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

    UG g = new UG(fileName);
    Set<Edge> mst = runKruskal(g);
    if (mst != null) {
      System.out.println(setSum(mst));
    } else {
      System.err.println("Kruskal's algorithm could not be completed");
    }
  }
}
