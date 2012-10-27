package Graph;

/**
 * @author Jatin
 * @param <T> The Vertex to be connected
 * @param <E> 
 */
abstract class DefaultEdge<E> implements Edge<E>
{
    private Vertex<?> source;
    private Vertex<?> dest;
    private E attr;

    DefaultEdge(E attr,Vertex<?> source,Vertex<?> dest) 
    {
        this.attr = attr;
        this.source = source;
        this.dest = dest;
    }
    DefaultEdge(E attr) 
    {
        this.attr = attr;        
    }
    
    @Override
    public void setSource(Vertex<?> source)
    {
        this.source = source;
    }
    
    @Override
    public void setDestination(Vertex<?> dest)
    {
        this.dest = dest;
    }

    @Override
    public E getAttribute() {
        return attr;
    }
    
    @Override
    public Vertex<?> getDestination() {
        return dest;
    }

    @Override
    public Vertex<?> getSource() {
        return source;
    }

    @Override
    public void setAttribute(E attr) {
        this.attr = attr;
    }

    @Override
    public abstract Vertex<?> getPassage(Vertex<?> source) ;
    
   
}
