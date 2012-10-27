package Graph.Algo.ShortestPath;

import Graph.*;
import java.util.*;

/**
 *
 * @author Jatin
 */
public class BellmanFord<T, E extends Comparable<? super E>> {

    final Graphs<T, E> graph;
    final Vertex<T> start;
    final Adder<E> adder;
    E zero;
    E infinite;

    public BellmanFord(Graphs<T, E> graph, Vertex<T> start, Adder<E> adder, E zero, E infinite) {
        this.adder = adder;
        this.graph = graph;
        this.start = start;
        this.zero = zero;
        this.infinite = infinite;
    }

    public boolean findShortestPath() {
        for (Vertex<? extends T> ver : graph.getAllVertex()) {
            ver.setComputation(new BFData());
        }
        getBFData(start).key = zero;

        for (int i = 0; i < graph.getAllVertex().size(); i++) {
            for (Edge<E> edge : graph.getAllEdges()) {
              //  System.out.println("------------");
//                System.out.println("Ite " + i + " | " + edge);
                //relax
                Vertex<T> u = (Vertex<T>) edge.getSource();
                Vertex<T> v = (Vertex<T>) edge.getDestination();
                E sum = adder.add(getBFData(u).key, edge.getAttribute());
//                System.out.println("sum " + sum+"  key "+v+" compate "+getBFData(v).key.compareTo(sum));
                if (getBFData(v).key.compareTo(sum) > 0) 
                {
                    getBFData(v).key = adder.add(getBFData(u).key, edge.getAttribute());
                    getBFData(v).prev = u;
                //    System.out.println(u+" to "+v);
                }
            }
        }

        for (Edge<E> edge : graph.getAllEdges()) 
        {
            Vertex<T> u = (Vertex<T>) edge.getSource();
            Vertex<T> v = (Vertex<T>) edge.getDestination();
            E sum = adder.add(getBFData(u).key, edge.getAttribute());
            //System.out.println(sum+" key "+getBFData(v).key);
            if (getBFData(v).key.compareTo(sum) > 0) 
            {
                return false;
            }
        }
        setPath();
        return true;
    }

    private void setPath() {
        for (Vertex<? extends T> ver : graph.getAllVertex()) 
        {
            Vertex<T> prev = getBFData(ver).prev;
            if (prev == null) {
                continue;
            }
            graph.connectPath(prev, ver);
        }
    }

    public Graphs<T, E> getShortestPathGraph() {
        return graph;
    }

    private BFData getBFData(Vertex<? extends T> v) {
        return ((BFData) v.getComputation());
    }

    class BFData implements Computation {

        E key = infinite;
        Vertex<T> prev;
    }
}
