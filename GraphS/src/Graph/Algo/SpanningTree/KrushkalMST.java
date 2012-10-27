package Graph.Algo.SpanningTree;

import Graph.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
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
 * 
 * @author Jatin Puri
 */
public class KrushkalMST<K, E extends Comparable<? super E>> {

    private final Graphs<K, E> graph;
    
    /**
     * Maps Vertex to the respective set. Now the condition on which the 
     * algo works depends on the fact that a vertex cannot lie in more than one
     * SetOfVertices.
     * The only intention of having this is to make the process of retrical of
     * the set faster.
     */
    private HashMap<Vertex<? extends K>, SetOfVertices<K>> map;

    public KrushkalMST(UnDirectedGraph<K, E> graph) 
    {
        this.graph = graph;    
        map = new HashMap<Vertex<? extends K>, SetOfVertices<K>>();
    }

    public Graphs<K, E> getMST()     
    {
        for (Vertex<? extends K> v : graph.getAllVertex()) 
        {
            SetOfVertices<K> set = new SetOfVertices<K>();
            set.addvertexToSet((Vertex<K>)v);
            map.put(v, set);
    
        }
        
        //sort the edges using Heap Sort.
        PriorityQueue<Edge<E>> mainQueue = new PriorityQueue<Edge<E>>(10, new Comparator<Edge<E>>() {
            @Override
            public int compare(Edge<E> o1, Edge<E> o2) {
                return o1.getAttribute().compareTo(o2.getAttribute());
            }
        });
        for(Edge<E> ed: graph.getAllEdges())
        {
            mainQueue.add(ed);             
        }
        
        int size = mainQueue.size();
        for(int i=0;i<size;i++)
        {
            Edge<E> edge = mainQueue.poll();
            //System.out.println(mainQueue.size()+" "+edge);
            Vertex<K> source = (Vertex<K>) edge.getSource();            
            Vertex<K> dest = (Vertex<K>) edge.getDestination();
            if(!map.get(source).equals(map.get(dest)))
            {                
                graph.connectPath(source, dest);        
                union(map.get(source),map.get(dest));    
            }
        }
        
        return graph;
    }

    private void union(SetOfVertices<K> main, SetOfVertices<K> secondary) {
        for (Vertex<K> v : secondary.getAllVertex()) {
            main.addvertexToSet(v);
            map.put(v, main);
        }
    }

    class SetOfVertices<K> {

        private Set<Vertex<K>> set = new HashSet<Vertex<K>>();

        void addvertexToSet(Vertex<K> ver) {
            set.add(ver);
        }

        boolean containsVertex(Vertex<K> ver) {
            return set.contains(ver);
        }

        Set<Vertex<K>> getAllVertex() {
            return set;
        }

        @Override
        public boolean equals(Object ob) 
        {
            if (ob instanceof SetOfVertices) 
            {
                SetOfVertices<K> se = (SetOfVertices<K>) ob;
                if(se.getAllVertex().size()==this.getAllVertex().size())//check if they contain same number of elements
                {
                    for(Vertex<K> v : se.getAllVertex())
                    {
                        if(set.contains(v))
                            continue;
                        else return false;
                    }
                    return true;
                }else return false;
            } else  return false;            
        }
        
        @Override
        public String toString()
        {
            return set.toString();
        }
    }
}
