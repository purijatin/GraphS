
package Graph;

/**
 *
 * @author Jatin
 */
public class DefaultVertex<T> extends Vertex<T> 
{
    public DefaultVertex(T attr)
    {
        super(attr);
    }
    
    @Override
    public void updateOtherVertexOfRemoval(Vertex<?> connect,Graphs<?,?> graph) 
    {
        if(graph.getEdgeType().equals(UnDirectedEdge.class))
        {
                connect.removeConnectivityToVertex(this, graph);                    
                return;
        }
        if(graph.getEdgeType().equals(DirectedEdge.class))
        {
            return;//do nothing
        }
            
        throw new RuntimeException("Library not updated");
    }

    @Override
    protected <E> void connectTo(Vertex<?> connect, E edgeAttribute,Class<? extends Edge> edgetype) 
    {               
        if(edgetype.equals(DirectedEdge.class))    
        {
            DirectedEdge<E> edge = new DirectedEdge<E>(edgeAttribute);
            edge.setSource(this);
            edge.setDestination(connect);
            this.allEdges.add(edge);
            //connect.allEdges.add(edge);
            return;     
        }else if(edgetype.equals(UnDirectedEdge.class))
        {
            UnDirectedEdge<E> edge = new UnDirectedEdge<E>(edgeAttribute);
            edge.setSource(this);
            edge.setDestination(connect);
            this.allEdges.add(edge);
            //connect.allEdges.add(edge);
            return;     
        }
        
        throw new RuntimeException("library not updated");
        
    }
    
    /**
     * Does not do a deep clone nor a shallow clone. Makes a new Vertex and sets it attribute
     * as the attribute of "this" vertex
     * @return the cloned Vertex
     */
    @Override
    public Vertex<T> getPartiallyShallowClone()
    {
        Vertex<T> clone = new DefaultVertex<T>(this.getAttribute());
        return clone;
    }

    
    
}
