/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A default Implementation of Vertice.
 * 
 * @author Jatin
 */
public abstract class Vertex<T> implements Vertice<T>
{
    /**
     * The HastSet containing all the edges this vertex is connected to.
     */
    final LinkedHashSet<Edge<?>> allEdges;
    private T attribute;
    private final long ID;
    private String name;
    public Computation computation;    
    /**
     * To be used in path algorithms to demonstrate the paths.
     */
    LinkedHashSet<Edge<?>> allPaths;
    
    public Vertex(T att) 
    {
        this.attribute = att;
        allEdges = new LinkedHashSet<Edge<?>>();
        ID = Graphs.getVertexId();
        name = new String(""+ID);
        allPaths = new LinkedHashSet<Edge<?>>();
    }

    @Override
    public void setAttribute(T ob) {
        attribute = (T) ob;
    }

    @Override
    public T getAttribute() {
        return attribute;
    }

    @Override
    public Set<Edge<?>> getConnectedEdges() {
        return allEdges;
    }

    public  <F extends Computation> void setComputation(F c)
    {
        this.computation = c;
    }
    
    public Computation getComputation()
    {
        return this.computation;
    }
    
    /**
     * Connects "this" Vertex to Vertex connect. Sets the attribute of the edge as edgeAttribute
     *
     * @param <E> The EdgeAttribute
     * @param edgeAttribute 
     * @param connect
     * @param edgeType The type of edge, for example a directed edge or not
     */
    protected abstract <E> void connectTo(Vertex<?> connect,E edgeAttribute,Class<? extends Edge> edge);
    
    
    public boolean isConnectedTo(Vertex<? extends T> connect) 
    {
        for (Edge<?> edge : allEdges) {
            if (edge.getPassage(this).equals(connect)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if there is a path connecting "this" vertice to the Vertex 'connect'
     * @param connect The VErtex to which we are checking if there exits an Edge that connects both.
     * @return The Edge if there exists an Edge. Or else returns Null;
     */
    @Override
    public Edge<?> getPassageToVertice(Vertice<?> connect) {
        for (Edge<?> edge : allEdges) 
        {
            if (edge.getPassage(this).equals(connect)) 
            {
                return edge;
            }
        }
        return null;
    }

    /**
     * Updates internally by removing the edge connecting to Vertice connect. If an edge exists, then updates.
     * If no edge exists connecting the vertice's then does nothing.
     * @param connect 
     */
    public void removeConnectivityToVertex(Vertex<?> connect,Graphs<?,?> graph) 
    {
        Edge<?> edge = getPassageToVertice(connect);
        if(edge == null) return;
        else
        {            
            if(allEdges.remove(edge))
                updateOtherVertexOfRemoval(connect,graph);
            else
                throw new RuntimeException("This should never happen");
        }
    }
    
    /**
     * Say a Vertice A is connected to Vertice B
     * <li>A-----edge------B</li>
     * If A deletes the edge internally, still B has to know that there is no path existing to B,
     * because A has already deleted the path.
     * Hence this method is used to update B.
     * Depending on what type of the Graph it is, the sub class should respond.
     * @param connect 
     */
    public abstract void updateOtherVertexOfRemoval(Vertex<?> connect,Graphs<?,?> graph);
    
     /**
     * Does not do a deep clone nor a shallow clone. Makes a new Vertex and sets it attribute
     * as the attribute of "this" vertex
     * @return the cloned Vertex
     */
    public abstract Vertex<T> getPartiallyShallowClone();
    
    protected void addPath(Edge<?> edge)
    {
        if(edge.getSource()== this)
        {
            allPaths.add(edge);
        }
        else throw new RuntimeException("Source of the edge is not this");
    }
    
    public Set<Edge<?>> getAllPaths()
    {
        return allPaths;
    }
    
    @Override
    public boolean equals(Object ob) {
        if (ob instanceof Vertex) {
            Vertex<?> obb = (Vertex<?>) ob;
            if (obb.getAttribute().equals(this.getAttribute()))
            {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.attribute != null ? this.attribute.hashCode() : 0);
        return hash;
    }

    
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return this.name.toString();
    }
    
    @Override
    public String toString()
    {
        return attribute.toString();
    }
    
    
}
