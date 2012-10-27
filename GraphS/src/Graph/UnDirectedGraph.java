
package Graph;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The graph acts as Directed Graph only that there are edges connecting in both sides.
 * ie <li> A ------------edge-----------B</li>
 * acts as 
 *    -->------>-------->--
 *  /                        \
 * A                          B  
 *  \                       /
 *    --<------<--------<--
 * Basically there are two separate edges copnnecting them. Hence for any change in one edge,
 * the respective change in another edge should also be done. 
 * 
 * @author Jatin
 */
public class UnDirectedGraph<T,E>  extends Graphs<T,E> 
{
    final Class<UnDirectedEdge> undirected;
    public UnDirectedGraph(Class<? extends Vertex> vertexType) 
    {
        super(vertexType,UnDirectedEdge.class);
        undirected = UnDirectedEdge.class;
    }
    
    /**
     * Returns all Edges. If A and B are connected by a UndirectedEdge. 
     * Then this method will only return one edge which connects both of them.
     * @return 
     * @Override
     */
    @Override
    public Set<Edge<E>> getAllEdges()
    {
        return super.getAllEdges();
    }

    /**
     * Connects the source to the destination. Also updates the dest about its connection to the source
     * @param source
     * @param dest 
     */
    @Override
    public void connect(Vertex<? extends T> source, E edgeAttribute,Vertex<? extends T> dest) 
    {
        if(super.allVertex.contains(source))              
        {
            if(super.allVertex.contains(dest))  
            {
                source.connectTo(dest, edgeAttribute,UnDirectedEdge.class);
                dest.connectTo(source, edgeAttribute,UnDirectedEdge.class);                
                return;
            }
            else throw new NullPointerException("Destination is not added in the graph");
        }else
            throw new NullPointerException("source is not added in the graph");        
        
    }

    @Override
    public void removeConnctivity(Vertex<?> source, Vertex<?> destination) 
    {
        source.removeConnectivityToVertex(destination, this);
        destination.removeConnectivityToVertex(source, this);
    }

    @Override
    public void connectPath(Vertex<? extends T> source, Vertex<? extends T> dest) 
    {
        Edge<?> ed = source.getPassageToVertice(dest);
        if(ed == null) throw new RuntimeException("No path from "+source+" to "+dest);
        source.addPath(ed);
        ed = dest.getPassageToVertice(source);
        if(ed == null) throw new RuntimeException("No path from "+dest+" to "+source);
        dest.addPath(ed);
    }

    
    
    
}
