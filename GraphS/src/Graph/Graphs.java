package Graph;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 *	The idea is to create basic properties of a Graph so that later one can create Planar Graphs, Directed Graphs
 *	etc by extending it.
 *		
 * @param <T>  
 * @author Jatin
 */
public abstract class Graphs<T, E> 
{
    final LinkedHashSet<Vertex<? extends T>> allVertex;
    //final LinkedHashSet<Edge<? extends E>> allEdges;
    final Class<Vertex> vertexType;
    final Class<Edge> edgeType;
    protected static long vertexId = Long.MIN_VALUE;
            
    public Graphs(Class<? extends Vertex> vertexType,Class<? extends Edge> edgeType) 
    {
        allVertex = new LinkedHashSet<Vertex<? extends T>>();
        this.vertexType = (Class<Vertex>) vertexType;
        this.edgeType = (Class<Edge>) edgeType; 
        //this.allEdges =  new LinkedHashSet<Edge<? extends E>>();
    }
    
    /**
     * Adds a Vertex to the Graph.
     */
    public void addVertex(Vertex<? extends T> vertex)
    {
        allVertex.add(vertex);
    }
    
   /**
     * Connect The Source Vertex to the Destination.
     * @param source
     * @param dest 
     */
    public abstract void connect(Vertex<? extends T> source, E edgeAttribute,Vertex<? extends T> dest);
    
    
    /**
     * Path is different from edge. Path is used for traversal in algo.
     * @param source
     * @param edgeAttribute
     * @param dest 
     */
    public abstract void connectPath(Vertex<? extends T> source, Vertex<? extends T> dest);
    
    
    public Set<Edge<E>> getAllPaths() 
    {
        LinkedHashSet<Edge<E>> allEdges = new LinkedHashSet<Edge<E>>();
        for(Vertex<? extends T> v : allVertex)
        {
            for(Edge<?> e: v.getAllPaths())
            {
                allEdges.add((Edge<E>)e);
            }
        }
        return allEdges;        
    }
    
    
    /**
     * Removes the respective vertex from the graph. 
     * @param vertex 
     * @return True if the vertex exists or else False.
     */
    public boolean removeVertex(Vertex<? extends T> vertex)
    {
        boolean ans = allVertex.remove(vertex);
//        if(ans)
//        {
//            for(Edge<?> e: vertex.getConnectedEdges())
//            {
//                allEdges.remove(e);
//            }
//        }
        return ans;
    }
    
    /**
     * Removes edge connecting vertex and the destination. The removal impact depends from a graph to graph.
     * If the edge is say Undirected, then it automatically updates the destination.
     * Similarly graphs according to their own internal policy can update the respective Vertice's.
     * If no edge exists between them, then does nothing
     * @param source
     * @param destination 
     */
    public abstract void removeConnctivity(Vertex<?> source, Vertex<?> destination);
    
    /**
     * 
     * @param ob
     * @return THe vertex associated with the Attribute
     */
    public Vertex<? extends T> getVertex(T ob)
    {
        for(Vertex<? extends T> g : allVertex)
        {
            if(g.getAttribute().equals(ob))
                return g;
        }
        return null;
    }
    
    protected static long getVertexId()
    {
        return (vertexId++);
    }
    
    protected Class<Edge> getEdgeType()
    {
        return edgeType;
    }
    
    public Set<Vertex<? extends T>> getAllVertex()
    {
        return allVertex;
    }
    
    /**
     * Returns all Edges.
     * @return 
     */
    public Set<Edge<E>> getAllEdges()
    {
        LinkedHashSet<Edge<E>> allEdges = new LinkedHashSet<Edge<E>>();
        for(Vertex<? extends T> v : allVertex)
        {
            for(Edge<?> e: v.getConnectedEdges())
            {
                allEdges.add((Edge<E>)e);
            }
        }
        return allEdges;        
    }
    
    @Override
    public String toString()
    {
        StringBuilder build = new StringBuilder();
        build.append("All vertex :");
        for(Vertex<? extends T> v : allVertex)
        {
            build.append(v+",");
        }
        build.append("\n");
        for(Edge<E> f : this.getAllEdges())
        {
            build.append(f.toString()+"\n");
        }
//        for(Vertex<? extends T> v : allVertex)
//        {
//            for(Edge<?> edge : v.getConnectedEdges())
//            {
////                build.append(v+" is connected to "+edge.getPassage(v)+" with edge "+edge.getAttribute()+"\n");
//                build.append(edge.toString()+"\n");
//            }
//        }
        return build.toString();
    }
    
    public String printAllPaths()
    {
        StringBuilder build = new StringBuilder();
        build.append("All vertex :");
        for(Vertex<? extends T> v : allVertex)
        {
            build.append(v+",");
        }
        build.append("\n");
        for(Edge<E> f : this.getAllPaths())
        {
            build.append(f.toString()+"\n");
        }
        return build.toString();
    }
    
}
