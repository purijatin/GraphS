package Graph;

/**
 *
 * @author Jatin
 */
public class DirectedEdge<E> extends DefaultEdge<E> 
{
    DirectedEdge(E attr, Vertex<?> source, Vertex<?> dest) 
    {
        super(attr, source, dest);
    }
    DirectedEdge(E attr) 
    {
        super(attr);
    }

    @Override
    public Vertex<?> getPassage(Vertex<?> source) 
    {
        if (source.equals(this.getSource())) 
        {
            return this.getDestination();
        } 
        else 
        {
            return null;
        }

    }
    
    /**
     * Depends on the source, destination and then attribute
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (super.getSource() != null ? super.getSource().hashCode() : 0);
        hash = 71 * hash + (super.getDestination() != null ? super.getDestination().hashCode() : 0);
        hash = 71 * hash + (super.getAttribute() != null ? super.getAttribute().hashCode() : 0);
        return hash;
    }
    
    /**
     * Returns true if ob and "this" object have the same source, same destination and the same attribute.
     * Uses equals() of Vertex and Attribute to check.
     * @param ob
     * @return 
     */
     @Override
    public boolean equals(Object ob)
    {
         if (ob instanceof Edge) 
         {
            DefaultEdge<?> obb = (DefaultEdge<?>) ob;
            if (obb.getAttribute().equals(this.getAttribute())) 
            {
                if(obb.getSource().equals(this.getSource()))
                    if(obb.getDestination().equals(this.getDestination()))
                     return true;
            } else          
                return false;
         
        }     
            return false;
        
    }
     @Override
     public String toString()
     {
         String an = new String(""+super.getSource()+" connected to "+super.getDestination()+" with attribute "+super.getAttribute());
         return an;
     }
   
}
