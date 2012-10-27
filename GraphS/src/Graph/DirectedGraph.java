package Graph;

/**
 *
 * @author Jatin
 */
public class DirectedGraph<T,E> extends Graphs<T,E>
{
    final Class<DirectedEdge> directed;
    public DirectedGraph(Class<? extends Vertex> vertexType) 
    {
        super(vertexType,DirectedEdge.class);
        directed = DirectedEdge.class;
    }

    /**
     * Connects the source to the destination. 
     * @param source
     * @param dest 
     */
    @Override
    public void connect(Vertex<? extends T> source, E edgeAttribute, Vertex<? extends T> dest) 
    {
        if(super.allVertex.contains(source))              
        {
            if(super.allVertex.contains(dest))  
            {
                source.connectTo(dest, edgeAttribute,DirectedEdge.class);
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
    }
    
    
    @Override
    public void connectPath(Vertex<? extends T> source, Vertex<? extends T> dest) 
    {
        Edge<?> ed = source.getPassageToVertice(dest);
        if(ed == null) throw new RuntimeException("No path from "+source+" to "+dest);
        source.addPath(ed);
     
    }
    
}
