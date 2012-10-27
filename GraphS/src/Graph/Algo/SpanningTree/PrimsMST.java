package Graph.Algo.SpanningTree;

import Graph.*;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Given a connected, undirected graph, a spanning tree of that graph is a subgraph 
 * that is a tree and connects all the vertices together. A single graph can have many different spanning trees. 
 * We can also assign a weight to each edge, which is a number representing how unfavorable it is, 
 * and use this to assign a weight to a spanning tree by computing the sum of the 
 * weights of the edges in that spanning tree. A minimum spanning tree (MST) or 
 * minimum weight spanning tree is then a spanning tree with weight less than or 
 * equal to the weight of every other spanning tree. More generally, any undirected graph 
 * (not necessarily connected) has a minimum spanning forest, which is a union of minimum 
 * spanning trees for its connected components.
 */
/**
 *
 * @author Jatin
 */
public class PrimsMST<K, E extends Comparable<? super E>> {

    private Graphs<K, E> graph;
    PriorityQueue<Vertex<K>> mainQueue = new PriorityQueue<Vertex<K>>(10, new Comparator<Vertex<K>>() {

            @Override
            public int compare(Vertex<K> o1, Vertex<K> o2) {

                PrimsData o1Data = (PrimsData) o1.getComputation();
                PrimsData o2Data = (PrimsData) o2.getComputation();
                if (o1Data.key == null) {
                    return 1;
                }
                if (o2Data.key == null) {
                    return -1;
                }
                return o1Data.key.compareTo(o2Data.key);
            }
        });

    public PrimsMST(UnDirectedGraph<K, E> graph) {
        this.graph = graph;
    }

    public Graphs<K, E> getMST() {
     
        //set Computation.
        for (Vertex<? extends K> ver : graph.getAllVertex()) {
            ver.setComputation(new PrimsData());
            mainQueue.add((Vertex<K>) ver);
        }
        while (!mainQueue.isEmpty()) 
        {
            Vertex<K> u = mainQueue.poll();
            //System.out.println("------------------");
            //System.out.println("starting with "+u);
            Iterator<Edge<?>> iter = u.getConnectedEdges().iterator();
            while (iter.hasNext())//int i=0;i<u.getConnectedEdges().size();i++)
            {
                Edge<E> edge = (Edge<E>) iter.next();
                Vertex<K> v = (Vertex<K>) edge.getPassage(u);                
                boolean access = false;
                if (getPrimsData(v).key == null) {
                    access = true;
                }
                if (!access) 
                {                    
                    if (mainQueue.contains(v) && edge.getAttribute().compareTo(getPrimsData(v).key) < 0) {
                        //System.out.println(v+" and its prev "+u);
                        getPrimsData(v).prev = u;
                        getPrimsData(v).key = edge.getAttribute();
                    }
                }
                else
                {
                     if (mainQueue.contains(v))
                     {
                        // System.out.println(v+" and its prev "+u);
                        getPrimsData(v).prev = u;
                        getPrimsData(v).key = edge.getAttribute();
                    }
                }

            }
            refreshMainQueue();
        }
        setPath();
        return graph;
    }

    private void refreshMainQueue()
    {
        PriorityQueue<Vertex<K>> temp = new PriorityQueue<Vertex<K>>(20, new Comparator<Vertex<K>>() {
            @Override
            public int compare(Vertex<K> o1, Vertex<K> o2) {

                PrimsData o1Data = (PrimsData) o1.getComputation();
                PrimsData o2Data = (PrimsData) o2.getComputation();
                if (o1Data.key == null) {
                    return 1;
                }
                if (o2Data.key == null) {
                    return -1;
                }
                return o1Data.key.compareTo(o2Data.key);
            }
        });
        for(Vertex<K> v : mainQueue)
        {
            temp.add(v);
        }
        mainQueue = temp;
    }
    private void setPath() {
        for (Vertex<? extends K> ver : graph.getAllVertex()) {
            Vertex<K> prev = getPrimsData(ver).prev;
            if (prev == null) {
                continue;
            }
            graph.connectPath(prev, ver);
        }
    }

    private PrimsData getPrimsData(Vertex<? extends K> v) {
        return ((PrimsData) v.getComputation());
    }

    class PrimsData implements Computation 
    {
        E key;
        Vertex<K> prev;
    }
}
