package Graph;

/**
 * <p>Defines an Edge which basically is a passage from One Vertex to Another.
 * It is completely upto the edge to provide an access depending on the very own
 * definition of the instantiated edge.
 * </p>
 * Should never be created in Public because one can publically not set the edges it connects to.
 * @param <E> is of type attribute of Edge
 * @author jatin
 */
public interface Edge<E> 
{
//	public Vertex<T> getSource();
//	public Vertex<T> getDestination();

    /**
     * Returns the attribute Associated with the Edge
     * @return The Attribute associated with the Edge
     */
    public E getAttribute();

    public void setSource(Vertex<?> source);
    
    public void setDestination(Vertex<?> dest);
    
    /**
     * Sets the attribute of the edge
     * @param attr Sets the attribute of the Edge
     */
    void setAttribute(E attr);

    /**
     * Get the permission to access the destination from the source.
     * <p> For example:    
     * <li>A-------edge---------B </li>
     * here A is a vertex
     *      B is a vertex
     *      edge is the edge.
     * If the argument of the method is vertex A then it returns Vertex B, if it is
     * legal to return (for ex will return B if the edge is intended to be Undirected)
     * This method can be used to create different types of Edge's.</p>
     * <p> In case of a directed edge
     * <li>A----->----edge------>B</li>
     * on calling getPassage(B) it will return null.
     * </p>
     * @param source One end of the edge
     * @return The other end of the edge if the edge has access to. Or else return null;
     */
    public Vertex<?> getPassage(Vertex<?> source);
    
    public Vertex<?> getSource();
    
    public Vertex<?> getDestination();
}
