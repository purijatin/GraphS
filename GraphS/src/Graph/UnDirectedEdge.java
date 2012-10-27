package Graph;

/**
 *
 * For the below graph.
 *     -->------a-------->--
 *  /                        \
 * A                          B  
 *  \                       /
 *    --<-------b-------<--
 * 
 * Edges a and b mean the same thing. ie equals() on a and b will return true.
 * Both might exist as different objects, but are considered equals.
 * 
 * @author Jatin
 */
class UnDirectedEdge<E> extends DefaultEdge<E>
{
    UnDirectedEdge(E attr,Vertex<?> source,Vertex<?> dest)
    {
        super(attr,source,dest);
    }
    UnDirectedEdge(E attr)
    {
        super(attr);
    }

    @Override
    public Vertex<?> getPassage(Vertex<?> source) 
    {
        if(source.equals(this.getSource()))
            return this.getDestination();
        else return null;
    }
     
  
    /**
     * Depends on the attribute.
     * @return 
     */
    @Override
    public int hashCode() 
    {
        int hash = 7;        
        hash = 71 * hash + (super.getAttribute() != null ? super.getAttribute().hashCode() : 0);
        hash = hash+ super.getSource().hashCode()+super.getDestination().hashCode();
        return hash;
    }
    
    /**
     * Returns true if ob and "this" object have the same same outlets :- source and destination and the same attribute.
     * ie ob source or destination can be "this"'s source or destination.
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
                if(obb.getSource().equals(this.getSource()) || obb.getSource().equals(this.getDestination()))
                    if(obb.getDestination().equals(this.getDestination()) || obb.getDestination().equals(this.getSource()))
                     return true;
            } else          
                return false;
         
        }     
            return false;
        
    }
     @Override
     public String toString()
     {
         String an = new String(""+super.getSource()+" and "+super.getDestination()+" both connected with "+super.getAttribute());
         return an;
     }
   
}
